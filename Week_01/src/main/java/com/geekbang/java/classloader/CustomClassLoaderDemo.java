package com.geekbang.java.classloader;

import java.lang.reflect.Method;

/**
 * 自定义类加载实例
 *
 * @author wenhai
 * @date   2020/10/21
 */
public class CustomClassLoaderDemo {
    public static void main(String[] args) throws Exception {
        Class<?> helloClass = new CustomClassLoader().loadClass("Hello");
        Method helloMethod = helloClass.getDeclaredMethod("hello");
        Object hello = helloClass.newInstance();
        helloMethod.invoke(hello);

    }




}
