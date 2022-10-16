package com.gabriel.ferreira.moviesbattle.controller;
import com.gabriel.ferreira.moviesbattle.core.exception.model.UserNotFoundException;
import com.gabriel.ferreira.moviesbattle.model.Match;
import com.gabriel.ferreira.moviesbattle.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    MatchService matchService;

    @PostMapping("/user/{id}/start")
    public ResponseEntity<String> startMatch(@PathVariable("id") String id) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.createNewMatch(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Match>> getAllMatchesByUser(@PathVariable("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(matchService.getAllMatchesByUser(id));
    }

    @GetMapping()
    public ResponseEntity<List<Match>> getAllRanking(){
        return ResponseEntity.status(HttpStatus.OK).body(matchService.ranking());
    }

    @GetMapping("/user/{id}/finish")
    public ResponseEntity<String> finishMatch(@PathVariable("id") String id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(matchService.finishMatch(id));
    }

}
