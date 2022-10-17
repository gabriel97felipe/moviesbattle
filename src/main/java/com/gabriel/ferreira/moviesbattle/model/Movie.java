package com.gabriel.ferreira.moviesbattle.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Movie", uniqueConstraints={@UniqueConstraint(columnNames = {"id" , "name"})})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private Double rating;

    private Integer ratingCount;
}
