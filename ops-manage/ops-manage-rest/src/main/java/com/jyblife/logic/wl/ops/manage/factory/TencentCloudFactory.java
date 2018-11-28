package com.jyblife.logic.wl.ops.manage.factory;

import com.jyblife.logic.wl.ops.core.spring.ApplicationContextHolder;
import com.jyblife.logic.wl.ops.manage.config.TencentCloudConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;

import java.io.File;

/**
 * @author yangcey
 * @date 2018/9/27
 **/

public class TencentCloudFactory {

    private COSClient cosClient;
    private TencentCloudConfig cloudConfig;
    private String bucketName;

    public TencentCloudFactory() {
        this.cloudConfig = ApplicationContextHolder.getBean("tencentCloudConfig");
        // 1 初始化用户身份信息
        COSCredentials cred = new BasicCOSCredentials(this.cloudConfig.getSecret_id(), this.cloudConfig.getSecret_key());
        // 2 设置bucket的区域
        ClientConfig clientConfig = new ClientConfig(new Region(this.cloudConfig.getRegion()));
        // 3 生成cos客户端
        this.cosClient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid}
        this.bucketName = this.cloudConfig.getBucket_name();
    }


    /**
     * 简单文件上传,最大支持 5 GB，适用于小文件上传, 建议20M以下的文件使用该接口
     *
     * @param file
     * @param key  对象键（Key）是对象在存储桶中的唯一标识,例如，在对象的访问域名 `bucket1-1250000000.cos.ap-guangzhou.myqcloud.com/doc1/pic1.jpg` 中，对象键为 doc1/pic1.jpg
     */
    public void uploadSimpleFile(File file, String key) {
        //再上传文件
        PutObjectRequest putObjectRequest = new PutObjectRequest(this.createBucketName(this.bucketName), key, file);
        PutObjectResult putObjectResult = this.cosClient.putObject(putObjectRequest);

    }

    /**
     * 文件下载
     *
     * @param file
     * @param key
     */
    public void downLoadFile(File file, String key) {
        GetObjectRequest getObjectRequest = new GetObjectRequest(this.createBucketName(this.bucketName), key);
        ObjectMetadata downObjectMeta = this.cosClient.getObject(getObjectRequest, file);
    }

    /**
     * 删除文件
     *
     * @param key
     */
    public void deleteFile(String key) {
        this.cosClient.deleteObject(this.createBucketName(this.bucketName), key);
    }

    /**
     * 关闭连接，释放资源
     */
    public void shutdown() {
        if (this.cosClient != null) {
            this.cosClient.shutdown();
        }
    }

    /**
     * 创建一个bucket
     *
     * @param bucketName
     */
    public void createBucket(String bucketName) {
        if (this.doesBucketExist(this.createBucketName(bucketName))) {
            return;
        }
        this.cosClient.createBucket(this.createBucketName(bucketName));
    }

    /**
     * 删除
     *
     * @param bucketName
     */
    public void deleteBucket(String bucketName) {
        this.cosClient.deleteBucket(this.createBucketName(bucketName));
    }

    /**
     * 是否存在
     *
     * @param bucketName
     * @return
     */
    public boolean doesBucketExist(String bucketName) {
        return this.cosClient.doesBucketExist(this.createBucketName(bucketName));
    }

    private String createBucketName(String bucketName) {
        return bucketName + "-" + this.cloudConfig.getApp_id();
    }
}
