package com.snow.webflux.controller;

import com.snow.webflux.entity.User;
import com.snow.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public Mono<User> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    public Flux<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/adduser")
    public Mono<Void> addUser(@RequestBody User user) {
        return userService.addUser(Mono.just(user));
    }
}
