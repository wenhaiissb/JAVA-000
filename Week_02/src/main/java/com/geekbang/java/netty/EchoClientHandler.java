package com.geekbang.java.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * echo 客户端 {@link ChannelHandler}
 *
 * @author wenhai
 * @date 2020/10/27
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends ChannelInboundHandlerAdapter{
    private int counter;
//    static final String ECHO_REQ ="Hi,谢文海,Welcome to Netty.$_";
    static final String ECHO_REQ ="Hi,谢文海,Welcome to Netty";
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ, CharsetUtil.UTF_8));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        System.out.println("Client received:" + msg.toString(CharsetUtil.UTF_8));
        System.out.println("This is " + ++counter + " times receive server:[" + msg + "]");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
