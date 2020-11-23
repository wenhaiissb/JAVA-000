package com.geekbang.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * echo server {@link ChannelHandler}
 *
 * @author wenhai
 * @date 2020/10/27
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf in = (ByteBuf) msg;
//        System.out.println("Server received:" + in.toString(CharsetUtil.UTF_8));
        String boyd = (String) msg;
        System.out.println("This is " + ++counter + " times receive client:" + boyd);
//        boyd += "$_";
        ByteBuf echo = Unpooled.copiedBuffer(boyd.getBytes());
        ctx.writeAndFlush(echo);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
