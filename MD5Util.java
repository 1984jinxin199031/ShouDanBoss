/**
 * 
 */
package com.jxsq.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author JX
 * MD5工具类
 */
public class MD5Util {
	public final static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用md5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("md5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				// System.out.println((int)b);
				// 将没个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 自定义秘钥MD5
	 * @param s
	 * @param key
	 * @return
	 * 2016年11月4日 上午10:58:00
	 */
	public final static String md5ByKey(String s, String key) {
		char hexDigits[] = key.toCharArray();
		try {
			byte[] strTemp = s.getBytes();
			// 使用md5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("md5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				// System.out.println((int)b);
				// 将没个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static String test(String str) throws NoSuchAlgorithmException {
		byte[] buf = str.getBytes();
		MessageDigest md5 = MessageDigest.getInstance("md5");
		md5.update(buf);
		byte[] tmp = md5.digest();
		StringBuilder sb = new StringBuilder();
		for (byte b : tmp) {
			sb.append(Integer.toHexString(b & 0xff));
		}
		return sb.toString();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
//		String xx = md5(md5("jinxin") + "145545");
//		System.out.println(xx);
//		if ("23579e328697ea1f9c434cad187d94ed".equals(xx))
//			System.out.println("456");
//		else
//			System.out.println("error");
//		
//		System.out.println(md5("jinxin"));
//		System.out.println(md5("jinxin").equals("23579e328697ea1f9c434cad187d94ed"));
		System.out.println(md5("app_id=0MRLuXgRvHCXGfxIvbF6bA&charge={amount=100&channel=cashier_pay&currency=cny&extra={return_url=http://www.baidu.com/returnUrl.html}&notify_url=http://www.baidu.com/notifyUrl.html&optional=aaa=111,bbb=222,ccc=333&order_no=test1470653700419&subject=测试商品&time_expire=201605121430}&charset=UTF-8&client_ip=127.0.0.1&device=PC&live_mode=true&sdk_mark=sdk v1.1.16&timestamp=20160512143030&version=V2.1.1&key=897006250b4c09247ec02edce69f6a2c"));
		System.out.println("ef288a7754b0460e9b93e79d83f0e0e6".length());
	}
}
