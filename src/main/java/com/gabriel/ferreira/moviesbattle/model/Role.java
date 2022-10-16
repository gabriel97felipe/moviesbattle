package com.gabriel.ferreira.moviesbattle.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Role", uniqueConstraints={@UniqueConstraint(columnNames = {"id" , "name"})})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Role(String name) {
        super();
        this.name = name;
    }
}
