package com.jxsq.common.util;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;


/**
 * Hmac摘要， 默认HmacSHA256算法
 * Created by mah on 2015/10/28.
 */
public class HmacHanlder {
    public static final String HMACSHA256 = "HmacSHA256";
    public static final String HMACSHA1 = "HmacSHA1";
    public static final String HMACSHA512 = "HmacSHA512";
    public static final String HMACMD5 = "HmacMD5";

    private String macKey;
    private String defaultEncryptType = HMACSHA256;
    private String defaultEncoding = "UTF-8";

    public HmacHanlder(String macKey){
        this.macKey = macKey;
    }

    public HmacHanlder(String macKey, String defaultEncryptType){
        this.macKey = macKey;
        this.defaultEncryptType = defaultEncryptType;
    }

    /**
     * 生成摘要，默认HMACSHA256
     * @param s 明文
     * @return 摘要
     * @throws Exception
     */
    public String encrypt(String s) throws Exception {
        String sign = null;
        try {
            Mac mac = Mac.getInstance(defaultEncryptType);
            SecretKey secret = new SecretKeySpec(macKey.getBytes(defaultEncoding),
                    defaultEncryptType);
            mac.init(secret);
            byte[] doFinal = mac.doFinal(s.getBytes(defaultEncoding));
            sign = Hex.encodeHexString(doFinal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }
}