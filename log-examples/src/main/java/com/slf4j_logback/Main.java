package com.slf4j_logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    // 获取日志对象（传入当前类）
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // 推荐使用 占位符{} 格式打印日志，性能更高、代码更简洁
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

        var userService = new UserService();
        userService.login();
    }

}

/*
D:\Program\Java\jdk-21\bin\java.exe "-javaagent:D:\Program\JetBrains\IntelliJ IDEA 2025.1.3\lib\idea_rt.jar=10662" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath D:\Develop\JavaProjects\log-examples\target\classes;C:\Users\Snow-Angel\.m2\repository\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;C:\Users\Snow-Angel\.m2\repository\org\slf4j\slf4j-api\2.0.9\slf4j-api-2.0.9.jar;C:\Users\Snow-Angel\.m2\repository\ch\qos\logback\logback-core\1.4.11\logback-core-1.4.11.jar;C:\Users\Snow-Angel\.m2\repository\ch\qos\logback\logback-classic\1.4.11\logback-classic-1.4.11.jar com.slf4j_logback.Main
2026-06-09 17:15:09.630 [main] TRACE com.slf4j_logback.Main - 追踪信息：张三
2026-06-09 17:15:09.636 [main] DEBUG com.slf4j_logback.Main - 调试信息
2026-06-09 17:15:09.637 [main] INFO  com.slf4j_logback.Main - 普通信息
2026-06-09 17:15:09.638 [main] WARN  com.slf4j_logback.Main - 警告信息
2026-06-09 17:15:09.638 [main] ERROR com.slf4j_logback.Main - 错误信息
2026-06-09 17:15:09.639 [main] ERROR com.slf4j_logback.Main - 业务执行异常
java.lang.ArithmeticException: / by zero
	at com.slf4j_logback.Main.main(Main.java:21)
2026-06-09 17:15:09.644 [main] INFO  com.slf4j_logback.UserService - 使用 Lombok 打印日志

 */

/*
SLF4J 日志门面 + Logback 日志实现

日志级别（从低到高）：
TRACE：最详细（一般不用）
DEBUG：调试信息
INFO：常规运行信息
WARN：警告，不影响运行
ERROR：错误异常
OFF：关闭日志
配置 level="INFO" 表示只会打印 INFO 及以上级别 的日志。

依赖：
<!-- SLF4J 门面接口 -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.0.9</version>
</dependency>
<!-- Logback 核心实现 -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-core</artifactId>
    <version>1.4.11</version>
</dependency>
<!-- Logback 与 SLF4J 适配 -->
<!-- 说明：logback-classic 已经自动包含了 SLF4J 适配，无需额外配置桥接包 -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.4.11</version>
</dependency>
<!-- 可选：Lombok 简化写法，直接用 @Slf4j 注解，无需手动创建 Logger -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.30</version>
    <scope>provided</scope>
</dependency>

配置文件：
logback.xml

 */