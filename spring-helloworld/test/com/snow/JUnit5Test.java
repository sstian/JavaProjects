package com.snow;

import com.snow.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:bean-jdbc.xml")
@SpringJUnitConfig(locations = "classpath:bean-jdbc.xml")   //复合注解
public class JUnit5Test {

    @Autowired
    private AccountService accountService;

    @Test
    public void account() {
        accountService.account();
    }

}
