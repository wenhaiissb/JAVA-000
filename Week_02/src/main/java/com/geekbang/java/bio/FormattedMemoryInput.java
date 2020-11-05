package com.geekbang.java.bio;

import io.netty.util.CharsetUtil;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * 格式化的内存输入
 *
 * @author wenhai
 * @date   2020/11/5
 */
public class FormattedMemoryInput {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(
                BufferedInputFile.read(".\\Week_02\\src\\main\\java\\com\\geekbang\\java\\bio\\BufferedInputFile.java").getBytes(CharsetUtil.UTF_8)));
        while (in.available() != 0) {
            System.out.print((char) in.readByte());
        }
    }
}
