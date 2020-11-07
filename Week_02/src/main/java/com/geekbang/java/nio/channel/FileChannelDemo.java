package com.geekbang.java.nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName FileChannelDemo
 * @Description {@link FileChannel} 演示
 * @Author 谢文海
 * @Date 2020/11/3 23:59
 * @Version 1.0
 **/
public class FileChannelDemo {
    private static FileOutputStream fosRef;
    private static FileInputStream fisRef;
    private static FileChannel inFileChannel;
    private static FileChannel outFileChannel;

//    static {
//        try {
//            fosRef = new FileOutputStream(new File(".\\Week_02\\src\\main\\resources/a.txt"));
//            fisRef = new FileInputStream(new File(".\\Week_02\\src\\main\\resources/b.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        outFileChannel = fosRef.getChannel();
//        inFileChannel = fisRef.getChannel();
//    }

    public static void main(String[] args) throws Exception {
        // writeMethod();
        // writeSynchronous();
        // readMethod();
        // bulkWriteMethod();
        // bulkReadMethod();
        // bulkRangeWriteMethod();
        // bulkRangeReadMethod();
        // setChannelPosition();
        // truncateMethod();
        // aTransferToBMethod();
        // aTransferFromBMethod();
        // fileLockMethod();
        // shareLockCannotWrite();
        // readOnlyMapMode();
        // writeMapMode();
        // privateMapMode();
        // loadAndIsLoaded();
        openFile();
    }

    private static void openFile() throws IOException, InterruptedException {
        File file = new File("./d.txt");
        Path path = file.toPath();
        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.DELETE_ON_CLOSE, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        fileChannel.write(ByteBuffer.wrap("1234".getBytes()));
        TimeUnit.SECONDS.sleep(10);
    }

