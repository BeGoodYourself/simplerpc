package com.github.begoodyourself.consumer;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
public class MethodInvoker {
    private final Object instance;
    private final FastMethod fastMethod;

    public MethodInvoker(Object instance, FastMethod fastMethod) {
        this.instance = instance;
        this.fastMethod = fastMethod;
    }

    public Object invoke(Object[] params) throws Exception{
        Class[] parameterTypes = fastMethod.getParameterTypes();
        for(int i = 0; i < parameterTypes.length; i++){
            if(parameterTypes[i].isEnum()){
                params[i] = refectEnumValueOf(parameterTypes[i], new Object[]{params[i]});
            }
        }

        return fastMethod.invoke(instance, params);
    }


    private Object refectEnumValueOf(Class c, Object[] enums) throws Exception{
       return  c.getMethod("valueOf", new Class[]{ com.google.protobuf.Descriptors.EnumValueDescriptor.class}).invoke(null,enums);
    }
}
