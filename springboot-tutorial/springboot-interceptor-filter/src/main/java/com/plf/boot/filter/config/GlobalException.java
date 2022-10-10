package com.plf.boot.filter.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author panlf
 * @date 2022/10/6
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = Exception.class)
    public String globalException(Exception e){
        return e.getMessage();
    }
}
