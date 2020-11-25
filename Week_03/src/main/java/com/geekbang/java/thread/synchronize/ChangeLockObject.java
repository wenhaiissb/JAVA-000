package com.geekbang.java.thread.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ChangeLockObject
 * @Description
 * @Author 谢文海
 * @Date 2020/11/25 23:36
 * @Version 1.0
 **/
public class ChangeLockObject {
    private String lock = "13";

    public void changeLock() {
        try {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "begin " + System.currentTimeMillis());
                lock = "23";
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "end " + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        ChangeLockObject clo = new ChangeLockObject();
        Thread ta = new Thread(() -> clo.changeLock(),"a");
        Thread tb = new Thread(() -> clo.changeLock(), "b");
        ta.start();
        tb.start();
    }
}
