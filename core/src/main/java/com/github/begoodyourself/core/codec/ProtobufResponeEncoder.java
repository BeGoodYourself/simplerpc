package com.github.begoodyourself.core.codec;

import com.github.begoodyourself.core.bo.ResponeWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/12
 */
public class ProtobufResponeEncoder extends MessageToByteEncoder<ResponeWrapper>{
    protected void encode(ChannelHandlerContext ctx, ResponeWrapper msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.encode());
    }
}
