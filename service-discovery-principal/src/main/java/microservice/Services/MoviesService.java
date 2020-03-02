package microservice.Services;


import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import microservice.Models.Movie;

@Service
public class MoviesService {

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod="getFallbackMovies")
	public List<Movie> getMovies(){
		ResponseEntity<Movie[]> response =
				  restTemplate.getForEntity(
				  "http://services-movies/api/movies/",
				  Movie[].class);
		
				Movie[] movies = response.getBody();
				
				List<Movie> m = Arrays.asList(movies);
		return  m;
	}
	public List<Movie> getFallbackMovies() {
		
		return Arrays.asList(new Movie(4, "Pelicula suplente", "suplente") );
	}
}
