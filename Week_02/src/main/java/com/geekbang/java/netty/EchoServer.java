package com.geekbang.java.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }


    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage：" + EchoServer.class.getSimpleName() + " <port>");
        }
        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }


    public void start() throws Exception {

        // 1.创建 EventLoopGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            // 2.创建 ServerBootstrap
            ServerBootstrap sbs = new ServerBootstrap();
            sbs.group(bossGroup, workGroup)
                    // 3.指定所使用的 NIO 传输 Channel
                    .channel(NioServerSocketChannel.class)
                    // 4.使用指定的端口设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    // 5.添加一个 EchoServerHandler 到子 Channel 的 ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            // 分隔符
                            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                            ch.pipeline()
//                                    .addLast(new DelimiterBasedFrameDecoder(1024, delimiter))
                                    .addLast(new FixedLengthFrameDecoder(20))
                                    .addLast(new StringDecoder())
                                    // EchoServerHandler 被标注为 @Shareable,所以我们可以总是使用同样的实例
                                    // 创建自定义业务 ChannelHandler
                                    .addLast(new EchoServerHandler());

                        }
                    });
            // 6.异步地绑定服务器；调用 sync() 方法阻塞等待直到绑定完成
            ChannelFuture f = sbs.bind().sync();
            // 7.获取 Channel 的 CloseFuture，并且阻塞当前线程直到它完成
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 8.优雅关闭 EventLoopGroup，释放所有的资源
            bossGroup.shutdownGracefully().sync();
            workGroup.shutdownGracefully();
        }

    }
}
