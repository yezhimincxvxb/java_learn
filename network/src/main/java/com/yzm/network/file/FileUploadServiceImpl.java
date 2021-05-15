package com.yzm.network.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

    private String temPath = "F:\\Img\\";

    @Override
    public void upload(MultipartFile file) {
        if (null == file) return;

        new Thread(() -> {
            int start = (int) System.currentTimeMillis();

            String fileName = file.getOriginalFilename();
            if (!StringUtils.isEmpty(fileName)) {
                log.info("upload file name={}", fileName);
                String path = temPath + System.currentTimeMillis() + "_" + fileName;
                try {
                    File localFile = new File(path);
                    // 判断该路径是否存在，不存在则创建
                    if (!localFile.exists()) localFile.mkdirs();

                    // 转换
                    file.transferTo(localFile);

                    //localFile.delete();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            int end = (int) System.currentTimeMillis();
            log.info("write file cost time={}", end - start);
        }).start();
    }

}
