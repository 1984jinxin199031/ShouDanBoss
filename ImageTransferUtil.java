/**
 * 
 */
package com.jxsq.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author JX
 * 图片 -> 字节数组【转换工具】
 */
public class ImageTransferUtil {
	
	 /**
	  * 【字节数组】转【图片】
	  * @param byteArray
	  * @param picUrl
	  * @throws IOException
	  */
	public static void byteArrayToImage(byte[] byteArray, String picUrl) throws IOException 
	{
		FileOutputStream fout = new FileOutputStream(picUrl);
		fout.write(byteArray);
		fout.close();
	}
	 
	 /**
	  * 【图片】转【字节数组】
	  * @param imgUrl
	  * @return
	  * @throws Exception
	  */
	public static byte[] imageToByteArray(String imgUrl) throws Exception {
		FileInputStream fin = new FileInputStream(new File(imgUrl));
		byte[] bytes = new byte[fin.available()];
		fin.read(bytes);
		fin.close();
		return bytes;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, Exception {
		byte[] byteArray = new String(ImageTransferUtil.imageToByteArray("d:\\copy\\x.jpg"), "utf-8").getBytes("utf-8");
		byteArrayToImage(byteArray, "d:\\copy\\999.jpg");
	}
}
