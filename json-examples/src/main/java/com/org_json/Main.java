package com.org_json;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n==== buildJsonObject ===="); buildJsonObject();
        System.out.println("\n==== buildJsonArray ===="); buildJsonArray();
        System.out.println("\n==== parseJsonString ===="); parseJsonString();
    }

    // 构建 JSON 对象
    private static void buildJsonObject() {
        // 创建空 JSONObject
        JSONObject json = new JSONObject();

        // 放入键值对（支持字符串、数字、布尔、null、嵌套对象）
        json.put("name", "张三");
        json.put("age", 20);
        json.put("isStudent", true);
        json.put("score", (Object) null);

        // 嵌套 JSONObject
        JSONObject address = new JSONObject();
        address.put("province", "广东");
        address.put("city", "深圳");
        json.put("address", address);

        // 转为格式化 JSON 字符串（参数：缩进空格数）
        String jsonStr = json.toString(4);
        System.out.println(jsonStr);
    }

    // 构建 JSON 数组
    private static void buildJsonArray() {
        // 创建 JSON 数组
        JSONArray array = new JSONArray();

        // 添加元素（支持任意类型）
        array.put("Java");
        array.put(99);
        array.put(true);

        // 添加嵌套 JSON 对象
        JSONObject book = new JSONObject();
        book.put("title", "编程入门");
        book.put("price", 59.9);
        array.put(book);

        // 输出格式化字符串
        String arrayStr = array.toString(4);
        System.out.println(arrayStr);
    }

    // 解析 JSON 字符串
    // 最常用场景：把接口返回的 JSON 字符串转为 Java 对象读取数据
    private static void parseJsonString() {
        // 待解析的 JSON 字符串
        String jsonStr = """
                {
                    "name": "李四",
                    "age": 22,
                    "hobbies": ["读书", "跑步"],
                    "info": {"height": 175, "weight": 65}
                }
                """;

        // 解析字符串为 JSONObject
        JSONObject json = new JSONObject(jsonStr);

        // 按类型取值（get+类型 方法）
        String name = json.getString("name");
        int age = json.getInt("age");

        // 解析嵌套数组
        JSONArray hobbies = json.getJSONArray("hobbies");
        String firstHobby = hobbies.getString(0);

        // 解析嵌套对象
        JSONObject info = json.getJSONObject("info");
        int height = info.getInt("height");

        // 打印结果
        System.out.println("姓名：" + name);
        System.out.println("年龄：" + age);
        System.out.println("爱好1：" + firstHobby);
        System.out.println("身高：" + height);

        // 安全取值（避免键不存在报错）
        // 使用 optXXX() 方法替代 getXXX()，键不存在时返回默认值，不抛异常
        int score = json.optInt("score", 0);    // 键不存在，返回默认值 0
        String email = json.optString("email", ""); // 键不存在，返回空字符串
        System.out.println("score:" + score);
        System.out.println("email:" + email);
    }

}

/*
D:\Program\Java\jdk-21\bin\java.exe "-javaagent:D:\Program\JetBrains\IntelliJ IDEA 2025.1.3\lib\idea_rt.jar=3793" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath D:\Develop\JavaProjects\json-examples\target\classes;C:\Users\Snow-Angel\.m2\repository\jakarta\json\jakarta.json-api\2.1.2\jakarta.json-api-2.1.2.jar;C:\Users\Snow-Angel\.m2\repository\org\eclipse\parsson\parsson\1.1.2\parsson-1.1.2.jar;C:\Users\Snow-Angel\.m2\repository\org\json\json\20240303\json-20240303.jar com.orgjson.Main

==== buildJsonObject ====
{
    "address": {
        "province": "广东",
        "city": "深圳"
    },
    "name": "张三",
    "age": 20,
    "isStudent": true
}

==== buildJsonArray ====
[
    "Java",
    99,
    true,
    {
        "price": 59.9,
        "title": "编程入门"
    }
]

==== parseJsonString ====
姓名：李四
年龄：22
爱好1：读书
身高：175
score:0
email:

 */

/*
org.json 是轻量无依赖的 JSON 工具，核心类：JSONObject/JSONArray
构建 JSON：put() 方法添加数据，toString() 转字符串
解析 JSON：传入字符串创建对象，getXXX()/optXXX() 取值
适合简单 JSON 操作，上手成本极低

依赖：
<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20240303</version>
</dependency>

org.json 只有 4 个核心类：
JSONObject：对应 JSON 对象 {key:value}
JSONArray：对应 JSON 数组 [元素1, 元素2]
JSONTokener：JSON 解析器（用于解析字符串 / 流）
JSONException：JSON 操作异常

JSONObject 方法：
方法	作用
put(key, value)	添加键值对
getString(key)	获取字符串值
getInt(key)	获取整型值
getBoolean(key)	获取布尔值
getJSONObject(key)	获取嵌套对象
getJSONArray(key)	获取嵌套数组
has(key)	判断是否包含某个键
remove(key)	删除指定键
toString(indent)	格式化输出 JSON

JSONArray 方法：
方法	作用
put(value)	添加元素
get(index)	按索引获取元素
getString(index)	获取字符串元素
getInt(index)	获取整型元素
length()	获取数组长度
toList()	转为 Java List

注意事项:
异常处理：所有 JSON 操作都可能抛出 JSONException，建议用 try-catch 包裹
无自动类型转换：取值类型必须与 JSON 实际类型一致（如数字不能用 getString 读取）
轻量无依赖：适合简单场景；复杂场景（如 JSON 与 Java 对象互转）推荐使用 Gson/Fastjson/Jackson
 */