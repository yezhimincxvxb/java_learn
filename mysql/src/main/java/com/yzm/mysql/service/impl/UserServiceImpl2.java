package com.yzm.mysql.service.impl;

import com.yzm.mysql.entity.User;
import com.yzm.mysql.mapper.UserMapper;
import com.yzm.mysql.service.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl2 implements UserService2 {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void method01_1() {
        User user = userMapper.findById(2);
        user.setMoney(user.getMoney() + 100D);
        userMapper.updateUser(user);
//        int i = 1 / 0;
//        try {
//            method01_2();
//        } catch (Exception e) {
//            System.out.println("捕获了异常");
//        }
    }

    @Override
    public void method01_2() {
        User user = userMapper.findById(2);
        user.setMoney(user.getMoney() + 100D);
        userMapper.updateUser(user);
        int i = 1 / 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void method02_1() {
        User user = userMapper.findById(2);
        user.setMoney(user.getMoney() + 100D);
        userMapper.updateUser(user);
//        int i = 1 / 0;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
    public void method03_1() {
        System.out.println("外层没有事务，内层直接报错不执行这段话");
        User user = userMapper.findById(2);
        user.setMoney(user.getMoney() + 100D);
        userMapper.updateUser(user);
//        int i = 1 / 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void method04_1() {
        User user = userMapper.findById(2);
        user.setMoney(user.getMoney() + 100D);
        userMapper.updateUser(user);
//        int i = 1 / 0;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public void method05_1() {
        User user = userMapper.findById(2);
        user.setMoney(user.getMoney() + 100D);
        userMapper.updateUser(user);
//        int i = 1 / 0;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
    public void method06_1() {
        System.out.println("外层有事务，内层直接报错不执行这段话");
        User user = userMapper.findById(2);
        user.setMoney(user.getMoney() + 100D);
        userMapper.updateUser(user);
//        int i = 1 / 0;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void method07_1() {
        User user = userMapper.findById(2);
        user.setMoney(user.getMoney() + 100D);
        userMapper.updateUser(user);
//        int i = 1 / 0;
    }

}
