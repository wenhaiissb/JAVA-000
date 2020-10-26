package com.geekbang.java.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞式 I/O Server
 *
 * @author wenhai
 * @date 2020/10/25
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 1) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // ignore use default port 8080
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port：" + port);
            Socket socket = null;
            while (true) {
                TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 1000);
                socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));

//                new Thread(new TimeServerHandler(socket), "Time server handler").start();
            }

        } finally {
            if (server != null) {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}
