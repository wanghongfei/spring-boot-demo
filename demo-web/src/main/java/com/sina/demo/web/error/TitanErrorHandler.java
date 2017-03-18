package com.sina.demo.web.error;

import com.sina.demo.common.exception.ErrorEnum;
import com.sina.demo.web.config.TitanResponse;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wanghongfei on 18/03/2017.
 */
@RestController
public class TitanErrorHandler implements ErrorController {
    @RequestMapping(value = "/error")
    public TitanResponse handleError() {
        return new TitanResponse(ErrorEnum.FAILED.code(), ErrorEnum.FAILED.message());
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
