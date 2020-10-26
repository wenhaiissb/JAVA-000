package com.geekbang.java.garbage;

import static com.geekbang.java.garbage.PreferredAllocationEdenSpace._1MB;

/**
 * VM 参数：-Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * JDK 6 Update 24 之后版本参数不在影响
 * 分配担保: -XX:+/-HandlePromotionFailure
 * 在发生 Minor GC 之前虚拟机必须先检查老年代最大可用的连续空间是否大于新生代所有对象总空间，
 * 如果这个条件成立，那这次 Minor GC 可以确保是安全的。
 * 如果不成立，则虚拟机会先查看 XX: HandlePromotionFailure 参数的设置值是否允许担保失败；
 * 如果允许，那会继续检查老年代最大可用的连续空间是否大于历次晋升到老年代对象的平均大小，如果大于，将尝试进行一次 Minor GC，有风险；
 * 如果不于，或者 -XX:HandlePromotionFailure 参数设置不允许冒险，那这时要改为进行一次 Full GC
 *
 * @author wenhai
 * @date 2020/10/23
 */
public class HandlePromotion {
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];
    }
}
