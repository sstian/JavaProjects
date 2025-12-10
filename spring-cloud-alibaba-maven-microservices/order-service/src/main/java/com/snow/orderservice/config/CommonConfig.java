package com.snow.orderservice.config;

import com.alibaba.cloud.nacos.annotation.NacosConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class CommonConfig {

    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @NacosConfig(dataId = "order-dev.properties", group = "DEFAULT_GROUP", key = "spring.datasource.database")
    String database;


    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

}
/*
@Value和@NacosConfig都可以将nacos的属性注入到Spring Bean的字段中，两者的区别在于：

@Value是Spring提供的注解，nacos中的属性源是众多属性源之一，通过@Value引用配置值会收到其他属性源的影响，优先级为JVM>ENV>Nacos
@Value默认不支持运行期动态更新，需要结合@RefreshScope注解实现动态刷新，@NacosConfig默认支持运行期动态更新。。
@NacosConfig需要设置目标的dataId和group以及配置中的指定key，不受其他属性源影响，准确性更高。
@NacosConfig支持复杂对象的注入，如自定义JavaBean以及其集合类型，如Set List及Map。
@NacosConfig可以作用于SpringBean，类似@ConfigurationProperties。
 */
