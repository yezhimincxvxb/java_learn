package com.yzm.base.transient_;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class TransientDemo {

    private static void demo01() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");

        System.out.println("序列化前：=========================");
        System.out.println("username: " + user.getUsername());
        System.err.println("password: " + user.getPassword());

        //序列化
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("classpath:user.txt"));
            os.writeObject(user); // 将User对象写进文件
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //反序列化
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("C:/user.txt"));
            user = (User) is.readObject(); // 从流中读取User的数据
            is.close();

            System.out.println("序列化后：=========================");
            System.out.println("username: " + user.getUsername());
            System.err.println("password: " + user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void demo02() {
        User2 user = new User2();
        user.setUsername("admin");
        user.setPassword("123456");

        System.out.println("序列化前：=========================");
        System.out.println("username: " + user.getUsername());
        System.err.println("password: " + user.getPassword());

        //序列化
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("C:/user2.txt"));
            os.writeObject(user); // 将User对象写进文件
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //反序列化
        try {
            User2.username = "root";

            ObjectInputStream is = new ObjectInputStream(new FileInputStream("C:/user2.txt"));
            user = (User2) is.readObject(); // 从流中读取User的数据
            is.close();

            System.out.println("序列化后：=========================");
            System.out.println("username: " + user.getUsername());
            System.err.println("password: " + user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        demo01();
//        demo02();
    }



}
