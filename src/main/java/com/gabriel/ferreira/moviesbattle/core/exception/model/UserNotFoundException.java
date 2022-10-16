package com.gabriel.ferreira.moviesbattle.core.exception.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserNotFoundException extends Exception{

    private HttpStatus code;

    private String userMsg;

    public UserNotFoundException(String msg, String userMsg){
        super(msg);
        this.code = HttpStatus.NOT_FOUND;
        this.userMsg = userMsg;
    }
}

