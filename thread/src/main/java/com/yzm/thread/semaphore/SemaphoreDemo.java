package com.yzm.thread.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    public static void main(String[] args) {
        demo01();
    }

    //==================================================================

    private static final Semaphore semaphore = new Semaphore(2);

    private static void demo01() {
        for (int i = 1; i <= 10; i++) {
            new Thread(SemaphoreDemo::method01, "t" + i).start();
        }
    }

    private static void method01() {
        try {
            semaphore.acquire(2);
            System.out.println("线程:" + Thread.currentThread().getName() + "获得许可");
            TimeUnit.SECONDS.sleep(2);
            semaphore.release(2);
            System.out.println("线程:" + Thread.currentThread().getName() + "释放许可");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
