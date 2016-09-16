package com.github.begoodyourself.api.sample;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/16
 */
public class TestProto {
    public static void main(String[] args) {
        Req.Person.newBuilder().getData().toByteArray();
    }
}
