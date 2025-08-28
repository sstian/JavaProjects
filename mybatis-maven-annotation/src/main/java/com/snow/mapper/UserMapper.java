package com.snow.mapper;

import com.snow.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Select("select * from User")
    List<User> getUsers();

    @Select("select * from User where id = #{id}")
    User getUserById(int id);

    // 模糊查询
    @Select("select * from user where name like concat('%', #{value}, '%')")
    List<User> getUserByLike(@Param("value") String value);

    @Insert("insert into user(id, name, pwd) values (#{id}, #{name}, #{pwd})")
    int addUser(User user);

    @Delete("delete from user where id = #{id}")
    int deleteUser(int id);

    // 修改用户
    @Update("update user set name = #{name},pwd = #{pwd} where id = #{id}")
    int updateUser(User user);
    // 修改用户。使用万能的Map
    @Update("update user set pwd = #{userpwd} where id = #{userid}")
    int updateUserByMap(Map<String, Object> map);
}
