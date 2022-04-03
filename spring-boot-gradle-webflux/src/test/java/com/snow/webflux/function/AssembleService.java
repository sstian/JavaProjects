//package com.snow.webflux.function;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//
//@Slf4j
//@Service
//public class AssembleService {
//
//    private WebClient assembleBaseWebClient;
//
//    @Autowired
//    public AssembleService(WebClient assembleBaseWebClient) {
//        this.assembleBaseWebClient = assembleBaseWebClient;
//    }
//
//    public Mono<String> createAssembleApplication(final String id) {
//        return this.assembleBaseWebClient.get()
//                .uri(String.format("/create/%s"), id)
//                .retrieve()
//                .bodyToMono(String.class)
//                .filter(rawResult -> {
//                    JSONObject jsonObject = JSON.parseObject(rawResult);
//                    String status = jsonObject.getString("status");
//                    return status.equalsIgnoreCase("DONE");
//                }).repeatWhenEmpty(repeat -> repeat.zipWith(Flux.range(1, 10), (e, idx) -> idx).flatMap(time -> {
//                    log.info("The NPX is running to create a new application, please waite a moment...");
//                    return Mono.delay(Duration.ofSeconds(time * 5));
//                })).onErrorResume(throwable -> Mono.just(String.format("error: %s", throwable.getMessage())));
//    }
//}
