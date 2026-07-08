package com.alibaba_fastjson2;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.Date;

// 实体类
public class User {
//    // 字段别名（前后端字段名不一致）
//    @JSONField(name = "user_id")  // JSON 字段名：user_id
    private Long id;

    private String name;
    private int age;
    private boolean isVip;

    // 忽略字段，不参与序列化，不会出现在 JSON 里
    @JSONField(serialize = false)
    private String password;

    // 自动格式化日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    // 无参构造
    public User() {}

    // getter/setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public boolean isVip() { return isVip; }
    public void setVip(boolean vip) { isVip = vip; }
}