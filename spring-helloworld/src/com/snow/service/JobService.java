package com.snow.service;

import com.snow.dao.JobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// @Component
// <=> @Component(value = "jobService")
// <=> <bean id="jobService" class="com.snow.service.JobService"></bean>
@Service
public class JobService {

    @Autowired
//    @Qualifier(value="jobDaoImpl1")
    private JobDao jobDao;

    @Value(value = "abc")
    private String name;

    public void process() {
        System.out.println("JobService.process + " + name);
       jobDao.process();
    }
}
