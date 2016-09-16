package com.github.begoodyourself;

import com.github.begoodyourself.api.CalService;
import com.github.begoodyourself.api.sample.Req;
import com.github.begoodyourself.api.sample.Resp;
import com.github.begoodyourself.net.Producer;
import com.github.begoodyourself.proxy.JavaDynamicProxy;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
public class Test {
    public static void main(String[] args) {
        Producer producer = new Producer();
        try {
            producer.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CalService calService = (CalService) JavaDynamicProxy.proxy(CalService.class,producer);
        Resp.CalRespMessage respMessage = calService.aa("java",100, "1111", 1111111, Req.Person.PhoneType.HOME);
        System.out.println(respMessage);
        producer.shutdown();
    }
}
