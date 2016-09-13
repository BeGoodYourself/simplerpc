package com.github.begoodyourself.sample.pro;

import com.github.begoodyourself.core.bo.RpcMethod;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/13
 */
public interface CalService {
    @RpcMethod(args = com.github.begoodyourself.sample.pro.AddressBookProtos.Person.class)
    Object aa(String name, int id, String email, long phoneNum, AddressBookProtos.Person.PhoneType phoneType);
}
