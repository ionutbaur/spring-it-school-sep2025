package com.itschool.springapp.service.impl;

import com.itschool.springapp.service.GreetingService;
import org.springframework.stereotype.Service;

@Service // Annotation to tell Spring that this is a bean of type Service (usually contains business logic)
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String getGreeting(String name) {
        return "Hello " + name;
    }
}
