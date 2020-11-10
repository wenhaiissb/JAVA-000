package com.geekbang.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 简易 {@link ServerSocketChannel} 服务端
 *
 * @author wenhai
 * @date   2020/11/10
 */
public class SimpleConnectServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost", 8888));
        SocketChannel socketChannel = ssc.accept();
        System.out.println(socketChannel.isConnectionPending());
        socketChannel.close();
        ssc.close();
        System.out.println("Server end");

    }
}
