package com.heycar.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "IOException occurred")
    @ExceptionHandler({IOException.class})
    public void handleBadRequestException() {
    }
}
