package com.yzm.thread.volatile_learn;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
@NoArgsConstructor
public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {
//        demo01();
//        demo02();
//        demo02_2();
//        demo02_3();
//        demo02_4();
    }

    public VolatileDemo(int a, int b) {
        this.a = a;
        this.b = b;
    }

    private int a;
    private int b;
    private static int c;

    private void method01() {
        try {
            Thread.sleep(1);
            a = 2;
            b = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void method01_2() {
        try {
            Thread.sleep(1);
            System.out.println("线程：" + Thread.currentThread().getName() + " ==> a=" + a + " and b=" + b);
            if (b > a) {
                System.out.println("++++++++++++++++++++++++++++++++线程：" + Thread.currentThread().getName() + " ==> a=" + a + " and b=" + b);
                c++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看--指令重排序现象
     * t1线程对a，b进行赋值，正常执行流程，a永远比b大，c变量就一直是0
     * 若发生了 指令重排序 即先执行了b = 1，而a还是0；t2或t3线程比较b>a，c++
     */
    private static void demo01() {
        for (int i = 1; i <= 8000; i++) {
            VolatileDemo demo = new VolatileDemo(0, 0);
            new Thread(demo::method01, "t1").start();
            new Thread(demo::method01_2, "t2").start();
            new Thread(demo::method01_2, "t3").start();

            //等待t1、t2、t3线程执行完毕，再执行main线程
            while (Thread.activeCount() > 2) Thread.yield();
            System.out.println(i + "：===============================");
        }
        System.out.println(VolatileDemo.c);
    }

    //=============================================================================================

    private int inc;

    /**
     * volatile不适合复合操作
     * 参数inc 加不加上 volatile 都是一样的：不能确保最终结果一致
     */
    private static void demo02() throws InterruptedException {
        System.out.println("测试==volatile不适合复合操作示例");
        CountDownLatch count = new CountDownLatch(1);
        VolatileDemo demo = new VolatileDemo();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> demo.method02(count), "t" + i).start();
        }

        //让子线程同时启动
        Thread.sleep(10);
        count.countDown();

        //运行main方法会启动2个默认线程，其余的是手动启动的线程
        //等待其余线程跑完在跑main线程
        while (Thread.activeCount() > 2) Thread.yield();
        System.out.println(demo.inc);
    }

    private void method02(CountDownLatch count) {
        System.out.println("线程：" + Thread.currentThread().getName() + " is ready >> " + inc);
        try {
            count.await();
            for (int i = 0; i < 1000; i++) {
                inc++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("线程：" + Thread.currentThread().getName() + " is over >> " + inc);
    }

    /**
     * synchronized
     * 参数inc 不加 volatile
     */
    private static void demo02_2() throws InterruptedException {
        System.out.println("测试==synchronized解决复合操作示例");
        CountDownLatch count = new CountDownLatch(1);
        VolatileDemo demo = new VolatileDemo();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> demo.method02_2(count), "t" + i).start();
        }

        Thread.sleep(10);
        count.countDown();

        while (Thread.activeCount() > 2) Thread.yield();
        System.out.println(demo.inc);
    }

    private void method02_2(CountDownLatch count) {
        System.out.println("线程：" + Thread.currentThread().getName() + " is ready >> " + inc);
        try {
            count.await();
            synchronized (VolatileDemo.class) {
                for (int i = 0; i < 1000; i++) {
                    inc++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("线程：" + Thread.currentThread().getName() + " is over >> " + inc);
    }

    /**
     * ReentrantLock
     * 参数inc 不加 volatile
     */
    private static void demo02_3() throws InterruptedException {
        System.out.println("测试==ReentrantLock解决复合操作示例");
        CountDownLatch count = new CountDownLatch(1);
        Lock lock = new ReentrantLock();
        VolatileDemo demo = new VolatileDemo();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> demo.method02_3(count, lock), "t" + i).start();
        }

        Thread.sleep(10);
        count.countDown();

        while (Thread.activeCount() > 2) Thread.yield();
        System.out.println(demo.inc);
    }

    private void method02_3(CountDownLatch count, Lock lock) {
        System.out.println("线程：" + Thread.currentThread().getName() + " is ready >> " + inc);
        try {
            count.await();
            lock.lock();
            for (int i = 0; i < 1000; i++) {
                inc++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println("线程：" + Thread.currentThread().getName() + " is over >> " + inc);
    }

    /**
     * AtomicInteger
     * 参数inc 不加 volatile
     */
    private AtomicInteger atomicInc = new AtomicInteger();

    private static void demo02_4() throws InterruptedException {
        System.out.println("测试==AtomicInteger解决复合操作示例");
        CountDownLatch count = new CountDownLatch(1);
        VolatileDemo demo = new VolatileDemo();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> demo.method02_4(count), "t" + i).start();
        }
        Thread.sleep(10);
        count.countDown();
        while (Thread.activeCount() > 2) Thread.yield();
        System.out.println(demo.atomicInc.get());
    }

    private void method02_4(CountDownLatch count) {
        System.out.println("线程：" + Thread.currentThread().getName() + " is begin >> " + atomicInc.get());
        try {
            count.await();
            for (int i = 0; i < 1000; i++) {
                atomicInc.getAndIncrement();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("线程：" + Thread.currentThread().getName() + " is over >> " + atomicInc.get());
    }

}
