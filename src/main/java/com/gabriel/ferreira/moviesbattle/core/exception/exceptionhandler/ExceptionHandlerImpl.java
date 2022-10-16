package com.gabriel.ferreira.moviesbattle.core.exception.exceptionhandler;

import com.gabriel.ferreira.moviesbattle.core.exception.model.Error;
import com.gabriel.ferreira.moviesbattle.core.exception.model.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerImpl extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleApiException(WebRequest request, UserNotFoundException ex) {
        Error error = new Error(ex.getUserMsg(), ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), ex.getCode(), request);
    }
}
