package com.jxsq.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密工具
 * 
 * @author Jx
 *
 */
public class AESUtil {

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(password.getBytes());
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();

			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(password.getBytes());
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 【PHP】加密专用
	 * 
	 * 2017年6月22日 下午5:12:38
	 */
	public static String encryptPhp(String input, String key) {
		byte[] crypted = null;
		try {
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			crypted = cipher.doFinal(input.getBytes());
			return new String(Base64.encode(crypted));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public static String decryptPhp(String input, String key) {
		byte[] output = null;
		try {
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skey);
			output = cipher.doFinal(Base64.decode(input));
			return new String(output);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	public static void main(String[] args) {
//		String content = "{\"timestamp\":\"20170402153732\",\"postArray\":[{\"authorName\":\"流浪诗人\",\"dateline\":\"2016-06-14\",\"favoriteNum\":\"0\",\"forumName\":\"股票交流\",\"imageUrl\":\"http://bbs.jx.vc/data/attachment/forum/201606/14/103918z00dsv0b7yxyzv0v.jpg\",\"postUrl\":\"http://jx.jn1535.com/post/postDetail/vWGqiEetfk4=\",\"replies\":\"0\",\"subject\":\"A股首份股灾金融行为报告：6成股民仍持有当时股票\",\"views\":\"420\"},{\"authorName\":\"Angel_WJVZ\",\"dateline\":\"2016-06-13\",\"favoriteNum\":\"0\",\"forumName\":\"股票交流\",\"imageUrl\":\"\",\"postUrl\":\"http://jx.jn1535.com/post/postDetail/vPSad7BHBqE=\",\"replies\":\"1\",\"subject\":\"新人入社\",\"views\":\"428\"},{\"authorName\":\"hao\",\"dateline\":\"2016-06-13\",\"favoriteNum\":\"0\",\"forumName\":\"股票交流\",\"imageUrl\":\"http://bbs.jx.vc/data/attachment/forum/201606/13/154323qudqqz3g0ddzo37c.png\",\"postUrl\":\"http://jx.jn1535.com/post/postDetail/eNnBPkYmYRg=\",\"replies\":\"0\",\"subject\":\"你感觉到的“跌不下去”，其实是一种错觉！\",\"views\":\"416\"},{\"authorName\":\"连清\",\"dateline\":\"2016-06-07\",\"favoriteNum\":\"0\",\"forumName\":\"股票交流\",\"imageUrl\":\"http://bbs.jx.vc/data/attachment/forum/201606/07/162410qma1tm9cmua2eaum.png\",\"postUrl\":\"http://jx.jn1535.com/post/postDetail/Ht9+2F4NtaI=\",\"replies\":\"0\",\"subject\":\"为什么我建议放弃选股，购买并持有指数？\",\"views\":\"420\"},{\"authorName\":\"年轮\",\"dateline\":\"2016-06-07\",\"favoriteNum\":\"1\",\"forumName\":\"股票交流\",\"imageUrl\":\"http://bbs.jx.vc/data/attachment/forum/201606/07/102719khjpc1dfctccacvd.png\",\"postUrl\":\"http://jx.jn1535.com/post/postDetail/H3fYVfzvKzI=\",\"replies\":\"0\",\"subject\":\"弃港民即散天下：香港，“中国战车”突围必守的“塔山“\",\"views\":\"421\"},{\"authorName\":\"炒股的正确姿势\",\"dateline\":\"2016-06-06\",\"favoriteNum\":\"1\",\"forumName\":\"股票交流\",\"imageUrl\":\"\",\"postUrl\":\"http://jx.jn1535.com/post/postDetail/ew0oJWXP3IE=\",\"replies\":\"2\",\"subject\":\"炒股的正确姿势1\",\"views\":\"411\"},{\"authorName\":\"天下无双\",\"dateline\":\"2016-06-06\",\"favoriteNum\":\"0\",\"forumName\":\"股票交流\",\"imageUrl\":\"http://bbs.jx.vc/data/attachment/forum/201606/06/094944shxh0x0xqhopox00.jpg\",\"postUrl\":\"http://jx.jn1535.com/post/postDetail/dbooZfR5+L8=\",\"replies\":\"2\",\"subject\":\"血泪史：我是怎么买入一支亏损48%的股票\",\"views\":\"912\"},{\"authorName\":\"充电宝\",\"dateline\":\"2016-06-03\",\"favoriteNum\":\"0\",\"forumName\":\"股票交流\",\"imageUrl\":\"\",\"postUrl\":\"http://jx.jn1535.com/post/postDetail/M5ehzicPbHA=\",\"replies\":\"0\",\"subject\":\"我为什么不买中概股？\",\"views\":\"408\"},{\"authorName\":\"大名散坑\",\"dateline\":\"2016-06-01\",\"favoriteNum\":\"0\",\"forumName\":\"股票交流\",\"imageUrl\":\"http://bbs.jx.vc/data/attachment/forum/201606/01/183053bg9ggabxjmlbsgnr.png\",\"postUrl\":\"http://jx.jn1535.com/post/postDetail/QEMSBQQDOOA=\",\"replies\":\"0\",\"subject\":\"首度减持：软银79亿美元巨额抛售 阿里盘后跌逾2%\",\"views\":\"420\"},{\"authorName\":\"天下无双\",\"dateline\":\"2016-06-01\",\"favoriteNum\":\"1\",\"forumName\":\"股票交流\",\"imageUrl\":\"http://bbs.jx.vc/data/attachment/forum/201606/01/100212jlyc3rnh061y8yw6.png\",\"postUrl\":\"http://jx.jn1535.com/post/postDetail/vKcnm4qsMfQ=\",\"replies\":\"0\",\"subject\":\"多事之夏：这是六月最重要的投资机会\",\"views\":\"416\"},{\"authorName\":\"不学无术\",\"dateline\":\"2016-05-30\",\"favoriteNum\":\"0\",\"forumName\":\"股票交流\",\"imageUrl\":\"http://bbs.jx.vc/data/attachment/forum/201605/30/144049wuovy11gifnfrnrb.jpg\",\"postUrl\":\"http://jx.jn1535.com/post/postDetail/4oZBKlDFf+Y=\",\"replies\":\"0\",\"subject\":\"龙虎榜悄悄告诉你机构青睐的10只个股都是谁\",\"views\":\"420\"}],\"rspCode\":\"0001\",\"rspMsg\":\"成功\",\"flowNo\":\"6\"}";
//		String password = "12345678";
//		// 加密
//		System.out.println("加密前：" + content);
//		byte[] encryptResult = encrypt(content, password);
//		String encryptResultStr = parseByte2HexStr(encryptResult);
//		System.out.println("加密后：" + encryptResultStr);
//		// 解密
//		byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
//		byte[] decryptResult = decrypt(decryptFrom, password);
//		System.out.println("解密后：" + new String(decryptResult));
		System.out.println(decryptPhp("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", "212BP3Y84550495j"));
//		System.out.println(decrypt("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&signature=dZYhhK5jWYoqLpEOSTFgB6yOHFcFK".getBytes("utf-8"), password));
	}
}
