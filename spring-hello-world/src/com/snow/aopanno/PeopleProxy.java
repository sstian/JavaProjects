package com.snow.aopanno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 增强的类
@Component  // 创建对象
@Aspect     // 生成代理对象
@Order(1)   // 增强类优先级
public class PeopleProxy {

    // 相同切入点抽取
    @Pointcut(value = "execution(* com.snow.aopanno.People.add(..))")
    public void pointdemo() {}

    // 前置通知
    @Before(value = "pointdemo()")
    public void before() {
        System.out.println("PeopleProxy.before");
    }

    // 最终通知，无论是否异常都会在方法执行后执行
    @After(value = "pointdemo()")
    public void after() {
        System.out.println("PeopleProxy.after");
    }

    // 后置通知（返回通知），返回后执行，发生异常不会执行
    @AfterReturning(value = "pointdemo()")
    public void afterReturning() {
        System.out.println("PeopleProxy.afterReturning");
    }

    // 异常通知，只有在发生异常时才会执行
    @AfterThrowing(value = "pointdemo()")
    public void AfterThrowing() {
        System.out.println("PeopleProxy.AfterThrowing");
    }

    // 环绕通知
    @Around(value = "pointdemo()")
    public void Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("PeopleProxy.Around + before");
        proceedingJoinPoint.proceed();
        System.out.println("PeopleProxy.Around + after");
    }
}
