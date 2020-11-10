package com.geekbang.java.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName Server
 * @Description
 * @Author 谢文海
 * @Date 2020/11/8 11:01
 * @Version 1.0
 **/
public class Server {
    public static void main(String[] args) throws IOException {

        // setRcvBuffer();
        // getRemotePort();
        // shutdownMethod();
        // setSoLinger();
        setTimeout();


    }

    private static void setTimeout() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8881);
        Socket accept = serverSocket.accept();
        System.out.println("ServerSocket.accept 获得 Socket 的 默认 SO_TIMEOUT = " + accept.getSoTimeout());
        InputStream inputStream = accept.getInputStream();
        accept.setSoTimeout(3000);
        System.out.println("Socket SO_TIMEOUT = " + accept.getSoTimeout());
        byte[] buffer = new byte[1024];
        int readLength;
        long start = System.currentTimeMillis();
        try {
            while ((readLength = inputStream.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, readLength));
            }
        } finally {
            System.out.println("read time = " + (System.currentTimeMillis() - start) / 1000);
        }
    }

    private static void setSoLinger() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8881);
        Socket accept = serverSocket.accept();
        System.out.println("ServerSocket.accept 获得 Socket 的 默认 SO_LINGER = " + accept.getSoLinger());
        accept.setSoLinger(false, 0);
        System.out.println("Socket SO_LINGER = " + accept.getSoLinger());
        OutputStream out = accept.getOutputStream();
        for (int i = 0; i < 10; i++) {
            out.write("123123123123333333333333333333313123122222222222222222222222222222222221".getBytes());
        }
        out.write("end".getBytes());
        System.out.println("socket close before = " + System.currentTimeMillis());
        accept.close();
        System.out.println("Socket close after = " + System.currentTimeMillis());
        serverSocket.close();
    }

    private static void shutdownMethod() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8881);
        Socket accept = serverSocket.accept();
        OutputStream outputStream = accept.getOutputStream();
        outputStream.write("Hello, client".getBytes());
        accept.shutdownOutput();
        outputStream.write("Hello, client".getBytes());
        accept.getOutputStream();

    }

    private static void getRemotePort() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8881);
        Socket accept = serverSocket.accept();
        System.out.println("remote port = " + accept.getPort());
        System.out.println("local port = " + accept.getLocalPort());
        accept.close();
        serverSocket.close();
    }

    /**
     * 设置 ServerSocket 接受的套接字
     */
    private static void setRcvBuffer() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        System.out.println(" ServerSocket default rcvBuffer size = " + serverSocket.getReceiveBufferSize());
        serverSocket.setReceiveBufferSize(66);
        serverSocket.bind(new InetSocketAddress(8888));
        System.out.println(" Set rcvBuffer after ServerSocket  rcvBuffer size = " + serverSocket.getReceiveBufferSize());

    }

    /**
     * 客户端 与 服务度 ObjectInputStream/ObjectOutputStream 顺序会导致阻塞
     *
     * @throws IOException
     */
    private static void oisAndOusSequenceBlock() throws IOException {
        byte[] buffer = new byte[20];
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("ServerSocket.timeOut = " + serverSocket.getSoTimeout());
        serverSocket.setSoTimeout(2000);
        System.out.println("ServerSocket.timeOut = " + serverSocket.getSoTimeout());
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        int readLength;
        while ((readLength = inputStream.read(buffer)) != -1) {
            String s = new String(buffer, 0, readLength);
            System.out.println(s);
        }
        inputStream.close();
        // OutputStream outputStream = socket.getOutputStream();

        socket.close();
        serverSocket.close();
    }
}
