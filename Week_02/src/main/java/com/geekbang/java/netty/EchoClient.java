package com.geekbang.java.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.net.InetSocketAddress;

public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void start() throws Exception {
        // 1.创建 EventLoopGroup
//        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 2.创建 Bootstrap
            Bootstrap b = new Bootstrap();
            b.group(group)
                    //
                    .channel(NioSocketChannel.class)
//                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
//                            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                            ch.pipeline()
//                                    .addLast(new DelimiterBasedFrameDecoder(1024, delimiter))
                                    .addLast(new FixedLengthFrameDecoder(20))
                                    .addLast(new StringDecoder())
                                    .addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: " + EchoClient.class.getSimpleName() + " <host> <port>");
            return;
        }
        new EchoClient(args[0], Integer.parseInt(args[1])).start();
    }
}
