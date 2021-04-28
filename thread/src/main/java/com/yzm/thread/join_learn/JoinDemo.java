package com.yzm.thread.join_learn;

/**
 * 在很多情况下，主线程创建并启动子线程，如果子线程中要进行大量的耗时运算，主线程将可能早于子线程结束。
 * 如果主线程需要知道子线程的执行结果时，就需要等待子线程执行结束了。
 * 主线程可以sleep(xx),但这样的xx时间不好确定，因为子线程的执行时间不确定，join()方法比较合适这个场景。
 */
public class JoinDemo {

    public static void main(String[] args) {
        demo01();
    }

    /**
     * 主线程等待子线程的终止。也就是说主线程的代码块中，如果碰到了t.join()方法，此时主线程需要等待（阻塞），
     * 等待子线程结束了，才能继续执行t.join()之后的代码块。
     */
    private static void demo01() {
        Thread t1 = new Thread(() -> {
            System.out.println("线程：" + Thread.currentThread().getName() + " >> ready");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程：" + Thread.currentThread().getName() + " >> over");
        }, "t1");

        t1.start();
        try {
            System.out.println("main线程等待t1线程执行");
            t1.join();
            // 带参数，则main线程等待一段时间，超时就不理子线程了自己继续执行
            // t1.join(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("main线程执行完毕");

    }

}
