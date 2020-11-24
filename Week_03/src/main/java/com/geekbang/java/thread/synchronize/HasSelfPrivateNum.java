package com.geekbang.java.thread.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName HasSelfPrivateNum
 * @Description
 * @Author 谢文海
 * @Date 2020/11/24 23:09
 * @Version 1.0
 **/
public class HasSelfPrivateNum {
    private int num;

    synchronized public void addI(String username) {
        try {
            if ("a".equals(username)) {
                num = 100;
                System.out.println("a set over");
                TimeUnit.SECONDS.sleep(2);
            }
            if ("b".equals(username)) {
                num = 200;
                System.out.println("b set over");
            }
            System.out.println("username = " + username + "  num = " + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
