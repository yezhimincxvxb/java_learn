package com.yzm.thread.wait;


public class WaitDemo {

    public static void main(String[] args) {
//        demo01();
        demo02();
    }

    //=============================================================

    private static final WaitDemo demo = new WaitDemo();

    /**
     * 控制线程间的执行顺序
     */
    private static void demo01() {
        Thread t1 = new Thread(demo::method01, "t1");
        Thread t2 = new Thread(demo::method01_2, "t2");

        t1.start();
        t2.start();
    }

    private void method01() {
        String name = Thread.currentThread().getName();
        System.out.println("子线程：" + name + " is ready");
        try {
            Thread.sleep(10);
            synchronized (demo) {
                System.out.println("子线程：" + name + "--> 获取到锁");
                System.out.println("子线程：" + name + "--> 执行wait方法，线程挂起，等待被唤醒");
                Thread.sleep(3000);
                demo.wait();
                System.out.println("子线程：" + name + " is over");
                demo.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void method01_2() {
        String name = Thread.currentThread().getName();
        System.out.println("子线程：" + name + " is ready");
        try {
            Thread.sleep(50);
            synchronized (demo) {
                System.out.println("子线程：" + name + "--> 获取到锁");
                System.out.println("子线程：" + name + "--> 执行notifyAll方法，唤醒其他线程并调用wait方法使自己挂起");
                Thread.sleep(3000);
                demo.notifyAll();
                demo.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程：" + name + " is over");
    }

    // 定义生产最大量
    private int productCount = 0;

    private static void demo02() {
        // 创建t1线程，生产资源
        Thread t1 = new Thread(demo::demo02_product, "t1");
        // 创建t2线程，消费资源
        Thread t2 = new Thread(demo::demo02_customer, "t2");
        Thread t3 = new Thread(demo::demo02_customer, "t3");

        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }

    private synchronized void demo02_product() {
        try {
            while (true) {
                System.out.println(Thread.currentThread().getName() + ":::生产:::" + (++productCount));
                Thread.sleep(300);
                if (productCount >= 20) {
                    System.out.println("货舱已满...不必再生产");
                    notifyAll();
                    wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private synchronized void demo02_customer() {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
