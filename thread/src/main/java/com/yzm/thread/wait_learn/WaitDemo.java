package com.yzm.thread.wait_learn;

public class WaitDemo {

    /**
     * 控制线程间的执行顺序
     */
    private static void demo01() {
        WaitDemo lock = new WaitDemo();

        Thread t1 = new Thread(() -> {
            System.out.println("子线程：" + Thread.currentThread().getName() + "--> 等待获取锁lock");
            try {
                Thread.sleep(3000);
                synchronized (lock) {
                    System.out.println("子线程：" + Thread.currentThread().getName() + "--> 获取到锁lock");
                    System.out.println("子线程：" + Thread.currentThread().getName() + "--> 任务完成");
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1");

        Thread t2 = new Thread(() -> {
            System.out.println("子线程：" + Thread.currentThread().getName() + "--> 等待获取锁lock");
            try {
                Thread.sleep(1000);
                synchronized (lock) {
                    System.out.println("子线程：" + Thread.currentThread().getName() + "--> 获取到锁lock");
                    System.out.println("子线程：" + Thread.currentThread().getName() + "--> 执行wait方法，线程挂起，等待被唤醒");
                    Thread.sleep(3000);
                    lock.wait();
                    System.out.println("子线程：" + Thread.currentThread().getName() + "--> 任务完成");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
//        demo01();
        demo02();
    }

    // 定义生产最大量
    private int productCount = 0;

    private synchronized void product() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::生产:::" + (++productCount));
            Thread.sleep(300);
            if (productCount >= 20) {
                System.out.println("货舱已满...不必再生产");
                notifyAll();
                wait();
            }
        }
    }

    private synchronized void customer() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::消费:::" + productCount);
            Thread.sleep(300);
            if (productCount <= 1) {
                productCount = 0;
                System.out.println("货舱已无货...无法消费");
                notifyAll();
                wait();
            } else productCount--;
        }
    }

    private static void demo02() {
        WaitDemo lock = new WaitDemo();
        // 创建t1线程，生产资源
        Thread t1 = new Thread(() -> {
            try {
                lock.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        // 创建t2线程，消费资源
        Thread t2 = new Thread(() -> {
            try {
                lock.customer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        // 创建t3线程，消费资源
        Thread t3 = new Thread(() -> {
            try {
                lock.customer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3");

        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }


}
