package com.geekbang.java.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * @ClassName ServerSocketMethodDemo
 * @Description {@link java.net.ServerSocket} 方法示例
 * @Author 谢文海
 * @Date 2020/11/8 15:51
 * @Version 1.0
 **/
public class ServerSocketMethodDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        System.out.println("Socket 绑定的本地端口 = " + serverSocket.getLocalPort());
        InetSocketAddress socketAddress = (InetSocketAddress) serverSocket.getLocalSocketAddress();
        if (socketAddress != null) {
            System.out.println(socketAddress.getAddress());
            System.out.println(socketAddress.getHostName());
            System.out.println(socketAddress.getPort());
        }
        serverSocket.bind(new InetSocketAddress("192.168.31.188", 9899));
        System.out.println("Socket 绑定的本地端口 = " + serverSocket.getLocalPort());
        socketAddress = (InetSocketAddress) serverSocket.getLocalSocketAddress();
        if (socketAddress != null) {
            System.out.println(socketAddress.getAddress());
            System.out.println(socketAddress.getHostString());
            System.out.println(socketAddress.getHostName());
            System.out.println(socketAddress.getHostString());
            System.out.println(socketAddress.getPort());
        }
    }
}
