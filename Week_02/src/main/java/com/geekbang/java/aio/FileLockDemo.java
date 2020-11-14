package com.geekbang.java.aio;

import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName FileLockDemo
 * @Description
 * @Author 谢文海
 * @Date 2020/11/14 9:33
 * @Version 1.0
 **/
public class FileLockDemo {
    public static void main(String[] args) throws Exception {
        fileLock();
    }


    private static void fileLock() throws Exception {
        Path path = Paths.get("I:\\project\\JAVA-000\\Week_02\\src\\main\\resources\\a.txt");
        AsynchronousFileChannel af = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        System.out.println("B lock begin = " + System.currentTimeMillis());
        Future<FileLock> fileLockFuture = af.lock();
        System.out.println("B lock end = " + System.currentTimeMillis());
        FileLock fileLock = fileLockFuture.get();
        System.out.println("B get lock time = " + System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(8);
        fileLock.release();
        af.close();
    }
}
