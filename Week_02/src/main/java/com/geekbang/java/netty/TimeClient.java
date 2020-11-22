package com.geekbang.java.netty;

/**
 * @ClassName TimeClient
 * @Description
 * @Author 谢文海
 * @Date 2020/11/22 19:12
 * @Version 1.0
 **/
public class TimeClient {
    public static void main(String[] args) {
        new Thread(new TimeClientHandle("127.0.0.1", 8080), "TimeClient001").start();
    }
}
