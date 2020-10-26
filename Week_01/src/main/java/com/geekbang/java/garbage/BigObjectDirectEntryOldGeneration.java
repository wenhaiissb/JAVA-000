package com.geekbang.java.garbage;

import static com.geekbang.java.garbage.PreferredAllocationEdenSpace._1MB;

/**
 * 大对象直接进入老年代
 * 大对象：需要大量的连续空间的 Java 对象 -XX:PretenureSizeThreshold 参数，大于这个参数直接进入老年大
 * 避免大对象在年轻代产生大量的来回复制
 * JVM: -XX:+UseSerialGC -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728  3M
 *
 * @author wenhai
 * @date 2020/10/22
 */
public class BigObjectDirectEntryOldGeneration {
    public static void main(String[] args) {
        byte[] allocation = new byte[4 * _1MB];
    }
}
