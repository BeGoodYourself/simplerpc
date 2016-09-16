package com.github.begoodyourself.core.bo;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.lang3.StringUtils;


/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/12
 */
public class ResponseWrapper extends MessageWrapper<ResponseWrapper>{
    private short errcode;
    private String errMsg;

    public ByteBuf encode() {
        byte[] contentBytes = content == null ? new byte[0] : content.toByteArray();
        byte[] errMessageBytes =errMsg == null ? new byte[0] : errMsg.getBytes();
        byte[] messageIdBytes = messageId.getBytes();
        byte[] protoNameBytes = content == null ? new byte[0] : content.getClass().getName().getBytes();
        ByteBuf buf = Unpooled.buffer(contentBytes.length + 2 + errMessageBytes.length + messageIdBytes.length + protoNameBytes.length + 3 + 4);
        buf.writeInt(contentBytes.length + 2 + errMessageBytes.length + messageIdBytes.length + protoNameBytes.length + 3 );

        buf.writeByte(messageIdBytes.length);
        buf.writeBytes(messageIdBytes);

        buf.writeShort(errcode);

        buf.writeByte(errMessageBytes.length);
        buf.writeBytes(errMessageBytes);

        buf.writeByte(protoNameBytes.length);
        buf.writeBytes(protoNameBytes);

        buf.writeBytes(contentBytes);
        return buf;
    }

    public ResponseWrapper decode(ByteBuf src) {
        messageId = new String(readBytes(src));
        errcode = src.readShort();
        errMsg = new String(readBytes(src));
        String protoName = new String(readBytes(src));
        if(StringUtils.isEmpty(protoName))
            return this;
        GeneratedMessageV3 proto = null;
        try {
            proto = get(protoName);
            byte[] dst = new byte[src.readableBytes()];
            src.readBytes(dst, 0, dst.length);
            content = (GeneratedMessageV3) proto.toBuilder().mergeFrom(dst).build();
            return this;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public short getErrcode() {
        return errcode;
    }

    public void setErrcode(short errcode) {
        this.errcode = errcode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
