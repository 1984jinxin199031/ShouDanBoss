/**
 * 
 */
package com.jxsq.common.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

/**
 * @author JX
 * 日志工具
 */
public class LogUtil {
	/**
	 * 获得【单条查询日志】
	 * 每次都获取
	 * @return
	 * 2016年12月1日 上午10:42:51
	 */
	public static Log getOrderQueryThreadLog()
	{
		return LogFactory.getLog("orderQuery");
	}
	
	/**
	 * 获得【通知日志】
	 * 每次都获取
	 * @return
	 * 2016年12月1日 下午2:51:54
	 */
	public static Log getNotifyLog()
	{
		return LogFactory.getLog("notify");
	}
	
	/**
	 * 获得【响应密文打印】
	 * 每次都获取
	 * @return
	 * 2016年12月1日 下午2:51:54
	 */
	public static Log getRspCiphertext()
	{
		return LogFactory.getLog("rspCiphertext");
	}
	
	/**
	 * 记录错误日志信息
	 * @param methodName
	 * @param exception
	 * @param log
	 */
	public static void logErrorWrite(String methodName, Exception exception, Logger log) {
		StringBuilder sb = new StringBuilder();
		sb.append("【Method】").append(methodName);
		sb.append("【ExceptionMsg】").append(exception.getMessage());
		log.error(sb.toString(), exception);
	}
	
	/**
	 * 返回【返回Json日志】串
	 * @param methodName
	 * @param controllerName
	 * @param returnJsonStr
	 * @param timestamp
	 */
	public static String getReturnJsonLogStr(String methodName, String controllerName,
			String returnJsonStr, String timestamp, String flowNo) 
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("【flowNo】").append(flowNo);
		stringBuilder.append("【时间戳】").append(timestamp);
		stringBuilder.append("【Controller】").append(controllerName);
		stringBuilder.append("【Method】").append(methodName);
		stringBuilder.append("【返回参数】").append(returnJsonStr);
		return stringBuilder.toString();
	}
	
	/**
	 * 返回【返回Json日志】串
	 * @param methodName
	 * @param controllerName
	 * @param returnJsonStr
	 * @param timestamp
	 */
	public static String getReturnJsonLogStr(String methodName, String controllerName,
			String returnJsonStr, String flowNo) 
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("【flowNo】").append(flowNo);
		stringBuilder.append("【Controller】").append(controllerName);
		stringBuilder.append("【Method】").append(methodName);
		stringBuilder.append("【返回参数】").append(returnJsonStr);
		return stringBuilder.toString();
	}
	
	/**
	 * 返回【日志】串
	 * @param flowNo
	 * @param timestamp
	 * @return
	 */
	public static String getLogStr(String flowNo, String timestamp) 
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("【flowNo】").append(flowNo);
		stringBuilder.append("【时间戳】").append(timestamp);
		return stringBuilder.toString();
	}
}
