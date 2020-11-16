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
        // randomAccess();
        // readBytes();
        // writeBytes();
        // slice();
         copy();
        // getAndSet();
    }

    private static void writeBytes() {
        ByteBuf bb1 = Unpooled.buffer();
        System.out.println(" write before bb1 rind = " + bb1.readerIndex() + " , wind = " + bb1.writerIndex() + ", capacity = " + bb1.capacity());
        ByteBuf bb2 = Unpooled.wrappedBuffer("1231231".getBytes());
        System.out.println(" write before bb2 rind = " + bb2.readerIndex() + ", bb2 wind = " + bb2.writerIndex());
        System.out.println(bb1.writeBytes(bb2));
        System.out.println(" write after bb1 rind = " + bb1.readerIndex() + " , wind = " + bb1.writerIndex() + ", capacity = " + bb1.capacity());
        System.out.println(" write after bb2 rind = " + bb2.readerIndex() + ", bb2 wind = " + bb2.writerIndex());
        bb1.writableBytes();
    }

    private static void readBytes() {
        ByteBuf bb1 = Unpooled.wrappedBuffer("1231231".getBytes());
        ByteBuf bb2 = Unpooled.buffer(5);
        System.out.println("bb1 rind = " + bb1.readerIndex() + ", bb1 wind = " + bb1.writerIndex());
        System.out.println("bb2 rind = " + bb2.readerIndex() + ", bb2 wind = " + bb2.writerIndex());
        bb1.readBytes(bb2);
        System.out.println("bb1 rind = " + bb1.readerIndex() + ", bb1 wind = " + bb1.writerIndex());
        System.out.println("bb2 rind = " + bb2.readerIndex() + ", bb2 wind = " + bb2.writerIndex());
        System.out.println(new String(bb2.array()));


        System.out.println("============");


        while (bb2.isReadable()) {
            System.out.print((char) bb2.readByte());
        }
        System.out.println("bb2 rind = " + bb2.readerIndex() + ", bb2 wind = " + bb2.writerIndex());
    }

    private static void randomAccess() {
        ByteBuf byteBuf = Unpooled.wrappedBuffer("aaccc".getBytes());
        for (int i = 0; i < byteBuf.capacity(); i++) {
            System.out.println("reader index = " + byteBuf.readerIndex());
            System.out.println("writer index = " + byteBuf.writerIndex());
            System.out.println((char) byteBuf.getByte(i));
        }
        System.out.println("==================");
        System.out.println((char) byteBuf.readByte());
        System.out.println("reader index = " + byteBuf.readerIndex());
        System.out.println("writer index = " + byteBuf.writerIndex());
        byteBuf.discardReadBytes();
        System.out.println("reader index = " + byteBuf.readerIndex());
        System.out.println("writer index = " + byteBuf.writerIndex());
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
