package com.slf4j_log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    // 获取日志对象
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // 占位符 {} 性能极强，无需字符串拼接
        logger.trace("追踪信息：{}", "张三");

        logger.debug("调试信息");
        logger.info("普通信息");
        logger.warn("警告信息");
        logger.error("错误信息");

        // 异常打印（自动输出堆栈）
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            logger.error("业务执行异常", e);
        }
    }
}

/*

 */

/*
SLF4J（门面） + Log4j2（实现）

Log4j2 最大优势：
性能极高：异步日志、无锁设计，比 Logback 快 10~20 倍
支持 {} 占位符：SLF4J 原生支持，避免字符串拼接开销
自动压缩日志：超过大小自动压缩
配置更强大：支持多文件、异步、过滤、自定义格式
安全修复：老 Log4j 1.x 已停止维护，存在漏洞

依赖：
<!-- SLF4J 门面接口 -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.0.9</version>
</dependency>
<!-- Log4j2 核心实现 -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.20.0</version>
</dependency>
<!-- SLF4J 绑定 Log4j2（桥接） -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j2-impl</artifactId>
    <version>2.20.0</version>
</dependency>

配置文件：
log4j2.xml


日志级别（SLF4J ↔ Log4j2）：
logger.trace("追踪");   // TRACE
logger.debug("调试");   // DEBUG
logger.info("信息");    // INFO  默认
logger.warn("警告");    // WARN
logger.error("错误");   // ERROR

Spring / SpringBoot 项目使用：
1. Spring 项目
直接加入上面 3 个依赖 + log4j2.xml 即可。
2. Spring Boot 项目
排除 Spring Boot 默认的 Logback，引入 Log4j2 依赖
添加 log4j2.xml 配置文件（必须）
```
<!-- 排除 SpringBoot 默认的 Logback -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<!-- 引入 Log4j2 依赖，Spring Boot 集成 Log4j2（自动包含 SLF4J） -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

 */