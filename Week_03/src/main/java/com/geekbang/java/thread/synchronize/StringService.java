package com.geekbang.java.thread.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName StringService
 * @Description
 * @Author 谢文海
 * @Date 2020/11/25 23:10
 * @Version 1.0
 **/
public class StringService {
    public static void print(String stringParam) {
        synchronized (stringParam) {
            try {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
