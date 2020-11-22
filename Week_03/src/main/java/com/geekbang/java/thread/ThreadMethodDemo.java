package com.geekbang.java.thread;

/**
 * @ClassName ThreadMethodDemo
 * @Description : {@link Thread} 方法演示
 * @Author 谢文海
 * @Date 2020/11/22 22:20
 * @Version 1.0
 **/
public class ThreadMethodDemo {
    public static void main(String[] args) {
        stackTrace();
    }

    private static void stackTrace() {
        new MultiMethodInvoke().a();
    }
}
