package com.sina.demo.web.config;

import com.sina.demo.common.exception.ErrorEnum;
import lombok.Data;

/**
 * Created by wanghongfei on 11/03/2017.
 */
@Data
public class TitanResponse {
    private int code = ErrorEnum.SUCCESS.code();
    private String message = ErrorEnum.SUCCESS.message();

    private Object data;

    public TitanResponse() {}

    public TitanResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public TitanResponse(Object data) {
        this.data = data;
    }
}
