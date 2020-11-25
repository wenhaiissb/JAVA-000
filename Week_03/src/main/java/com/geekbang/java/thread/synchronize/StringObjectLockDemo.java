package com.geekbang.java.thread.synchronize;

/**
 * @ClassName StringObjectLockDemo
 * @Description
 * @Author 谢文海
 * @Date 2020/11/25 23:12
 * @Version 1.0
 **/
public class StringObjectLockDemo {
    public static void main(String[] args) {
        new Thread(() -> StringService.print("AA"), "A").start();
        new Thread(() -> StringService.print("AA"), "B").start();
    }
}
