package com.spring_jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("\n==== serialize ===="); serialize();
        System.out.println("\n==== deserialize ===="); deserialize();
        System.out.println("\n==== convert ===="); convert();
    }

    // 对象转 JSON（序列化）
    private static void serialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        User user = new User();
        user.setId(1001L);
        user.setName("张三");
        user.setAge(25);
        user.setPhone("13800138000");
        user.setCreateTime(new Date());

        // 转换
        String json = mapper.writeValueAsString(user);
        System.out.println("json:" + json);

        // 格式输出
        String jsonPretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        System.out.println("jsonPretty:" + jsonPretty);
    }

    // JSON 转对象（反序列化）
    private static void deserialize() throws JsonProcessingException {
        String json = "{\"id\":1001,\"name\":\"张三\",\"age\":25}";

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);

        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getAge());
    }

    // 转换 List / 集合（泛型）
    private static void convert() throws JsonProcessingException {
        String jsonArray = "[{\"name\":\"张三\"},{\"name\":\"李四\"}]";

        ObjectMapper mapper = new ObjectMapper();
        List<User> userList = mapper.readValue(jsonArray, new TypeReference<List<User>>() {});
        System.out.println(userList);
    }

}

/*
D:\Program\Java\jdk-21\bin\java.exe "-javaagent:D:\Program\JetBrains\IntelliJ IDEA 2025.1.3\lib\idea_rt.jar=6695" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath D:\Develop\JavaProjects\json-examples\target\classes;C:\Users\Snow-Angel\.m2\repository\jakarta\json\jakarta.json-api\2.1.2\jakarta.json-api-2.1.2.jar;C:\Users\Snow-Angel\.m2\repository\org\eclipse\parsson\parsson\1.1.2\parsson-1.1.2.jar;C:\Users\Snow-Angel\.m2\repository\org\json\json\20240303\json-20240303.jar;C:\Users\Snow-Angel\.m2\repository\com\github\cliftonlabs\json-simple\4.0.0\json-simple-4.0.0.jar;C:\Users\Snow-Angel\.m2\repository\com\google\code\gson\gson\2.10.1\gson-2.10.1.jar;C:\Users\Snow-Angel\.m2\repository\com\alibaba\fastjson2\fastjson2\2.0.52\fastjson2-2.0.52.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\boot\spring-boot-starter-web\3.5.6\spring-boot-starter-web-3.5.6.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\boot\spring-boot-starter\3.5.6\spring-boot-starter-3.5.6.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\boot\spring-boot\3.5.6\spring-boot-3.5.6.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\boot\spring-boot-autoconfigure\3.5.6\spring-boot-autoconfigure-3.5.6.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\boot\spring-boot-starter-logging\3.5.6\spring-boot-starter-logging-3.5.6.jar;C:\Users\Snow-Angel\.m2\repository\ch\qos\logback\logback-classic\1.5.18\logback-classic-1.5.18.jar;C:\Users\Snow-Angel\.m2\repository\ch\qos\logback\logback-core\1.5.18\logback-core-1.5.18.jar;C:\Users\Snow-Angel\.m2\repository\org\slf4j\slf4j-api\2.0.17\slf4j-api-2.0.17.jar;C:\Users\Snow-Angel\.m2\repository\org\apache\logging\log4j\log4j-to-slf4j\2.24.3\log4j-to-slf4j-2.24.3.jar;C:\Users\Snow-Angel\.m2\repository\org\apache\logging\log4j\log4j-api\2.24.3\log4j-api-2.24.3.jar;C:\Users\Snow-Angel\.m2\repository\org\slf4j\jul-to-slf4j\2.0.17\jul-to-slf4j-2.0.17.jar;C:\Users\Snow-Angel\.m2\repository\jakarta\annotation\jakarta.annotation-api\2.1.1\jakarta.annotation-api-2.1.1.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\spring-core\6.2.11\spring-core-6.2.11.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\spring-jcl\6.2.11\spring-jcl-6.2.11.jar;C:\Users\Snow-Angel\.m2\repository\org\yaml\snakeyaml\2.4\snakeyaml-2.4.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\boot\spring-boot-starter-json\3.5.6\spring-boot-starter-json-3.5.6.jar;C:\Users\Snow-Angel\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.19.2\jackson-databind-2.19.2.jar;C:\Users\Snow-Angel\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.19.2\jackson-annotations-2.19.2.jar;C:\Users\Snow-Angel\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.19.2\jackson-core-2.19.2.jar;C:\Users\Snow-Angel\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.19.2\jackson-datatype-jdk8-2.19.2.jar;C:\Users\Snow-Angel\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.19.2\jackson-datatype-jsr310-2.19.2.jar;C:\Users\Snow-Angel\.m2\repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.19.2\jackson-module-parameter-names-2.19.2.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\boot\spring-boot-starter-tomcat\3.5.6\spring-boot-starter-tomcat-3.5.6.jar;C:\Users\Snow-Angel\.m2\repository\org\apache\tomcat\embed\tomcat-embed-core\10.1.46\tomcat-embed-core-10.1.46.jar;C:\Users\Snow-Angel\.m2\repository\org\apache\tomcat\embed\tomcat-embed-el\10.1.46\tomcat-embed-el-10.1.46.jar;C:\Users\Snow-Angel\.m2\repository\org\apache\tomcat\embed\tomcat-embed-websocket\10.1.46\tomcat-embed-websocket-10.1.46.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\spring-web\6.2.11\spring-web-6.2.11.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\spring-beans\6.2.11\spring-beans-6.2.11.jar;C:\Users\Snow-Angel\.m2\repository\io\micrometer\micrometer-observation\1.14.11\micrometer-observation-1.14.11.jar;C:\Users\Snow-Angel\.m2\repository\io\micrometer\micrometer-commons\1.14.11\micrometer-commons-1.14.11.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\spring-webmvc\6.2.11\spring-webmvc-6.2.11.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\spring-aop\6.2.11\spring-aop-6.2.11.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\spring-context\6.2.11\spring-context-6.2.11.jar;C:\Users\Snow-Angel\.m2\repository\org\springframework\spring-expression\6.2.11\spring-expression-6.2.11.jar com.spring_jackson.Main

==== serialize ====
json:{"id":1001,"name":"张三","age":25,"createTime":"2026-06-08 18:28:19","phone_number":"13800138000"}
jsonPretty:{
  "id" : 1001,
  "name" : "张三",
  "age" : 25,
  "createTime" : "2026-06-08 18:28:19",
  "phone_number" : "13800138000"
}

==== deserialize ====
1001
张三
25

==== convert ====
[com.spring_jackson.User@3c87521, com.spring_jackson.User@2aece37d]

 */

