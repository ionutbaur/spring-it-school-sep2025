package com.itschool.springapp.controller;

import com.itschool.springapp.service.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController // indicates that this class has the controll over a REST API and exposes its endpoints
public class GreetingController {

    private final GreetingService greetingService;

    // dependency injection of GreetingService using constructor (recommended way)
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // below we are exposing the endpoints of this REST API

    // maps to an HTTP request using GET method/verb, available at path <hostname>/greet/<name>
    @GetMapping("greet/{name}")
    public String getGreet(@PathVariable String name) { // suggests that the above {name} used in path should be bound to this input String param
        return greetingService.getGreeting(name); // call the method of the injected service with the captured param from endpoint's path
    }
}
