package com.plf.minio.controller;

import com.plf.annotation.Log;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author panlf
 * @date 2022/1/1
 */
@RestController
@RequestMapping("minio")
public class MinioController {

    @Resource
    private MinioClient minioClient;

    @Log(desc = "MinIO测试案例")
    @GetMapping("test")
    public String test() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Log(desc = "MinIO上传文件")
    @PostMapping("/upload")
    public String upload(MultipartFile file) {

        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .object(file.getOriginalFilename())
                    .bucket("test-image")
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(),
                            file.getSize(), -1).build();

            minioClient.putObject(objectArgs);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
