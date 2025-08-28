package com.snow.mapper;

import com.snow.entity.User;
import com.snow.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    private SqlSession session;
    private UserMapper mapper;

    @BeforeEach
    void setUp() {
        this.session = MybatisUtil.getSession();
        this.mapper = session.getMapper(UserMapper.class);
    }

    @AfterEach
    void tearDown() {
        this.session.close();
    }

    private void printList(List<User> users) {
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void getUsers() {
        List<User> users = this.mapper.getUsers();
        this.printList(users);
    }

    @Test
    void getUserById() {
        User user = this.mapper.getUserById(1);
        System.out.println(user);
    }

    @Test
    void getUserByLike() {
        List<User> users = this.mapper.getUserByLike("李");
        this.printList(users);
    }

    @Test
    void addUser() {
        User user4 = new User(40, "李刚", "haohaiyo");
        User user5 = new User(50, "王五", "helloword");

        int result4 = this.mapper.addUser(user4);
        int result5 = this.mapper.addUser(user5);
        System.out.println(result4);
        System.out.println(result5);
    }

    @Test
    void deleteUser() {
        int result = this.mapper.deleteUser(50);
        System.out.println(result);
    }

    @Test
    void updateUser() {
        User newUser = new User(40, "李铁", "nilaiya");
        int result = this.mapper.updateUser(newUser);
        System.out.println(result);
    }

    @Test
    void updateUserByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("userid", 40);
        map.put("userpwd", "nishishei");

        int result = this.mapper.updateUserByMap(map);
        System.out.println(result);
    }

}