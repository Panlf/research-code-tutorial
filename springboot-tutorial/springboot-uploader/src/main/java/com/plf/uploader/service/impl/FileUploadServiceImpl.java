package com.plf.uploader.service.impl;

import com.plf.uploader.service.FileUploadService;
import com.plf.uploader.vo.FileInfo;
import com.plf.uploader.vo.FileParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author panlf
 * @date 2021/11/15
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${upload.file.path}")
    private String uploadFilePath;

    @Override
    public ResponseEntity<String> uploadFile(FileParam fileParam) {
        String identifier = fileParam.getIdentifier();

        int chunkNumber = fileParam.getChunkNumber();

        File fileTemp = new File(uploadFilePath+"/"+identifier+"/"+chunkNumber);
        File fileParent = fileTemp.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        try{
            MultipartFile file = fileParam.getFile();
            file.transferTo(fileTemp);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("success");
    }

    @Override
    public ResponseEntity<String> mergeFile(FileInfo fileInfo) {

        //用缓存的技术实现

        return null;
    }

    @Override
    public ResponseEntity<Map<String,Object>> uploadCheck(FileParam fileParam) {
        String identifier = fileParam.getIdentifier();
        File fileExistTemp = new File(uploadFilePath+"/"+identifier+"/"+fileParam.getFilename());
        File fileExistDir = new File(uploadFilePath+"/"+identifier+"/temp");
        List<String> fileList = new ArrayList<>();
        boolean isExist = fileExistTemp.exists();
        if(!isExist && fileExistDir.listFiles() != null){
            File[] listFiles = fileExistDir.listFiles();
            for(int i=0;i<listFiles.length;i++){
                fileList.add(listFiles[i].getName());
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("upload",fileList.toArray());
        map.put("needSkiped",isExist);
        return ResponseEntity.ok(map);
    }
}
