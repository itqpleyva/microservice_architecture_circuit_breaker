package microservice.MainController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import microservice.Models.Movie;
import microservice.Models.MovieRating;
import microservice.Services.MoviesService;
import microservice.Services.RatingsService;

@RestController
@RequestMapping("/api")
public class MainController {

	@Autowired
	MoviesService moviesServices;
	
	@Autowired
	RatingsService ratingsServices;
	
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/movies")
	public List<Movie> test() {
		
		List<MovieRating> moviesRatings =ratingsServices.getRatings();  
				
		List<Movie> movies = moviesServices.getMovies();
	      
		return movies;

	}

}
