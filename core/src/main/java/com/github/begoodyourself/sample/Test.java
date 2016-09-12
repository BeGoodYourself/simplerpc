package com.github.begoodyourself.sample;

import com.github.begoodyourself.core.bo.MessageWrapper;
import com.github.begoodyourself.sample.AddressBookProtos;
import com.google.protobuf.GeneratedMessageV3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/12
 */
public class Test {
    static  Map<String, GeneratedMessageV3> generatedMessageV3MapCaches = new HashMap<String, GeneratedMessageV3>();
    public static void main(String[] args) {

        AddressBookProtos.AddressBook book = AddressBookProtos.AddressBook.newBuilder()
                .addPeople(AddressBookProtos.Person.newBuilder().setName("aaaa").setId(100).setEmail("aaaa!aa.ccc").addPhones(AddressBookProtos.Person.PhoneNumber.newBuilder().setType(AddressBookProtos.Person.PhoneType.HOME).setNumber("11111111111111"))).build();
        System.out.println(book);
        try {
            GeneratedMessageV3 o = (GeneratedMessageV3) get(book.getClass().getName()).toBuilder().mergeFrom(book.toByteArray()).build();
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static GeneratedMessageV3 get(String clsName) throws  Exception{
        GeneratedMessageV3 instace = generatedMessageV3MapCaches.get(clsName);
        if(instace == null){
            synchronized (MessageWrapper.class){
                instace = generatedMessageV3MapCaches.get(clsName);
                if(instace == null){
                    instace = (GeneratedMessageV3) Class.forName(clsName).getMethod("getDefaultInstance").invoke(null);
                    generatedMessageV3MapCaches.put(clsName, instace);
                }
            }
        }
        return instace;
    }
}
