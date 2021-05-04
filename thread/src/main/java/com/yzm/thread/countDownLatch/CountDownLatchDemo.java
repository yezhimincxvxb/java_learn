package com.yzm.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 控制线程启动
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
//        demo_01();
        demo_02();
    }

    /**
     * 主线程等待所有子线程的任务完成，才继续往下执行
     * 应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行
     */
    private static void demo_01() {
        final CountDownLatch latch = new CountDownLatch(3);

        Thread t1 = new Thread(getRunnable(latch), "t1");
        Thread t2 = new Thread(getRunnable(latch), "t2");
        Thread t3 = new Thread(getRunnable(latch), "t3");

        t1.start();
        t2.start();
        t3.start();

        try {
            latch.await();//阻塞当前线程，直到计数器的值为0
            System.out.println("主线程：" + Thread.currentThread().getName() + "执行完成...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Runnable getRunnable(CountDownLatch latch) {
        return () -> {
            try {
                System.out.println("子线程：" + Thread.currentThread().getName() + "开始执行");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("子线程：" + Thread.currentThread().getName() + "执行完成");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    /**
     * 赛跑
     * 将多个线程放到起点，等待发令枪响，然后同时开跑
     */
    private static void demo_02() {
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(4);

        Thread t1 = new Thread(getRunnable2(cdOrder, cdAnswer), "t1");
        Thread t2 = new Thread(getRunnable2(cdOrder, cdAnswer), "t2");
        Thread t3 = new Thread(getRunnable2(cdOrder, cdAnswer), "t3");
        Thread t4 = new Thread(getRunnable2(cdOrder, cdAnswer), "t4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("==================裁判" + Thread.currentThread().getName() + "即将发布口令==================");
            cdOrder.countDown();
            System.out.println("裁判" + Thread.currentThread().getName() + "已发送口令，正在等待所有选手到达终点");
            cdAnswer.await();
            System.out.println("==================所有选手都到达终点==================");
            System.out.println("裁判" + Thread.currentThread().getName() + "汇总成绩排名");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Runnable getRunnable2(CountDownLatch cdOrder, CountDownLatch cdAnswer) {
        return () -> {
            try {
                System.out.println("选手：" + Thread.currentThread().getName() + "正在等待裁判发布口令");
                cdOrder.await();
                System.out.println("选手：" + Thread.currentThread().getName() + "已接受裁判口令");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("选手：" + Thread.currentThread().getName() + "到达终点");
                cdAnswer.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

}
