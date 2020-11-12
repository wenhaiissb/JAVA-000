package com.geekbang.java.nio.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * {@link java.nio.channels.DatagramChannel} 实现 UPD 客户端
 *
 * @author wenhai
 * @date 2020/11/12
 */
public class DatagramChannelClient {
    public static void main(String[] args) throws Exception {
        DatagramChannel dc = DatagramChannel.open();
        dc.configureBlocking(false);

        Selector selector = Selector.open();

        dc.register(selector, SelectionKey.OP_WRITE);
        selector.select();
        Set<SelectionKey> selectedKeySet = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectedKeySet.iterator();
        while (iterator.hasNext()) {
            SelectionKey sk = iterator.next();
            if (sk.isWritable()) {
                dc.send(ByteBuffer.wrap("你好，服务端！我来自客户端".getBytes()), new InetSocketAddress("localhost", 8080));
                dc.close();

            }
        }
        System.out.println("client end ");
    }
}
