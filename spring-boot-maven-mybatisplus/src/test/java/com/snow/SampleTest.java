package com.snow;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snow.entity.User;
import com.snow.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);
    }
/*
Creating a new SqlSession
SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7eb27768] was not registered for synchronization because synchronization is not active
JDBC Connection [HikariProxyConnection@1953069155 wrapping org.sqlite.jdbc4.JDBC4Connection@ed91d8d] will not be managed by Spring
==>  Preparing: SELECT id,name,age,email FROM `user`
==> Parameters:
<==    Columns: id, name, age, email
<==        Row: 1, Jone, 18, test1@baomidou.com
<==        Row: 2, Jack, 20, test2@baomidou.com
<==        Row: 3, Tom, 28, test3@baomidou.com
<==        Row: 4, Sandy, 21, test4@baomidou.com
<==        Row: 5, Billie, 24, test5@baomidou.com
<==      Total: 5
Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7eb27768]
User(id=1, name=Jone, age=18, email=test1@baomidou.com)
User(id=2, name=Jack, age=20, email=test2@baomidou.com)
User(id=3, name=Tom, age=28, email=test3@baomidou.com)
User(id=4, name=Sandy, age=21, email=test4@baomidou.com)
User(id=5, name=Billie, age=24, email=test5@baomidou.com)
 */

    @Test
    public void testSelectMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Jack");
        map.put("age", 20);
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }
/*
Creating a new SqlSession
SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@5520f675] was not registered for synchronization because synchronization is not active
JDBC Connection [HikariProxyConnection@1470523336 wrapping org.sqlite.jdbc4.JDBC4Connection@1a6dc589] will not be managed by Spring
==>  Preparing: SELECT id,name,age,email FROM `user` WHERE (name = ? AND age = ?)
==> Parameters: Jack(String), 20(Integer)
<==    Columns: id, name, age, email
<==        Row: 2, Jack, 20, test2@baomidou.com
<==      Total: 1
Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@5520f675]
User(id=2, name=Jack, age=20, email=test2@baomidou.com)
 */
    @Test
    public void testWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "J").ge("age", 18);

        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }
/*
Creating a new SqlSession
SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@1b7554d4] was not registered for synchronization because synchronization is not active
JDBC Connection [HikariProxyConnection@1470523336 wrapping org.sqlite.jdbc4.JDBC4Connection@3b48e183] will not be managed by Spring
==>  Preparing: SELECT id,name,age,email FROM `user` WHERE (name LIKE ? AND age >= ?)
==> Parameters: %J%(String), 18(Integer)
<==    Columns: id, name, age, email
<==        Row: 1, Jone, 18, test1@baomidou.com
<==        Row: 2, Jack, 20, test2@baomidou.com
<==      Total: 2
Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@1b7554d4]
User(id=1, name=Jone, age=18, email=test1@baomidou.com)
User(id=2, name=Jack, age=20, email=test2@baomidou.com)
 */
}