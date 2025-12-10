package com.snow.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {
    @Bean
    // 开启 @LoadBalanced 与 Ribbon 的集成
    // 解决问题：java.net.UnknownHostException: order-service
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
