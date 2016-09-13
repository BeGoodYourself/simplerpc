package com.github.begoodyourself.proxy;

import com.github.begoodyourself.core.bo.RequestWrapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/13
 */
public class JavaDynamicProxy implements InvocationHandler{
    public static Object proxy(Class interfaceCls){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{interfaceCls}, new JavaDynamicProxy());
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return null;
    }
}
