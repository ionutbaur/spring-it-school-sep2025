package com.itschool.springapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

// a custom exception indicating that a user was not found with HTTP 404 status
public class UserNotFoundException extends ResponseStatusException {

    public UserNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
