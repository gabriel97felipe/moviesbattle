package com.gabriel.ferreira.moviesbattle.service;

import com.gabriel.ferreira.moviesbattle.core.exception.model.UserNotFoundException;
import com.gabriel.ferreira.moviesbattle.model.Match;

import java.util.List;

public interface MatchService {
    Match getLastMatch(String userId) throws Exception;
    String createNewMatch(String userId) throws UserNotFoundException;
    List<Match> getAllMatchesByUser(String userId);
    List<Match> ranking();
    void saveMatch(Match match);
    String finishMatch(String userId) throws Exception;
}
