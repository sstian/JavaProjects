package com.snow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class HelloApplication {

    @Value("${server.port}")
    private String port;

    @Bean(name = "initStarter")
    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
        return args -> log.info(">>> springboot-gradle-swagger is up at " + this.port);
    }
    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

}
