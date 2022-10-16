package com.gabriel.ferreira.moviesbattle.core.exception.model;

import lombok.Data;

@Data
public class Error {

    private String userMsg;
    private String errorMsg;

    public Error(String errorMsg, String userMsg) {
        super();
        this.userMsg = userMsg;
        this.errorMsg = errorMsg;
    }
}
