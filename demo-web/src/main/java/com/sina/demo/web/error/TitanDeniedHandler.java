package com.sina.demo.web.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sina.demo.common.exception.ErrorEnum;
import com.sina.demo.web.config.TitanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登陆用户访问受保护资源时调用
 * Created by wanghongfei on 11/03/2017.
 */
@Component
public class TitanDeniedHandler implements AccessDeniedHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf8");

        response.getOutputStream().write(objectMapper.writeValueAsBytes(
                new TitanResponse(ErrorEnum.PERMISSION_DENIED.code(), ErrorEnum.PERMISSION_DENIED.message())
        ));
    }
}
