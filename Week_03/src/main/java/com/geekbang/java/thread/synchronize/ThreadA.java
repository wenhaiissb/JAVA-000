package com.geekbang.java.thread.synchronize;

/**
 * @ClassName ThreadA
 * @Description
 * @Author 谢文海
 * @Date 2020/11/24 23:14
 * @Version 1.0
 **/
public class ThreadA extends Thread {
    private HasSelfPrivateNum hasSelfPrivateNum;

    public ThreadA(HasSelfPrivateNum hasSelfPrivateNum) {
        this.hasSelfPrivateNum = hasSelfPrivateNum;
    }

    @Override
    public void run() {
        super.run();
        hasSelfPrivateNum.addI("a");

    }
}
