package com.snow;

import com.snow.config.SpringConfig;
import com.snow.service.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JobTest {

    @Test
    public void testJobService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-annotation.xml");
        JobService jobService = context.getBean("jobService", JobService.class);
        jobService.process();
    }

    @Test
    public void testJobServiceWithConfiguration() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        JobService jobService = context.getBean("jobService", JobService.class);
        jobService.process();
    }
}
