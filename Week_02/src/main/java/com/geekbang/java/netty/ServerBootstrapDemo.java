package com.geekbang.java.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * {@link ServerBootstrap} 示例
 *
 * @author wenhai
 * @date   2020/11/20
 */
public class ServerBootstrapDemo {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(group, group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        System.out.println("Received data");
                    }
                });

        ChannelFuture future = bootstrap.bind(80);
        future.addListener((ChannelFutureListener) listener -> {
            if (listener.isSuccess()) {
                System.out.println("Server bound");
            } else {
                System.out.println("Bound attempt failed");
                listener.cause().printStackTrace();
            }
        });
    }
}
