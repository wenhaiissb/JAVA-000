package com.geekbang.java.nio.selector;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * {@link Selector} 方法示例
 *
 * @author wenhai
 * @date 2020/11/10
 */
public class SelectorDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        // selectNow();
        // wakeup();
        // cancel();
        // selectorInitCollection();
        removeSelectionKey();

    }

    private static void removeSelectionKey()throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8888));
        Selector selector = Selector.open();
        SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
        selector.keys().remove(key);
    }

    private static void selectorInitCollection() throws IOException {
        Selector selector = Selector.open();
        System.out.println("selector.selectedKeys()" + selector.selectedKeys().size());
        System.out.println("selector.keys()()" + selector.keys().size());
        System.out.println("selector.selectedKeys()" + selector.selectedKeys().size());
    }

    private static void cancel() throws IOException {


        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8888));
        ssc.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey selectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println(selectionKey.hashCode());
        ServerSocketChannel ssc2 = ServerSocketChannel.open();
        ssc2.bind(new InetSocketAddress(8088));
        ssc2.configureBlocking(false);
        SelectionKey selectionKey2 = ssc2.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println(selectionKey2.hashCode());

        boolean isRun = true;
        while (isRun) {
            System.out.println("==========");
//            System.out.println(ssc.socket());
            selector.select();
            Set<SelectionKey> keys = selector.keys();
            System.out.println("cancel before keys.size = " + keys.size());
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                InetSocketAddress ip = (InetSocketAddress) channel.getLocalAddress();
                if (ip.getPort() == 8888) {
                    next.cancel();
                } else {
                    next.channel().close();
                }
                System.out.println(next.hashCode());
            }
            System.out.println("cancel after keys.size = " + keys.size());
        }


    }

    private static void wakeup() throws IOException, InterruptedException {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8080));


        ServerSocketChannel ssc2 = ServerSocketChannel.open();
        ssc2.configureBlocking(false);
        ssc2.bind(new InetSocketAddress(8888));


        Selector selector = Selector.open();

        new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            selector.wakeup();
        }).start();
        TimeUnit.SECONDS.sleep(2);
        selector.select();
        System.out.println("Selector.select end");
        System.out.println("Selector.select end");

    }

    private static void selectNow() throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8888));

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        boolean isRun = true;

        while (isRun) {
            System.out.println("while(isRun == true) " + System.currentTimeMillis());
            selector.selectNow();
            System.out.println("selectNow  " + System.currentTimeMillis());
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    Socket socket = channel.socket().accept();
                    socket.close();
                }
                iterator.remove();
            }
        }
        ssc.close();
        SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey key2 = ssc2.register(selector, SelectionKey.OP_ACCEPT);

        boolean isRun = true;
        while (isRun) {
            int keyCount = selector.select();
            Set<SelectionKey> SelectionKeySet = selector.keys();
            Set<SelectionKey> selectedKeySet = selector.selectedKeys();
            System.out.println("keyCount = " + keyCount);
            System.out.println("SelectionKeySet size = " + SelectionKeySet.size());
            System.out.println("selectedKeySet size = " + selectedKeySet.size());
            Iterator<SelectionKey> iterator = selectedKeySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                ServerSocketChannel serverSocketChannel = ((ServerSocketChannel) selectionKey.channel());
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel == null) {
                    System.out.println("打印这条信息证明是连接8888服务器时,重复消费的情况发生");
                    System.out.println("将8080关联的 SelectionKey 对应的 SocketChannel 通道取出来");
                    System.out.println("但是值为 null , socketChannel == null");

                }
                InetSocketAddress localAddress = (InetSocketAddress) serverSocketChannel.getLocalAddress();
                System.out.println(localAddress.getPort() + " 被客户端连接了");
//                iterator.remove();
            }
        }
        ssc.close();
        ssc2.close();
    }
}
