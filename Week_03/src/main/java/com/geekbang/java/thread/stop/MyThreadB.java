package com.geekbang.java.thread.stop;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyThreadA
 * @Description
 * @Author 谢文海
 * @Date 2020/11/23 1:03
 * @Version 1.0
 **/
public class MyThreadB extends Thread {

    private MyService myService;

    public MyThreadB(MyService myService) {
        this.myService = myService;
    }


    @Override
    public void run() {
        super.run();
        System.out.println(myService.getUsername());
        System.out.println(myService.getPassword());
        while (true){
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
