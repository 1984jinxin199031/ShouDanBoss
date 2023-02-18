/**
 * 
 */
package com.jxsq.common.util;

import com.jxsq.common.constant.SmsConstant;

/**
 * @author JX
 * 系统工具 
 */
public class SysUtil {
	/**
	 * 验证码生命周期【单位：秒】
	 * 【一分半】
	 */
	private static final int CHECKCODE_LIFE_PERIOD = 90;
	/**
	 * 校验码生命周期【单位：秒】
	 * 【三分钟】
	 */
	private static final int VALIDATECODE_LIFE_PERIOD = 180;
	
	
	/**
	 * 【用在】
	 * 1、获得短信验证码的混合KEY
	 * 2、【validateCheckCode】请求响应生成的validateCode
	 * @param telephone
	 * @param operationType
	 * @return
	 */
	public static String getSmsKey(String telephone, String operationType)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(telephone);
		stringBuilder.append("|");
		stringBuilder.append(SmsConstant.noteOperationTypeMap.get(operationType));
		return stringBuilder.toString();
	}
	
	/**
	 * 处理验证码
	 * 【加上到期时间(long)】
	 * 0 -> 验证码
	 * 1 -> 过期时刻
	 * @param checkCode
	 * @return
	 */
	public static String dealCheckCode(String checkCode)
	{
		return checkCode + "|" + DateUtil.getNowByAdded(CHECKCODE_LIFE_PERIOD);
	}
	
	/**
	 * 处理校验码
	 * 【加上到期时间(long)】
	 * 0 -> 校验码
	 * 1 -> 过期时刻
	 * @param checkCode
	 * @return
	 */
	public static String dealValidateCode(String validateCode)
	{
		return validateCode + "|" + DateUtil.getNowByAdded(VALIDATECODE_LIFE_PERIOD);
	}
}
