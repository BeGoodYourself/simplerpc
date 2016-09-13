package com.github.begoodyourself.util;

import com.google.protobuf.GeneratedMessageV3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/13
 */
public class ContextUtil {

    private volatile static Map<String, GeneratedMessageV3> generatedMessageV3MapCaches = new HashMap<String, GeneratedMessageV3>();

    public static GeneratedMessageV3 get(String clsName) throws Exception{
        GeneratedMessageV3 generatedMessageV3 = generatedMessageV3MapCaches.get(clsName);
        if(generatedMessageV3 == null){
            synchronized (ContextUtil.class){
                generatedMessageV3 = generatedMessageV3MapCaches.get(clsName);
                if(generatedMessageV3 == null){
                    generatedMessageV3 = (GeneratedMessageV3) Class.forName(clsName).getMethod("getDefaultInstance").invoke(null);
                    generatedMessageV3MapCaches.put(clsName, generatedMessageV3);
                }
            }
        }
        return generatedMessageV3;
    }

}
