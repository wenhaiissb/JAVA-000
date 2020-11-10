package com.geekbang.java.nio.channel;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * {@link FileChannel#transferTo} 方法演示
 *
 * @author wenhai
 * @date   2020/11/10
 */
public class FileChannelTransferToMethod {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        SocketChannel socketChannel = null;
        ssc.bind(new InetSocketAddress(8888));
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        boolean isRun = true;
        while (isRun) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey sk = iterator.next();
                iterator.remove();
                if (sk.isAcceptable()) {
                    socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                }
                if (sk.isWritable()) {
                    RandomAccessFile file = new RandomAccessFile("C:\\Users\\wenhai\\Downloads\\AdobeReader.zip", "rw");
                    System.out.println("file.length() = " + file.length());
                    FileChannel fileChannel = file.getChannel();
                    fileChannel.transferTo(0, file.length(), socketChannel);
                    fileChannel.close();
                    file.close();
                    socketChannel.close();
                }
            }
        }
        ssc.close();
    }
}
