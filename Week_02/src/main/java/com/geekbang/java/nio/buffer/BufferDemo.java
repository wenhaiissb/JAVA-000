package com.geekbang.java.nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * {@link Buffer} 方法演示
 *
 * @author wenhai
 * @date 2020/11/3
 */
public class BufferDemo {
    public static void main(String[] args) {
        duplicateAndSliceDifference();
    }

    private static void duplicateAndSliceDifference() {
        byte[] byteArrayIn = {1, 2, 3, 4, 5};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayIn);

    }
}
