package com.snow.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 开启SBA服务
@EnableAdminServer
@SpringBootApplication
public class SpringBootGradleAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGradleAdminApplication.class, args);
    }

}
