package com.geekbang.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketOption;
import java.net.UnknownHostException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 获得支持的 {@link SocketOption} 列表
 *
 * @author wenhai
 * @date   2020/11/10
 */
public class SocketOptionSet {
    public static void main(String[] args) throws IOException {
        Thread t = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                Socket socket = new Socket("localhost", 8888);
                socket.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost", 8888));
        SocketChannel sc = ssc.accept();
        Set<SocketOption<?>> sscSocketOptions = ssc.supportedOptions();
        Set<SocketOption<?>> scSocketOptions1 = sc.supportedOptions();
        System.out.println("ServerSocketChannel supported options");
        sscSocketOptions.forEach(System.out::println);
        System.out.println();
        System.out.println("SocketChannel  supported options");
        scSocketOptions1.forEach(System.out::println);
        sc.close();
        ssc.close();

    }
}
