package com.geekbang.java.thread;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadStack
 * @Description :  查看线程 jps +jstack; jmc ; jvisualvm
 * @Author 谢文海
 * @Date 2020/11/22 21:22
 * @Version 1.0
 **/
public class ThreadStack {

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
