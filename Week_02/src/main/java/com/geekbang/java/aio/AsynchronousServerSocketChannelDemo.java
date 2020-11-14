package com.geekbang.java.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName AsynchronousServerSocketChannelDemo
 * @Description
 * @Author 谢文海
 * @Date 2020/11/14 11:09
 * @Version 1.0
 **/
public class AsynchronousServerSocketChannelDemo {
    public static void main(String[] args) throws Exception {
        AsynchronousServerSocketChannel assc = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8888));
        assc.accept("我是附件", new CompletionHandler<AsynchronousSocketChannel, String>() {
            @Override
            public void completed(AsynchronousSocketChannel result, String attachment) {
                assc.accept("我是附件2",this);
                System.out.println("public void completed(AsynchronousSocketChannel result, String attachment) attachment = " + attachment);
                System.out.println("thread name " + Thread.currentThread().getName());
                ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                Future<Integer> read = result.read(byteBuffer);
                try {
                    System.out.println(new String(byteBuffer.array(), 0, read.get()));
//                    byteBuffer.clear();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, String attachment) {
                System.out.println("public void failed");
            }
        });
       while (true){


       }
    }
}
