package com.geekbang.java.thread.stop;

/**
 * @ClassName MyThreadA
 * @Description
 * @Author 谢文海
 * @Date 2020/11/23 1:03
 * @Version 1.0
 **/
public class MyThreadA extends Thread {

    private MyService myService;

    public MyThreadA(MyService myService) {
        this.myService = myService;
    }


    @Override
    public void run() {
        super.run();
        myService.printString("b", "bb");
    }
}
