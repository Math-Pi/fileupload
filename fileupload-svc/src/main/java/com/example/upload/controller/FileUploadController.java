package com.example.upload.controller;

import com.example.upload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-10-08  22:06
 */
@RestController
@CrossOrigin(origins = "*")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFile(@RequestParam Map<String,String> map, @RequestParam("file") MultipartFile[] file) {
        try {
            fileUploadService.uploadFiles(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
