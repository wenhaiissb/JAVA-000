package com.geekbang.java.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * {@link DatagramChannel#connect(SocketAddress)} 实现 UDP 功能
 *
 * @author wenhai
 * @date 2020/11/12
 */
public class DatagramChannelClientByConnect {

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        DatagramChannel dc = DatagramChannel.open();
        dc.configureBlocking(false);
        dc.connect(new InetSocketAddress("localhost", 8080));
        dc.register(selector, SelectionKey.OP_WRITE);
        selector.select();

        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectedKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey sk = iterator.next();
            if (sk.isWritable()) {
                dc.write(ByteBuffer.wrap("你好，服务端。我是客户端！".getBytes()));
                dc.close();
            }

        }
        System.out.println("client end");
    }
}
