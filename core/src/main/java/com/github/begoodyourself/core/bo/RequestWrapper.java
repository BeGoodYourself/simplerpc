package com.github.begoodyourself.core.bo;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/12
 */
public class RequestWrapper extends MessageWrapper<RequestWrapper>{
    private String serviceName;
    private String methodName;

    public ByteBuf encode() {
        byte[] bodies = content.toByteArray();
        byte[] serviceBytes = serviceName.getBytes();
        byte[] methodBytes = methodName.getBytes();
        byte[] protoNameBytes = content.getClass().getName().getBytes();

        byte[] messageIdBytes = messageId.getBytes();

        ByteBuf buf = Unpooled.buffer(bodies.length + protoNameBytes.length + serviceBytes.length + methodBytes.length + messageIdBytes.length + 4 + 4);

        buf.writeInt(bodies.length + protoNameBytes.length + serviceBytes.length + methodBytes.length + messageIdBytes.length + 4);

        buf.writeByte(messageIdBytes.length);
        buf.writeBytes(messageIdBytes);

        buf.writeByte(serviceBytes.length);
        buf.writeBytes(serviceBytes);

        buf.writeByte(methodBytes.length);
        buf.writeBytes(methodBytes);

        buf.writeByte(protoNameBytes.length);
        buf.writeBytes(protoNameBytes);

        buf.writeBytes(bodies);
        return buf;
    }

    public RequestWrapper decode(ByteBuf src) {
        messageId = new String(readBytes(src));
        serviceName = new String (readBytes(src));
        methodName = new String (readBytes(src));
        String protoName = new String (readBytes(src));
        byte[] bytes = new byte[src.readableBytes()];
        src.readBytes(bytes, 0, bytes.length);
        try {
            content = (GeneratedMessageV3) get(protoName).toBuilder().mergeFrom(bytes).build();
            return this;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("messageId", messageId)
                .append("serviceName", serviceName)
                .append("methodName", methodName)
                .append("content", content)
                .toString();
    }

    public String getKey(){
        return serviceName+"_"+methodName+"_"+ content.getDescriptorForType().getFields().size();
    }
}
