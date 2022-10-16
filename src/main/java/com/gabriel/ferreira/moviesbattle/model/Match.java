package com.gabriel.ferreira.moviesbattle.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Match", uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY )
    private List<Round> rounds = new ArrayList<>();

    private Double points;

    private boolean active;

    private int errors;

}
