package com.geekbang.java.netty.decode;

import com.geekbang.java.netty.xml.HttpXmlRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;

public class HttpXmlRequestDecoder extends AbstractHttpXmlDecoder<FullHttpRequest> {

    public HttpXmlRequestDecoder(Class<?> clazz) {
        this(clazz, false);
    }

    public HttpXmlRequestDecoder(Class<?> clazz, boolean isPrint) {
        super(clazz, isPrint);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, FullHttpRequest msg, List<Object> out) throws Exception {
        if (msg.decoderResult().isFailure()) {
            // sendError(ctx, BAD_REQUEST);
        }
        HttpXmlRequest request = new HttpXmlRequest(msg, decode0(ctx, msg.content()));
    }
}
