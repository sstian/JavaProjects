package com.snow.service;

import com.snow.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ,timeout = 10,readOnly = false)
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public void account() {
        // bank account: lucy - 100, marry + 100
        accountDao.reduceMoney();
        int a = 10 / 0;  // 模拟异常
        accountDao.addMoney();
    }
}
