/**
 * 
 */
package com.jxsq.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.aliyun.oss.OSSClient;

/**
 * @author JX
 * 阿里文件服务器工具
 * 文件处理相关
 * 2017年2月23日 下午2:46:27
 */
public class OssUtil {
	private static final Logger log = Logger.getLogger(OssUtil.class);
	
	/**
	 * 获得OSS实例
	 * @return
	 * 2017年2月23日 下午3:14:29
	 */
	private static OSSClient createOssClient()
	{
		return new OSSClient(PropertyUtil.getEndPoint(), PropertyUtil.getAccessId(), PropertyUtil.getSecretKey());
	}
	
	/**
	 * 上传本地文件
	 * @param file
	 * @param bucketName
	 * @param key
	 * 2017年2月24日 上午11:28:57
	 */
	public static void uploadLocalFile(File file, String bucketName, String key)
	{
		OSSClient ossClient = createOssClient();
		ossClient.putObject(bucketName, key, file);
		ossClient.shutdown();
	}
	
	/**
	 * 上传本地文件
	 * @param file
	 * @param key
	 * 2017年2月24日 上午11:46:58
	 */
	public static void uploadLocalFile(String localFilePath, String key)
	{
		OSSClient ossClient = createOssClient();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(localFilePath);
		} catch (FileNotFoundException e) {
			log.error("【oss上传文件】 失败");
			//日志
			e.printStackTrace();
		}
		ossClient.putObject(PropertyUtil.getDefaultBucketName(), key, inputStream);
		ossClient.shutdown();
	}
	
	/**
	 * 验证【key】是否存在
	 * @param key
	 * @return
	 * 2017年2月24日 下午3:56:58
	 */
	public static boolean objectExists(String key)
	{
		OSSClient ossClient = createOssClient();
		boolean found = ossClient.doesObjectExist(PropertyUtil.getDefaultBucketName(), key);
		ossClient.shutdown();
		return found;
	}
	
	@SuppressWarnings("unused")
	public static void uploadFileTest()
	{
//		String accessKeyId = "STS.HQU6Fsnu2R6sC3XVyzjqLnAGJ";
		String accessKeyId = "LTAIdCPJkDvYhODJ";
//		String accessKeySecret = "Bbt2fTsxy1MQEeFNGXgweMcnyUWEt2E5BQtxo6G2TN2s";
		String accessKeySecret = "E1e52vs99Mu9k9WXY4hDMBNsSfc8Jw";
		String securityToken = "CAISggJ1q6Ft5B2yfSjIqZLgffzHg6oT5fSYQRXpsnkvZv5ggYTsqDz2IHBKfXZpBeEfv/wzn2pS6P8Slrp1VptBa2PrYNdI445S8Bi6Yo3H429dT1fVy8/LI3OaLjKm9vawCryLaLGwU/OpbE++3E0X6LDmdDKkckW4OJmS8/BOZcgWWQ/KD1gmA8xNdDN/tI1gQhm3D/u2NQPwiWf9FVdhvhEG6Vly8qOi2MaRmHG85R/YsrZM9t+pesf1P5A1Z84kAo+PsbYoJvab4kl58ANX8ap6tqtA9Arcs8uVa1sruEXYarWNrow2fVclPfJgRfcUtpnnieY9oOvXmp/nXZKS0gSctE4agAE0pFP8Tu64Xn7Q4O6WmVkFIaq2HKObETq/1Gm8oS/YQQiuW9x5bE4a/mGROTgMqlkqe/v2ZdLUEBJ5sEYHAOPmrggBONzmeP5qQC93ZvZxXL617RuSt4YWYXHMC9xyiJ0swpIegQYaV/RQhdcVkz4sFcgPHTlhm8jONQ7rWFwKfg==";
		String endpoint = "http://oss-cn-shanghai.aliyuncs.com";

//		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, securityToken);
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		
		
		String fileName = "2017061415464764615110508.mp3";
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("d:\\mp3\\" + fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		ossClient.putObject("manggoooo", "audio/imgs/" + fileName, inputStream);
//		boolean found = ossClient.doesObjectExist("manggoooo", "forum/201705/11/20170511113000027146126394560.jpg");
//		System.out.println("found：" + found);
		//音频回答
		ossClient.putObject("answer-audio", "audio/questionAnswer/201706/14/" + fileName, inputStream);
		ossClient.shutdown();
	}
	
	
	
	public static void main(String[] args) {
		uploadFileTest();
	}
	
	
	
}
