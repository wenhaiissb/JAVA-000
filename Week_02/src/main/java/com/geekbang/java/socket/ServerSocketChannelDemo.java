package com.geekbang.java.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * {@link ServerSocketChannel} 示例
 *
 * @author wenhai
 * @date 2020/10/26
 */
public class ServerSocketChannelDemo {
    public static void main(String[] args) throws IOException {
        // 1. 打开 ServerSocketChannel 用于监听客户端的连接，它是所有客户端连接的父管道
        ServerSocketChannel acceptorSvr = ServerSocketChannel.open();
        System.out.println("ServerSocketChannel default block status = " + acceptorSvr.isBlocking());
        // 2. 绑定监听端口，设置非阻塞模式
        acceptorSvr.bind(new InetSocketAddress(InetAddress.getByName("localhost"), 8888));
        System.out.println("begin " + System.currentTimeMillis());
        acceptorSvr.configureBlocking(false);
        SocketChannel socketChannel = acceptorSvr.accept();
        System.out.println("end " + System.currentTimeMillis());
        if (socketChannel != null) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            while (socketChannel.read(byteBuffer) != -1) {
                System.out.println(new String(byteBuffer.array()));
                byteBuffer.flip();
            }
            socketChannel.close();
        }
        acceptorSvr.close();
    }
}
