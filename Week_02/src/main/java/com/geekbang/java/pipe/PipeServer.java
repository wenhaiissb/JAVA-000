package com.geekbang.java.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.concurrent.TimeUnit;

/**
 * {@link Pipe} 实现传输
 *
 * @author wenhai
 * @date   2020/11/12
 */
public class PipeServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel write = pipe.sink();
        Pipe.SourceChannel read = pipe.source();


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                for (int i = 1; i < 6; i++) {
                    write.write(ByteBuffer.wrap(("我来自客户端 A 编号为：" + i + "\r\n").getBytes()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                for (int i = 1; i < 6; i++) {
                    write.write(ByteBuffer.wrap(("我来自客户端 B 编号为：" + i + "\r\n").getBytes()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(3);

        write.close();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
        int readLength;
        while ((readLength = read.read(byteBuffer))!=-1){
            System.out.println(new String(byteBuffer.array(), 0, readLength));
        }

        read.close();
    }
}
