package com.github.begoodyourself.consumer.exception;

/**
 * Created with simplerpc0
 * AUTHOR ; BEGOODYOURSELF
 * DATE : 2016/9/14
 */
public class RpcException extends RuntimeException{
    private short errcode;
    private String errMsg;

    public RpcException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, short errcode, String errMsg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errcode = errcode;
        this.errMsg = errMsg;
    }

    public RpcException(short errcode, String errMsg) {
        this.errcode = errcode;
        this.errMsg = errMsg;
    }

    public RpcException(String message, short errcode, String errMsg) {
        super(message);
        this.errcode = errcode;
        this.errMsg = errMsg;
    }

    public RpcException(String message, Throwable cause, short errcode, String errMsg) {
        super(message, cause);
        this.errcode = errcode;
        this.errMsg = errMsg;
    }

    public RpcException(Throwable cause, short errcode, String errMsg) {
        super(cause);
        this.errcode = errcode;
        this.errMsg = errMsg;
    }

    public short getErrcode() {
        return errcode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
