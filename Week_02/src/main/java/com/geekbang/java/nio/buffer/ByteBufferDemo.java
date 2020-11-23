package com.geekbang.java.nio.buffer;

import java.nio.ByteBuffer;

/**
 * {@link ByteBuffer} 方法演示
 *
 * @author wenhai
 * @date 2020/11/3
 */
public class ByteBufferDemo {
    public static void main(String[] args) {
//        duplicateAndSliceDifference();
        encode();
    }

    private static void encode() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] bytes = "我是你大爷".getBytes();
        byteBuffer.putInt(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        byte[] result = new byte[byteBuffer.remaining()];
        byteBuffer.get(result);
        System.out.println(new String(result));
    }

    private static void duplicateAndSliceDifference() {
        byte[] byteArrayIn = {1, 2, 3, 4, 5};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayIn);

    }
}
