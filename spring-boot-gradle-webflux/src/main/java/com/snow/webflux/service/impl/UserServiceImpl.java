package com.snow.webflux.service.impl;

import com.snow.webflux.model.GenderEnum;
import com.snow.webflux.model.User;
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
        this.users.put(1, new User("Tom", GenderEnum.Male, 20));
        this.users.put(2, new User("Jerry", GenderEnum.Male, 30));
        this.users.put(3, new User("Lucy", GenderEnum.Female, 18));
        this.users.put(4, new User("Marry", GenderEnum.Female, 19));
        this.users.put(5, new User("Sata", GenderEnum.Female, 1000));
        this.users.put(6, new User("Cat", GenderEnum.Female, 2));
        this.users.put(7, new User("Dog", GenderEnum.Female, 3));
        this.users.put(8, new User("Ant", GenderEnum.Female, 4));
        this.users.put(9, new User("John", GenderEnum.Female, 29));
        this.users.put(10, new User("Saber", GenderEnum.Female, 500));
        this.users.put(11, new User("Lancer", GenderEnum.Female, 400));
        this.users.put(12, new User("Archer", GenderEnum.Female, 300));
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
