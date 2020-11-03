package com.geekbang.java.bio;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * @ClassName DirFilter
 * @Description 自定义过滤器
 * @Author 谢文海
 * @Date 2020/11/4 1:09
 * @Version 1.0
 **/
public class DirFilter implements FilenameFilter {
    private Pattern pattern;

    public DirFilter(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).find();
    }
}
