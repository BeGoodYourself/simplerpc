package com.github.begoodyourself.core.bo;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/12
 */
public abstract class MessageWrapper<T> {
    protected volatile static Map<String, GeneratedMessageV3> generatedMessageV3MapCaches = new HashMap<String, GeneratedMessageV3>();
    protected GeneratedMessageV3 content;

    public abstract ByteBuf encode();

    public abstract T decode(ByteBuf src);

    protected byte[] readBytes(ByteBuf buf){
        byte[] bytes = new byte[buf.readByte()];
        buf.readBytes(bytes, 0, bytes.length);
        return bytes;
    }

    protected GeneratedMessageV3 get(String clsName) throws  Exception{
        GeneratedMessageV3 instace = generatedMessageV3MapCaches.get(clsName);
        if(instace == null){
            synchronized (MessageWrapper.class){
                instace = generatedMessageV3MapCaches.get(clsName);
                if(instace == null){
                    instace = (GeneratedMessageV3) Class.forName(clsName).getMethod("getDefaultInstance").invoke(null);
                    generatedMessageV3MapCaches.put(clsName, instace);
                }
            }
        }
        return instace;
    }
}
