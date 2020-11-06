package com.geekbang.java.bio;

import java.io.*;

/**
 * 存储和恢复数据
 *
 * @author wenhai
 * @date 2020/11/5
 */
public class StoringAndRecoveringData {
    public static void main(String[] args) throws Exception {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(".\\Week_02\\src\\main\\java\\com\\geekbang\\java\\bio\\data.txt")));
        out.writeDouble(3.1415926);
        out.writeUTF("That was pi");
        out.writeDouble(1.41413);
        out.writeUTF("Square root of 2");
        out.close();

        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(".\\Week_02\\src\\main\\java\\com\\geekbang\\java\\bio\\data.txt")));
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readDouble());
        in.close();
    }


}
