package com.kgw.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.util.StringUtils;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/1 16:52
 * 文件说明：
 */
public class UploadUtils {


    public static String upload(Part part) {
        //指定的是endPoint 去对应的bucket的概述中查找（查找外网访问的）
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI5tA2XxN59tj6g1phM5GP";
        String accessKeySecret = "Ywt24I2adwGwj8STm7K3Ze7vCiZp6t";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //通过文件Part获取流程
        InputStream inputStream = null;
        try {
            inputStream = part.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
          参数一： 表示上传到哪个bucket 取值就是云存储上面的bucket的名称
          参数二： 表示文件名 （必须加后缀）
          参数三： 文件流
         */
        String fileName = System.nanoTime() + "." + StringUtils.getFilenameExtension(part.getSubmittedFileName());
        ossClient.putObject("ershibaqi", fileName, inputStream);
        ossClient.shutdown();
        System.out.println(part.getSubmittedFileName());
        return "https://ershibaqi.oss-cn-beijing.aliyuncs.com/" + fileName;
    }
}
