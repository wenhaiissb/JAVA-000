package com.geekbang.java.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioSocketChannel;

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
       OioEventLoopGroup group = new OioEventLoopGroup();
        try {
            // 2.创建 Bootstrap
            Bootstrap b = new Bootstrap();
            b.group(group)
                    //
                    .channel(OioSocketChannel.class)
//                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
//                            ch.pipeline().addLast(new EchoClientHandler());
                            ch.pipeline().addLast(new ChannelOutboundHandlerAdapter() {

                            });
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
