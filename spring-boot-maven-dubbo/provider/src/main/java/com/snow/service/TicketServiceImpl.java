package com.snow.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

// 实现类

// 标记 springframework 服务
@Service
// 标记 Dubbo 服务
// @Service 注解从 3.0 版本开始就已经废弃，改用 @DubboService，以区别于 Spring 的 @Service 注解
@DubboService
//@DubboService(version = "1.0.0", group = "dev", timeout = 5000)
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "provider提供的买票服务";
    }
}