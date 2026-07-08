package com.jcl_jul;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main {
    // 加载配置文件 logging.properties，让 debug 日志显示出来
    static {
        try {
            java.util.logging.LogManager.getLogManager().readConfiguration(
                    Main.class.getClassLoader().getResourceAsStream("logging.properties")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取 JCL 日志对象
    private static final Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        // 2. 打印日志
        log.debug("调试信息");
        log.info("普通信息");
        log.warn("警告信息");
        log.error("错误信息");
        log.fatal("严重信息");

        // 打印异常堆栈
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            // 会自动打印完整异常堆栈
            log.error("执行出错", e);
        }
    }
}

/*
D:\Program\Java\jdk-21\bin\java.exe "-javaagent:D:\Program\JetBrains\IntelliJ IDEA 2025.1.3\lib\idea_rt.jar=9649" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath D:\Develop\JavaProjects\log-examples\target\classes;C:\Users\Snow-Angel\.m2\repository\commons-logging\commons-logging\1.2\commons-logging-1.2.jar com.jcl_jul.Main
6月 09, 2026 4:05:45 下午 com.jcl_jul.Main main
详细: 调试信息
6月 09, 2026 4:05:45 下午 com.jcl_jul.Main main
信息: 普通信息
6月 09, 2026 4:05:45 下午 com.jcl_jul.Main main
警告: 警告信息
6月 09, 2026 4:05:45 下午 com.jcl_jul.Main main
严重: 错误信息
6月 09, 2026 4:05:45 下午 com.jcl_jul.Main main
严重: 严重信息
6月 09, 2026 4:05:45 下午 com.jcl_jul.Main main
严重: 执行出错
java.lang.ArithmeticException: / by zero
	at com.jcl_jul.Main.main(Main.java:31)

 */

/*
JCL = Apache Commons Logging / Jakarta Commons Logging
JUL = java.util.logging

JCL 日志门面接口 + JUL 日志实现
纯 Apache + 纯 JDK 官方组合，零第三方日志框架，老项目、Spring 传统项目最常用。

JCL 会按顺序寻找日志实现：
Log4j
JUL（java.util.logging）
SimpleLog
只要没引入 Log4j，JCL 会自动使用 JUL。

依赖：
<!-- JCL 门面 -->
<dependency>
    <groupId>commons-logging</groupId>
    <artifactId>commons-logging</artifactId>
    <version>1.2</version>
</dependency>
<!-- JUL 是 JDK 自带，不用加依赖-->

配置文件：
logging.properties


级别对应关系：
JCL 级别	JUL 级别
debug	FINE
info	INFO
warn	WARNING
error	SEVERE
JUL 默认只输出 INFO 及以上，所以 debug 默认不显示。

优点：
纯 JDK + 官方
零第三方日志框架
Spring 默认支持
适合老项目、简单项目

缺点：
没有 {} 占位符
格式丑
性能一般
新项目不推荐
 */