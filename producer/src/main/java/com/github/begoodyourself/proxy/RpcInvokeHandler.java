package com.github.begoodyourself.proxy;

import com.github.begoodyourself.core.bo.RequestWrapper;
import com.github.begoodyourself.core.bo.RpcMethod;
import com.github.begoodyourself.net.Producer;
import com.github.begoodyourself.util.ContextUtil;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.ProtocolMessageEnum;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/13
 */
public class RpcInvokeHandler {
    private Producer producer;

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    protected Object invoke(Method method, Object[] args) throws Throwable {
        RpcMethod rpcMethod = method.getAnnotation(RpcMethod.class);
        if(rpcMethod == null){
            throw new RuntimeException();
        }
        GeneratedMessageV3 instance = ContextUtil.get(rpcMethod.args().getName());
        GeneratedMessageV3.Builder builder = (GeneratedMessageV3.Builder) instance.newBuilderForType();
        List<FieldDescriptor> fieldDescriptorList = instance.getDescriptorForType().getFields();
        for (int i = 0; i < fieldDescriptorList.size(); i++) {
            Object value = args[i];
            if(value instanceof ProtocolMessageEnum){
                value = ((ProtocolMessageEnum)value).getValueDescriptor();
            }
            builder.setField(fieldDescriptorList.get(i),value);
        }

        RequestWrapper requestWrapper = new RequestWrapper();
        requestWrapper.setContent((GeneratedMessageV3) builder.build());
        requestWrapper.setMethodName(method.getName());
        requestWrapper.setServiceName(method.getDeclaringClass().getName());
        return producer.write(requestWrapper);
    }
}
