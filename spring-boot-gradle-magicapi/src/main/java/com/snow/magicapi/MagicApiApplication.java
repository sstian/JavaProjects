package com.snow.magicapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class MagicApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MagicApiApplication.class, args);
    }

}
