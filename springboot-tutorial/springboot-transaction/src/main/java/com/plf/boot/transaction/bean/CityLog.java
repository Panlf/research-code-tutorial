package com.plf.boot.transaction.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityLog {
    private Long id;
    private String methodName;
    private String data;

    public CityLog(String methodName,String data){
        this.methodName=methodName;
        this.data = data;
    }
}
