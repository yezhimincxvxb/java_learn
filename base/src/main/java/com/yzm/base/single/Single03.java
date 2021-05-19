package com.yzm.base.single;

/**
 * 懒汉式 + 双重锁
 */
public class Single03 {

    private static volatile Single03 INSTANCE;

    private Single03() {
    }

    public static Single03 getInstance() {
        if (INSTANCE == null) {
            synchronized (Single03.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Single03();
                }
            }
        }
        return INSTANCE;
    }
}
