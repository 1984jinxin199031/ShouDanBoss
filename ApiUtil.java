/**
 * 
 */
package com.jxsq.common.util;

import java.util.Map;

import com.jxsq.common.po.common.AppEncrypt;
import com.jxsq.common.service.factory.AppEncryptFactory;
import com.jxsq.common.service.factory.ReqDataTransferFactory;
import com.jxsq.common.service.factory.ReturnDataFactory;

/**
 * @author jx
 * APP通信接口工具
 */
public class ApiUtil {
	/**
	 * 随机签名、加密长度【加密和验签用】
	 */
	private static final int RANDOM_KEY_LENGTH = 8;
	/**
	 * 外部接口随机签名、加密长度【加密和验签用】
	 */
	private static final int OUT_SIDE_RANDOM_KEY_LENGTH = 16;
	
	/**
	 * 用秘钥解码【密码】
	 * @param password
	 * @return
	 */
	public static String deCodePassword(String password)
	{
		return EncDecUtil.dec(PropertyUtil.getEncodePwdKey(), password);
	}
	
	/**
	 * 数据加密
	 * @param appEncrypt
	 * @param tranData
	 * @return
	 * 2016年10月12日 上午10:27:52
	 */
	public static String encode(AppEncrypt appEncrypt, String tranData, String cipherText) {
		boolean isEncrypt = appEncrypt.getIsEncrypt().equals(AppEncryptFactory.IS_ENCRYPT);
		if (isEncrypt)
			return AppEncryptFactory.getAppEncryptManner(appEncrypt.getEncryptType()).encode(appEncrypt, tranData, cipherText);
		return tranData;
	}

	/**
	 * 数据解密
	 * @param appEncrypt
	 * @param tranData
	 * @return
	 * 2016年10月12日 上午10:29:18
	 */
	public static String decode(AppEncrypt appEncrypt, String tranData, String cipherText) {
		boolean isEncrypt = appEncrypt.getIsEncrypt().equals(AppEncryptFactory.IS_ENCRYPT);
		if (isEncrypt)
			return AppEncryptFactory.getAppEncryptManner(appEncrypt.getEncryptType()).decode(appEncrypt, tranData, cipherText);
		return tranData;
	}
	
	/**
	 * 获得随机签名key
	 * @return
	 * 2016年10月12日 下午4:28:40
	 */
	public static String getRandomKey()
	{
		return StringUtil.getStringRandom(RANDOM_KEY_LENGTH);
	}
	
	/**
	 * 【外部接口】获得随机key
	 * 
	 * 2017年6月22日 下午3:47:33
	 */
	public static String getRandomKeyOutSide()
	{
		return StringUtil.getStringRandom(OUT_SIDE_RANDOM_KEY_LENGTH);
	}
	
	/**
	 * 返回json处理
	 * @param returnJson
	 * @param appEncrypt
	 * @param version
	 * 2016年10月12日 下午5:06:44
	 */
	public static String dealReturnJson(String returnJson, AppEncrypt appEncrypt, String version)
	{
		return ReturnDataFactory.getReturnDataService(appEncrypt.getSignType()).
			dealReturnData(appEncrypt, returnJson, version);
	}

	/**
	 * 请求数据处理
	 * @param appEncrypt
	 * @param paramMap
	 * @param methodName
	 * @return
	 * 2016年12月19日 下午7:06:00
	 */
	public static Map<String, String> dealReqData(AppEncrypt appEncrypt, Map<String, String[]> paramMap,
			String methodName)
	{
		return ReqDataTransferFactory.getReqDataTransferService(appEncrypt.getSignType())
			.transferReqData(appEncrypt, paramMap, methodName);
	}
	
}

