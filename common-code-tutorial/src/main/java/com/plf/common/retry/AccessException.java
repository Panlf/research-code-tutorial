package com.plf.common.retry;

/**
 * @author panlf
 * @date 2022/7/20
 */
public class AccessException extends Exception{

    public AccessException(){}

    public AccessException(String message){
        super(message);
    }
}
