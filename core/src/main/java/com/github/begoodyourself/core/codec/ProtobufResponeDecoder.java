package com.github.begoodyourself.core.codec;

import com.github.begoodyourself.core.bo.ResponseWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/12
 */
public class ProtobufResponeDecoder extends ByteToMessageDecoder{
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(new ResponseWrapper().decode(Unpooled.copiedBuffer(in)));
    }
}
