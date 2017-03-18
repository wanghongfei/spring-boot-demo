package com.sina.demo.web.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sina.demo.common.exception.ErrorEnum;
import com.sina.demo.web.config.TitanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户已登陆, 但权限不足时调用
 * Created by wanghongfei on 11/03/2017.
 */
@Component
public class TitanUnauthenticatedHandler implements AuthenticationEntryPoint {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf8");

        response.getOutputStream().write(objectMapper.writeValueAsBytes(
                new TitanResponse(ErrorEnum.NOT_LOG_IN.code(), ErrorEnum.NOT_LOG_IN.message())
        ));
    }
}
