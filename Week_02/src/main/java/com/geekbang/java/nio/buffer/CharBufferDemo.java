package com.geekbang.java.nio.buffer;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Arrays;

/**
 * {@link CharBuffer}
 *
 * @author wenhai
 * @date 2020/11/3
 */
public class CharBufferDemo {
    public static void main(String[] args) throws IOException {
        // appendAndCharAtMethod();
        // putAndReadAndSubSequenceMethod();
        // wrapMethod();
        lengthMethod();
    }


    /**
     * {@link CharBuffer#length()} 等价于 {@link CharBuffer#remaining()} limit - position
     */
    private static void lengthMethod() {
        CharBuffer charBuffer = CharBuffer.wrap("abcd");
        System.out.println("position = " + charBuffer.position() + " , remaining = " + charBuffer.remaining() + " , length = " + charBuffer.length());
        System.out.println(charBuffer.get());
        System.out.println("position = " + charBuffer.position() + " , remaining = " + charBuffer.remaining() + " , length = " + charBuffer.length());
        System.out.println(charBuffer.get());
        System.out.println("position = " + charBuffer.position() + " , remaining = " + charBuffer.remaining() + " , length = " + charBuffer.length());
        System.out.println(charBuffer.get());
        System.out.println("position = " + charBuffer.position() + " , remaining = " + charBuffer.remaining() + " , length = " + charBuffer.length());
        System.out.println(charBuffer.get());
        System.out.println("position = " + charBuffer.position() + " , remaining = " + charBuffer.remaining() + " , length = " + charBuffer.length());
    }

    private static void wrapMethod() {
        CharBuffer charBuffer = CharBuffer.wrap("abcdefg", 3, 5);
        System.out.println("charBuffer capacity = " + charBuffer.capacity() + " , position =" + charBuffer.position() + " , limit = " + charBuffer.limit());
        for (int i = charBuffer.position(); i < charBuffer.limit(); i++) {
            System.out.print(charBuffer.get() + " ");
        }
        System.out.println();
        charBuffer.append("我是制度的，不能添加数据，会出现异常！");
    }

    /**
     * {@link CharBuffer} put/read/subSequence 方法示例
     */
    private static void putAndReadAndSubSequenceMethod() throws IOException {
        CharBuffer charBuffer = CharBuffer.allocate(10);
        charBuffer.append("ab123456");
        charBuffer.position(2);
        charBuffer.put("cde");
        charBuffer.rewind();
//        charBuffer.flip();
        System.out.println("charBuffer position = " + charBuffer.position());
        System.out.println("limit = " + charBuffer.limit());
        System.out.println("charBuffer = " + Arrays.toString(charBuffer.array()));
        charBuffer.position(1);
        CharBuffer charbuffer2 = CharBuffer.allocate(4);
        System.out.println("A charbuffer2 position = " + charbuffer2.position());
        charBuffer.read(charbuffer2);
        System.out.println("B charbuffer2 position = " + charbuffer2.position());
        System.out.println("charBuffer position = " + charBuffer.position());
        System.out.println("charBuffer2" + Arrays.toString(charbuffer2.array()));
        charBuffer.position(2);
        CharBuffer charBuffer3 = charBuffer.subSequence(0, 2);
        System.out.println("charBuffer3 position = " + charBuffer3.position() + ",limit = " + charBuffer3.limit() + " , capacity = " + charBuffer3.capacity());
        System.out.print("charBuffer3 = ");
        for (int i = charBuffer3.position(); i < charBuffer3.limit(); i++) {
            System.out.print(charBuffer3.get() + ",");
        }


    }

    /**
     * {@link CharBuffer} append()/charAt(index) 方法等价于 put/get(position+index) 方法
     */
    private static void appendAndCharAtMethod() {
        CharBuffer charBuffer = CharBuffer.allocate(10);
        charBuffer.append("abcdefg");
//        charBuffer.position(0);
        System.out.println(charBuffer.charAt(0));
        System.out.println(charBuffer.get(0));
        System.out.println(charBuffer.charAt(1));
        System.out.println(charBuffer.get(1));
        System.out.println("position = " + charBuffer.position());
    }
}
