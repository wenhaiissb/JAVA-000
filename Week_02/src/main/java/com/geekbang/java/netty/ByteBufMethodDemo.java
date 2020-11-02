package com.geekbang.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * {@link ByteBuf} 方法示例
 *
 * @author wenhai
 * @date 2020/10/28
 */
public class ByteBufMethodDemo {
    public static void main(String[] args) {
//        slice();
//        copy();
        getAndSet();
    }

    /**
     * {@link ByteBuf } 中 setX() 跟 getX() 方法索引不变
     */
    private static void getAndSet() {
        System.out.println("============getAndSet start=============");
        Charset utf8 = CharsetUtil.UTF_8;
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        System.out.println("ByteBuf.getByte(index) method result:" + (char) buf.getByte(0));
        buf.setByte(0, (byte) 'B');
        System.out.println("byteBuf.setByte(0,(byte)'B') result:" + (char) buf.getByte(0));
        int updateReaderIndex = buf.readerIndex();
        int updateWriterIndex = buf.writerIndex();
        System.out.println("readerIndex 在 set/get 方法之后结果与方法调用之前比较是否相等：" + (readerIndex == updateReaderIndex));
        System.out.println("writerIndex 在 set/get 方法之后结果与方法调用之前比较是否相等：" + (writerIndex == updateWriterIndex));
        System.out.println("============getAndSet end=============");

    }


    /**
     * {@link ByteBuf#slice} 方法有引用关联会修改原对象
     */
    private static void slice() {
        System.out.println("=========slice start=============");
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf byteBuf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf sliced = byteBuf.slice(0, 15);
        System.out.println("byteBuf.slice(0,15)===>" + sliced.toString(utf8));
        System.out.println("=========================================");
        byteBuf.setByte(0, (byte) 'J');
        System.out.println("byteBuf.setByte(0,(byte)'J')===========>" + byteBuf.toString(utf8));
        assert byteBuf.getByte(0) == sliced.getByte(0);
        System.out.println("=========slice end=============");
    }

    /**
     * {@link ByteBuf#copy} 方法是一个全新的副本，不会影响原对象
     */
    private static void copy() {
        System.out.println("============copy start==============");
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf copy = buf.copy(0, 15);
        System.out.println("ByteBuf copy method result :" + copy.toString(utf8));
        buf.setByte(0, (byte) 'J');
        System.out.println("ByteBuf setByte(0,(byte)'J') method result :" + buf.toString(utf8));
        assert buf.getByte(0) != copy.getByte(0);
        System.out.println("buf final result:" + buf.toString(utf8));
        System.out.println("copy final result:" + copy.toString(utf8));
        System.out.println("============copy end==============");
    }
}
