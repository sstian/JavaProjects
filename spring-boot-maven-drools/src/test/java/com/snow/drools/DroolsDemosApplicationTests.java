package com.snow.drools;

import com.snow.drools.model.Order;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试类
 */
@SpringBootTest
public class DroolsDemosApplicationTests {

    @Autowired
    private KieContainer kieContainer;

    @Test
    public void test() {
        //从KieContainer获取会话对象
        KieSession session = kieContainer.newKieSession();

        //使用实体类设置数据
        Order order = new Order();
        order.setAmount(1500);

        //通过会话对象，把order对象插入到工作内存里面
        session.insert(order);

        //激活规则
        session.fireAllRules();

        //关闭会话
        session.dispose();

        System.out.println("订单金额："+order.getAmount() + " ,增加积分："+order.getScore());

        /*
        成功匹配到规则四：1000元以上 加1000分
        订单金额：1500.0 ,增加积分：1000.0
         */
    }
}
