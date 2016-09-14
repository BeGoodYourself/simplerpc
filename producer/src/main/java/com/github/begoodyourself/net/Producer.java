package com.github.begoodyourself.net;

import com.github.begoodyourself.core.bo.RequestWrapper;
import com.github.begoodyourself.core.bo.ResponseWrapper;
import com.github.begoodyourself.core.codec.ProtobufRequestEncoder;
import com.github.begoodyourself.core.codec.ProtobufResponeDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.apache.commons.lang3.StringUtils;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
public class Producer implements RpcEventListener{

    EventLoopGroup boss;
    private ServerFinder serverFinder;
    private ChannelHandlerContext ctx;

    private ConcurrentHashMap<String, SyncRpcResult> rpcResults = new ConcurrentHashMap<>();

    public void start() throws InterruptedException {
        if(serverFinder == null){
            serverFinder = ServerFinder.getInstance();
        }

        rpcResults.clear();

        String consumerServer = serverFinder.findConsumerServer();
        String[] ar = StringUtils.split(consumerServer,':');

        if(boss == null)
            boss = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(boss);
        bootstrap.channel(NioSocketChannel.class).handler(
                new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0 ,4,0,4),
                            new ProtobufResponeDecoder(), new ProtobufRequestEncoder(),
                                new RpcProducerHandler().setRpcEventListener(Producer.this)
                        );
                    }
                }
        );


        ChannelFuture cf = bootstrap.connect(new InetSocketAddress(ar[0],Integer.parseInt(ar[1]))).syncUninterruptibly();
        System.out.println("Producer start:"+ consumerServer);

        cf.channel().closeFuture().sync();
    }


    public void shutdown(){
        if(boss != null){
            boss.shutdownGracefully();
        }
    }

    @Override
    public void onChannelConnected(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, ResponseWrapper msg) {
        SyncRpcResult rpcResult = rpcResults.remove(msg.getMessageId());
        if(rpcResult != null){
            rpcResult.setRespone(msg);
        }
    }

    public Object write(RequestWrapper msg) throws Exception{
        SyncRpcResult rpcResult = new SyncRpcResult();
        rpcResults.put(msg.getMessageId(), new SyncRpcResult());
        ctx.writeAndFlush(msg);
        return rpcResult.result();
    }
}
