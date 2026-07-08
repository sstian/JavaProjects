package com.java_json;

import jakarta.json.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1.1 构建 JSON
         JsonObject json = Json.createObjectBuilder()
                .add("id", 1)
                .add("name", "张三")
                .add("age", 20)
                .build();

        // JsonObject -> JSON字符串
        String jsonStr = json.toString();
        System.out.println("jsonStr：" + jsonStr);

         // 1.2 构建嵌套 JSON
        JsonObject jsonNested = Json.createObjectBuilder()
                .add("id", 1)
                .add("user", Json.createObjectBuilder()
                        .add("name", "张三")
                        .add("age", 20)
                )
                .build();
        String jsonNestedStr = jsonNested.toString();
        System.out.println("jsonNestedStr：" + jsonNestedStr);

        // 1.3 构建 JSON 数组
        JsonArray jsonArray = Json.createArrayBuilder()
                .add(Json.createObjectBuilder().add("name", "张三"))
                .add(Json.createObjectBuilder().add("name", "李四"))
                .build();
        String jsonArrayStr = jsonArray.toString();
        System.out.println("jsonArrayStr：" + jsonArrayStr);

        // 2. 解析 JSON：JSON字符串 -> JsonObject
        try (JsonReader reader = Json.createReader(new StringReader(jsonStr))) {
            JsonObject obj = reader.readObject();

            int id = obj.getInt("id");
            String name = obj.getString("name");
            int age = obj.getInt("age");
            System.out.println(id + " " + name + " " + age);
        }

        // 3. 写入 JSON 到文件
        try (JsonWriter writer = Json.createWriter(new FileWriter("src/main/java/com/javajson/user.json"))) {
            writer.writeObject(json);
            System.out.println("write ok");
        }
    }
}

/*
D:\Program\Java\jdk-21\bin\java.exe "-javaagent:D:\Program\JetBrains\IntelliJ IDEA 2025.1.3\lib\idea_rt.jar=3814" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath D:\Develop\JavaProjects\json-examples\target\classes;C:\Users\Snow-Angel\.m2\repository\jakarta\json\jakarta.json-api\2.1.2\jakarta.json-api-2.1.2.jar;C:\Users\Snow-Angel\.m2\repository\org\eclipse\parsson\parsson\1.1.2\parsson-1.1.2.jar;C:\Users\Snow-Angel\.m2\repository\org\json\json\20240303\json-20240303.jar com.javajson.Main
jsonStr：{"id":1,"name":"张三","age":20}
jsonNestedStr：{"id":1,"user":{"name":"张三","age":20}}
jsonArrayStr：[{"name":"张三"},{"name":"李四"}]
1 张三 20
write ok

 */

/*
Java 官方标准，最规范
不支持自动对象映射，必须手动取值 / 赋值
适合简单 JSON 处理、接口定义、标准环境

依赖：
<!-- JSON-P 接口 -->
<!-- 注意：Java 11+ 用 jakarta.json（新版），不是旧版 javax.json -->
<dependency>
    <groupId>jakarta.json</groupId>
    <artifactId>jakarta.json-api</artifactId>
    <version>2.1.2</version>
</dependency>
<!-- 正确实现包：解决 Provider not found 错误 -->
<!-- JSON-P 2.x 实现已经换成 parsson 了，glassfish 不再适配 -->
<dependency>
    <groupId>org.eclipse.parsson</groupId>
    <artifactId>parsson</artifactId>
    <version>1.1.2</version>
    <scope>runtime</scope>
</dependency>
 */