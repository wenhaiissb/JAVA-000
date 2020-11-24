package com.geekbang.java.thread.resume;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SynchronizedObject
 * @Description
 * @Author 谢文海
 * @Date 2020/11/23 22:47
 * @Version 1.0
 **/
public class SynchronizedObject {
    synchronized public void printString() {
        System.out.println("begin");
        if ("a".equals(Thread.currentThread().getName())) {
            System.out.println("a 线程永远 suspend 了!");
            Thread.currentThread().suspend();
        }
        System.out.println("end");
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedObject object = new SynchronizedObject();
        Thread t1 = new Thread(()->{
            System.out.println("thread name = "+Thread.currentThread().getName() +" start");
            object.printString();
            System.out.println("thread name = "+Thread.currentThread().getName() +" end");

        },"a");
        t1.start();
        TimeUnit.SECONDS.sleep(2);
         new Thread(()->{
            System.out.println("thread name = "+Thread.currentThread().getName() +" start");
            object.printString();
            System.out.println("thread name = "+Thread.currentThread().getName() +" end");

        },"b").start();
    }
}
