package com.itschool.springapp;

public record Activity(Long id,
                       String title,
                       String dueDate,
                       Boolean completed) {
}