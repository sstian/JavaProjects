package com.snow.hasor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/test/hello")
    public String hello(@RequestParam(required = false) String name) {
        return ((name == null || name.isEmpty()) ? "Hello Hasor" : "Hello " + name);
    }
}
