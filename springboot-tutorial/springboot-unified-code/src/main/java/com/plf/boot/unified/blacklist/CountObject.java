package com.plf.boot.unified.blacklist;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountObject {
    private String method;
    private String ip;
    private Integer second;
    private Integer count;

    private String key;
    private String value;

    public CountObject(String method,String ip,Integer second,Integer count){
        this.method = method;
        this.ip = ip;
        this.second = second;
        this.count = count;
    }

    public String setKey(String method,String ip){
        this.method = method;
        this.ip = ip;
        this.key = method + "-" + ip;
        return this.key;
    }

    public String getKey(){
        return this.method + "-" + this.ip;
    }

    public String setValue(Integer second, Integer count){
        this.second = second;
        this.count = count;
        this.value = second + "-" + count;
        return this.value;
    }

    public String getValue(){
        return this.second + "-" + this.count;
    }

    public synchronized Integer increase(){
        this.count = this.count + 1;
        return this.count;
    }

}
