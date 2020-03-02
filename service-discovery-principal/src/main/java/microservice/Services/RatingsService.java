package microservice.Services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import microservice.Models.MovieRating;

@Service
public class RatingsService {


	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="getFallbackRatings")
	public List<MovieRating> getRatings(){
		ResponseEntity<MovieRating[]> response =
				  restTemplate.getForEntity(
				  "http://services-rating/api/ratings/",
				  MovieRating[].class);
		
		MovieRating[] moviesRatings = response.getBody();
				
				List<MovieRating> m = Arrays.asList(moviesRatings);
		
				return  m;
	}

	public List<MovieRating> getFallbackRatings() {
		
		return Arrays.asList(new MovieRating(1, 2, 3, 4) );
	}
}
