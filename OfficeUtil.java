/**
 * 
 */
package com.jxsq.common.util;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * @author JX
 * 附件下载生成工具
 * 2016年10月24日 下午2:54:59
 */
public class OfficeUtil {
	private static final Logger log = Logger.getLogger(OfficeUtil.class);
	
	/**
	 * 文件转换URL
	 * @param file
	 * @param aid
	 * @return
	 * 2016年10月24日 下午3:32:22
	 */
	public static String fileToUrl(File file, String aid)
	{
		String apiKey = "UIpKkLOLJwnZOgbtnkA2gRsizs2eQMtD";
		CloseableHttpClient httpclient = null;
		HttpPost httppost = null;
		CloseableHttpResponse response = null;
		HttpEntity resEntity = null;
		try {
			httpclient = HttpClients.createDefault();
			httppost = new HttpPost("http://view.webofficeapi.com/upload");
			StringBody apiKeyPart = new StringBody(apiKey, ContentType.TEXT_PLAIN);
			FileBody filePart = new FileBody(file);
			HttpEntity reqEntity = MultipartEntityBuilder.create()
			        .addPart("api_key", apiKeyPart).addPart("file", filePart)
			        .build();
			httppost.setEntity(reqEntity);
			
			log.info("executing request " + httppost.getRequestLine());
			response = httpclient.execute(httppost);
			log.info(response.getStatusLine());
			resEntity = response.getEntity();
			if (resEntity != null) {
				String fileUrl = getRspFileUrl(EntityUtils.toString(resEntity));
				//http://view.webofficeapi.com/docs/M69Q8Z4N
				log.info("【转换文件到URL】" + fileUrl);
			    return fileUrl;
			}
			return null;
		} catch (ClientProtocolException e) {
			log.error("【转换文件到URL】出现异常【AID】" + aid, e);
			e.printStackTrace();
		} catch (ParseException e) {
			log.error("【转换文件到URL】出现异常【AID】" + aid, e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("【转换文件到URL】出现异常【AID】" + aid, e);
			e.printStackTrace();
		}
		finally {
			try {
				if (response != null)
				{
					EntityUtils.consume(resEntity);
					response.close();
				}
				if (httppost != null)
					httppost.abort();
				if (httpclient != null)
					httpclient.close();
			} catch (IOException e) {
				log.error("【转换文件到URL】关闭流出现异常【AID】" + aid, e);
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 拼响应文件URL
	 * @param rspJsonStr
	 * @return
	 * 2016年10月24日 下午3:08:26
	 */
	private static String getRspFileUrl(String rspJsonStr)
	{
		JSONObject jsonObject = JsonUtil.getJSONObject(rspJsonStr);
		if (jsonObject != null)
		{
			String uuid = jsonObject.getString("uuid");
			if (!ValidateUtil.validateStrNullValue(uuid))
				return "http://view.webofficeapi.com/docs/" + uuid;
			return null;
		}
		return null;
	}
}
