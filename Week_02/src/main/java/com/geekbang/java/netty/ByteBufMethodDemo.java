package com.geekbang.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * {@link ByteBuf} 示例
 *
 * @author wenhai
 * @date 2020/10/28
 */
public class ByteBufMethodDemo {
    public static void main(String[] args) {
        slice();
        copy();
    }


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
