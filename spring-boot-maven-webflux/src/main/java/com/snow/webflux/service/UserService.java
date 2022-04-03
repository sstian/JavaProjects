package com.snow.webflux.service;

import com.snow.webflux.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    // 查询一个用Mono，查询多个用Flux
    // 根据id查询
    Mono<User> getUserById(Integer id);

    // 查询所有
    Flux<User> getUsers();

    // 添加
    Mono<Void> addUser(Mono<User> userMono);
}
