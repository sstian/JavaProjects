package com.snow.orderservice.controller;

import com.snow.orderservice.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private CommonConfig commonConfig;

    @GetMapping("/order")
    public String order() {
        return "order(): username=" + this.commonConfig.getUsername() + ", password=" + this.commonConfig.getPassword() + ", database=" + this.commonConfig.getDatabase();
//        return "order():" + this.username;
    }

}