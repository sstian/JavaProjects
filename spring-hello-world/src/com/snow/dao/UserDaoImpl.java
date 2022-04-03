package com.snow.dao;

public class UserDaoImpl implements UserDao {

    @Override
    public int add(int a, int b) {
        System.out.println("UserDaoImpl.add");
        return a + b;
    }

    @Override
    public void update() {
        System.out.println("UserDaoImpl.update()...");
    }
}
