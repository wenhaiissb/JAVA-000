package com.geekbang.java.nio.selector;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * {@link Selector} 方法示例
 *
 * @author wenhai
 * @date 2020/11/10
 */
public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        System.out.println(selector);
    }
}
