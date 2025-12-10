package com.snow.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    private final RestTemplate restTemplate;

    @Autowired
    public ConsumerController(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    @GetMapping(value = "/consumer/order")
    public String echo() {
        return this.restTemplate.getForObject("http://order-service/order", String.class);
    }
}
