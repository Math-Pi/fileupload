package com.example.upload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author CJM
 * @Date 2021-10-08  22:07
 */
@Service
public class FileUploadService {

    /**
     * 多文件上传
     * @param files
     */
    public void uploadFiles(MultipartFile[] files) throws IOException {
        if (files != null && files.length > 0) {
            for(int i =0 ;i< files.length; i++) {
                saveFile(files[i]);
            }
        }
    }

    /**
     * 单文件上传（transferTo）
     * @param file
     */
    public void uploadFile(MultipartFile file) {
        //判断文件是否为空
        if (!file.isEmpty()) {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            //加个时间戳，尽量避免文件名称重复
            fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 文件上传后的路径
            String filePath = "D:\\Vue\\test\\upload\\src\\main\\resources\\files\\";
            //创建文件路径
            File dest = new File(filePath + fileName);
            // 检测文件父目录是否存在,不存在则递归创建目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                //保存文件
                file.transferTo(dest);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 单文件上传（通过使用BufferedOutputStream）
     * @param file
     * @throws IOException
     */
    public void saveFile(MultipartFile file) throws IOException {
        //判断文件是否为空
        if (!file.isEmpty()) {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            //加个时间戳，尽量避免文件名称重复
            fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            byte[] bytes = file.getBytes();

            // 文件上传后的路径
            String filePath = "D:\\Vue\\test\\upload\\src\\main\\resources\\files\\";
            //创建文件路径
            File dest = new File(filePath + fileName);

            // 检测文件父目录是否存在,不存在则递归创建目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(dest));
            buffStream.write(bytes);
            buffStream.close();
        }
    }
}
