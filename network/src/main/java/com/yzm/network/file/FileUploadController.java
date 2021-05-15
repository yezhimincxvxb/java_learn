package com.yzm.network.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("index")
    public String index() {
        return "index.jsp";
    }

    @PostMapping(path = "file/upload")
    @ResponseBody
    public String fileUpload(HttpServletRequest request) {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件
            Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
            if (fileMap.values().size() > 0) {
                for (MultipartFile multipartFile : fileMap.values()) {
                    fileUploadService.upload(multipartFile);
                }
            }
        }
        return "success";
    }
}
