package com.itschool.springapp;

import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://fakerestapi.azurewebsites.net/api/v1/Activities";
        Activity[] activities = restTemplate.getForObject(url, Activity[].class);

        //System.out.println(activities[1]);

        Activity secondActivity = activities[1];
        System.out.println("Title of secondActivity: " + secondActivity.title());
    }

}
