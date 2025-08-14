package com.snow.dao;

import com.snow.entity.Mobile;

import java.util.List;

public interface MobileDao {

    void add(Mobile mobile);
    void modify(Mobile mobile);
    void delete(String id);

    Integer findCount();
    Mobile findOne(String id);
    List<Mobile> findAll();

    void batchAdd(List<Mobile> mobiles);
    void batchModify(List<Mobile> mobiles);
    void batchDelete(List<String> ids);

}
