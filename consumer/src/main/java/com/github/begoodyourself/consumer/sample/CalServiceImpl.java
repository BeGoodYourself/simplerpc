package com.github.begoodyourself.consumer.sample;

import com.github.begoodyourself.api.CalService;
import com.github.begoodyourself.api.sample.Req.*;
import com.github.begoodyourself.api.sample.Resp.*;
import com.github.begoodyourself.core.bo.RpcService;

import java.util.Random;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
@RpcService
public class CalServiceImpl implements CalService {
    @Override
    public CalRespMessage aa(String name, int id, String email, long phoneNum, Person.PhoneType phoneType) {
        CalRespMessage.Builder builder = CalRespMessage.newBuilder();
        builder.setResult(new Random().nextInt(10000));
        return builder.build();
    }
}
