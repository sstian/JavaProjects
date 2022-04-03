package com.snow.dao;

import com.snow.entity.Mobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MobileDaoImpl implements MobileDao {

    @Autowired  // DAO注入JdbcTemplate
    private JdbcTemplate jdbcTemplate;

    //  增加/修改/删除 public int update(String sql, @Nullable Object... args)
    @Override
    public void add(Mobile mobile) {
        String sql = "insert into mobile (id, name, status) values (?, ?, ?)";
        String[] args = {mobile.getId(), mobile.getName(), mobile.getStatus()};
        this.jdbcTemplate.update(sql, args);
    }

    @Override
    public void modify(Mobile mobile) {
        String sql = "update mobile set name=?, status=? where id=?";
        String[] args = {mobile.getName(), mobile.getStatus(), mobile.getId()};
        this.jdbcTemplate.update(sql, args);
    }

    @Override
    public void delete(String id) {
        String sql = "delete from mobile where id=?";
        this.jdbcTemplate.update(sql, id);
    }

    // 查询返回值
    // public <T> T queryForObject(String sql, Class<T> requiredType)
    @Override
    public Integer findCount() {
        String sql = "select count(1) from mobile";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // 查询单个对象
    // 接口RowMapper针对不同场景返回不同类型数据，使用实现类完成数据封装
    // public <T> T queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
    @Override
    public Mobile findOne(String id) {
        String sql = "select * from mobile where id=?";
        return this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Mobile>(Mobile.class), id);
    }

    // 查询对象集合
    // public <T> List<T> query(String sql, RowMapper<T> rowMapper)
    @Override
    public List<Mobile> findAll() {
        String sql = "select * from mobile";
        return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<Mobile>(Mobile.class));

    }

    // 批量操作-添加/修改/删除
    // public int[] batchUpdate(String sql, List<Object[]> batchArgs)
    @Override
    public void batchAdd(List<Mobile> mobiles) {
        String sql = "insert into mobile (id, name, status) values (?, ?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (Mobile mobile : mobiles) {
            Object[] obj = {mobile.getId(), mobile.getName(), mobile.getStatus()};
            batchArgs.add(obj);
        }
        this.jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    @Override
    public void batchModify(List<Mobile> mobiles) {
        String sql = "update mobile set name=?, status=? where id=?";
        List<Object[]> batchArgs = new ArrayList<>();
        for (Mobile mobile : mobiles) {
            Object[] obj = {mobile.getName(), mobile.getStatus(), mobile.getId()};
            batchArgs.add(obj);
        }
        this.jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    @Override
    public void batchDelete(List<String> ids) {
        String sql = "delete from mobile where id=?";
        List<Object[]> batchArgs = new ArrayList<>();
        for (String id : ids) {
            Object[] obj = {id};
            batchArgs.add(obj);
        }
        this.jdbcTemplate.batchUpdate(sql, batchArgs);
    }
}
