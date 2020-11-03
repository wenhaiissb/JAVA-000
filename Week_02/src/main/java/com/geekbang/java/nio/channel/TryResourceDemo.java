package com.geekbang.java.nio.channel;

public class TryResourceDemo {
    public static void main(String[] args) {
        try (AutoCloseableDemo autoCloseableDemo = new AutoCloseableDemo()) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
