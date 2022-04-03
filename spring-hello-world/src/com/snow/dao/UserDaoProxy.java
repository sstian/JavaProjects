package com.snow.dao;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class UserDaoProxy implements InvocationHandler {

    private Object object;
    public UserDaoProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        switch (method.getName()) {
            case "add":
                // 方法之前
                System.out.println("UserDaoProxy.invoke, before " + method.getName() + "+" + Arrays.toString(args));
                // 被增强的方法
                result = method.invoke(object, args);
                // 方法之后
                System.out.println("UserDaoProxy.invoke, after " + object);
                break;
            case "update":
                System.out.println("UserDaoProxy.invoke update");
                result = method.invoke(object, args);
                break;
        }
        return result;
    }
}
