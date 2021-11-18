package com.plf.uploader.controller;

import com.plf.uploader.service.FileUploadService;
import com.plf.uploader.vo.FileInfo;
import com.plf.uploader.vo.FileParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author panlf
 * @date 2021/11/15
 */
@RestController
@RequestMapping(value = "upload")
public class UploadController {

    @Resource
    private FileUploadService fileUploadService;

    @PostMapping(value = "uploadFile")
    public ResponseEntity upload(FileParam file){
        return fileUploadService.uploadFile(file);
    }

    @PostMapping(value = "mergeFile")
    public ResponseEntity mergeFile(@RequestBody FileInfo file){
        return fileUploadService.mergeFile(file);
    }

}
