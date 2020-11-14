package com.geekbang.java.aio;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CompletionHandlerDemo
 * @Description
 * @Author 谢文海
 * @Date 2020/11/14 10:06
 * @Version 1.0
 **/
public class CompletionHandlerDemo {
    public static void main(String[] args) throws Exception {
        completionHandler();
    }

    private static void completionHandler() throws Exception {
        Path path = Paths.get("I:\\project\\JAVA-000\\Week_02\\src\\main\\resources\\a.txt");
        AsynchronousFileChannel af = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        af.close();
        af.lock("我是附件", new CompletionHandler<FileLock, String>() {
            @Override
            public void completed(FileLock result, String attachment) {
                System.out.println("public void completed(FileLock result, String attachment) attachment = " + attachment);
                try {
                    result.release();
                    af.close();
                    System.out.println("release and close ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, String attachment) {
                System.out.println("public void fialed(Throwalbe exc, String attachment) attachment = " + attachment);
                System.out.println("getMessage = " + exc.getMessage());
                System.out.println("exc.getClass().getName() = " + exc.getClass().getName());
            }
        });
        System.out.println("end time = " + System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(10);
    }
}
