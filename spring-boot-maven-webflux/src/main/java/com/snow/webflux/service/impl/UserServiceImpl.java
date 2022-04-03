package com.snow.webflux.service.impl;

import com.snow.webflux.entity.User;
import com.snow.webflux.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final Map<Integer, User> users = new HashMap<>();

    public UserServiceImpl() {
        this.users.put(1, new User("tom", "nan", 20));
        this.users.put(2, new User("lucy", "nv", 30));
        this.users.put(3, new User("marry", "nv", 18));
    }

    @Override
    public Mono<User> getUserById(Integer id) {
        return Mono.justOrEmpty(this.users.get(id));
    }

    @Override
    public Flux<User> getUsers() {
        return Flux.fromIterable(this.users.values());
    }

    @Override
    public Mono<Void> addUser(Mono<User> userMono) {
        return userMono.doOnNext(user -> {
            int id = this.users.size() + 1;
            this.users.put(id, user);
        }).thenEmpty(Mono.empty());
    }
}
