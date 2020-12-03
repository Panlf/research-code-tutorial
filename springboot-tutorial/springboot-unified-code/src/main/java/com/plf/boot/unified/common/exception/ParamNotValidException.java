package com.plf.boot.unified.common.exception;

import lombok.Getter;

@Getter
public class ParamNotValidException extends RuntimeException{
    private Integer code;
    public ParamNotValidException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
