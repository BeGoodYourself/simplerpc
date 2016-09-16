package com.github.begoodyourself.api;

import com.github.begoodyourself.api.sample.Req.*;
import com.github.begoodyourself.api.sample.Resp.*;
import com.github.begoodyourself.core.bo.RpcMethod;
import com.github.begoodyourself.core.bo.RpcService;


/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/13
 */
@RpcService
public interface CalService {
   @RpcMethod(args = Person.class)
   CalRespMessage aa(String name, int id, String email, long phoneNum, Person.PhoneType phoneType);
}
