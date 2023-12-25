package com.plf.boot.unified.common.exception;

public class RequestForbiddenException extends RuntimeException{
    public RequestForbiddenException(String message){
        super(message);
    }
}
