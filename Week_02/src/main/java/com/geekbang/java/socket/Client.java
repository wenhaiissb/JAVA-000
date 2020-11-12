package com.geekbang.java.socket;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Client
 * @Description
 * @Author 谢文海
 * @Date 2020/11/8 11:20
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) throws Exception {
//        Socket client = new Socket("localhost", 9999);
//        InputStream inputStream = client.getInputStream();
//        OutputStream outputStream = client.getOutputStream();
//        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//        outputStream.write("我是中国人".getBytes());
//        outputStream.close();
//        TimeUnit.SECONDS.sleep(100);
        Socket socket = new Socket();
//        socket.bind(new InetSocketAddress("localhost",8888));
        long start = System.currentTimeMillis();
        try {
            socket.setReceiveBufferSize(1);
            socket.connect(new InetSocketAddress("localhost",8888));
            System.out.println("local port = " + socket.getLocalPort());
            System.out.println("remote port = " +socket.getPort());
            System.out.println("sendBuffer size = " + socket.getSendBufferSize());
            TimeUnit.SECONDS.sleep(4);

//            InputStream inputStream = socket.getInputStream();
//            byte[] buffer = new byte[1];
//            int length;
//
//            while ((length = inputStream.read(buffer)) != -1) {
//                System.out.print(new String(buffer, 0, length));
//            }
        } finally {
            System.out.println();
            System.out.println((start - System.currentTimeMillis()) / 1000);
        }

    }
}
