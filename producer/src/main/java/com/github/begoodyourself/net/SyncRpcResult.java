package com.github.begoodyourself.net;

import com.github.begoodyourself.core.bo.ResponseWrapper;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
public class SyncRpcResult {

    private Object object = new Object();

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private ResponseWrapper respone;

    public Object result() throws Exception{
        try {
            lock.lock();
            condition.await();
            if(respone != null)
                 return respone.getContent();
            return null;
        }finally {
            lock.unlock();
        }

    }

    public void setRespone(ResponseWrapper respone) {
        try {
            lock.lock();
            this.respone = respone;
            condition.signal();
        }finally {
            lock.unlock();
        }

    }


}
