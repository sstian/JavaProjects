package com.snow;

import com.snow.config.TxConfig;
import com.snow.service.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class AccountTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void accountAnno() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        AccountService accountService = context.getBean("accountService", AccountService.class);
        accountService.account();
    }

    @Test
    void accountXML() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbc.xml");
        AccountService accountService = context.getBean("accountService", AccountService.class);
        accountService.account();
    }
}