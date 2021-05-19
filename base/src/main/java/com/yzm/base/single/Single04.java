package com.yzm.base.single;

/**
 * 静态内部类
 */
public class Single04 {

    private Single04() {
    }

    public static Single04 getInstance() {
        return SingleInter.INSTANCE;
    }

    private static class SingleInter {
        private static Single04 INSTANCE = new Single04();
    }

}
