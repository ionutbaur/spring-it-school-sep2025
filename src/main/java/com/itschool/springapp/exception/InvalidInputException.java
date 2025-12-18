package com.itschool.springapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

// a custom exception indicating invalid input with HTTP 400 status
public class InvalidInputException extends ResponseStatusException {

    public InvalidInputException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
