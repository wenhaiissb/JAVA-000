package com.geekbang.java.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

/**
 * {@link Bootstrap} 示例
 *
 * @author wenhai
 * @date   2020/11/20
 */
public class BootstrapDemo {
    public static void main(String[] args) {
        AttributeKey<Integer> id = AttributeKey.newInstance("ID");

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap  = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
                        System.out.println("Received data");
                    }
                    @Override
                    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                        System.out.println(ctx.channel().attr(id).get());
                    }
                });

        bootstrap.option(ChannelOption.SO_KEEPALIVE, true).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        bootstrap.attr(id, 12345);
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost", 80));
        future.addListener((ChannelFutureListener) listener -> {
            if (listener.isSuccess()) {
                System.out.println("Connection established");
            }else {
                System.out.println("Connection attempt failed");
                listener.cause().printStackTrace();
            }
        });
        future.syncUninterruptibly();
    }
}
