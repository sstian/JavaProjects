package com.alibaba_fastjson2;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n==== serialize ===="); serialize();
        System.out.println("\n==== deserialize ===="); deserialize();
        System.out.println("\n==== convert ===="); convert();
    }

    // 对象 → JSON（序列化）
    private static void serialize() {
        User user = new User();
        user.setId(1001L);
        user.setName("张三");
        user.setAge(25);
        user.setVip(true);

        // 一行代码转 JSON
        String json = JSON.toJSONString(user);
        System.out.println("json:" + json);

        // 格式化
        String jsonPretty = JSON.toJSONString(user, JSONWriter.Feature.PrettyFormat); // 美观输出 JSON，带缩进
        System.out.println("jsonPretty:" + jsonPretty);
    }

    // JSON → 对象（反序列化）
    private static void deserialize() {
        String json = "{\"id\":1001,\"name\":\"张三\",\"age\":25,\"isVip\":true}";

        // 一行代码转对象
        User user = JSON.parseObject(json, User.class);

        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getAge());
        System.out.println(user.isVip());
    }

    // 转换 List / 数组
    private static void convert() {
        // JSON 数组字符串
        String jsonArray = "[{\"name\":\"张三\"},{\"name\":\"李四\"}]";

        // 直接转 List<User>
        List<User> userList = JSON.parseArray(jsonArray, User.class);
        System.out.println(userList);
    }

}

/*
D:\Program\Java\jdk-21\bin\java.exe "-javaagent:D:\Program\JetBrains\IntelliJ IDEA 2025.1.3\lib\idea_rt.jar=6301" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath D:\Develop\JavaProjects\json-examples\target\classes;C:\Users\Snow-Angel\.m2\repository\jakarta\json\jakarta.json-api\2.1.2\jakarta.json-api-2.1.2.jar;C:\Users\Snow-Angel\.m2\repository\org\eclipse\parsson\parsson\1.1.2\parsson-1.1.2.jar;C:\Users\Snow-Angel\.m2\repository\org\json\json\20240303\json-20240303.jar;C:\Users\Snow-Angel\.m2\repository\com\github\cliftonlabs\json-simple\4.0.0\json-simple-4.0.0.jar;C:\Users\Snow-Angel\.m2\repository\com\google\code\gson\gson\2.10.1\gson-2.10.1.jar;C:\Users\Snow-Angel\.m2\repository\com\alibaba\fastjson2\fastjson2\2.0.52\fastjson2-2.0.52.jar com.alibaba_fastjson2.Main

==== serialize ====
json:{"age":25,"id":1001,"name":"张三","vip":true}
jsonPretty:{
	"age":25,
	"id":1001,
	"name":"张三",
	"vip":true
}

==== deserialize ====
1001
张三
25
false

==== convert ====
[com.alibaba_fastjson2.User@76b0bfab, com.alibaba_fastjson2.User@17d677df]

 */

/*
Fastjson 是阿里巴巴开源的高性能 JSON 库，主打速度快、使用简单，是国内最常用的 JSON 工具之一。
现在推荐使用 Fastjson 2（最新版，修复了老版本安全漏洞，性能更强）。
支持格式化、日期、别名、忽略字段、泛型集合

依赖：
<dependency>
    <groupId>com.alibaba.fastjson2</groupId>
    <artifactId>fastjson2</artifactId>
    <version>2.0.52</version>
</dependency>

核心 API（只记一个类：JSON）
Fastjson 设计极简，所有功能都在 JSON 类，不用创建对象：
```
import com.alibaba.fastjson2.JSON;

// 1. Java对象 → JSON字符串（序列化）
String json = JSON.toJSONString(对象);

// 2. JSON字符串 → Java对象（反序列化）
User user = JSON.parseObject(json, User.class);

// 3. JSON数组 → List集合
List<User> list = JSON.parseArray(jsonArray, User.class);
```

Fastjson vs Gson（简单对比）：
特点	Fastjson	Gson
性能	极快（国内第一梯队）	良好
使用难度	极简（一行代码）	稍繁琐（需创建 Gson 对象）
国内生态	企业主流	国外/老项目常用
依赖	轻量	轻量
 */