package com.geekbang.java.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AsynchronousSocketChannelDemo
 * @Description
 * @Author 谢文海
 * @Date 2020/11/14 11:22
 * @Version 1.0
 **/
public class AsynchronousSocketChannelDemo {
    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel asc = AsynchronousSocketChannel.open();
        asc.connect(new InetSocketAddress("localhost",8888), "我是附件", new CompletionHandler<Void, String>() {
            @Override
            public void completed(Void result, String attachment) {
                Future<Integer> write = asc.write(ByteBuffer.wrap("你好服务端,我是客户端2".getBytes()));
                try {
                    System.out.println("写入大小 = " + write.get());
                    asc.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, String attachment) {

            }
        });
        TimeUnit.SECONDS.sleep(1);

    }
}
