package com.plf.boot.unified.common.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{
    private Integer code;

    public UserNotFoundException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
