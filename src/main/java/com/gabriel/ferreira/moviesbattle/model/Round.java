package com.gabriel.ferreira.moviesbattle.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Round", uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int round;

    @ManyToOne
    @JoinColumn(name = "movie_one_id")
    private Movie movieOne;


    @ManyToOne
    @JoinColumn(name = "movie_two_id")
    private Movie movieTwo;

    private boolean active;

}
