package com.snow;

import com.snow.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConsumerApplicationTest {
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        userService.bugTicket();
    }
}