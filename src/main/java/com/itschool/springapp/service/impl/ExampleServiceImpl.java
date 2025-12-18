package com.itschool.springapp.service.impl;

import com.itschool.springapp.service.ExampleService;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl implements ExampleService {

    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public String parity(int n) {
        return n % 2 == 0 ? "even" : "odd";
    }
}
