package com.itschool.springapp.service.impl;

import com.itschool.springapp.service.ExampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExampleServiceImplTest {

    private ExampleService exampleService;

    @BeforeEach
    void setUp() {
        exampleService = new ExampleServiceImpl();
    }

    @Test
    void sum() {
        int actualResult = exampleService.sum(3, 7);
        int expectedResult = 10;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void parity() {
        String parity = exampleService.parity(5);
        assertEquals("odd", parity);
    }
}