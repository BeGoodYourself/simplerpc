package com.github.begoodyourself.net;

import java.io.IOException;
import java.util.Properties;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
public class ServerFinder {
    private static ServerFinder instance ;

    private String registryServerUrl;

    private ServerFinder() {
        Properties p = new Properties();
        try {
            p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("registry.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        registryServerUrl = (String) p.get("registryServerUrl");
        if(registryServerUrl == null){
            throw  new RuntimeException("");
        }
    }

    public static ServerFinder getInstance(){
        if(instance == null){
            synchronized (ServerFinder.class){
                if(instance == null)
                     instance = new ServerFinder();
            }
        }
        return instance;
    }


    public String findConsumerServer(){
        return "127.0.0.1:8990";
    }
}
