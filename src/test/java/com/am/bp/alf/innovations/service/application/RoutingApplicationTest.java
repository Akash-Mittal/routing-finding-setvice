package com.am.bp.alf.innovations.service.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SpringBootApplication
public class RoutingApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(RoutingApplicationTest.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Gson getGSON() {
        return new GsonBuilder().setPrettyPrinting().create();

    }

}
