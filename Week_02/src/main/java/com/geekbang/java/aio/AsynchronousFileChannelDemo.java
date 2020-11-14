package com.geekbang.java.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AsynchronousFileChannelDemo
 * @Description: {@link AsynchronousFileChannel} 方法示例
 * @Author 谢文海
 * @Date 2020/11/14 9:26
 * @Version 1.0
 **/
public class AsynchronousFileChannelDemo {
    public static void main(String[] args) throws Exception {
        // fileLock();
        // read1();
        read2();
    }

    private static void read2() throws Exception {
        AsynchronousFileChannel af = getAsynchronousFileChannel(StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        af.read(byteBuffer, 0, "我是附件", new CompletionHandler<Integer, String>() {
            @Override
            public void completed(Integer result, String attachment) {
                System.out.println("public void completed(Integer result, String attachment) attachment = " + attachment);
                System.out.println(new String(byteBuffer.array(), 0, result));
            }

            @Override
            public void failed(Throwable exc, String attachment) {

            }
        });

        af.close();
    }

    private static void read1() throws Exception {
        AsynchronousFileChannel af = getAsynchronousFileChannel(StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        Future<Integer> future = af.read(byteBuffer, 0);
        System.out.println("read length = " + future.get());
        af.close();
        System.out.println(new String(byteBuffer.array(), 0, future.get()));
    }

    private static void fileLock() throws Exception {
        AsynchronousFileChannel af = getAsynchronousFileChannel(StandardOpenOption.WRITE);
        Future<FileLock> fileLockFuture = af.lock();
        FileLock fileLock = fileLockFuture.get();
        System.out.println("A get lock time = " + System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(8);
        fileLock.release();
        System.out.println("A release lock time = " + System.currentTimeMillis());
        af.close();
    }

    private static AsynchronousFileChannel getAsynchronousFileChannel(StandardOpenOption standardOpenOption) throws IOException {
        Path path = Paths.get("I:\\project\\JAVA-000\\Week_02\\src\\main\\resources\\a.txt");
        return AsynchronousFileChannel.open(path, standardOpenOption);
    }
}
