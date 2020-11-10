package com.geekbang.java.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * 使用 {@link Selector} 多路复用
 *
 * @author wenhai
 * @date 2020/11/10
 */
public class MultiplexorSocket {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false); // true:IllegalBlockingModeException
        ServerSocket socket = ssc.socket();
        socket.bind(new InetSocketAddress("localhost", 8888));
        Selector selector = Selector.open();
        Selector selector2 = Selector.open();
        SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey key2 = ssc.register(selector2, SelectionKey.OP_ACCEPT);
        System.out.println("selector = " + selector);
        System.out.println("selector2 = " + selector2);
        System.out.println("Key = " + key);
        System.out.println("key2 = " + key2);
        socket.close();
        ssc.close();


    }
}
