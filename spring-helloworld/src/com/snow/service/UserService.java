package com.snow.service;

import com.snow.dao.UserDao;

public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void update() {
        System.out.println("UserService.update()...");
        this.userDao.update();
    }
}
