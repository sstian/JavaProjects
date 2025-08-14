package com.snow.aopanno;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(3)
public class PRCProxy {

    @Before(value = "execution(* com.snow.aopanno.People.add(..))")
    public void before() {
        System.out.println("PRCProxy.before");
    }

    @After(value = "execution(* com.snow.aopanno.People.add(..))")
    public void after() {
        System.out.println("PRCProxy.after");
    }

    @AfterReturning(value = "execution(* com.snow.aopanno.People.add(..))")
    public void afterReturning() {
        System.out.println("PRCProxy.afterReturning");
    }
}
