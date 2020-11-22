package com.geekbang.java.thread;

import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName MultiMethodInvoke
 * @Description
 * @Author 谢文海
 * @Date 2020/11/22 22:20
 * @Version 1.0
 **/
public class MultiMethodInvoke {
    public void a() {
        b();
    }

    public void b() {
        c();
    }

    public void c() {
        d();
    }

    public void d() {
        e();
    }

    public void e() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            System.err.println(element);
        }


        int age = 0;
        age = 100;
        if (age == 100) {
            Thread.dumpStack();
        }

        System.err.println("===========");

        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();

        allStackTraces.forEach((k,v)->{
            System.out.println(k);
            System.out.println(Arrays.toString(v));
        });


    }
}
