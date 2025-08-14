package com.snow;

import com.snow.aopanno.People;
import com.snow.aopxml.Toy;
import com.snow.config.AopConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class PeopleTest {

    @Test
    public void testAopAnno() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-aopanno.xml");
        People people = context.getBean("people", People.class);
        people.add();
    }

    @Test
    public void testAopAnnoWithConfiguration() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        People people = context.getBean("people", People.class);
        people.add();
    }

    @Test
    public void testAopXml() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-aopxml.xml");
        Toy toy = context.getBean("toy", Toy.class);
        toy.buy();
    }

}