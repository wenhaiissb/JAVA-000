package com.geekbang.java.bio;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * @ClassName FileDemo
 * @Description {@link File#list(FilenameFilter)} 示例
 * @Author 谢文海
 * @Date 2020/11/4 1:11
 * @Version 1.0
 **/
public class FileDemo {
    public static void main(String[] args) {
        File path = new File(".\\Week_02\\src\\main\\java\\com\\geekbang\\java\\bio\\");
        String[] list;
        if (args.length == 0) {
            list = path.list();
        } else {
            list = path.list(new DirFilter(args[0]));
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        Arrays.stream(list).forEach(System.out::println);

    }
}
