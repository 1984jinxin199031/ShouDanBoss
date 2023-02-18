package com.jxsq.common.util;

/**
 * 加密解密工具
 * @author JX
 */
public class EncDecUtil {

	/**
	 * 解密
	 * @param encMsg
	 * @return
	 */
	public static String dec(String key,String encMsg){
		String decMsg="";
		try{
		byte[] srcBytes = Des3.decryptMode(key, org.apache.commons.codec.binary.Base64.decodeBase64(encMsg));
		//正式
		decMsg =new String(srcBytes,"UTF-8");
		//测试
//		decMsg =new String(srcBytes);
		}catch(Exception e){
			decMsg="";
			e.printStackTrace();
		}
		return decMsg;
	}
	
	/**
	 * 加密
	 * @param msg
	 * @return
	 */
	public static String enc(String key,String msg){
		String encMsg="";
		try{
		//正式	
		byte[] encoded = Des3.encryptMode(key, msg.getBytes("UTF-8"));
		//测试	
//		byte[] encoded = Des3.encryptMode(key, msg.getBytes());
		encMsg=new String(encoded);
		}catch(Exception e){
			e.printStackTrace();
			encMsg="";
		}
		return encMsg;
	}
	
	public static void main(String[] args) {
		System.out.println("6lldefcabd8ffd3c417c932d2fe7c3c8a9f0e7c6de8ca971".length());
	}
}
