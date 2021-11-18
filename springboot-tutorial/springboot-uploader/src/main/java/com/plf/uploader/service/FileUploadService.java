package com.plf.uploader.service;

import com.plf.uploader.vo.FileInfo;
import com.plf.uploader.vo.FileParam;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author panlf
 * @date 2021/11/15
 */
public interface FileUploadService {
    ResponseEntity<String> uploadFile(FileParam fileParam);

    ResponseEntity<String> mergeFile(FileInfo fileInfo);

    ResponseEntity<Map<String,Object>> uploadCheck(FileParam fileParam);
}
