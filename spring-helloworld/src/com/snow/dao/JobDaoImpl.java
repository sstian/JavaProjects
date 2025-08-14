package com.snow.dao;

import org.springframework.stereotype.Repository;

@Repository
//@Repository(value="jobDaoImpl1")
public class JobDaoImpl implements JobDao {

    @Override
    public void process() {
        System.out.println("JobDaoImpl.process");
    }
}
