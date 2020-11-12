package com.geekbang.java.nio.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * {@link java.nio.channels.DatagramChannel} 实现 UDP 通信
 *
 * @author wenhai
 * @date 2020/11/12
 */
public class DatagramChannelServer {
    public static void main(String[] args) throws Exception {
        DatagramChannel dc = DatagramChannel.open();
        dc.configureBlocking(false);
        dc.bind(new InetSocketAddress(8080));

        Selector selector = Selector.open();


        dc.register(selector, SelectionKey.OP_READ);

        selector.select();


        Set<SelectionKey> selectedKeySet = selector.selectedKeys();

        Iterator<SelectionKey> iterator = selectedKeySet.iterator();

        while (iterator.hasNext()) {
            SelectionKey selectionKey = iterator.next();

            if (selectionKey.isReadable()) {
                dc = (DatagramChannel) selectionKey.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
                dc.receive(byteBuffer);
                System.out.println(new String(byteBuffer.array(), 0, byteBuffer.position()));
            }
            iterator.remove();
        }
        dc.close();

    }
}
