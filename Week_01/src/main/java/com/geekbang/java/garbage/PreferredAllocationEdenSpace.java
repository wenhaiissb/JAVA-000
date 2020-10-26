package com.geekbang.java.garbage;

/**
 * 对象优先分配在Eden区
 * JVM : -XX:+UseSerialGC -Xms20m -Xmx20m -Xmn10m -XX:PrintGCDetails -XX:SurvivorRatio=8
 *
 * @author wenhai
 * @date   2020/10/22
 */
public class PreferredAllocationEdenSpace {
    public static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1 = new byte[2 * _1MB];
        byte[] allocation2 = new byte[2 * _1MB];
        byte[] allocation3 = new byte[2 * _1MB];
        byte[] allocation4 = new byte[4 * _1MB];

    }
}
