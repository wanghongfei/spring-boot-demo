package com.sina.demo.web.config;

import com.sina.demo.common.exception.ErrorEnum;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 处理控制器的返回值
 * Created by wanghongfei on 11/03/2017.
 */
@Component
public class TitanHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    /**
     * 如果是TitanResponse对象,直接序列化;
     * 如果不是, 则封装成TitanResponse对象并序列化
     * @param object
     * @param type
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (false == object instanceof TitanResponse) {
            TitanResponse response = new TitanResponse();
            response.setCode(ErrorEnum.SUCCESS.code());
            response.setMessage(ErrorEnum.SUCCESS.message());
            response.setData(object);

            object = response;
        }

        super.writeInternal(object, type, outputMessage);
    }
}
