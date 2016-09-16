package com.github.begoodyourself.consumer;

import com.github.begoodyourself.consumer.spring.ConsumerApplicationContext;
import com.github.begoodyourself.core.bo.RequestWrapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
public class SimpleRpcConsumerHandler extends SimpleChannelInboundHandler<RequestWrapper>{

    private ConsumerApplicationContext consumerApplicationContext;

    public SimpleRpcConsumerHandler(ConsumerApplicationContext consumerApplicationContext) {
        this.consumerApplicationContext = consumerApplicationContext;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestWrapper msg) throws Exception {
        consumerApplicationContext.executor(new DispatchTask(ctx,msg, consumerApplicationContext.find(msg.getKey())));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
