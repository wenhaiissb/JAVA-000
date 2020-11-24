package com.geekbang.java.thread.yield;

/**
 * @ClassName YieldDemo
 * @Description
 * @Author 谢文海
 * @Date 2020/11/23 23:15
 * @Version 1.0
 **/
public class YieldDemo extends Thread {
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 500000000; i++) {
            Thread.yield();
            count = count + i + 1;
        }
        System.out.println("用时:" + (System.currentTimeMillis() - start) + " 毫秒");
    }

    public static void main(String[] args) {
        new YieldDemo().start();
    }
}
