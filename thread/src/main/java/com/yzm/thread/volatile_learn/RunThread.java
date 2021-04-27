package com.yzm.thread.volatile_learn;

import lombok.Data;

@Data
public class RunThread extends Thread {
    //volatile
    private boolean isRunning = true;

    @Override
    public void run() {
        System.out.println("进入到run方法中了");
        while (isRunning) {
        }
        System.out.println("线程执行完成了");
    }

    public static void main(String[] args) {
        try {
            RunThread run = new RunThread();
            run.start();
            Thread.sleep(1000);
            System.out.println("试图让线程结束死循环");
            run.setRunning(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
