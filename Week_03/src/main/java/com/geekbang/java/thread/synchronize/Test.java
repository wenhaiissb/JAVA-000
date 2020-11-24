package com.geekbang.java.thread.synchronize;

/**
 * @ClassName Test
 * @Description
 * @Author 谢文海
 * @Date 2020/11/24 23:14
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        HasSelfPrivateNum hasSelfPrivateNum2 = new HasSelfPrivateNum();
//        new ThreadA(hasSelfPrivateNum).start();
//        new ThreadA(hasSelfPrivateNum).start();
//        new ThreadB(hasSelfPrivateNum2).start();

        System.out.println("different object the class object of same = " + (hasSelfPrivateNum.getClass() == hasSelfPrivateNum2.getClass()));
    }
}
