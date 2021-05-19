package com.yzm.thread.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledService {

    /* @Scheduled(fixedRate = 5000)*/
    public void fixedRate() throws InterruptedException {
        log.info("项目启动时立即执行第一次，之后每过5秒固定执行一次，不受方法执行耗时影响");
        Thread.sleep(2000);
    }

    /*@Scheduled(fixedDelay = 5000)*/
    public void fixedDelay() throws InterruptedException {
        log.info("项目启动时立即执行第一次，方法内部执行完成之后过5秒再次执行");
        Thread.sleep(2000);
    }

    /*@Scheduled(initialDelay = 5000,fixedRate = 10000)*/
    public void initialDelayFixedRate() throws InterruptedException {
        log.info("项目启动时延迟5秒才执行第一次，在第一次执行时每过10秒固定执行一次，不受方法执行耗时影响");
        Thread.sleep(2000);
    }

    /*@Scheduled(cron = "0/5 * *  * * ? ")*/
    public void cron() throws InterruptedException {
        log.info("项目启动时延迟5秒才执行第一次，在第一次执行时每过5秒固定执行一次，不受方法执行耗时影响");
        Thread.sleep(2000);
    }

    /*@Scheduled(cron = "0/3 * *  * * ? ")*/
    public void cron2() throws InterruptedException {
        log.info("项目启动时延迟5秒才执行第一次，在第一次执行时每过5秒固定执行一次，不受方法执行耗时影响");
        Thread.sleep(2000);
    }
}
