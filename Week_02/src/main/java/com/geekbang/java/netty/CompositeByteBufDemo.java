package com.geekbang.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @ClassName CompositeByteBufDemo
 * @Description
 * @Author 谢文海
 * @Date 2020/11/15 14:13
 * @Version 1.0
 **/
public class CompositeByteBufDemo {
    public static void main(String[] args) {
        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
        ByteBuf headBuf = Unpooled.wrappedBuffer("我是头部".getBytes());
        ByteBuf boydBuf = Unpooled.wrappedBuffer("我是体".getBytes());
        messageBuf.addComponents(headBuf,boydBuf);
        System.out.println(messageBuf);
        int length = messageBuf.readableBytes();
        System.out.println(length);
        byte[] array = new byte[length];
        messageBuf.getBytes(messageBuf.readerIndex(), array);
        System.out.println(new String(array, 0, length));
        for (ByteBuf byteBuf : messageBuf) {
            System.out.println(byteBuf);
        }
    }
}
