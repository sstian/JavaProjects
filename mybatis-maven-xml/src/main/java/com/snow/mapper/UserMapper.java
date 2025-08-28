package com.snow.mapper;

import com.snow.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> getUsers();

    User getUserById(int id);

    // 模糊查询
    List<User> getUserByLike(@Param("value") String value);

    int addUser(User user);

    int deleteUser(int id);

    // 修改用户
    int updateUser(User user);
    // 修改用户。使用万能的Map
    int updateUserByMap(Map<String, Object> map);
}
