package com.geekbang.java.nio.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.geekbang.java.nio.channel.FileChannelDemo.*;

/**
 * @ClassName FileLockDemo
 * @Description
 * @Author 谢文海
 * @Date 2020/11/7 11:48
 * @Version 1.0
 **/
public class FileLockDemo {
    public static void main(String[] args) throws Exception {
//        fileLockMethod();
        RandomAccessFile f = getRandomAccessFile("a");
        FileChannel channel = f.getChannel();
        System.out.println("A begin");
        channel.lock(1, 4, false);
        channel.write(ByteBuffer.wrap("wwww".getBytes()));
        System.out.println("A end");
        channel.close();
        f.close();
    }
}

