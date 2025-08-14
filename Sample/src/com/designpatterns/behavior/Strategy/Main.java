package com.designpatterns.behavior.Strategy;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        DiscountContext ctx = new DiscountContext();

        // 默认使用普通会员折扣:
        BigDecimal pay1 = ctx.calculatePrice(BigDecimal.valueOf(105));
        System.out.println(pay1);

        // 使用满减折扣:
        ctx.setStrategy(new OverDiscountStrategy());
        BigDecimal pay2 = ctx.calculatePrice(BigDecimal.valueOf(105));
        System.out.println(pay2);

    }
}
/*
94.50
85.00
 */

/*
策略
定义一系列的算法，把它们一个个封装起来，并且使它们可相互替换。本模式使得算法可独立于使用它的客户而变化。

┌───────────────┐      ┌─────────────────┐
│DiscountContext│─ ─ ─▶│DiscountStrategy │
└───────────────┘      └─────────────────┘
                                ▲
                                │ ┌─────────────────────┐
                                ├─│UserDiscountStrategy │
                                │ └─────────────────────┘
                                │ ┌─────────────────────┐
                                ├─│PrimeDiscountStrategy│
                                │ └─────────────────────┘
                                │ ┌─────────────────────┐
                                └─│OverDiscountStrategy │
                                  └─────────────────────┘
 */