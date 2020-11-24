package com.geekbang.java.thread.priority;

/**
 * @ClassName PriorityThread
 * @Description
 * @Author 谢文海
 * @Date 2020/11/23 23:25
 * @Version 1.0
 **/
public class PriorityThread extends Thread {
    @Override
    public void run() {
//        long start = System.currentTimeMillis();
//        long addResult = 0;
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 50000; j++) {
//                new Random().nextInt();
//                addResult += i;
//            }
//        }
       // System.out.println("******* " + Thread.currentThread().getName() + "use time: " + (System.currentTimeMillis() - start) + " 毫秒; Priority = " + this.getPriority());
        System.out.println("******* " + Thread.currentThread().getName() + "; Priority = " + this.getPriority());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            PriorityThread highPriorityThread = new PriorityThread();
            highPriorityThread.setName("highPriorityThread");
            highPriorityThread.setPriority(10);
            highPriorityThread.start();


            PriorityThread lowPriorityThread = new PriorityThread();
            lowPriorityThread.setName("lowPriorityThread");
            lowPriorityThread.setPriority(1);
            lowPriorityThread.start();


        }
    }
}
