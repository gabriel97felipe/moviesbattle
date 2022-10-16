package com.gabriel.ferreira.moviesbattle.controller;
import com.gabriel.ferreira.moviesbattle.model.Movie;
import com.gabriel.ferreira.moviesbattle.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody()  Movie movie){
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.saveMovie(movie));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getByCode(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovie(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable String id){
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    @GetMapping("/random")
    public ResponseEntity<Movie> getRandomMovie() {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getRandomMovie());
    }

}
