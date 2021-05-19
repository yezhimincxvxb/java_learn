package com.yzm.mysql;

import com.yzm.mysql.service.AccountService;
import com.yzm.mysql.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MysqlApplicationTests {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;

    /**
     * 通过转账校验事务是否有效
     */
    @Test
    void transfer() {
        accountService.transfer("张三", "李四", 200D);
    }

    /**
     * 事务传播途径：REQUIRED(默认)
     */
    @Test
    void method01() {
        userService.method01();
    }

    /**
     * 事务传播途径：SUPPORTS
     */
    @Test
    void method02() {
        userService.method02();
    }

    /**
     * 事务传播途径：MANDATORY
     */
    @Test
    void method03() {
        userService.method03();
    }

    /**
     * 事务传播途径：REQUIRES_NEW
     */
    @Test
    void method04() {
        userService.method04();
    }

    /**
     * 事务传播途径：NOT_SUPPORTED
     */
    @Test
    void method05() {
        userService.method05();
    }

    /**
     * 事务传播途径：NEVER
     */
    @Test
    void method06() {
        userService.method06();
    }

    /**
     * 事务传播途径：NESTED
     */
    @Test
    void method07() {
        userService.method07();
    }

}
