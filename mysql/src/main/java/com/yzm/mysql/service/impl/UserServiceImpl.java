package com.yzm.mysql.service.impl;

import com.yzm.mysql.entity.User;
import com.yzm.mysql.mapper.UserMapper;
import com.yzm.mysql.service.UserService;
import com.yzm.mysql.service.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserService2 userService2;
    @Autowired
    private UserMapper userMapper;

    /**
     * 第一种 重点
     * 传播行为：REQUIRED(默认)
     * A有事务，则A2加入A事务；
     * A没有事务，则A2创建一个事务自己加入
     * <p>
     * 注意：(同一事务)
     * A正常A2正常，A、A2一起提交
     * A异常A2异常，A、A2一起回滚
     * A正常A2异常，A、A2一起回滚
     * A异常A2正常，A、A2一起回滚
     * <p>
     * A正常A2异常(A2捕获)，A、A2一起提交
     * A异常(A捕获)A2正常，A、A2一起提交
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void method01() {
        User user = userMapper.findById(1);
        user.setMoney(user.getMoney() - 100D);
        userMapper.updateUser(user);
        userService2.method01_1();
//        userService2.method01_2();
//        int i = 1 / 0;
    }

    /**
     * 第二种
     * 传播行为：SUPPORTS(少见)
     * B有事务，则B2加入B事务(此时跟REQUIRED是一样的)
     * B没有事务(即没有@Transactional注解)，则B2以非事务运行(此时B、B2都是非事务运行的)
     * 没有事务，任何执行到的SQL语句都会提交，不存在回滚
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void method02() {
        User user = userMapper.findById(1);
        user.setMoney(user.getMoney() - 100D);
        userMapper.updateUser(user);
        userService2.method02_1();
//        int i = 1 / 0;
    }

    /**
     * 第三种
     * 传播行为：MANDATORY(少见)
     * C有事务，则C2加入C事务；
     * C没有事务，则抛出异常
     * 与SUPPORTS的区别在于,一旦C方法没有事务,就会报错
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void method03() {
        User user = userMapper.findById(1);
        user.setMoney(user.getMoney() - 100D);
        userMapper.updateUser(user);
        userService2.method03_1();
//        int i = 1 / 0;
    }


    /**
     * 第四种 重点
     * 传播行为：REQUIRES_NEW(掌握)
     * D有事务，D事务挂起，D2创建新的事务；
     * D没有事务，D2创建新的事务；
     * <p>
     * 注意：(两个独立事务)
     * D正常D2正常：D2先提交、D再提交
     * D异常D2异常：D2先回滚、D再回滚
     * D异常D2正常：D2先提交、D再回滚
     * D正常D2异常：D未捕获D2的异常，D2异常默认往上抛，D2先回滚、D再回滚
     * D捕获了D2的异常，D2先回滚、D再提交
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void method04() {
        User user = userMapper.findById(1);
        user.setMoney(user.getMoney() - 100D);
        userMapper.updateUser(user);
//        try {
        userService2.method04_1();
//        } catch (Exception e) {
//            System.out.println("捕获异常");
//        }
//        int i = 1 / 0;
    }

    /**
     * 第五种
     * 传播行为：NOT_SUPPORTED (少见)
     * 无论E是否有事务，E2都以非事务运行
     * 若E有事务，而E2抛出异常，E未捕获，则E会回滚，因为异常是向上抛的
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void method05() {
        User user = userMapper.findById(1);
        user.setMoney(user.getMoney() - 100D);
        userMapper.updateUser(user);
        userService2.method05_1();
//        int i = 1 / 0;
    }

    /**
     * 第六种
     * 传播行为：NEVER(少见)
     * 与NOT_SUPPORTED的区别在于，一旦F有事务，则报错
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void method06() {
        User user = userMapper.findById(1);
        user.setMoney(user.getMoney() - 100D);
        userMapper.updateUser(user);
        userService2.method06_1();
//        int i = 1 / 0;
    }

    /**
     * 第七种 重点
     * 传播行为：NESTED(掌握)
     * G有事务，G2创建一个G事务的子事务并加入；
     * G没有事务，则G2就相当于REQUIRED
     * <p>
     * 注意：(G2事务嵌套在G事务中，G2是G事务的一个子事务)
     * G正常G2正常：G、G2一起提交
     * G异常G2异常：G、G2一起回滚
     * G异常G2正常：G、G2一起回滚
     * G正常G2异常：G未捕获G2的异常，G、G2一起回滚
     * G捕获了G2的异常，G2先回滚，G再提交
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void method07() {
        User user = userMapper.findById(1);
        user.setMoney(user.getMoney() - 100D);
        userMapper.updateUser(user);
        userService2.method07_1();
//        int i = 1 / 0;
    }


}
