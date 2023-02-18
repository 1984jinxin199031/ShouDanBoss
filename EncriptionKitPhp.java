/**
 * 
 */
package com.jxsq.common.util;

import org.apache.log4j.Logger;



/**
 * @author JX 
 * 【RSA】加密工具
 * 2017-06-22 17:35:22【PHP】用 
 * 2016年11月30日 下午5:13:21
 */
public class EncriptionKitPhp {
	private static final Logger log = Logger.getLogger(EncriptionKitPhp.class);
	
	/**
	 * RSA解密
	 * password解密
	 * @param publicKey
	 * @param password
	 * @return
	 * 2016年11月30日 下午5:56:44
	 */
	public static String passwordDecrypt(String publicKey, String password) {
		String result = null;
		try {
			result = ConfigTools.decrypt(publicKey, password);// 解密
		} catch (Exception e) {
			log.error("【RSA解密密码】出现异常", e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * RSA加密
	 * password解密
	 * @param publicKey
	 * @param password
	 * @return
	 * 2016年11月30日 下午5:56:44
	 */
	public static String passwordEncrypt(String publicKey, String password) {
		String result = null;
		try {
			result = ConfigTools.encrypt(publicKey, password);
		} catch (Exception e) {
			log.error("【RSA加密密码】出现异常", e);
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		
	}
	
}
