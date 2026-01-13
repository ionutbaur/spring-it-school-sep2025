package com.itschool.springapp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

// Singleton class to provide a single instance of heavy ObjectMapper object, by following the Bill Pugh pattern
public class ObjectMapperSingleton {

    private ObjectMapperSingleton() {
        // singleton
    }

    private static class SingletonHolder {
        private static final ObjectMapper INSTANCE = new ObjectMapper();
    }

    public static ObjectMapper getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
