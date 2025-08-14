package com.snow;

import com.snow.entity.Mobile;
import com.snow.service.MobileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.List;

public class JdbcTemplateTest {

    MobileService mobileService;
    @BeforeEach
    public void setup(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbc.xml");
        mobileService = context.getBean("mobileService", MobileService.class);
    }

    @Test
    public void testAdd() {
        Mobile mobile = new Mobile("1", "aaa", "a");
        mobileService.add(mobile);
    }

    @Test
    public void testModify() {
        Mobile mobile = new Mobile("1", "abc", "a");
        mobileService.modify(mobile);
    }

    @Test
    public void testDelete() {
        mobileService.delete("1");
    }

    @Test
    public void testFindCount() {
        Integer count = mobileService.findCount();
        System.out.println(count);
    }

    @Test
    public void testFindOne() {
        Mobile mobile = mobileService.findOne("1");
        System.out.println(mobile);
    }

    @Test
    public void testFindAll() {
        List<Mobile> mobileList = mobileService.findAll();
        System.out.println(mobileList);
    }

    @Test
    public void testBatchAdd() {
        Mobile mobile1 = new Mobile("3", "java", "a");
        Mobile mobile2 = new Mobile("4", "c++", "b");

        List<Mobile> mobiles = new ArrayList<>();
        mobiles.add(mobile1);
        mobiles.add(mobile2);

        mobileService.batchAdd(mobiles);
    }

    @Test
    public void testBatchModify() {
        Mobile mobile1 = new Mobile("3", "java_test", "a");
        Mobile mobile2 = new Mobile("4", "c++_demo", "b");

        List<Mobile> mobiles = new ArrayList<>();
        mobiles.add(mobile1);
        mobiles.add(mobile2);

        mobileService.batchModify(mobiles);
    }

    @Test
    public void testBatchDelete() {
        List<String> ids = new ArrayList<>();
        ids.add("3");
        ids.add("4");
        PlatformTransactionManager p;
        mobileService.batchDelete(ids);
    }
}
