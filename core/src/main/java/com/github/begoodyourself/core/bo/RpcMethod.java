package com.github.begoodyourself.core.bo;

import java.lang.annotation.*;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RpcMethod {
    Class args();
}
