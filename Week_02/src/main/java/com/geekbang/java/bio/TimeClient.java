package com.geekbang.java.bio;

import java.io.*;
import java.net.Socket;

/**
 * 阻塞式 I/O client
 *
 * @author wenhai
 * @date 2020/10/25
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;

        if (args != null && args.length > 1) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // ignore use default port 8080
            }
        }
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("localhost", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("QUERY TIME ORDER");
            System.out.println("Send order 2 server succeed.");
            String resp = in.readLine();
            System.out.println("Now is :" + resp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            in = null;
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socket = null;
        }
    }
}
