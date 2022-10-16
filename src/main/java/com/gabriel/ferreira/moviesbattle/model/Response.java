package com.gabriel.ferreira.moviesbattle.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response {

    private String message;

    private String movieOne;

    private String movieTwo;
}
