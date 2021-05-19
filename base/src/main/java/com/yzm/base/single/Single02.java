package com.yzm.base.single;

/**
 * 懒汉式
 */
public class Single02 {

    private static Single02 INSTANCE;

    private Single02() {
    }

    public static Single02 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Single02();
        }
        return INSTANCE;
    }
}
