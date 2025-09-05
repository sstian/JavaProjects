package com.snow;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 启用Dubbo，将加载 Dubbo 注解定义的服务
// 应用启动起来，dubbo就会扫描指定的包下带有 @DubboService 注解的服务，将它发布在指定的注册中心中！
@EnableDubbo
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}