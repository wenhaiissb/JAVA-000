package com.geekbang.java.bio;

import java.io.*;

/**
 * 基本的文件输出
 *
 * @author wenhai
 * @date   2020/11/5
 */
public class BasicFileOutput {
    static String file = ".\\Week_02\\src\\main\\java\\com\\geekbang\\java\\bio\\BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        int            lineCount    = 1;
        BufferedReader in           = new BufferedReader(new StringReader(BufferedInputFile.read(".\\Week_02\\src\\main\\java\\com\\geekbang\\java\\bio\\BasicFileOutput.java")));
        PrintWriter    out          = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        String s;
        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + ":" + s);
            System.out.println(lineCount++ + ":" + s);
        }
        in.close();
        out.close();
    }
}
