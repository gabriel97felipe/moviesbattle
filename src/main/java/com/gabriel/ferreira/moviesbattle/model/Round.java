package com.gabriel.ferreira.moviesbattle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_one_id")
    private Movie movieOne;


    @ManyToOne
    @JoinColumn(name = "movie_two_id")
    private Movie movieTwo;

    private boolean running;

}
