package com.plf.minio.controller;

import com.plf.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author panlf
 * @date 2022/1/1
 */
@RestController
@RequestMapping("minio")
public class MinioController {

    @Log(desc = "MinIO测试案例")
    @GetMapping("test")
    public String test(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

}
