package com.github.begoodyourself.consumer.sample;

import com.github.begoodyourself.consumer.Consumer;
import com.github.begoodyourself.consumer.spring.ConsumerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
public class Tes {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("app.xml");

        Consumer consumer = new Consumer();
        consumer.start((ConsumerApplicationContext)applicationContext.getBean("consumerApplicationContext"));
    }
}
