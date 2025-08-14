package com.snow.aopanno;

import org.springframework.stereotype.Component;

// 被增强的类
@Component
public class People {

    public void add() {
//        int i = 10 / 0;
        System.out.println("People.add");
    }
}
