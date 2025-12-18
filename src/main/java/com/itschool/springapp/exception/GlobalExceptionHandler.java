package com.itschool.springapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice // Spring bean annotation to advice all REST controllers how to handle events
public class GlobalExceptionHandler {

    // dedicated Logger for logging complex events
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // method to handle all ResponseStatusException exceptions propagated to controllers
    // similar to a catch block but applied globally to all controllers
    @ExceptionHandler(ResponseStatusException.class) // annotation to specify the type of exception to handle
    public void handleResponseStatusException(ResponseStatusException e) { // pass the handled exception as a parameter in order to access it
        LOGGER.error("Exception propagated to controller: ", e);
        throw e; // rethrow the exception to let Spring handle it and return the appropriate HTTP response
    }
}
