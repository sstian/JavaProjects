package com.snow.controller;

import com.snow.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Hello APIs")
@RequestMapping("/api/v1")
@RestController
public class HelloController {

    @GetMapping("/hello")
    @ApiOperation(
            value = "Say Hello",
            notes = "Are you OK"
    )
    public String hello(
            @ApiParam("Your name")
            @RequestParam(required = false) String name) {
            if (!StringUtils.isEmpty(name)) {
                return String.format("Hello %s", name);
        }
        return "Hello Spring Boot";
    }

    @PostMapping("/meet")
    public User meet() {
        return new User("Jack", "123456");
    }
}