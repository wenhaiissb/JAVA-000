package com.geekbang.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;

public class ByteBufForEach {
    public static void main(String[] args) {
        int offset = 0;
        ByteBuf buffer = Unpooled.copiedBuffer(("我爱" + System.getProperty("line.separator") + "你呀").getBytes());
        int totalLength = buffer.readableBytes();
        int i = buffer.forEachByte(buffer.readerIndex() + offset, totalLength - offset, ByteProcessor.FIND_LF);
        if (i >= 0) {
            offset = 0;
            if (i > 0 && buffer.getByte(i - 1) == '\r') {
                i--;
            }
        } else {
            offset = totalLength;
        }
    }
}
