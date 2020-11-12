package com.geekbang.java.nio;

public class NioServerDemo {
    public static void main(String[] args) throws Exception {
        new NioServer().server(8888);
    }
}
