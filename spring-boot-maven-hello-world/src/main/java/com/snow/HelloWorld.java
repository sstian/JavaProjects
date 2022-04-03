package com.snow;

/*
1. 创建 Maven 项目
2. 导入依赖，Spring Boot 相关，pom.xml
3. 编写主程序，启动 Spring Boot 应用
4. 编写相关的 Controler, Service
5. 运行主程序测试，浏览器地址栏：localhost:8080/helloackage.
6. 简化部署，Maven -> Lifecycle -> package, cmd: java -jar <package.jar>
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplicaion 标注主程序类，说明这是一个Spring Boot 应用
 */
@SpringBootApplication
public class HelloWorld {

    public static void main(String[] args) {
        // Spring 应用启动
        SpringApplication.run(HelloWorld.class, args);
    }
}
