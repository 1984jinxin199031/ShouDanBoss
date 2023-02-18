/**
 * 
 */
package com.jxsq.common.util;

import org.apache.log4j.Logger;
import com.alibaba.druid.filter.config.ConfigTools;

/**
 * @author JX 
 * 【淘宝】
 * 【RSA】加密工具 
 * 2016年11月30日 下午5:13:21
 */
public class EncriptionKit {
	private static final Logger log = Logger.getLogger(EncriptionKit.class);
	
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
		System.out.println(passwordEncrypt("", "Jn1535bbsJx"));
		
		System.out.println(passwordDecrypt("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOeix6FeVxjykK1EKvZvdQd8Wsj9JZXJDRHZToqEYzOr7zcTz+D9SBWeYxtVAEVIv/hhBKQis4HJnPRHoPBmNgsCAwEAAQ==", "3TEQoRUS2I2Ju/t9kY+z55L6pHb1oUhDFpA8BJrUgSUC90h99Su8PxLaL+KXKAXBA41+B7Uc9vX/ANyuKE7pEw=="));
	}
	
}
