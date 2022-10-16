package com.gabriel.ferreira.moviesbattle.repository;

import com.gabriel.ferreira.moviesbattle.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
}
