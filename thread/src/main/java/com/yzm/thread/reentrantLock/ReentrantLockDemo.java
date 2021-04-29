package com.yzm.thread.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {
//        demo01();
//        demo02();
//        demo03();
        demo04();
    }

    //=========================================================================

    private static final ReentrantLockDemo demo = new ReentrantLockDemo();
    private static final Lock lock = new ReentrantLock(); //非公平锁
    private static final Lock fair = new ReentrantLock(true); //公平锁

    /**
     * lock()：是平常使用得最多的一个方法，就是用来获取锁。如果锁已被其他线程获取，则进行等待。
     * unLock()：是用来释放锁的
     */
    private static void demo01() {
        Thread t1 = new Thread(demo::method01, "t1");
        Thread t2 = new Thread(demo::method01, "t2");

        t1.start();
        t2.start();
    }

    private void method01() {
        System.out.println("线程：" + Thread.currentThread().getName() + " is ready");
        try {
            Thread.sleep(100);
            lock.lock();
            System.out.println("线程：" + Thread.currentThread().getName() + " 获取锁");
            System.out.println("线程：" + Thread.currentThread().getName() + " 执行任务。。。");
            Thread.sleep((long) (Math.random() * 2000));
            System.out.println("线程：" + Thread.currentThread().getName() + " 完成任务。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程：" + Thread.currentThread().getName() + " 释放锁");
            lock.unlock();
        }
        System.out.println("线程：" + Thread.currentThread().getName() + " is over");
    }


    /**
     * tryLock()：是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），则返回false，
     * 也就说这个方法无论如何都会立即返回。在拿不到锁时不会一直在那等待。
     * tryLock(long time, TimeUnit unit)：和tryLock()方法是类似的，只不过区别在于这个方法在拿不到锁时会等待一定的时间，
     * 在时间期限之内如果还拿不到锁，就返回false。如果如果一开始拿到锁或者在等待期间内拿到了锁，则返回true。
     */
    private static void demo02() {
        Thread t1 = new Thread(demo::method01, "t1");
        Thread t2 = new Thread(demo::method02, "t2");

        t1.start();
        t2.start();

    }

    private void method02() {
        System.out.println("线程：" + Thread.currentThread().getName() + " is ready");
        try {
            Thread.sleep(1000);
            while (true) {
                if (lock.tryLock()) {
                    System.out.println("线程：" + Thread.currentThread().getName() + " 获取锁");
                    System.out.println("线程：" + Thread.currentThread().getName() + " 执行任务。。。");
                    Thread.sleep((long) (Math.random() * 2000));
                    System.out.println("线程：" + Thread.currentThread().getName() + " 完成任务。。。");
                    break;
                } else {
                    System.out.println("线程：" + Thread.currentThread().getName() + " 获取锁失败，先做点别");
                    Thread.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程：" + Thread.currentThread().getName() + " 释放锁");
            lock.unlock();
        }
        System.out.println("线程：" + Thread.currentThread().getName() + " is over");
    }

    /**
     * lockInterruptibly()：当通过这个方法去获取锁时，如果线程正在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态。也就使说，
     * 当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，假若此时线程A获取到了锁，而线程B只有在等待，那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程。
     * <p>
     * 由于lockInterruptibly()的声明中抛出了异常，所以lock.lockInterruptibly()必须放在try块中或者
     * 在调用lockInterruptibly()的方法外声明抛出InterruptedException。
     */
    private static void demo03() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                demo.method03_1();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程：" + Thread.currentThread().getName() + " 响应中断");
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                demo.method03_2();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程：" + Thread.currentThread().getName() + " 响应中断");
            }
        }, "t2");

        t1.start();
        t2.start();

        Thread.sleep(2000);
        System.out.println("==============紧急中断==============");
        t2.interrupt();
    }

    private void method03_1() throws InterruptedException {
        System.out.println("线程：" + Thread.currentThread().getName() + " is ready");
        Thread.sleep(100);
        lock.lockInterruptibly(); //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println("线程：" + Thread.currentThread().getName() + " 获取锁");
            System.out.println("线程：" + Thread.currentThread().getName() + " 执行任务。。。");
            Thread.sleep(5000);
            System.out.println("线程：" + Thread.currentThread().getName() + " 完成任务。。。");
        } finally {
            System.out.println("线程：" + Thread.currentThread().getName() + " 释放锁");
            lock.unlock();
        }
        System.out.println("线程：" + Thread.currentThread().getName() + " is over");
    }

    private void method03_2() throws InterruptedException {
        System.out.println("线程：" + Thread.currentThread().getName() + " is ready");
        Thread.sleep(1000);
        lock.lockInterruptibly();
        try {
            System.out.println("线程：" + Thread.currentThread().getName() + " 获取锁");
            System.out.println("线程：" + Thread.currentThread().getName() + " 执行任务。。。");
            System.out.println("线程：" + Thread.currentThread().getName() + " 完成任务。。。");
        } finally {
            System.out.println("线程：" + Thread.currentThread().getName() + " 释放锁");
            lock.unlock();
        }
        System.out.println("线程：" + Thread.currentThread().getName() + " is over");
    }

    /**
     * 公平锁：等待时间长的线程优先获取锁
     */
    private static void demo04() {
        new Thread(demo::method04, "t1").start();
        new Thread(demo::method04, "t2").start();
        new Thread(demo::method04, "t3").start();
    }

    private void method04() {
        System.out.println("线程：" + Thread.currentThread().getName() + " is ready");
        for (int i = 1; i <= 2; i++) {
            try {
                Thread.sleep(10);
                fair.lock();
                System.out.println("============== 线程：" + Thread.currentThread().getName() + " 获取锁 ============== " + i);
                System.out.println("线程：" + Thread.currentThread().getName() + " 执行任务。。。");
                Thread.sleep((long) (Math.random() * 3000));
                System.out.println("线程：" + Thread.currentThread().getName() + " 完成任务。。。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("============== 线程：" + Thread.currentThread().getName() + " 释放锁 ============== " + i);
                fair.unlock();
            }
        }
    }
}
