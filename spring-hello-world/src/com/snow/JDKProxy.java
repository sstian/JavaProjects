package com.snow;

import com.snow.dao.UserDao;
import com.snow.dao.UserDaoImpl;
import com.snow.dao.UserDaoProxy;

import java.lang.reflect.Proxy;

public class JDKProxy {

    public static void main(String[] args) {

        Class[] interfaces = {UserDao.class};
        UserDao userDao = (UserDao)(Proxy.newProxyInstance(
                JDKProxy.class.getClassLoader(),
                interfaces,
                new UserDaoProxy(new UserDaoImpl())));
        int res = userDao.add(12, 13);
        System.out.println("JDKProxy.main userDao.add " + res);

        userDao.update();
    }
}
