package com.sina.demo.common.exception;

/**
 * Created by wanghongfei on 13/03/2017.
 */
public class TitanException extends RuntimeException {
    private int code;

    public TitanException(int code, String msg) {
        super(msg);

        this.code = code;
    }


    public int getCode() {
        return code;
    }
}
