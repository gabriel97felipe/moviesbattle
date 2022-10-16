package com.gabriel.ferreira.moviesbattle.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="_User", uniqueConstraints={@UniqueConstraint(columnNames = {"id" , "username", "email"})})
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String name;

    private String password;

    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
