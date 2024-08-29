package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    // 将AliOSSUtils类交给IOC容器管理
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {

        log.info("文件上传，文件名：{}",image.getOriginalFilename());

        // 调用阿里云OSS工具类进行文件上传
        String url = aliOSSUtils.upload(image);

        log.info("文件上传成功，文件url：{}",url);

        return Result.success(url);

    }
}
