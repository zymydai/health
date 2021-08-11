package com.itheima.utils;


import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class AliCouldUtils {

    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    private static String accessKeyId = "LTAI5tM2TVG6Cm8egKnSVo7H";
    private static String accessKeySecret = "AwGQXUAiUCWPKcJQal0ECCfMnBlPot";

    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
    private static String bucketName = "dai-img";

    private static OSS ossClient;


    //上传文件
    public static void uploadAli(byte[] bytes, String fileName){
        try {
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream is = new ByteArrayInputStream(bytes);
            ossClient.putObject(bucketName, fileName, is);
        } catch (OSSException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }

    //删除文件
    public static void deleteFileFromAli(String fileName){

        try {
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient.deleteObject(bucketName, fileName);
        } catch (OSSException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }
}
