package com.github.begoodyourself.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import java.lang.reflect.Method;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/13
 */
public class CglibProxy extends RpcInvokeHandler implements MethodInterceptor{

    public static Object proxy(Class interfaceCls){
        Enhancer en = new Enhancer();
        //进行代理
        en.setSuperclass(interfaceCls);
        en.setCallback(new CglibProxy());
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return invoke(method,objects);
    }
}
