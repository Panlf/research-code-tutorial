package com.plf.feign.client.global;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.SocketTimeoutException;

/**
 * @author panlf
 * @date 2022/5/9
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 这边应该是服务降级的代码。 简单的做法就是返回统一错误
     * @param e
     * @return
     */
    @ExceptionHandler(SocketTimeoutException.class)
    @ResponseBody
    public String ExceptionHandler(Exception e){
        return "接口超时，请稍后再试";
    }
}
