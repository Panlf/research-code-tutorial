package com.plf.boot.unified.common;

import cn.hutool.http.HttpUtil;

public class ThreadTest {
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread(()->{
                String result = HttpUtil.get("http://localhost:9000/unified/get?id=1");
                System.out.println(result);
            }).start();
        }
    }
}
