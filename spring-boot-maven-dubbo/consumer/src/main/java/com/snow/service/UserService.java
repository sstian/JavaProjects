package com.snow.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

// 标记 springframework 服务
@Service
public class UserService {
    // @DubboReference 注解将自动注入为 Dubbo 服务代理实例，使用 ticketService 即可发起远程服务调用
    // @Reference 注解从 3.0 版本开始就已经废弃，改用 @DubboReference，以区别于 Spring 的 @Reference 注解
    @DubboReference
//    @DubboReference(version = "1.0.0", group = "dev", timeout = 5000)
    TicketService ticketService;

    public void bugTicket(){
        String ticket = ticketService.getTicket();
        System.out.println("在注册中心拿到了=>"+ticket);
    }
}