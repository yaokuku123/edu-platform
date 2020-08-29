package com.yqj.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.yqj.oss.service.OssService;
import com.yqj.oss.utils.OssPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: OssServiceImpl
 * Author: yaoqijun
 * Date: 2020/8/29 20:24
 */
@Service
public class OssServiceImpl implements OssService {

    //上传头像图片到oss
    @Override
    public String uploadAvatarFile(MultipartFile file) {

        //调用工具类加载配置信息
        String endpoint = OssPropertiesUtils.END_POINT;
        String accessKeyId = OssPropertiesUtils.KEY_ID;
        String accessKeySecret = OssPropertiesUtils.KEY_SECRET;
        String bucketName = OssPropertiesUtils.BUCKET_NAME;

        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            // 上传文件 参数 bucket名称，文件名称，文件输入流
            String fileName = file.getOriginalFilename();
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            // url示例：https://edu-yorick.oss-cn-beijing.aliyuncs.com/timg.jpg
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
