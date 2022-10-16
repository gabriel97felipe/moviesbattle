package com.gabriel.ferreira.moviesbattle.service;

import com.gabriel.ferreira.moviesbattle.model.Match;
import com.gabriel.ferreira.moviesbattle.model.Movie;
import com.gabriel.ferreira.moviesbattle.model.Response;
import com.gabriel.ferreira.moviesbattle.model.Round;
import com.gabriel.ferreira.moviesbattle.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoundServiceImpl implements  RoundService {

    @Autowired
    RoundRepository roundRepository;

    @Autowired
    MatchService matchService;

    @Autowired
    MovieService movieService;

    public Optional<Round> getLastRound(Match match) {
        var roundList = match.getRounds();
        Optional<Round> optionalRound = Optional.empty();
        if(roundList.isEmpty())
            return optionalRound;
        return Optional.of(roundList.get(roundList.size() - 1));
    }

    public Response createNewRound(String userId) throws Exception {
        Match match = matchService.getLastMatch(userId);
        Optional<Round> lastRound = getLastRound(match);

        if(lastRound.isPresent() && lastRound.get().isActive()) {
            return Response.builder()
                .movieOne(lastRound.get().getMovieOne().getName())
                .movieTwo(lastRound.get().getMovieTwo().getName())
                .message(String
                        .format("Choose between the two movies and send the name of the selected one here: GET round/user/%s/answer", userId))
                .build();
        }

        var roundNumber = lastRound.map(value -> value.getRound() + 1).orElse(1);

        var movieOne = movieService.getRandomMovie();
        var movieTwo = movieService.getRandomMovie();

        while (movieOne.getId().equals(movieTwo.getId())) {
            movieTwo = movieService.getRandomMovie();
        }

        var lastRounds = match.getRounds();
        new Round();
        Round round = Round.builder()
                .round(roundNumber)
                .active(true)
                .movieOne(movieService.getRandomMovie())
                .movieTwo(movieService.getRandomMovie())
                .build();
        Round newRound = roundRepository.save(round);
        lastRounds.add(round);
        match.setRounds(lastRounds);

        matchService.saveMatch(match);

        return Response.builder()
            .movieOne(newRound.getMovieOne().getName())
            .movieTwo(newRound.getMovieTwo().getName())
            .message(String
                    .format("Choose between the two movies and send the name of the selected one here: GET round/user/%s/answer", userId))
            .build();
    }

    public String anwserRound(String userId, String answer) throws Exception {
        Match match = matchService.getLastMatch(userId);
        Optional<Round> lastRound = getLastRound(match);
        if(lastRound.isPresent() && lastRound.get().isActive()) {
            Movie movieOne = lastRound.get().getMovieOne();
            Movie movieTwo = lastRound.get().getMovieTwo();
            Optional<Movie> selectedMovie = movieService.getMovieByName(answer);
            if(selectedMovie.isEmpty() || !selectedMovie.get().getName().equals(movieOne.getName()) && !selectedMovie.get().getName().equals(movieTwo.getName()))
                return "You don`t have this option or you write it wrong. Try again with the correct name.";
            lastRound.get().setActive(false);
            roundRepository.save(lastRound.get());
            double resultMovieOne = movieOne.getRating() * movieOne.getRatingCount();
            double resultMovieTwo = movieTwo.getRating() * movieTwo.getRatingCount();
            Movie correcMovie = resultMovieOne > resultMovieTwo ? movieOne: movieTwo;
            String response;
            if(correcMovie.getId().equals(selectedMovie.get().getId())) {
                match.setPoints(match.getPoints() + 1);
                response = String.format("Well done! Go to the next one here: round/user/%s", userId);
            }
            else {
                match.setErrors(match.getErrors() + 1);
                if(match.getErrors() >= 3) {
                    match.setActive(false);
                    response = String.format("Your answer is wrong! unfortunately you already missed 3 times, but you can start a new match here: match/user/%s/start", userId);
                }
                else response = String.format("Your answer is wrong! The right answer is %s. Go to the next one here: round/user/%s", correcMovie.getName(), userId);
            }
            matchService.saveMatch(match);
            return response;
        }

        return "You don`t have a round running. Create a new round or match.";
    }

    public Round saveRound(Round round) {
        return roundRepository.save(round);
    }

    public List<Round> getAllRounds() {
        return roundRepository.findAll();
    }

}