    private static void loadAndIsLoaded() throws Exception {
        RandomAccessFile file = getRandomAccessFile("a");
        FileChannel fileChannel = file.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1);
        System.out.println(mappedByteBuffer.isLoaded());
        mappedByteBuffer.load();
        System.out.println(mappedByteBuffer.isLoaded());
    }

    private static void privateMapMode() throws Exception {
        RandomAccessFile file = getRandomAccessFile("a");
        FileChannel fileChannel = file.getChannel();
        System.out.println("fileChannel before map size = " + fileChannel.size());
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.PRIVATE, 0, 11);
        System.out.println("fileChannel after map size = " + fileChannel.size());
        System.out.println("MappedByteBuffer(MapMode.PRIVATE) initialization position = " + mappedByteBuffer.position() + " , limit = " + mappedByteBuffer.limit() + " , capacity = " + mappedByteBuffer.capacity());
        for (int i = 0; i < mappedByteBuffer.limit(); i++) {
            System.out.print((char) mappedByteBuffer.get());
        }
        System.out.println();
        System.out.println("MappedByteBuffer(MapMode.PRIVATE) position = " + mappedByteBuffer.position() + " , limit = " + mappedByteBuffer.limit() + " , capacity = " + mappedByteBuffer.capacity());
        mappedByteBuffer.position(7);
        mappedByteBuffer.put((byte) 'a');
        mappedByteBuffer.put((byte) 'c');
        mappedByteBuffer.put((byte) 'd');
        mappedByteBuffer.put((byte) 'e');
        System.out.println("MappedByteBuffer(MapMode.PRIVATE) put (a b c d) after fileChannel size = " + fileChannel.size());
        ByteBuffer bb = ByteBuffer.allocate(20);
        fileChannel.read(bb);
        System.out.println(Arrays.toString(bb.array()));
        mappedByteBuffer.rewind();
        for (int i = 0; i < mappedByteBuffer.limit(); i++) {
            System.out.print((char) mappedByteBuffer.get());
        }
    }

    private static void writeMapMode() throws Exception {
        RandomAccessFile file = getRandomAccessFile("a");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(100);
        System.out.println("fileChannel position = " + fileChannel.position());
        fileChannel.read(bb);
        System.out.println("fileChannel position = " + fileChannel.position());
        fileChannel.position(0);
        System.out.println("fileChannel position = " + fileChannel.position());
        System.out.println(Arrays.toString(bb.array()));
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 3);
        System.out.println("MappedByteBuffer position = " + mappedByteBuffer.position() + " , limit = " + mappedByteBuffer.limit() + " , capacity = " + mappedByteBuffer.capacity());
        for (int i = 0; i < mappedByteBuffer.limit(); i++) {
            System.out.print((char) mappedByteBuffer.get());
        }
        mappedByteBuffer.put(1, (byte) 'h');
        System.out.println();
        System.out.println("MappedByteBuffer position = " + mappedByteBuffer.position() + " , limit = " + mappedByteBuffer.limit() + " , capacity = " + mappedByteBuffer.capacity());
        mappedByteBuffer.flip();
        for (int i = 0; i < mappedByteBuffer.limit(); i++) {
            System.out.print((char) mappedByteBuffer.get());
        }
        System.out.println();
        System.out.println(Arrays.toString(bb.array()));
        fileChannel.close();
        file.close();
    }

    private static void readOnlyMapMode() throws Exception {
        RandomAccessFile file = getRandomAccessFile("a");
        FileChannel fileChannel = file.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, 3);
        fileChannel.close();
        file.close();
        System.out.println("MappedByteBuffer position = " + mappedByteBuffer.position() + " , limit = " + mappedByteBuffer.limit() + " , capacity = " + mappedByteBuffer.capacity());
        for (int i = 0; i < mappedByteBuffer.limit(); i++) {
            System.out.println((char) mappedByteBuffer.get());
        }
        mappedByteBuffer.put((byte) 1);

    }

    private static void shareLockCannotWrite() throws Exception {
        RandomAccessFile randomAccessFile = getRandomAccessFile("a");
        FileChannel fileChannel = randomAccessFile.getChannel();
        fileChannel.lock(1, 2, true);
//        fileChannel.write(ByteBuffer.wrap("w2".getBytes()));
        TimeUnit.SECONDS.sleep(1000);
    }

    public static void fileLockMethod() throws Exception {
//        RandomAccessFile fileA = getRandomAccessFile("a");
        FileOutputStream fileA = new FileOutputStream(".\\Week_02\\src\\main\\resources\\a.txt");
        FileChannel fileChannel = fileA.getChannel();
        ByteBuffer bb = ByteBuffer.wrap("123123".getBytes());
        fileChannel.write(bb);
//        System.out.println(Thread.currentThread().getName() + " A begin ");
        Thread a = new Thread(() -> {
            try {
                System.out.println("fileChannel.lock start");
                FileLock lock = fileChannel.lock(1, 2, false);
                System.out.println("fileChannel.lock end");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        Thread b = new Thread(() -> {
            try {
                System.out.println("fileChannel.close start");
                fileChannel.close();
                System.out.println("fileChannel.close end");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        a.start();
//        TimeUnit.SECONDS.sleep(1);
        b.start();

//        System.out.println(Thread.currentThread().getName() + "B end ");
        TimeUnit.SECONDS.sleep(199);
        fileChannel.close();
        fileA.close();

    }

    private static void aTransferFromBMethod() throws Exception {
        RandomAccessFile fileA = getRandomAccessFile("a");
        RandomAccessFile fileB = getRandomAccessFile("b");
        FileChannel fileChannelA = fileA.getChannel();
        FileChannel fileChannelB = fileB.getChannel();
        System.out.println("fileA  transferFrom method before position = " + fileChannelA.position() + " ,size = " + fileA.length());
        System.out.println("fileB  transferFrom method before position = " + fileChannelB.position() + " ,size = " + fileB.length());
        fileChannelA.transferFrom(fileChannelB, 20, 100);
        System.out.println("fileA  transferFrom method after position = " + fileChannelA.position() + " ,size = " + fileA.length());
        System.out.println("fileB  transferFrom method after position = " + fileChannelB.position() + " ,size = " + fileB.length());
    }

    public static RandomAccessFile getRandomAccessFile(String filename) throws FileNotFoundException {
        return new RandomAccessFile(".\\Week_02\\src\\main\\resources\\" + filename + ".txt", "rw");
    }

    private static void aTransferToBMethod() throws Exception {
        RandomAccessFile fileA = getRandomAccessFile("a");
        RandomAccessFile fileB = getRandomAccessFile("b");
        FileChannel fileChannelB = fileB.getChannel().position(9);
        FileChannel fileChannelA = fileA.getChannel();
        System.out.println("fileA  transferTo method before position = " + fileChannelA.position());
        System.out.println("fileB  transferTo method before position = " + fileChannelB.position());
        fileChannelA.transferTo(1, 100, fileChannelB);
        System.out.println("fileB  transferTo method after position = " + fileChannelB.position());
        System.out.println("fileA  transferTo method after position = " + fileChannelA.position());
        fileA.close();
        fileB.close();
    }

    /**
     * {@link FileChannel#truncate(long)} 从文件开始截取
     */
    private static void truncateMethod() throws IOException {
        ByteBuffer bb = ByteBuffer.wrap("12345678".getBytes());
        outFileChannel.write(bb);
        System.out.println("A size = " + outFileChannel.size() + " , position = " + outFileChannel.position());
        // 小于文件大小: 123
        outFileChannel.position(1);
        outFileChannel.truncate(3);
        // 等于文件大小: 12345678
        // outFileChannel.truncate(outFileChannel.size());
        // 大于文件大小: 12345678
        // outFileChannel.truncate(outFileChannel.size());
        // 文件位置大于给定大小,则将位置设置为该大小
        // outFileChannel.position(1000);
        System.out.println("B size = " + outFileChannel.size() + " , position = " + outFileChannel.position());
        // outFileChannel.truncate(3);
        // System.out.println("C size = " + outFileChannel.size() + " , position = " + outFileChannel.position());
        outFileChannel.close();
    }

    /**
     * 设置 Channel 的 position 的值,可以设置大于该文件当前大小的值是合法的,但这不会改变文件的大小
     * {@link FileChannel#position(long)}
     */
    private static void setChannelPosition() throws Exception {

        ByteBuffer bb = ByteBuffer.wrap("abcd".getBytes());
        ByteBuffer bb2 = ByteBuffer.wrap("cde".getBytes());
        FileOutputStream fileOutputStream = new FileOutputStream(new File(".\\Week_02\\src\\main\\resources/a.txt"));
        FileChannel fileChannel = fileOutputStream.getChannel();
        System.out.println("A FileChannel position = " + fileChannel.position() + " size = " + fileChannel.size());
        fileChannel.write(bb);
        System.out.println("B FileChannel position = " + fileChannel.position() + " size = " + fileChannel.size());
        fileChannel.position(2);
        System.out.println("C FileChannel position = " + fileChannel.position() + " size = " + fileChannel.size());
        fileChannel.write(bb2);
        System.out.println("D FileChannel position = " + fileChannel.position() + " size = " + fileChannel.size());
        bb.position(3);
        fileChannel.write(bb, 10);
        System.out.println("F FileChannel position = " + fileChannel.position() + " size = " + fileChannel.size());
        fileChannel.close();
        fileOutputStream.close();


    }

    /**
     * {@link FileChannel#write(ByteBuffer[], int, int)} 批量范围写
     */
    private static void bulkRangeWriteMethod() throws IOException {
        ByteBuffer bb1, bb2, bb3, bb4;
        bb1 = ByteBuffer.wrap("byteBuffer1\r\n".getBytes());
        bb2 = ByteBuffer.wrap("byteBuffer2\r\n".getBytes());
        bb3 = ByteBuffer.wrap("byteBuffer3\r\n".getBytes());
        bb4 = ByteBuffer.wrap("byteBuffer4\r\n".getBytes());
        ByteBuffer[] byteBufferArray = {bb1, bb2, bb3, bb4};
        outFileChannel.write(byteBufferArray, 0, 2);
    }

    /**
     * {@link FileChannel#write(ByteBuffer[], int, int)} 批量范围写
     */
    private static void bulkRangeReadMethod() throws IOException {
        ByteBuffer bb1, bb2, bb3, bb4;
        bb1 = ByteBuffer.allocate(12);
        bb2 = ByteBuffer.allocate(12);
        bb3 = ByteBuffer.allocate(12);
        bb4 = ByteBuffer.allocate(12);
        ByteBuffer[] byteBufferArray = {bb1, bb2, bb3, bb4};
        inFileChannel.read(byteBufferArray, 0, 2);
        bb1.flip();
        bb2.flip();
        bb3.flip();
        bb4.flip();
        System.out.println("bb1:");
        for (int i = 0; i < bb1.limit(); i++) {
            System.out.print((char) bb1.get() + "  ");
        }
        System.out.println();
        System.out.println("bb2:");
        for (int i = 0; i < bb2.limit(); i++) {
            System.out.print((char) bb2.get() + "  ");
        }
        System.out.println();
        System.out.println("bb3:");
        for (int i = 0; i < bb3.limit(); i++) {
            System.out.print((char) bb3.get() + "  ");
        }
        System.out.println();
        System.out.println("bb4:");
        for (int i = 0; i < bb4.limit(); i++) {
            System.out.print((char) bb4.get() + "  ");

        }
        System.out.println();
    }

    /**
     * {@link FileChannel#read(ByteBuffer[])} 批量读
     */
    private static void bulkReadMethod() throws IOException {
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(8);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(8);
        ByteBuffer[] byteBuffers = {byteBuffer1, byteBuffer2};
        long readLength = inFileChannel.read(byteBuffers);
        System.out.println("readLength = " + readLength);
        System.out.println(Arrays.toString(byteBuffer1.array()));
        System.out.println(Arrays.toString(byteBuffer2.array()));
        inFileChannel.close();
        fisRef.close();

    }

    /**
     * {@link FileChannel#write(ByteBuffer[])} 批量 写
     */
    private static void bulkWriteMethod() throws IOException {
        outFileChannel.write(ByteBuffer.wrap("123456".getBytes()));
        outFileChannel.position(3);
        ByteBuffer byteBuffer1 = ByteBuffer.wrap("ooooo1".getBytes());
        ByteBuffer byteBuffer2 = ByteBuffer.wrap("ooooo2".getBytes());
        ByteBuffer[] byteBuffers = {byteBuffer1, byteBuffer2};
        outFileChannel.write(byteBuffers);
        outFileChannel.close();
        fosRef.close();
    }

    private static void readMethod() throws Exception {
        inFileChannel.position(2);
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.position(3);
        int readLength = inFileChannel.read(byteBuffer);
        System.out.println(readLength);
        System.out.println(Arrays.toString(byteBuffer.array()));
        TimeUnit.SECONDS.sleep(3);
        inFileChannel.close();
        fisRef.close();
    }

    /**
     * 多线程同时写线程是安全的
     *
     * @throws Exception
     */
    private static void writeSynchronous() throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(() -> {
                try {
                    ByteBuffer byteBuffer = ByteBuffer.wrap("abcde\r\n".getBytes());
                    outFileChannel.write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Thread t2 = new Thread(() -> {
                try {
                    ByteBuffer byteBuffer = ByteBuffer.wrap("我是中国人\r\n".getBytes());
                    outFileChannel.write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t1.start();
            t2.start();
        }
        TimeUnit.SECONDS.sleep(3);
        outFileChannel.close();
        fosRef.close();
    }

    private static void writeMethod() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(".\\Week_02\\src\\main\\resources/a.txt"));
        System.out.println(Charset.defaultCharset());
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.wrap("我是中国人".getBytes("UTF8"));
        System.out.println("A fileChannel.position = " + fileChannel.position());
        System.out.println("write() 1 返回值 = " + fileChannel.write(byteBuffer));
        System.out.println("B fileChannel.position = " + fileChannel.position());
        fileChannel.position(3);
        byteBuffer.rewind();
        System.out.println("write() 2 返回值 = " + fileChannel.write(byteBuffer));
        System.out.println("C fileChannel.position = " + fileChannel.position());
        fileChannel.close();
        fileOutputStream.close();
    }
}
