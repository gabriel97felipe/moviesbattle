package com.gabriel.ferreira.moviesbattle.service;

import com.gabriel.ferreira.moviesbattle.core.exception.model.UserNotFoundException;
import com.gabriel.ferreira.moviesbattle.model.Match;
import com.gabriel.ferreira.moviesbattle.model.Ranking;
import com.gabriel.ferreira.moviesbattle.model.User;
import com.gabriel.ferreira.moviesbattle.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MatchServiceImpl implements MatchService{

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    UserService userService;

    public Match getLastMatch(String userId) throws Exception {
        Match lastMatch = matchRepository.findFirstByUserIdOrderByIdDesc(Long.valueOf(userId))
                .orElseThrow(() -> new Exception(String.format("You don`t have a match running! Go to /match/user/%s/start", userId)));
        if(!lastMatch.isActive())
            throw new Exception(String.format("You don`t have a match running! Go to /match/user/%s/start", userId));
        return lastMatch;
    }

    public String createNewMatch(String userId) throws UserNotFoundException {
        User user = userService.getUserById(userId);
        Optional<Match> lastMatch = matchRepository.findFirstByUserIdOrderByIdDesc(Long.valueOf(userId));
        if(lastMatch.isPresent() && lastMatch.get().isActive())
            return String.format("You have a match running! Go to: /round/user/%s", userId);

        new Match();
        saveMatch(
                Match.builder()
                .active(true)
                .errors(0)
                .points(0.0)
                .user(user)
                .build()
            );

        return String.format("A new match was created! Go to: /round/user/%s", userId);
    }

    public void saveMatch(Match match) {
        matchRepository.save(match);
    }

    public List<Match> getAllMatchesByUser(String userId) {
        return matchRepository.findByUserId(Long.valueOf(userId));
    }

    public List<Ranking> ranking() {
        return matchRepository.findAllByOrderByPointsDesc().stream()
                .map(this::rankingMatch)
                .toList();
    }

    public Ranking rankingMatch(Match match) {
        return Ranking.builder()
                .id(match.hashCode())
                .user(match.getUser().getUsername())
                .rounds(match.getRounds().size() + 1)
                .points(match.getPoints())
                .build();
    }

    public String finishMatch(String userId) throws Exception {
        Match match = getLastMatch(userId);
        match.setActive(false);
        saveMatch(match);
        return String.format("Match finished. Start a new one here: match/user/%s/start", userId);
    }

}
