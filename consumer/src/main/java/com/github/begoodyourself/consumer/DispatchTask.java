package com.github.begoodyourself.consumer;

import com.github.begoodyourself.api.ErrorCode;
import com.github.begoodyourself.consumer.exception.RpcException;
import com.github.begoodyourself.core.bo.RequestWrapper;
import com.github.begoodyourself.core.bo.ResponseWrapper;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
public class DispatchTask implements Runnable{
    private ChannelHandlerContext channelHandlerContext;
    private RequestWrapper requestWrapper;
    private MethodInvoker methodInvoker;

    public DispatchTask(ChannelHandlerContext channelHandlerContext, RequestWrapper requestWrapper, MethodInvoker methodInvoker) {
        this.channelHandlerContext = channelHandlerContext;
        this.requestWrapper = requestWrapper;
        this.methodInvoker = methodInvoker;
    }

    @Override
    public void run() {
        ResponseWrapper response = new ResponseWrapper();
        response.setMessageId(requestWrapper.getMessageId());
        try {
            GeneratedMessageV3 content = requestWrapper.getContent();
            List<Descriptors.FieldDescriptor> fieldDescriptorList = content.getDescriptorForType().getFields();
            Object[] params = new Object[fieldDescriptorList.size()];
            for (int i = 0; i < params.length; i++) {
                params[i] = content.getField(fieldDescriptorList.get(i));
            }
            response.setContent((GeneratedMessageV3) methodInvoker.invoke(params));
        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof RpcException){
                RpcException re = (RpcException)e;
                response.setErrcode(re.getErrcode());
                response.setErrMsg(re.getErrMsg());
            }else{
                response.setErrcode(ErrorCode.SYSTEM_ERROR);
                response.setErrMsg(e.getMessage());
            }
        }
        channelHandlerContext.writeAndFlush(response);
    }
}
