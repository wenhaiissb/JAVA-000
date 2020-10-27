package com.geekbang.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

/**
 * Netty 中 {@link ChannelFutureListener} 示例
 *
 * @author wenhai
 * @date 2020/10/27
 * @see Future
 * @see ChannelFuture
 * @see ChannelFutureListener
 */
public class ChannelFutureListenerDemo {
    public static void main(String[] args) {
        Channel channel = new NioServerSocketChannel();
        ChannelFuture future = channel.connect(new InetSocketAddress("localhost", 8080));
        future.addListener((ChannelFutureListener) listener -> {
            if (listener.isSuccess()) {
                ByteBuf buffer = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
                ChannelFuture cf = listener.channel().writeAndFlush(buffer);
            } else {
                listener.cause().printStackTrace();
            }
        });

    }
}
