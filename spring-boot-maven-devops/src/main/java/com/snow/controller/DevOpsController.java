package com.snow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevOpsController {

    @GetMapping("/hello")
    public String hello() {
        return "DevOpsController.hello()";
    }
}
