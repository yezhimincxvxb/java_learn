package com.yzm.thread.sync;

public class SynchronizedDemo {

    public static void main(String[] args) throws InterruptedException {
//        demo01();
//        demo02();
        demo02_2();
//        demo02_3();
//        demo03();
//        demo04();
//        demo05();
    }

    //=======================================================================================================

    //共享变量
    private static int inc = 0;
    private static final SynchronizedDemo demo = new SynchronizedDemo();

    /**
     * 不加 synchronized 处理
     * 多次测试，不能保证最终结果一致
     */
    private static void demo01() {
        for (int i = 1; i <= 2; i++) {
            new Thread(demo::method01, "t" + i).start();
        }
        while (Thread.activeCount() > 2) Thread.yield();
        System.out.println("inc =" + inc);
    }

    private void method01() {
        common();
    }

    private static void common() {
        System.out.println("线程：" + Thread.currentThread().getName() + " is ready >> " + inc);
        try {
            Thread.sleep(10);
            for (int i = 0; i < 1000; i++) {
                inc++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("线程：" + Thread.currentThread().getName() + " is over >> " + inc);
    }

    //=======================================================================================================

    /**
     * synchronized作用于实例方法
     * 当两个线程同时对一个对象的一个方法进行操作，只有一个线程能够抢到锁。因为一个对象只有一把锁，一个线程获取了该对象的锁之后，其他线程无法获取该对象的锁，
     * <p>
     * 2个线程一定是一个先完全执行完，另一个线程才会开始执行
     * 能保证最终结果一致
     */
    private static void demo02() {
        for (int i = 1; i <= 2; i++) {
            new Thread(demo::method02, "t" + i).start();
        }
        while (Thread.activeCount() > 2) Thread.yield();
        System.out.println("inc =" + inc);
    }

    private synchronized void method02() {
        common();
    }

    //=======================================================================================================

    /**
     * 一个线程获取了该对象的锁之后，其他线程可以访问其他非synchronized实例方法(method01 非synchronized，同时交替运行)
     * 一个线程获取了该对象的锁之后，其他线程无法访问其他synchronized实例方法(method02 synchronized)
     */
    private static void demo02_2() {
//        new Thread(demo::method01, "t1").start();
        new Thread(demo::method02, "t2").start();
        new Thread(demo::method02, "t3").start();

        while (Thread.activeCount() > 2) Thread.yield();
        System.out.println("inc =" + inc);
    }

    //=======================================================================================================

    /**
     * 多个线程作用于不同的对象
     * 因为两个线程作用于不同的对象，获得的是不同的锁，所以互相并不影响
     */
    private static void demo02_3() {
        SynchronizedDemo demo2 = new SynchronizedDemo();
        new Thread(demo::method02, "t1").start();
        new Thread(demo2::method02, "t2").start();

        while (Thread.activeCount() > 2) Thread.yield();
        System.out.println("inc =" + inc);
    }

    //=======================================================================================================

    /**
     * synchronized作用于静态方法
     */
    private static void demo03() {
        new Thread(SynchronizedDemo::method03, "t1").start();
        new Thread(SynchronizedDemo::method03, "t2").start();

        while (Thread.activeCount() > 2) Thread.yield();
        System.out.println("inc =" + inc);
    }

    private static synchronized void method03() {
        common();
    }

    //=======================================================================================================

    /**
     * synchronized作用于代码块
     */
    private static void demo04() {
        new Thread(demo::method04, "t1").start();
        new Thread(demo::method04, "t2").start();

        while (Thread.activeCount() > 2) Thread.yield();
        System.out.println("inc =" + inc);
    }

    private void method04() {
        System.out.println("线程：" + Thread.currentThread().getName() + " is ready >> " + inc);
        try {
            Thread.sleep(10);
            synchronized (this) {
                for (int i = 0; i < 1000; i++) {
                    inc++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("线程：" + Thread.currentThread().getName() + " is over >> " + inc);
    }

    //=======================================================================================================

    /**
     * synchronized 的可重入性
     * <p>
     * 在获取当前实例对象锁后进入synchronized代码块执行同步代码，并在代码块中调用了当前实例对象的另外一个synchronized方法，
     * 再次请求当前实例锁时，将被允许，进而执行方法体代码，这就是重入锁最直接的体现，需要特别注意另外一种情况，当子类继承父类时，
     * 子类也是可以通过可重入锁调用父类的同步方法。注意由于synchronized是基于monitor实现的，因此每次重入，monitor中的计数器仍会加1。
     */
    private static void demo05() throws InterruptedException {
        Thread t1 = new Thread(demo::method05, "t1");
        t1.start();
        t1.join();
    }

    private synchronized void method05() {
        System.out.println("线程：" + Thread.currentThread().getName() + " 执行了method05");
        method05_2();
    }

    private synchronized void method05_2() {
        System.out.println("线程：" + Thread.currentThread().getName() + " 执行了method05_2");
    }

}
