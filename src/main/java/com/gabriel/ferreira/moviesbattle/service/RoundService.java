package com.gabriel.ferreira.moviesbattle.service;

import com.gabriel.ferreira.moviesbattle.model.Match;
import com.gabriel.ferreira.moviesbattle.model.Response;
import com.gabriel.ferreira.moviesbattle.model.Round;

import java.util.List;
import java.util.Optional;

public interface RoundService {
    Optional<Round> getLastRound(Match match);
    Response createNewRound(String userId)throws Exception;
    List<Round> getAllRounds();
    String anwserRound(String userId, String answer) throws Exception;
    Round saveRound(Round round);
}
