package com.github.begoodyourself.proxy;

import com.github.begoodyourself.core.bo.RequestWrapper;
import com.github.begoodyourself.core.bo.RpcMethod;
import com.github.begoodyourself.net.Producer;
import com.github.begoodyourself.util.ContextUtil;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.GeneratedMessageV3;
import io.netty.channel.Channel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import java.util.Set;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/13
 */
public class JavaDynamicProxy extends RpcInvokeHandler implements InvocationHandler{

    private Channel channel;

    public static Object proxy(Class interfaceCls, Producer producer){
        JavaDynamicProxy dynamicProxy = new JavaDynamicProxy();
        dynamicProxy.setProducer(producer);
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{interfaceCls}, dynamicProxy);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return invoke(method,args);
    }
}
