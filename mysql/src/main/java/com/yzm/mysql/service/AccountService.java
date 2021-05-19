package com.yzm.mysql.service;

import com.yzm.mysql.entity.Account;

public interface AccountService {
    Account findByName(String name);

    boolean updateAccount(Account account);

    boolean transfer(String srcName, String destName, double money);

}
