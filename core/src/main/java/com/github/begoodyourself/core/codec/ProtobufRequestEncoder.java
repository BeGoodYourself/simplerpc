package com.github.begoodyourself.core.codec;

import com.github.begoodyourself.core.bo.RequestWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/12
 */
public class ProtobufRequestEncoder extends MessageToByteEncoder<RequestWrapper>{
    protected void encode(ChannelHandlerContext ctx, RequestWrapper msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.encode());
    }
}
