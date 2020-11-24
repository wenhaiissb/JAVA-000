package com.geekbang.java.thread.resume;

/**
 * @ClassName ResumeDemo
 * @Description: 使用 {@link Thread#suspend()} 暂停 ,使用 {@link Thread#resume()} 恢复,不推荐使用,有死锁风险
 * @Author 谢文海
 * @Date 2020/11/23 22:36
 * @Version 1.0
 **/
public class ResumeDemo extends Thread {
    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }


    @Override
    public void run() {
        // noinspection InfiniteLoopStatement
        while (true) {
            i++;
            System.out.println(i);
        }
    }
}
