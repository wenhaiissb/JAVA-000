package com.geekbang.java.nio.channel;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @ClassName FileChannelDemo
 * @Description {@link FileChannel} 演示
 * @Author 谢文海
 * @Date 2020/11/3 23:59
 * @Version 1.0
 **/
public class FileChannelDemo {
    public static void main(String[] args) throws Exception {
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
