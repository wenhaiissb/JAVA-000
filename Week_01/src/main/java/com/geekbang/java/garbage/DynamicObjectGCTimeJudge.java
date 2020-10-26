package com.geekbang.java.garbage;

import static com.geekbang.java.garbage.PreferredAllocationEdenSpace._1MB;

/**
 * 对象动态年龄判断：
 * 当没达到 MaxTenuringThreshold 但是 Survivor 中同年龄所有对象大小的总合大于 Survivor 空间的一般，年龄大于或等于该年龄的对象就可以直接进入老年代
 * <p>
 * VM 参数：-XX:+UseSerialGC -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
 *
 * @author wenhai
 * @date 2020/10/23
 */
public class DynamicObjectGCTimeJudge {
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[_1MB * 4];
        allocation4 = new byte[_1MB * 4];
        allocation4 = null;
        allocation4 = new byte[_1MB * 4];
    }
}
