package com.plf.boot.unified.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(true,200,"成功"),
    ERROR(false,500,"失败"),
    UNKNOWN_ERROR(false,600,"未知错误"),
    PARAM_ERROR(false,601,"参数错误"),
    USER_NOT_FOUND(false,602,"用户找不到");

    // 响应是否成功
    private Boolean flag;
    // 响应状态码
    private Integer code;
    // 响应信息
    private String message;

    ResultCodeEnum(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
}
