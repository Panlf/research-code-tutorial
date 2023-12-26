package com.plf.boot.unified.common;

import cn.hutool.http.HttpUtil;

public class ThreadTest {
    public static void main(String[] args) {
        for(int i=0;i<3;i++){
            new Thread(()->{
                String result = HttpUtil.get("http://localhost:8080/ratelimit");
                System.out.println(result);
            }).start();
        }
    }
}
