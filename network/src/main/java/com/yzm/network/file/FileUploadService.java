package com.yzm.network.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    void upload(MultipartFile file);
}
