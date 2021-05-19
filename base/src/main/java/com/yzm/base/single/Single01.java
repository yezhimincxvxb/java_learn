package com.yzm.base.single;

/**
 * 饿汉式
 */
public class Single01 {

    private static Single01 INSTANCE = new Single01();

    private Single01() {
    }

    public static Single01 getInstance() {
        return INSTANCE;
    }
}
