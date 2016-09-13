package com.github.begoodyourself.proxy;

import com.github.begoodyourself.core.bo.RequestWrapper;
import com.github.begoodyourself.core.bo.RpcMethod;
import com.github.begoodyourself.util.ContextUtil;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.GeneratedMessageV3;
import io.netty.channel.Channel;

import java.lang.reflect.Method;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/13
 */
public class RpcInvokeHandler {
    private Channel channel;

    protected Object invoke(Method method, Object[] args) throws Throwable {
        RpcMethod rpcMethod = method.getAnnotation(RpcMethod.class);
        if(rpcMethod == null){
            throw new RuntimeException();
        }
        GeneratedMessageV3 instance = ContextUtil.get(rpcMethod.args().getName());
        GeneratedMessageV3.Builder  builder = (GeneratedMessageV3.Builder)instance.toBuilder();
        FieldDescriptor[] fieldDescriptorList = (FieldDescriptor[])builder.getAllFields().keySet().toArray(new FieldDescriptor[0]);
        for (int i = 0; i < fieldDescriptorList.length; i++) {
            builder.setField(fieldDescriptorList[i], args[i]);
        }

        RequestWrapper requestWrapper = new RequestWrapper();
        requestWrapper.setContent((GeneratedMessageV3) builder.build());
        requestWrapper.setMethodName(method.getName());
        requestWrapper.setServiceName(method.getDeclaringClass().getName());
        //channel.writeAndFlush(requestWrapper);
        return requestWrapper;
    }
}
