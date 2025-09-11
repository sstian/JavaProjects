package com.snow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.snow.mapper")  // 扫描 Mapper 文件夹
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
