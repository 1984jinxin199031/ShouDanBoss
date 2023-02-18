package com.jxsq.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 安全散列算法SHA (Secure Hash Algorithm，SHA) 
 * @author JX
 *
 * 2016年10月20日 下午4:59:29
 */
public class SHA1 {
   
	public static String sha1Encode(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
