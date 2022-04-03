package com.snow;


import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericApplicationContext;

class RoomTest {

    @Test
    public void test() {
        // 函数式风格 手动创建对象添加到IOC容器 GenericApplicationContext
        // 创建
        GenericApplicationContext context = new GenericApplicationContext();
        // 注册
        context.refresh();
        context.registerBean("room", Room.class, Room::new);
        // 获取
        Room room = (Room)context.getBean("room");
        System.out.println(room);
    }
}