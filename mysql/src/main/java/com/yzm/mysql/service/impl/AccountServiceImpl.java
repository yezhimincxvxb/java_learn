package com.yzm.mysql.service.impl;

import com.yzm.mysql.entity.Account;
import com.yzm.mysql.mapper.AccountMapper;
import com.yzm.mysql.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findByName(String name) {
        return accountMapper.findByName(name);
    }

    @Override
    public boolean updateAccount(Account account) {
        return accountMapper.updateAccount(account);
    }

    @Override
    @Transactional
    public boolean transfer(String srcName, String destName, double money) {
        System.out.println("====================" + srcName + "向" + destName + "转账" + money + "元====================");

        Account srcAccount = accountMapper.findByName(srcName);
        Account destAccount = accountMapper.findByName(destName);

        srcAccount.setMoney(srcAccount.getMoney() - money);
        destAccount.setMoney(destAccount.getMoney() + money);

        System.out.println(srcName + "减少" + money + "元，成功与否：" + accountMapper.updateAccount(srcAccount));
        System.out.println(srcName + "持有金额：" + accountMapper.getMoneyById(srcAccount.getId()));
//        int i = 1 / 0;
        System.out.println(destAccount + "增加" + money + "元，成功与否：" + accountMapper.updateAccount(srcAccount));

        return true;
    }
}
