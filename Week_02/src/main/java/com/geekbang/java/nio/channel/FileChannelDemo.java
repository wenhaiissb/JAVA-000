package com.geekbang.java.nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName FileChannelDemo
 * @Description {@link FileChannel} 演示
 * @Author 谢文海
 * @Date 2020/11/3 23:59
 * @Version 1.0
 **/
public class FileChannelDemo {
    private static FileOutputStream fosRef;
    private static FileInputStream fisRef;
    private static FileChannel inFileChannel;
    private static FileChannel outFileChannel;

    static {
        try {
            fosRef = new FileOutputStream(new File(".\\Week_02\\src\\main\\resources/a.txt"));
            fisRef = new FileInputStream(new File(".\\Week_02\\src\\main\\resources/b.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        outFileChannel = fosRef.getChannel();
        inFileChannel = fisRef.getChannel();
    }

    public static void main(String[] args) throws Exception {
        // writeMethod();
        // writeSynchronous();
        // readMethod();
        // bulkWriteMethod();
        // bulkReadMethod();
        // bulkRangeWriteMethod();
        bulkRangeReadMethod();
    }

    /**
     * {@link FileChannel#write(ByteBuffer[], int, int)} 批量范围写
     */
    private static void bulkRangeWriteMethod() throws IOException {
        ByteBuffer bb1, bb2, bb3, bb4;
        bb1 = ByteBuffer.wrap("byteBuffer1\r\n".getBytes());
        bb2 = ByteBuffer.wrap("byteBuffer2\r\n".getBytes());
        bb3 = ByteBuffer.wrap("byteBuffer3\r\n".getBytes());
        bb4 = ByteBuffer.wrap("byteBuffer4\r\n".getBytes());
        ByteBuffer[] byteBufferArray = {bb1, bb2, bb3, bb4};
        outFileChannel.write(byteBufferArray, 0, 2);
    }

    /**
     * {@link FileChannel#write(ByteBuffer[], int, int)} 批量范围写
     */
    private static void bulkRangeReadMethod() throws IOException {
        ByteBuffer bb1, bb2, bb3, bb4;
        bb1 = ByteBuffer.allocate(12);
        bb2 = ByteBuffer.allocate(12);
        bb3 = ByteBuffer.allocate(12);
        bb4 = ByteBuffer.allocate(12);
        ByteBuffer[] byteBufferArray = {bb1, bb2, bb3, bb4};
        inFileChannel.read(byteBufferArray, 0, 2);
        bb1.flip();
        bb2.flip();
        bb3.flip();
        bb4.flip();
        System.out.println("bb1:");
        for (int i = 0; i < bb1.limit(); i++) {
            System.out.print((char) bb1.get() + "  ");
        }
        System.out.println();
        System.out.println("bb2:");
        for (int i = 0; i < bb2.limit(); i++) {
            System.out.print((char) bb2.get() + "  ");
        }
        System.out.println();
        System.out.println("bb3:");
        for (int i = 0; i < bb3.limit(); i++) {
            System.out.print((char) bb3.get() + "  ");
        }
        System.out.println();
        System.out.println("bb4:");
        for (int i = 0; i < bb4.limit(); i++) {
            System.out.print((char) bb4.get() + "  ");

        }
        System.out.println();
    }

    /**
     * {@link FileChannel#read(ByteBuffer[])} 批量读
     */
    private static void bulkReadMethod() throws IOException {
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(8);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(8);
        ByteBuffer[] byteBuffers = {byteBuffer1, byteBuffer2};
        long readLength = inFileChannel.read(byteBuffers);
        System.out.println("readLength = " + readLength);
        System.out.println(Arrays.toString(byteBuffer1.array()));
        System.out.println(Arrays.toString(byteBuffer2.array()));
        inFileChannel.close();
        fisRef.close();

    }

    /**
     * {@link FileChannel#write(ByteBuffer[])} 批量 写
     */
    private static void bulkWriteMethod() throws IOException {
        outFileChannel.write(ByteBuffer.wrap("123456".getBytes()));
        outFileChannel.position(3);
        ByteBuffer byteBuffer1 = ByteBuffer.wrap("ooooo1".getBytes());
        ByteBuffer byteBuffer2 = ByteBuffer.wrap("ooooo2".getBytes());
        ByteBuffer[] byteBuffers = {byteBuffer1, byteBuffer2};
        outFileChannel.write(byteBuffers);
        outFileChannel.close();
        fosRef.close();
    }

    private static void readMethod() throws Exception {
        inFileChannel.position(2);
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.position(3);
        int readLength = inFileChannel.read(byteBuffer);
        System.out.println(readLength);
        System.out.println(Arrays.toString(byteBuffer.array()));
        TimeUnit.SECONDS.sleep(3);
        inFileChannel.close();
        fisRef.close();
    }

    /**
     * 多线程同时写线程是安全的
     *
     * @throws Exception
     */
    private static void writeSynchronous() throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(() -> {
                try {
                    ByteBuffer byteBuffer = ByteBuffer.wrap("abcde\r\n".getBytes());
                    outFileChannel.write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Thread t2 = new Thread(() -> {
                try {
                    ByteBuffer byteBuffer = ByteBuffer.wrap("我是中国人\r\n".getBytes());
                    outFileChannel.write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t1.start();
            t2.start();
        }
        TimeUnit.SECONDS.sleep(3);
        outFileChannel.close();
        fosRef.close();
    }

    private static void writeMethod() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(".\\Week_02\\src\\main\\resources/a.txt"));
        System.out.println(Charset.defaultCharset());
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.wrap("我是中国人".getBytes("UTF8"));
        System.out.println("A fileChannel.position = " + fileChannel.position());
        System.out.println("write() 1 返回值 = " + fileChannel.write(byteBuffer));
        System.out.println("B fileChannel.position = " + fileChannel.position());
        fileChannel.position(3);
        byteBuffer.rewind();
        System.out.println("write() 2 返回值 = " + fileChannel.write(byteBuffer));
        System.out.println("C fileChannel.position = " + fileChannel.position());
        fileChannel.close();
        fileOutputStream.close();
    }
}
