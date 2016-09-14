package com.github.begoodyourself.net;

import com.github.begoodyourself.core.bo.ResponseWrapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
public class RpcProducerHandler extends SimpleChannelInboundHandler<ResponseWrapper>{
    private RpcEventListener rpcEventListener;

    public RpcProducerHandler setRpcEventListener(RpcEventListener rpcEventListener) {
        this.rpcEventListener = rpcEventListener;
        return this;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponseWrapper msg) throws Exception {
        if(rpcEventListener != null){
            rpcEventListener.channelRead(ctx, msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if(rpcEventListener != null){
            rpcEventListener.onChannelConnected(ctx);
        }
    }
}
