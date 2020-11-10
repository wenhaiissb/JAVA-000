package com.geekbang.java.nio;


import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * {@link SocketChannel#isConnectionPending()} 方法 示例
 *
 * @author wenhai
 * @date 2020/11/10
 */
public class SocketChannelConnectDemo {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;

        try {
            socketChannel = SocketChannel.open();
//            socketChannel.configureBlocking(false);
            System.out.println("SocketChannel.isConnectionPending = " + socketChannel.isConnectionPending());
            socketChannel.connect(new InetSocketAddress("localhost", 8888));
            System.out.println("SocketChannel.isConnectionPending = " + socketChannel.isConnectionPending());
            socketChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SocketChannel.isConnectionPending = " + socketChannel.isConnectionPending());
        }
    }
}
