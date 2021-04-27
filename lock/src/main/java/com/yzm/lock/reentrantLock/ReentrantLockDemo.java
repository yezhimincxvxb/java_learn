package com.yzm.lock.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
//        demo01();
//        demo02();
//        demo03();
        demo04();
    }

    /**
     * 默认非公平锁
     * 竞争锁全凭运气
     */
    private static void demo01() {

        Thread t1 = new Thread(ReentrantLockDemo::demo01_1, "t1");
        Thread t2 = new Thread(ReentrantLockDemo::demo01_1, "t2");
        Thread t3 = new Thread(ReentrantLockDemo::demo01_1, "t3");

        t1.start();
        t2.start();
        t3.start();
    }

    private static void demo01_1() {
        try {
            //竞争锁
            lock.lock();
            System.out.println("线程：" + Thread.currentThread().getName() + "开始执行");
            Thread.sleep((long) (Math.random() * 10000));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程：" + Thread.currentThread().getName() + "任务完成");
            System.out.println("==========================");
            //释放锁
            lock.unlock();
        }
    }

    /**
     * 公平锁：等待时间长的线程优先获取锁
     * 示例：
     * 定义3个线程，每个执行2遍
     * 第二遍的执行顺序跟第一遍是一样的
     */
    private static void demo02() {

        lock = new ReentrantLock(true);

        Thread t4 = new Thread(ReentrantLockDemo::demo02_1, "t4");
        Thread t5 = new Thread(ReentrantLockDemo::demo02_1, "t5");
        Thread t6 = new Thread(ReentrantLockDemo::demo02_1, "t6");

        t4.start();
        t5.start();
        t6.start();
    }

    private static void demo02_1() {
        for (int i = 0; i < 2; i++) {
            demo01_1();
        }
    }

    /**
     * 响应中断
     * 响应中断就是一个线程获取不到锁，不会傻傻的一直等下去，ReentrantLock会给予一个中断回应
     */
    private static void demo03() {
        Lock lock = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Thread t1 = new Thread(() -> demo03_1(lock, lock2), "t1");
        Thread t2 = new Thread(() -> demo03_1(lock, lock2), "t2");

        t1.start();
        t2.start();
        t1.interrupt();
    }

    private static void demo03_1(Lock lock, Lock lock2) {
        try {
            lock.lockInterruptibly();
            Thread.sleep(3000);
            lock2.lockInterruptibly();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            lock2.unlock();
        }
        System.out.println("线程：" + Thread.currentThread().getName() + "正常获取到锁");
    }

    private static void demo04() {
        Lock lock3 = new ReentrantLock();
        Lock lock4 = new ReentrantLock();

        Thread t3 = new Thread(() -> demo04_1(lock, lock3), "t3");
        Thread t4 = new Thread(() -> demo04_1(lock, lock4), "t4");

        t3.start();
        t4.start();
    }

    private static void demo04_1(Lock lock3, Lock lock4) {
        try {
            if (lock3.tryLock(5000, TimeUnit.SECONDS))
                System.out.println("线程：" + Thread.currentThread().getName() + "获取锁lock3--成功");
             else
                System.out.println("线程：" + Thread.currentThread().getName() + "获取锁lock3--失败");
            if (lock4.tryLock(5000, TimeUnit.SECONDS))
                System.out.println("线程：" + Thread.currentThread().getName() + "获取锁lock4--成功");
            else
                System.out.println("线程：" + Thread.currentThread().getName() + "获取锁lock4--失败");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock3.unlock();
            lock4.unlock();
        }
        System.out.println("线程：" + Thread.currentThread().getName() + "释放锁");
    }

}
