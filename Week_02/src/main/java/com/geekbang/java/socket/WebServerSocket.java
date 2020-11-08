package com.geekbang.java.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName WebServerSocket
 * @Description
 * @Author 谢文海
 * @Date 2020/11/8 10:39
 * @Version 1.0
 **/
public class WebServerSocket {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String lineData;
        while (!"".equals(lineData = bufferedReader.readLine())) {
            System.out.println(lineData);
        }


        OutputStream outputStream = accept.getOutputStream();
        outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
        outputStream.write("<html><body><a href='http://www.baidu.com'> i am baidu.com welcome you!</a></body></html>".getBytes());
        outputStream.flush();
        inputStream.close();
        outputStream.close();
        accept.close();
        serverSocket.close();
    }
}
