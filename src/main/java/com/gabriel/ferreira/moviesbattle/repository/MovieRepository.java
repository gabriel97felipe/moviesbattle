package com.gabriel.ferreira.moviesbattle.repository;

import com.gabriel.ferreira.moviesbattle.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
