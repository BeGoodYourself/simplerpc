package com.github.begoodyourself.consumer;

import com.github.begoodyourself.consumer.spring.ConsumerApplicationContext;
import com.github.begoodyourself.core.codec.ProtobufRequestDecoder;
import com.github.begoodyourself.core.codec.ProtobufRequestEncoder;
import com.github.begoodyourself.core.codec.ProtobufResponeDecoder;
import com.github.begoodyourself.core.codec.ProtobufResponeEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
public class Consumer {
    private EventLoopGroup boss;
    private EventLoopGroup worker;
    public void start(final ConsumerApplicationContext consumerApplicationContext){
        if(boss == null){
            boss = new NioEventLoopGroup();
        }
        if(worker == null){
            worker = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2);
        }
        ServerBootstrap b = new ServerBootstrap();
        try {
            ChannelFuture f = b.group(boss,worker).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4),
                            new ProtobufRequestDecoder(),
                            new ProtobufResponeEncoder(),
                            new SimpleRpcConsumerHandler(consumerApplicationContext));
                }
            }).bind(8990).sync();
            System.out.println("Consumer start ..."+ 8990);
            f.channel().closeFuture().sync();

        }catch (Exception e){
            //shutdown();
            e.printStackTrace();
        }
    }

    public void shutdown(){
        if(worker !=  null){
            worker.shutdownGracefully();
        }
        if(boss != null){
            boss.shutdownGracefully();
        }
    }
}
