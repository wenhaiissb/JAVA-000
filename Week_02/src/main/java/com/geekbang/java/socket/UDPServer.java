package com.geekbang.java.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 基于 UDP
 *
 * @author wenhai
 * @date   2020/11/9
 */
public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        byte[] buffer = new byte[10];
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(datagramPacket);
        datagramSocket.close();
        System.out.println("包中数据的长度：" + datagramPacket.getLength());
        System.out.println("包中数据的数据: " + new String(datagramPacket.getData(), 0, datagramPacket.getLength()));
    }
}
