package com.geekbang.java.netty.encode;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;

import java.io.StringWriter;

public abstract class AbstractHttpXmlEncode<T> extends MessageToMessageEncoder<T> {
    private IBindingFactory factory;
    private StringWriter writer;
    public final static String UTF_8 = "UTF-8";


    protected ByteBuf encode0(ChannelHandlerContext ctx, Object msg) throws Exception {
        factory = BindingDirectory.getFactory(msg.getClass());
        IMarshallingContext mctx = factory.createMarshallingContext();
        mctx.setIndent(2);
        mctx.marshalDocument(msg, UTF_8, null, writer);
        String xmlString = writer.toString();
        writer.close();
        writer = null;
        return Unpooled.copiedBuffer(xmlString, CharsetUtil.UTF_8);

    }
}
