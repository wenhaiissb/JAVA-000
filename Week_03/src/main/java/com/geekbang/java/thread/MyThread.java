package com.geekbang.java.thread;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyThread
 * @Description
 * @Author 谢文海
 * @Date 2020/11/23 0:43
 * @Version 1.0
 **/
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            System.out.println("run begin");
            TimeUnit.SECONDS.sleep(2000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Sleep enter Interrupted Exception this.isInterrupted = " + this.isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("main end");
    }
}
