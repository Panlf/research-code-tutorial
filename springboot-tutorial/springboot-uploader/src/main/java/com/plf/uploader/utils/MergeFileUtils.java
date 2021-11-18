package com.plf.uploader.utils;

import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * @author panlf
 * @date 2021/11/15
 */
public class MergeFileUtils {

    public static String mergeFile(String srcFile,String fileName,int chunks){
        FileOutputStream fileOutputStream = null;
        SequenceInputStream sequenceInputStream = null;
        try{
            ArrayList<FileInputStream> fileInputStreams = new ArrayList<>();
            for(int i=0;i<chunks;i++){
                fileInputStreams.add(new FileInputStream(new File(srcFile,i+"")));
            }
            Enumeration<FileInputStream> en = Collections.enumeration(fileInputStreams);
            sequenceInputStream = new SequenceInputStream(en);
            File file = new File(srcFile + "/" +fileName);
            fileOutputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = sequenceInputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer,0,len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(sequenceInputStream!=null){
                try {
                    sequenceInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "SUCCESS";
    }
}
