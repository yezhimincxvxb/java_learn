package com.yzm.mysql.mapper;

import com.yzm.mysql.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    Account findByName(String name);

    boolean updateAccount(Account account);

    double getMoneyById(Integer id);
}
