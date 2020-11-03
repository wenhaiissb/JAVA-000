package com.geekbang.java.nio.channel;

public class AutoCloseableDemo implements AutoCloseable{
    @Override
    public void close() throws Exception {
        System.out.println("自动关闭");
    }
}
