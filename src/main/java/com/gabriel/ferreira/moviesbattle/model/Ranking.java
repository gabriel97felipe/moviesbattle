package com.gabriel.ferreira.moviesbattle.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Ranking {
    private int id;
    private String user;
    private Double points;
    private int rounds;
}
