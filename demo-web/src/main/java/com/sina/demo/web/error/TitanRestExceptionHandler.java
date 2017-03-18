package com.sina.demo.web.error;

import com.sina.demo.common.exception.ErrorEnum;
import com.sina.demo.common.exception.TitanException;
import com.sina.demo.web.config.TitanResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器扔异常时调用
 * Created by wanghongfei on 10/03/2017.
 */
@ControllerAdvice
public class TitanRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Object handleException(HttpServletRequest req, Throwable ex) {
        if (ex instanceof TitanException) {
            TitanException exception = (TitanException) ex;

            return new TitanResponse(exception.getCode(), exception.getMessage());
        }


        return new TitanResponse(ErrorEnum.FAILED.code(), ex.getMessage());

    }
}
