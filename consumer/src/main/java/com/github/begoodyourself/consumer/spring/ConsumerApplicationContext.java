package com.github.begoodyourself.consumer.spring;

import com.github.begoodyourself.consumer.Consumer;
import com.github.begoodyourself.consumer.MethodInvoker;
import com.github.begoodyourself.core.bo.RpcService;
import net.sf.cglib.reflect.FastClass;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
public class ConsumerApplicationContext implements InitializingBean, ApplicationContextAware{
    private Map<String, MethodInvoker> methodInvokerMap = new HashMap<>();

    private ThreadPoolExecutor executor ;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RpcService.class);
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            Object o = entry.getValue();
            if(o.getClass().isInterface() || o.getClass().isAnonymousClass()){
                continue;
            }

            FastClass fastClass = FastClass.create(o.getClass());
            Method[] ms = o.getClass().getDeclaredMethods();
            String interfaceName = findRpcSeviceName(o);

            for(Method m : ms){
                String key = interfaceName+"_"+m.getName()+"_"+m.getParameters().length;
                //System.out.println(key);
                methodInvokerMap.put(key, new MethodInvoker(o, fastClass.getMethod(m)));
            }

            executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        }
    }

    private String findRpcSeviceName(Object o){
        //String interfceName = "";
        for(Class c : o.getClass().getInterfaces()){
            if(c.getAnnotation(RpcService.class) != null){
                return c.getName();
            }
        }
        throw new RuntimeException(" interface 没有RpcService注解");
    }

    public MethodInvoker find(String key){
        //System.out.println(key);
        return methodInvokerMap.get(key);
    }

    public void executor(Runnable r){
        executor.execute(r);
    }
}
