package com.gabriel.ferreira.moviesbattle.controller;

import com.gabriel.ferreira.moviesbattle.model.Response;
import com.gabriel.ferreira.moviesbattle.model.Round;
import com.gabriel.ferreira.moviesbattle.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/round")
public class RoundController {

    @Autowired
    RoundService roundService;

    @PostMapping("/user/{id}")
    public ResponseEntity<Response> createRound(@PathVariable("id") String id) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(roundService.createNewRound(id));
    }

    @PostMapping("/user/{id}/answer")
    public ResponseEntity<String> createRound(
            @PathVariable("id") String id,
            @RequestParam("answer") String answer
    ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(roundService.anwserRound(id, answer));
    }

    @GetMapping()
    public ResponseEntity<List<Round>> getAllRounds() {
        return ResponseEntity.status(HttpStatus.OK).body(roundService.getAllRounds());
    }
}
