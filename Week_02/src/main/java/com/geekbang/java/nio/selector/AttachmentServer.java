package com.geekbang.java.nio.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @ClassName AttachmentServer
 * @Description: {@link ServerSocketChannel#register(Selector, int, Object)} attachment 示例
 * @Author 谢文海
 * @Date 2020/11/11 23:35
 * @Version 1.0
 **/
public class AttachmentServer {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8888));

        Selector selector = Selector.open();

        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            System.out.println("自旋轮询 SelectedSet ");
            selector.select();
            Set<SelectionKey> selectedKeySet = selector.selectedKeys();
            for (SelectionKey selectionKey : selectedKeySet) {
                if (selectionKey.isAcceptable()) {
                    System.out.println("server is acceptable");
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socket = channel.accept();
                    socket.configureBlocking(false);
                    socket.register(selector, SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()) {
                    SocketChannel socket = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(12);
                    int readContentLength;
                    System.out.println(" read before ByteBuffer position = " + byteBuffer.position() + " ,capacity = " + byteBuffer.capacity() + " , limit = " + byteBuffer.limit());

                    while ((readContentLength = socket.read(byteBuffer)) != -1) {
                        System.out.println(" read after ByteBuffer position = " + byteBuffer.position() + " ,capacity = " + byteBuffer.capacity() + " , limit = " + byteBuffer.limit());
                        System.out.println(new String(byteBuffer.array(), 0, readContentLength));
                        byteBuffer.rewind();
                        System.out.println(" read before ByteBuffer position = " + byteBuffer.position() + " ,capacity = " + byteBuffer.capacity() + " , limit = " + byteBuffer.limit());
                    }
                    socket.close();
                }
                selectionKey.cancel();
            }
        }

    }
}
