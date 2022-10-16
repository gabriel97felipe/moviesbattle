package com.gabriel.ferreira.moviesbattle.repository;

import com.gabriel.ferreira.moviesbattle.model.Match;
import com.gabriel.ferreira.moviesbattle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByUserId(Long userId);
    List<Match> findAllByOrderByPointsDesc();
    Optional<Match> findFirstByUserIdOrderByIdDesc(Long userId);
}
