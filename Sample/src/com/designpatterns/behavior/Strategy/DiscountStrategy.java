package com.designpatterns.behavior.Strategy;

import java.math.BigDecimal;

public interface DiscountStrategy {
    // 计算折扣额度:
    BigDecimal getDiscount(BigDecimal total);
}
