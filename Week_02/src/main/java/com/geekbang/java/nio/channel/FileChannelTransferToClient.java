package com.geekbang.java.nio.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * {@link FileChannel#transferTo(long, long, WritableByteChannel)} 方法演示客户端
 *
 * @author wenhai
 * @date   2020/11/10
 */
public class FileChannelTransferToClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 8888));
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        boolean isRun = true;
        while (isRun) {
            System.out.println("begin selector");
            if (socketChannel.isOpen()) {
                selector.select();
                System.out.println(" end selector");
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isConnectable()) {
                        while (!socketChannel.finishConnect()) {
                            System.out.println("一直在连接");
                        }
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                    if (selectionKey.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
                        int readLength;
                        int count = 0;
                        while ((readLength = socketChannel.read(buffer)) != -1) {
                            count += readLength;
                            System.out.println("count = " + count + " readLength = " + readLength);
                            buffer.clear();
                        }
                        System.out.println("读取结束");
                        socketChannel.close();
                    }
                }
            }
        }
    }
}
