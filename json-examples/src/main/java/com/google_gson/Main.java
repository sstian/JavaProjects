package com.google_gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n==== serialize ===="); serialize();
        System.out.println("\n==== deserialize ===="); deserialize();
        System.out.println("\n==== convert ===="); convert();
    }

    // 序列化（对象 → JSON）
    private static void serialize() {
        Gson gson = new Gson();

        User user = new User();
        user.setId(1001L);
        user.setName("张三");
        user.setAge(25);
        user.setVip(true);

        // 对象转 JSON
        String json = gson.toJson(user);
        System.out.println("json:" + json);

        // 格式化
        Gson gsonPretty = new GsonBuilder()
                .setPrettyPrinting()    // 美观输出 JSON
                .setDateFormat("yyyy-MM-dd HH:mm:ss")   // 处理日期
                .create();
        String jsonPretty = gsonPretty.toJson(user);
        System.out.println("jsonPretty:" + jsonPretty);
    }

    // 反序列化（JSON → 对象）
    private static void deserialize() {
        String json = "{\"id\":1001,\"name\":\"张三\",\"age\":25,\"isVip\":true}";

        // JSON 转对象
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);

        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getAge());
        System.out.println(user.isVip());
    }

    // 转换 List / 数组（泛型）
    private static void convert() {
        // JSON 数组字符串
        String jsonArray = "[{\"name\":\"张三\"},{\"name\":\"李四\"}]";

        // 定义泛型类型
        Type listType = new TypeToken<List<User>>() {}.getType();

        // 转换
        Gson gson = new Gson();
        List<User> userList = gson.fromJson(jsonArray, listType);
        System.out.println(userList);
    }

    }

/*
D:\Program\Java\jdk-21\bin\java.exe "-javaagent:D:\Program\JetBrains\IntelliJ IDEA 2025.1.3\lib\idea_rt.jar=6150" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath D:\Develop\JavaProjects\json-examples\target\classes;C:\Users\Snow-Angel\.m2\repository\jakarta\json\jakarta.json-api\2.1.2\jakarta.json-api-2.1.2.jar;C:\Users\Snow-Angel\.m2\repository\org\eclipse\parsson\parsson\1.1.2\parsson-1.1.2.jar;C:\Users\Snow-Angel\.m2\repository\org\json\json\20240303\json-20240303.jar;C:\Users\Snow-Angel\.m2\repository\com\github\cliftonlabs\json-simple\4.0.0\json-simple-4.0.0.jar;C:\Users\Snow-Angel\.m2\repository\com\google\code\gson\gson\2.10.1\gson-2.10.1.jar com.googlegson.Main

==== serialize ====
json:{"id":1001,"name":"张三","age":25,"isVip":true}
jsonPretty:{
  "id": 1001,
  "name": "张三",
  "age": 25,
  "isVip": true
}

==== deserialize ====
1001
张三
25
true

==== convert ====
[com.googlegson.User@2a18f23c, com.googlegson.User@d7b1517]

 */

/*
Gson 是 Google 官方推出的 Java 库：
互相转换 Java 对象 ↔ JSON 字符串
简单易用、无侵入、支持复杂对象 / List / 泛型 / 日期 / 格式化 / 别名等场景

依赖：
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>

核心 API:
```
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// 1. 创建 Gson 对象（推荐单例）
Gson gson = new Gson();

// 2. Java 对象 → JSON（序列化）
String json = gson.toJson(对象);

// 3. JSON → Java 对象（反序列化）
User user = gson.fromJson(json, User.class);
```

常见问题：
报空指针：实体类必须有无参构造函数
字段名不一致：使用 @SerializedName
    @SerializedName("user_name")
    private String name;
null 值不输出：Gson 默认会忽略 null 字段
 */