package BookMyShow.services;

import java.util.*;

import BookMyShow.coreEntity.Movie;

public class MovieService {
    // in-memory database for movies
    private final Map<String, Movie> movies;

    public MovieService() {
        this.movies = new HashMap<>();
    }

    public Movie createMovie(String title, int duration) {
        String movieId = UUID.randomUUID().toString();
        Movie movie = new Movie(movieId, title, duration);
        movies.put(movieId, movie);
        return movie;
    }

    public Movie getMovie(String movieId) {
        if (!movies.containsKey(movieId)) {
            throw new IllegalArgumentException("Movie not found with ID: " + movieId);
        }
        return movies.get(movieId);
    }
}
