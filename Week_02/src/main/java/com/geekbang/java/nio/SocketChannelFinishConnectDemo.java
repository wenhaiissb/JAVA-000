package com.geekbang.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * {@link SocketChannel#finishConnect()} 方法示例
 *
 * @author wenhai
 * @date 2020/11/10
 */
public class SocketChannelFinishConnectDemo {
    public static void main(String[] args) throws IOException {
        long beginTime = 0;
        long endTime = 0;
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        boolean connectResult = sc.connect(new InetSocketAddress("localhost", 8888));
        Thread t = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
                ServerSocketChannel ssc = ServerSocketChannel.open();
                ssc.bind(new InetSocketAddress("localhost", 8888));
                SocketChannel socketChannel = ssc.accept();
                System.out.println(socketChannel.isConnectionPending());
                socketChannel.close();
                ssc.close();
                System.out.println("Server end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();
        if (!connectResult) {
            System.out.println("connectResult == false");
            while (!sc.finishConnect()) {
                System.out.println("一直在尝试连接");
            }
        }
        sc.close();
    }
}
