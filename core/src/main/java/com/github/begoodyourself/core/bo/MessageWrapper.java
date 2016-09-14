package com.github.begoodyourself.core.bo;

import com.github.begoodyourself.util.ContextUtil;
import com.google.protobuf.GeneratedMessageV3;
import io.netty.buffer.ByteBuf;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/12
 */
public abstract class MessageWrapper<T> {
    protected GeneratedMessageV3 content;
    protected String messageId;
    public abstract ByteBuf encode();

    public abstract T decode(ByteBuf src);

    protected byte[] readBytes(ByteBuf buf){
        byte[] bytes = new byte[buf.readByte()];
        buf.readBytes(bytes, 0, bytes.length);
        return bytes;
    }

    protected GeneratedMessageV3 get(String clsName) throws  Exception{
        return ContextUtil.get(clsName);
    }

    public GeneratedMessageV3 getContent() {
        return content;
    }

    public void setContent(GeneratedMessageV3 content) {
        this.content = content;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
