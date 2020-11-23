package com.geekbang.java.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class NettyTimeClient {
    private static int counter;

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline()
                                    .addLast(new ChannelInboundHandlerAdapter() {
                                                 @Override
                                                 public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                                     byte[] req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
                                                     ByteBuf message;
                                                     for (int i = 0; i < 100; i++) {
                                                         message = Unpooled.buffer(req.length);
                                                         message.writeBytes(req);
                                                         ctx.writeAndFlush(message);
                                                     }
                                                 }

                                                 @Override
                                                 public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                                     ByteBuf respBuf = (ByteBuf) msg;
//                                                     byte[] respBytes = new byte[respBuf.readableBytes()];
//                                                     respBuf.readBytes(respBytes);
//                                                     System.out.println("Now is :" + new String(respBytes, CharsetUtil.UTF_8) + " ; the counter is:" + ++counter);
                                                     String body = (String) msg;
                                                     System.out.println("Now is :" + body + " ; the counter is:" + ++counter);
                                                 }

                                                 @Override
                                                 public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                                     System.out.println("Unexpected exception from downstream :" + cause.getMessage());
                                                     ctx.close();
                                                 }
                                             }
                                    );
                        }
                    });
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8080).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}
