package com.plf.uploader.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author panlf
 * @date 2021/11/15
 */
@Data
public class FileParam {
    private int chunkNumber;
    private long chunkSize;
    private long currentChunkSize;
    private long totalSize;
    private String identifier;
    private String filename;
    private String relativePath;
    private int totalChunks;
    private MultipartFile file;
}
