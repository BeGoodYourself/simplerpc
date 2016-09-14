package com.github.begoodyourself.net;

import com.github.begoodyourself.core.bo.ResponseWrapper;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
public interface RpcEventListener {
    void onChannelConnected(ChannelHandlerContext ctx);

    void channelRead(ChannelHandlerContext ctx, ResponseWrapper msg);
}
