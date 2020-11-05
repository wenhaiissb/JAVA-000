package com.geekbang.java.bio;

import java.io.IOException;
import java.io.StringReader;

/**
 * 从内存输入
 *
 * @author wenhai
 * @date 2020/11/5
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(BufferedInputFile.read(".\\Week_02\\src\\main\\java\\com\\geekbang\\java\\bio\\MemoryInput.java"));
        int c;
        while ((c = in.read()) != -1) {
            System.out.print((char) c);
        }
    }
}
