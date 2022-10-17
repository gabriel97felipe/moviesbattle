package com.gabriel.ferreira.moviesbattle.service;

import com.gabriel.ferreira.moviesbattle.model.Movie;
import com.gabriel.ferreira.moviesbattle.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie getMovie(String id) {
        return movieRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Role not exists"));
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(Long.valueOf(id));
    }

    public Movie getRandomMovie() {
        Movie firstMovie = movieRepository.findFirstByOrderById();
        Movie lastMovie = movieRepository.findFirstByOrderByIdDesc();
        Random r = new Random();
        Optional<Movie> randomMovie = Optional.empty();
        while(randomMovie.isEmpty()) {
            Long getRandomId = r.nextLong(lastMovie.getId()- firstMovie.getId() + 1) + firstMovie.getId();
            randomMovie = movieRepository.findById(getRandomId);
        }
        return randomMovie.get();
    }

    public Optional<Movie> getMovieByName(String name) {
        return movieRepository.findFirstByName(name);
    }
}