/*
Jackson 是 SpringBoot / Spring MVC 默认的 JSON 序列化框架，无需额外引入依赖，直接开箱即用，也是企业级项目最标准、最稳定的选择。

优势：
Spring 官方内置，自动整合，零配置
稳定性极高，无安全漏洞
支持注解、日期、泛型、嵌套对象
与 SpringMVC / SpringBoot 无缝对接

依赖：
<!-- spring jackson -->
<!-- 用于spring项目，自带jackson，比如：spring-web / spring-boot-starter-web-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.5.6</version>
</dependency>
<!-- 用于纯java项目 -->
<!--        <dependency>-->
<!--            <groupId>com.fasterxml.jackson.core</groupId>-->
<!--            <artifactId>jackson-databind</artifactId>-->
<!--            <version>2.15.2</version>-->
<!--        </dependency>-->

核心 API：
```
import com.fasterxml.jackson.databind.ObjectMapper;

// 1. 创建对象（Spring 中推荐注入，不要频繁 new）
ObjectMapper objectMapper = new ObjectMapper();

// 2. Java 对象 → JSON（序列化）
String json = objectMapper.writeValueAsString(obj);

// 3. JSON → Java 对象（反序列化）
User user = objectMapper.readValue(json, User.class);

常用注解：
@JsonFormat 日期格式化
@JsonProperty 字段别名
@JsonIgnore 忽略字段
@JsonInclude 空值不输出
```

三大 JSON 库对比：
库	地位	性能	推荐度	适用场景
Jackson	Spring默认	高	⭐⭐⭐⭐⭐	企业项目、Spring 全家桶
Gson	Google官方	中	⭐⭐⭐	老项目、非 Spring 环境
Fastjson2	阿里开源	极高	⭐⭐⭐	追求极致速度
企业开发首选：Jackson

 */