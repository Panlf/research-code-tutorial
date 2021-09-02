package com.plf.boot.unified.common.bean;

import lombok.Data;

/**
 * @author panlf
 * @date 2021/9/2
 */
@Data
public class RequestInfo {
    private String ip;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;
    private Object result;
    private Long timeCost;
    private Boolean isError = false;
    private Exception exception;
}
