package com.snow.hasor;

import net.hasor.spring.boot.EnableHasor;
import net.hasor.spring.boot.EnableHasorWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHasor
// 将 hasor-web 配置到 Spring 环境中，Dataway 的 UI 是通过 hasor-web 提供服务
@EnableHasorWeb
@SpringBootApplication
public class HasorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HasorApplication.class, args);
    }

}
