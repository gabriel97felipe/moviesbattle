package com.gabriel.ferreira.moviesbattle.service;

import com.gabriel.ferreira.moviesbattle.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    Movie getMovie(String id);
    Movie saveMovie(Movie movie);
    List<Movie> getAllMovies();
    void deleteMovie(String id);
    Movie getRandomMovie();

    Optional<Movie> getMovieByName(String name);
}
