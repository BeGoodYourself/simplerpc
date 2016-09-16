package com.github.begoodyourself.net;

import com.github.begoodyourself.core.bo.ResponseWrapper;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
public class SyncRpcResult {

    //private Object object = new Object();

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private ResponseWrapper response;

    public Object result() throws Exception{
        try {
            lock.lock();
            condition.await(10, TimeUnit.SECONDS);
            if(response != null)
                 return response.getContent();
            return null;
        }finally {
            lock.unlock();
        }

    }

    public void setResponse(ResponseWrapper response) {
        try {
            lock.lock();
            this.response = response;
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }


}
