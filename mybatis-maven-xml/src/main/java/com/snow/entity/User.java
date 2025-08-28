package com.snow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;  //id
    private String name;   //姓名
    private String password;   //密码，数据表的字段时pwd
}
