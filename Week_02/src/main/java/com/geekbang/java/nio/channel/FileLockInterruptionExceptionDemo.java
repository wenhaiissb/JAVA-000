package com.geekbang.java.nio.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

import static com.geekbang.java.nio.channel.FileChannelDemo.*;

/**
 * @ClassName FileLockInterruptionExceptionDemo
 * @Description
 * @Author 谢文海
 * @Date 2020/11/7 12:22
 * @Version 1.0
 **/
public class FileLockInterruptionExceptionDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t =new Thread(() -> {

            try {
                RandomAccessFile f = getRandomAccessFile("a");
                FileChannel channel = f.getChannel();
                System.out.println("B begin");
                channel.lock(0, 3, false);
                System.out.println("B end");
                channel.close();
                f.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(2);
        t.interrupt();


    }
}
