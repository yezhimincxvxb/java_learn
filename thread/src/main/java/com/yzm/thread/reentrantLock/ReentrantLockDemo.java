package com.yzm.thread.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    /**
     * 控制线程间的执行顺序
     */
    private static void demo01() {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> getRunnable(lock, 3000), "t1");
        Thread t2 = new Thread(() -> getRunnable(lock, 1000), "t2");

        t1.start();
        t2.start();
    }

    private static void getRunnable(Lock lock, long time) {
        try {
            Thread.sleep(time);
            lock.lock();
            System.out.println("线程：" + Thread.currentThread().getName() + "获取锁");
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("线程：" + Thread.currentThread().getName() + "完成任务。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("线程：" + Thread.currentThread().getName() + "释放锁");
        }
    }

    public static void main(String[] args) {
        demo01();
//        demo02();
//        demo11();

    }

    /**
     *
     */
    private static void demo02() {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();
            System.out.println("get");
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("release");
            }
        });

        Thread t2 = new Thread(() -> {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (true) {
                if (lock.tryLock()) {
                    System.out.println("get success");
                    lock.unlock();
                    break;
                } else {
                    System.out.println("get faile ... ");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();

    }

    private static void demo03() {
        Lock lock = new ReentrantLock();
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                lock.lock();
                try {
                    Thread.sleep(10000);
                    System.out.println("goon ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        });

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    System.out.println("get ...");
                    lock.unlock();
                } catch (InterruptedException e1) {
                    //e1.printStackTrace();

                    System.out.println("interrupt ... ");
                }

            }

        });

        t.start();
        t1.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("to interrupt ");
        t1.interrupt();

    }

    private static void demo04() {
        Lock lock = new ReentrantLock();
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                lock.lock();
                try {
                    Thread.sleep(10000);
                    System.out.println("goon ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        });

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    if (lock.tryLock(5, TimeUnit.SECONDS)) {
                        System.out.println("get ...");
                        lock.unlock();
                    } else {
                        System.out.println("have not get  ...");
                    }
                } catch (InterruptedException e1) {
                    //e1.printStackTrace();

                    System.out.println("interrupt ... ");
                }

            }

        });

        t.start();
        t1.start();

    }

    private static void demo11() {
        int a = 1;
        Integer b = 1;
        Integer c = new Integer(1);
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(b == c);

        Integer d = Integer.valueOf(1);
        System.out.println(b == d);

        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
        System.out.println(f1 == f2);
        System.out.println(f3 == f4);
    }
}
