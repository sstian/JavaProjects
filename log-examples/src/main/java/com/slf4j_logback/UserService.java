package com.slf4j_logback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserService {
    public void login() {
        log.info("使用 Lombok 打印日志");
    }
}