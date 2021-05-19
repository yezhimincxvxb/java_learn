package com.yzm.thread.executor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.*;

@Slf4j
@Component
@EnableScheduling
public class ExecutorTestService {

    @Scheduled(cron = "0/50 * *  * * ? ")
    public void fixedRate() throws Exception {
//        fixedThreadPool();
//        cachedThreadPool();
//        singleThreadExecutor();
//        scheduledThreadPoolA();
//        scheduledThreadPoolB();
//        scheduledThreadPoolC();
//        singleThreadScheduledExecutor();
//        threadPoolExecutorA();
//        threadPoolExecutorB();
//        threadPoolExecutorC();
//        taskExecutor();
//        scheduleA();
//        scheduleB();
//        scheduleC();
//        scheduleD();
//        scheduleE();
    }

    @Resource(name = "fixedThreadPool")
    private ExecutorService fixedThreadPool;

    public void fixedThreadPool() {
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(() -> {
                try {
                    Thread.sleep(100);
                    log.info("固定大小线程池");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Resource(name = "cachedThreadPool")
    private ExecutorService cachedThreadPool;

    public void cachedThreadPool() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            cachedThreadPool.execute(() -> {
                try {
                    Thread.sleep(100);
                    log.info("缓存线程池");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(10000);
        for (int i = 0; i < 20; i++) {
            cachedThreadPool.execute(() -> {
                try {
                    Thread.sleep(100);
                    log.info("缓存线程池");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Resource(name = "singleThreadExecutor")
    private ExecutorService singleThreadExecutor;

    public void singleThreadExecutor() {
        for (int i = 0; i < 10; i++) {
            singleThreadExecutor.execute(() -> {
                try {
                    Thread.sleep(100);
                    log.info("单一线程池");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Resource(name = "scheduledThreadPool")
    private ScheduledExecutorService scheduledThreadPool;

    public void scheduledThreadPoolA() {
        for (int i = 0; i < 10; i++) {
            scheduledThreadPool.schedule(() -> {
                try {
                    Thread.sleep(100);
                    log.info("周期线程池，调用时延迟5秒执行一次任务，只执行一次");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 5, TimeUnit.SECONDS);
        }
    }

    public void scheduledThreadPoolB() {
        for (int i = 0; i < 10; i++) {
            scheduledThreadPool.scheduleAtFixedRate(() -> {
                try {
                    Thread.sleep(1000);
                    log.info("周期线程池，调用时延迟5秒执行一次任务，之后立即计算每过10秒再次执行一次，会不停重复执行，不受任务耗时影响，时间间隔固定");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 5, 10, TimeUnit.SECONDS);
        }
    }

    public void scheduledThreadPoolC() {
        for (int i = 0; i < 10; i++) {
            scheduledThreadPool.scheduleWithFixedDelay(() -> {
                try {
                    Thread.sleep(1000);
                    log.info("周期线程池，调用时延迟5秒执行一次任务，任务执行完毕之后才开始计算第10秒再次执行一次，会不停重复执行，受任务耗时影响，时间间隔不固定");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 5, 10, TimeUnit.SECONDS);
        }
    }

    @Resource(name = "singleThreadScheduledExecutor")
    private ScheduledExecutorService singleThreadScheduledExecutor;

    public void singleThreadScheduledExecutor() {
        for (int i = 0; i < 10; i++) {
            singleThreadScheduledExecutor.schedule(() -> {
                try {
                    Thread.sleep(1000);
                    log.info("单一周期线程池，调用时延迟5秒执行一次任务，只执行一次");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 5, TimeUnit.SECONDS);
        }
    }

    @Resource(name = "threadPoolExecutorA")
    private ThreadPoolExecutor threadPoolExecutorA;
    @Resource(name = "threadPoolExecutorB")
    private ThreadPoolExecutor threadPoolExecutorB;
    @Resource(name = "threadPoolExecutorC")
    private ThreadPoolExecutor threadPoolExecutorC;

    /**
     * 核心3，最大6，超时10秒，LinkedBlockingDeque队列长度5
     * 当前运行线程数<=核心数，直接使用核心数
     * 当前运行线程数>核心数，加入任务队列
     * 当前运行线程数>任务队列长度，创建非核心数(核心+非核心<=最大数)
     * 当前运行线程数>最大数，进行饱和策略(目前策略是直接丢弃)
     * 空闲非核心数，超过空闲时间时移除
     */
    public void threadPoolExecutorA() throws InterruptedException {
        System.out.println("先开三个线程");
        start(threadPoolExecutorA);
        System.out.println("=========================================");
        System.out.println("再开三个线程");
        start(threadPoolExecutorA);
        System.out.println("=========================================");
        System.out.println("再开三个线程");
        start(threadPoolExecutorA);
        System.out.println("=========================================");
        System.out.println("20秒后再开三个线程");
        Thread.sleep(20000);
        start(threadPoolExecutorA);
    }

    /**
     * 核心3，最大6，超时10秒，SynchronousQueue队列无长度
     * 当前运行线程数<=核心数，直接使用核心数
     * 当前运行线程数>核心数，不会加入任务队列，而是创建非核心线程来执行任务
     * 当前运行线程数>最大数，进行饱和策略(目前策略是直接丢弃)
     * 空闲非核心数，超过空闲时间时移除
     */
    public void threadPoolExecutorB() throws InterruptedException {
        System.out.println("先开三个线程");
        start(threadPoolExecutorB);
        System.out.println("=========================================");
        System.out.println("再开三个线程");
        start(threadPoolExecutorB);
        System.out.println("=========================================");
        System.out.println("再开三个线程");
        start(threadPoolExecutorB);
        System.out.println("=========================================");
        System.out.println("20秒后再开三个线程");
        Thread.sleep(20000);
        start(threadPoolExecutorB);
    }

    /**
     * 核心3，最大6，超时10秒，ArrayBlockingQueue队列长度5
     * 当前运行线程数<=核心数，直接使用核心数
     * 当前运行线程数>核心数，加入任务队列
     * 当前运行线程数>任务队列长度，创建非核心数(核心+非核心<=最大数)
     * 当前运行线程数>最大数，进行饱和策略(目前策略是直接丢弃)
     * 空闲非核心数，超过空闲时间时移除
     */
    public void threadPoolExecutorC() throws InterruptedException {
        System.out.println("先开三个线程");
        start(threadPoolExecutorC);
        System.out.println("=========================================");
        System.out.println("再开三个线程");
        start(threadPoolExecutorC);
        System.out.println("=========================================");
        System.out.println("再开三个线程");
        start(threadPoolExecutorC);
        System.out.println("=========================================");
        System.out.println("20秒后再开三个线程");
        Thread.sleep(20000);
        start(threadPoolExecutorC);
    }

    private void start(ThreadPoolExecutor threadPoolExecutor) {
        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    log.info("线程" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("核心线程数：" + threadPoolExecutor.getCorePoolSize());
        System.out.println("线程池数：" + threadPoolExecutor.getPoolSize());
        System.out.println("队列任务数：" + threadPoolExecutor.getQueue().size());
    }

    @Resource(name = "threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    public void taskExecutor() {
        taskExecutor.execute(() -> {
            log.info("ThreadPoolTaskExecutor 的 execute 方法调用");
        });
    }

    @Resource(name = "scheduledPoolTaskExecutor")
    private ScheduledThreadPoolExecutor scheduled;

    public void scheduleA() {
        scheduled.execute(new Runnable() {
            @Override
            public void run() {
                log.info("execute ");
            }
        });
    }

    public void scheduleB() throws ExecutionException, InterruptedException {
        scheduled.submit(new Runnable() {
            @Override
            public void run() {
                log.info("submit 无返回值");
            }
        });

        Future<String> result = scheduled.submit(new Runnable() {
            @Override
            public void run() {
                log.info("submit 有返回值");
            }
        }, "ok");
        System.out.println("result = " + result.get());

        Future<String> submit = scheduled.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("submit 有返回值");
                return "ok";
            }
        });
        System.out.println("submit = " + submit.get());

    }

    public void scheduleC() throws ExecutionException, InterruptedException {
        scheduled.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("方法调用，延迟5秒执行一次, 只执行一次");
            }
        }, 5000, TimeUnit.MILLISECONDS);

        ScheduledFuture<String> result = scheduled.schedule(new Callable<String>() {
            @Override
            public String call() {
                log.info("方法调用，延迟10秒执行一次, 只执行一次");
                return "ok";
            }
        }, 10000, TimeUnit.MILLISECONDS);
        System.out.println("result = " + result.get());
    }

    public void scheduleD() {
        scheduled.scheduleAtFixedRate(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.info("方法调用，延迟5秒首次执行，之后每过3秒轮询, 不受run方法执行耗时影响");
                Thread.sleep(2000);
            }
        }, 5000, 3000, TimeUnit.MILLISECONDS);
    }

    public void scheduleE() {
        scheduled.scheduleWithFixedDelay(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.info("方法调用，延迟5秒首次执行，之后每过3秒轮询，受run方法执行耗时影响");
                Thread.sleep(2000);
            }
        }, 5000, 3000, TimeUnit.MILLISECONDS);
    }

}
