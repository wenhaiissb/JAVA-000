package com.geekbang.java.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * {@link ServerSocketChannel} 示例
 *
 * @author wenhai
 * @date   2020/10/26
 */
public class ServerSocketChannelDemo {
    public static void main(String[] args) throws IOException {

        int port = 8080;
        if (args != null && args.length > 1) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // ignore use default port 8080
            }
        }


        // 1. 打开 ServerSocketChannel 用于监听客户端的连接，它是所有客户端连接的父管道
        ServerSocketChannel acceptorSvr = ServerSocketChannel.open();
        // 2. 绑定监听端口，设置非阻塞模式
        acceptorSvr.socket().bind(new InetSocketAddress(InetAddress.getByName("IP"), port));
        acceptorSvr.configureBlocking(false);
        // 3. 创建 Reactor 线程，创建多路复用器并启动线程
        Selector selector = Selector.open();
//        new Thread(new ReactorTask()).start();
    }
}
