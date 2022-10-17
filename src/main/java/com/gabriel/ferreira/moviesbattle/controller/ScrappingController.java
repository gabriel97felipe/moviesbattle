package com.gabriel.ferreira.moviesbattle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.ferreira.moviesbattle.model.Movie;
import com.gabriel.ferreira.moviesbattle.model.MovieInfo;
import com.gabriel.ferreira.moviesbattle.model.Scrapping;
import com.gabriel.ferreira.moviesbattle.repository.MovieRepository;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/webscrapping")
public class ScrappingController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping()
    public ResponseEntity<List<Movie>> getScrappingResponse(@RequestBody Scrapping scrapping) {

        var movies = new ArrayList<Movie>();
        scrapping.getMoviesId().forEach(movieId -> {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://imdb8.p.rapidapi.com/title/get-ratings?tconst=" + movieId)
                    .get()
                    .addHeader("X-RapidAPI-Key", "79fe5096e3msh7a387e4f5192d07p1e9d43jsnca2436129339")
                    .addHeader("X-RapidAPI-Host", "imdb8.p.rapidapi.com")
                    .build();

            try {
                var response = client.newCall(request).execute();
                var responseBody = response.body().string();
                JSONObject json = new JSONObject(responseBody);

                if(!(json.isNull("title") || json.isNull("rating") || json.isNull("ratingCount"))) {
                    new Movie();
                    Movie movie = Movie.builder()
                            .name(json.getString("title"))
                            .rating(json.getDouble("rating"))
                            .ratingCount(json.getInt("ratingCount"))
                            .build();
                    movies.add(movie);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        List<Movie> moviesResponse = movieRepository.saveAll(movies);

        return ResponseEntity.status(HttpStatus.OK).body(moviesResponse);
    }

}
