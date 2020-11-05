package com.geekbang.java.bio;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * 文本文件输出的快捷方式
 *
 * @author wenhai
 * @date 2020/11/5
 */
public class FileOutputShortCut {
    static String file = ".\\Week_02\\src\\main\\java\\com\\geekbang\\java\\bio\\FileOutputShortCut.out";

    public static void main(String[] args) throws Exception {
        BufferedReader in        = new BufferedReader(new StringReader(BufferedInputFile.read(".\\Week_02\\src\\main\\java\\com\\geekbang\\java\\bio\\FileOutputShortCut.java")));
        // Here's the shortcut
        PrintWriter    out       = new PrintWriter(file);
        int            lineCount = 1;
        String s;
        while ((s = in.readLine()) != null) {
            out.println(s);
        }
        in.close();
        out.close();
        System.out.println(BufferedInputFile.read(file));
    }
}
