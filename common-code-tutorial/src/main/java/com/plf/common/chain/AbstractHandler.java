package com.plf.common.chain;

import lombok.Data;

/**
 * 责任链抽象类
 * @author panlf
 * @date 2022/7/11
 */
@Data
public abstract class AbstractHandler<T> {
    protected String name;

    protected AbstractHandler<T>  nextHandler;

    public AbstractHandler(String name){
        this.name = name;
    }

    public boolean hasNextHandler(){
        return nextHandler != null;
    }


    public abstract T process(T t);
}
