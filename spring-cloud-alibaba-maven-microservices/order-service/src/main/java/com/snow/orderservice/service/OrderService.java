package com.snow.orderservice.service;

import com.alibaba.cloud.nacos.annotation.NacosConfigListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class OrderService {

    // @NacosConfigListener默认不会进行回调初始值，如果需要收到初始值，可以通过指定initNotify=true
    @NacosConfigListener(dataId = "order-dev.properties", group = "DEFAULT_GROUP")
    public void order(String orderConfig) {
        System.out.println("received order-dev config: "+orderConfig);
    }
}
