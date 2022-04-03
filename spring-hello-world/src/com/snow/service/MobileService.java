package com.snow.service;

import com.snow.dao.MobileDao;
import com.snow.entity.Mobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobileService {

    @Autowired  // Service注入DAO
    private MobileDao mobileDao;

    public void add(Mobile mobile) {
        this.mobileDao.add(mobile);
    }

    public void modify(Mobile mobile) {
        this.mobileDao.modify(mobile);
    }

    public void delete(String id) {
        this.mobileDao.delete(id);
    }

    public Integer findCount() {
        return this.mobileDao.findCount();
    }

    public Mobile findOne(String id) {
        return this.mobileDao.findOne(id);
    }

    public List<Mobile> findAll() {
        return this.mobileDao.findAll();
    }

    public void batchAdd(List<Mobile> mobiles) {
        this.mobileDao.batchAdd(mobiles);
    }

    public void batchModify(List<Mobile> mobiles) {
        this.mobileDao.batchModify(mobiles);
    }

    public void batchDelete(List<String> ids) {
        this.mobileDao.batchDelete(ids);
    }
}
