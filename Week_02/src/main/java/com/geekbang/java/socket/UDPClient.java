package com.geekbang.java.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 基于 UDP 客户端
 *
 * @author wenhai
 * @date 2020/11/9
 */
public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.connect(new InetSocketAddress("localhost", 8888));
//        byte[] bytes = "1234567890".getBytes();
        byte[] bytes = new byte[66307];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }
}
