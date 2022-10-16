package com.gabriel.ferreira.moviesbattle.service;

import com.gabriel.ferreira.moviesbattle.model.Movie;

import java.util.List;

public interface MovieService {
    Movie getMovie(String id);
    Movie saveMovie(Movie movie);
    List<Movie> getAllMovies();
    void deleteMovie(String id);
}
