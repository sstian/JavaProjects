package com.snow;

import com.snow.bean.Emp;
import com.snow.bean.Employee;
import com.snow.bean.Order;
import com.snow.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class UserTest {

    @Test
    public void testUser() {
        // 加载Spring配置文件
//        BeanFactory context2 = new ClassPathXmlApplicationContext("bean.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 获取配置创建的对象
//        User user = context.getBean("user", User.class);
        User user = context.getBean("user", User.class);
        user.add();
        user.test();
    }

    @Test
    public void testBook() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Book book = context.getBean("book", Book.class);
        book.test();
    }

    @Test
    public void testUserService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.update();
    }

    @Test
    public void testEmployee() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Employee employee = context.getBean("employee", Employee.class);
        System.out.println(employee.toString());
    }

    @Test
    public void testStudent() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Student student = context.getBean("student", Student.class);
        System.out.println(student.toString());
    }

    @Test
    public void testMyBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Book book = context.getBean("myBean", Book.class);
        System.out.println(book.toString());
    }

    @Test
    public void testOrder() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-post.xml");
        Order order = context.getBean("order", Order.class);
        System.out.println("6. use bean");
        System.out.println(order.toString());
        context.close();
    }

    @Test
    public void testEmp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp.toString());
    }
}