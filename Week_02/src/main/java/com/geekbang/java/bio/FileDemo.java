package com.geekbang.java.bio;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @ClassName FileDemo
 * @Description {@link File#list(FilenameFilter)} 示例
 * @Author 谢文海
 * @Date 2020/11/4 1:11
 * @Version 1.0
 **/
public class FileDemo {
    public static void main(String[] args) {
//        File path = new File(".\\Week_02\\src\\main\\java\\com\\geekbang\\java\\bio\\");
//        String[] list;
//        if (args.length == 0) {
//            list = path.list();
//        } else {
//            list = path.list(new DirFilter(args[0]));
//        }
//        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
//        Arrays.stream(list).forEach(System.out::println);
        Directory.TreeInfo treeInfo = Directory.walk(".", ".*\\.java$");
        System.out.println(treeInfo);

    }

    public static void fileData(File file) {
        System.out.println("Absolute path: " + file.getAbsolutePath()
                + "\n Can read: " + file.canRead()
                + "\n Can write: " + file.canWrite()
                + "\n getName: " + file.getName()
                + "\n getParent: " + file.getParent()
                + "\n getPath: " + file.getPath()
                + "\n length: " + file.length()
                + "\n lastModified: " + file.lastModified());
        if (file.isFile()) {
            System.out.println("It's a file");
        } else if (file.isDirectory()) {
            System.out.println("It's a directory");
        }

    }
}
