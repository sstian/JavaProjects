package com.simple_json;


import com.github.cliftonlabs.json_simple.*;


public class Main {
    public static void main(String[] args) throws JsonException {
        System.out.println("\n==== buildJsonObject ===="); buildJsonObject();
        System.out.println("\n==== buildJsonArray ===="); buildJsonArray();
        System.out.println("\n==== parseJsonString ===="); parseJsonString();
    }

    // 定义 key
    enum Keys implements JsonKey {
        name,
        age,
        isStudent;

        @Override
        public String getKey() {
            return this.name();
        }

        @Override
        public Object getValue() {
            return null;
        }

    }

    // 构建 JSON 对象
    private static void buildJsonObject() {
        // 创建 JSON 对象
        JsonObject json = new JsonObject();

        // 放入键值对
        json.put("name", "张三");
        json.put("age", 20);
        json.put("isStudent", true);

        // 输出（自动格式化）
        String toString = json.toString();
        String toJson = json.toJson();
        System.out.println("toString:"+ toString);
        System.out.println("toJson:"+ toJson);
    }

    // 构建 JSON 数组
    private static void buildJsonArray() {
        // 创建 JSON 数组
        JsonArray array = new JsonArray();
        array.add("Java");
        array.add(99);

        // 嵌套对象
        JsonObject book = new JsonObject();
        book.put("title", "编程入门");
        array.add(book);

        // 输出
        String arrayStr = array.toJson();
        System.out.println(arrayStr);
    }

    // 解析 JSON 字符串
    private static void parseJsonString() throws JsonException {
        String jsonStr = """
            {
                "name": "李四",
                "age": 22,
                "hobbies": ["读书", "跑步"]
            }
        """;

        // 解析 JSON 字符串
        JsonObject json = (JsonObject) Jsoner.deserialize(jsonStr);

//        // 取值：直接 get() + 强转
//        String name = (String) json.get("name");
//        Number age = (Number) json.get("age"); // 数字默认是 Long 类型
//        int ageInt = age.intValue(); // 如需 int 可手动转换

        // 取值：使用 JsonKey
        String name = json.getString(Keys.name);
        int ageInt = json.getInteger(Keys.age);

        // 解析数组
        JsonArray hobbies = (JsonArray) json.get("hobbies");
        String firstHobby = (String) hobbies.get(0);

        System.out.println("姓名：" + name);
        System.out.println("年龄：" + ageInt);
        System.out.println("爱好：" + firstHobby);
    }

}

/*
D:\Program\Java\jdk-21\bin\java.exe "-javaagent:D:\Program\JetBrains\IntelliJ IDEA 2025.1.3\lib\idea_rt.jar=5566" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath D:\Develop\JavaProjects\json-examples\target\classes;C:\Users\Snow-Angel\.m2\repository\jakarta\json\jakarta.json-api\2.1.2\jakarta.json-api-2.1.2.jar;C:\Users\Snow-Angel\.m2\repository\org\eclipse\parsson\parsson\1.1.2\parsson-1.1.2.jar;C:\Users\Snow-Angel\.m2\repository\org\json\json\20240303\json-20240303.jar;C:\Users\Snow-Angel\.m2\repository\com\github\cliftonlabs\json-simple\4.0.0\json-simple-4.0.0.jar com.simplejson.Main

==== buildJsonObject ====
toString:{name=张三, age=20, isStudent=true}
toJson:{"name":"张三","age":20,"isStudent":true}

==== buildJsonArray ====
["Java",99,{"title":"编程入门"}]

==== parseJsonString ====
姓名：李四
年龄：22
爱好：读书

 */

/*
simple json 是 Java 另一款超轻量、无依赖的 JSON 处理库，API 比 org.json 更简洁直观
适合快速开发、简单 JSON 读写场景

依赖：
<dependency>
    <groupId>com.github.cliftonlabs</groupId>
    <artifactId>json-simple</artifactId>
    <version>4.0.0</version>
</dependency>
 */