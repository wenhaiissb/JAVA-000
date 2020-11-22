package com.geekbang.java.thread.stop;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyService
 * @Description:  暴力执行 {@link Thread#stop()} 带来的数据不一致
 * @Author 谢文海
 * @Date 2020/11/23 1:00
 * @Version 1.0
 **/
public class MyService {
    private String username = "a";
    private String password = "aa";


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    synchronized public void printString(String username, String password) {
        try {
            this.username = username;
            TimeUnit.SECONDS.sleep(2);
            this.password = password;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        MyThreadA myThreadA = new MyThreadA(myService);
        MyThreadB myThreadB = new MyThreadB(myService);
        myThreadA.start();
        TimeUnit.SECONDS.sleep(1);
        myThreadB.start();
        // TimeUnit.SECONDS.sleep(3);
//        myThreadA.stop();
        System.out.println("MyThreadA stop 执行后,在下方打印 username 和 password");

    }
}
