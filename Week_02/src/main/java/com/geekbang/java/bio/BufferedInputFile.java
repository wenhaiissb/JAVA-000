package com.geekbang.java.bio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 缓冲输入文件
 *
 * @author wenhai
 * @date 2020/11/5
 * @see java.io.BufferedReader
 */
public class BufferedInputFile {
    public static String read(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null) {
            sb.append(s).append("\n");
        }
        in.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(read(".\\Week_02\\src\\main\\java\\com\\geekbang\\java\\bio\\BufferedInputFile.java"));
    }
}
