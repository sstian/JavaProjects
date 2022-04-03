package com.snow.webflux.web;

import com.snow.webflux.model.User;
import com.snow.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public Mono<User> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    public Flux<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value="/user/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getUsersWithStream() {
        return userService.getUsers();
    }

    @PostMapping("/adduser")
    public Mono<Void> addUser(@RequestBody User user) {
        return userService.addUser(Mono.just(user));
    }
}
