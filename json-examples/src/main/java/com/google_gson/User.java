package com.google_gson;

import com.google.gson.annotations.Expose;

// 实体类
public class User {
    private Long id;
    private String name;
    private int age;
    private boolean isVip;

    // 忽略字段，不参与序列化，不会参与 JSON 转换
    @Expose(serialize = false, deserialize = false)
    private String password;

    // 必须有无参构造（Gson 反射需要）
    public User() {}

    // getter / setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public boolean isVip() { return isVip; }
    public void setVip(boolean vip) { isVip = vip; }
}
