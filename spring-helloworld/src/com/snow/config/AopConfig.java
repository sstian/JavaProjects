package com.snow.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.snow.aopanno"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfig {
}
