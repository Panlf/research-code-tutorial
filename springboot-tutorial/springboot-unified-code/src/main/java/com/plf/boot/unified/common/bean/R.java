package com.plf.boot.unified.common.bean;

import com.plf.boot.unified.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T>{

    private Integer code;

    private String message;

    private T data;

    public static<T> R success(String message,T data){
        return new R(ResultCodeEnum.SUCCESS.getCode(),message,data);
    }

    public static<T> R success(T data){
        return R.success("处理成功",data);
    }

    public static<T> R success(String message){
        return R.success(message,null);
    }

    public static<T> R error(String message,T data){
        return new R(ResultCodeEnum.ERROR.getCode(),message,data);
    }

    public static<T> R error(T data){
        return R.error("处理失败",data);
    }

    public static<T> R error(String message){
        return R.error(message,null);
    }

    public static<T> R error(ResultCodeEnum result,T data){
        return new R(result.getCode(),result.getMessage(),data);
    }
}
