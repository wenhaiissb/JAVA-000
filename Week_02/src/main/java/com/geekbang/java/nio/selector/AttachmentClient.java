package com.geekbang.java.nio.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @ClassName AttachmentClient
 * @Description
 * @Author 谢文海
 * @Date 2020/11/11 23:57
 * @Version 1.0
 **/
public class AttachmentClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 8888));

        Selector selector = Selector.open();

        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        while (true) {
            System.out.println("自旋 SocketChannel ");
            selector.select();

            for (SelectionKey selectionKey : selector.selectedKeys()) {
                if (selectionKey.isConnectable()) {
                    System.out.println("client is connectable");
                    socketChannel = (SocketChannel) selectionKey.channel();
                    if (socketChannel.isConnectionPending()) {
                        System.out.println("client is connection pending");
                        while (!socketChannel.finishConnect()) {
                            System.out.println("client is not finishConnect");
                        }
                    }
                    socketChannel.register(selector, SelectionKey.OP_WRITE, "我使用附件进行注册,我来自客户端,你好服务端");
                }
                if (selectionKey.isWritable()) {
                    System.out.println("client is writable");
                    ByteBuffer byteBuffer = ByteBuffer.wrap(((String) selectionKey.attachment()).getBytes());
                    socketChannel.write(byteBuffer);
                    socketChannel.close();
                }
            }
        }
    }
}
