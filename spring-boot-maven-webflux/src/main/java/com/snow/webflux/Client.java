package com.snow.webflux;

import com.snow.webflux.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class Client {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://localhost:1274");

        String id = "1";
        User user = webClient.get().uri("/user/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(User.class).block();
        assert user != null;
        System.out.println(user.getName() + ":" + user.getGender() + ":" + user.getAge());
        // tom:nan:20

        Flux<User> users = webClient.get().uri("/user")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(User.class);
        users.map(User::getName).buffer().doOnNext(System.out::println).blockFirst();
        // [tom, lucy, marry]
    }
}
