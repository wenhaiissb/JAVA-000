package com.geekbang.java.aio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @ClassName SocketClient
 * @Description
 * @Author 谢文海
 * @Date 2020/11/14 11:20
 * @Version 1.0
 **/
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8888);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("你好服务端,我是来自客户端1".getBytes());
        outputStream.flush();
        outputStream.close();
        socket.close();

    }
}
