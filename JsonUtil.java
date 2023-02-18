/**
 * 
 */
package com.jxsq.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;
import com.jxsq.common.constant.AppRspCodeConstant;

/**
 * @author JX
 * json工具
 */
public class JsonUtil {
	
	/**
	 * 获得响应的Json串
	 * @param rspCode
	 * @return
	 */
	public static String getRspJsonStr(String rspCode)
	{
		HashMap<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("rspCode", rspCode);
		jsonMap.put("rspMsg", AppRspCodeConstant.rspCodeMap.get(rspCode));
		return JSONObject.toJSONString(jsonMap);
	}
	
	/**
	 * 获得响应的Json串
	 * 【固定响应参数】
	 * @param rspCode
	 * @param flowNo
	 * @param timestamp
	 * @return
	 * 2016年10月19日 下午7:28:11
	 */
	public static String getRspJsonStr(String rspCode, String flowNo, String timestamp)
	{
		HashMap<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("rspCode", rspCode);
		jsonMap.put("rspMsg", AppRspCodeConstant.rspCodeMap.get(rspCode));
		jsonMap.put("flowNo", flowNo);
		jsonMap.put("timestamp", timestamp);
		return JSONObject.toJSONString(jsonMap);
	}
	
	/**
	 * 获得响应的Json串
	 * 【固定响应参数】
	 * @param rspCode
	 * @param rspMsg
	 * @param flowNo
	 * @param timestamp
	 * @return
	 * 2016年11月16日 下午9:02:36
	 */
	public static String getRspJsonStr(String rspCode, String rspMsg, String flowNo, String timestamp)
	{
		HashMap<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("rspCode", rspCode);
		jsonMap.put("rspMsg", rspMsg);
		jsonMap.put("flowNo", flowNo);
		jsonMap.put("timestamp", timestamp);
		return JSONObject.toJSONString(jsonMap);
	}
	
	/**
	 * 获得【第二版】通用响应MAP
	 * 【固定响应参数】
	 * @param rspCode
	 * @param flowNo
	 * @param timestamp
	 * @return
	 * 2016年10月25日 下午5:15:27
	 */
	public static HashMap<String, String> getRspMap(String rspCode, String flowNo, String timestamp)
	{
		HashMap<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("rspCode", rspCode);
		jsonMap.put("rspMsg", AppRspCodeConstant.rspCodeMap.get(rspCode));
		jsonMap.put("flowNo", flowNo);
		jsonMap.put("timestamp", timestamp);
		return jsonMap;
	}
	
	/**
	 * 获得【第二版】通用响应MAP
	 * 【固定响应参数】
	 * @param rspCode
	 * @param flowNo
	 * @param timestamp
	 * @return
	 * 2016年10月25日 下午5:15:27
	 */
	public static HashMap<String, Object> getRspObjectMap(String rspCode, String flowNo, String timestamp)
	{
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rspCode", rspCode);
		jsonMap.put("rspMsg", AppRspCodeConstant.rspCodeMap.get(rspCode));
		jsonMap.put("flowNo", flowNo);
		jsonMap.put("timestamp", timestamp);
		return jsonMap;
	}
	
	/**
	 * 获得响应的Json串
	 * @param rspCode
	 * @return
	 */
	public static String getRspJsonStr(String rspCode, String rspMsg)
	{
		HashMap<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("rspCode", rspCode);
		jsonMap.put("rspMsg", rspMsg);
		return JSONObject.toJSONString(jsonMap);
	}
	
	/**
	 * 为jsonMap设置返回码和返回信息
	 * @param rspCode
	 * @param returnJsonMap
	 */
	public static void setRspCodeAndMsg(String rspCode, 
			HashMap<String, Object> returnJsonMap)
	{
		returnJsonMap.put("rspCode", rspCode);
		returnJsonMap.put("rspMsg", AppRspCodeConstant.rspCodeMap.get(rspCode));
	}
	
	/**
	 * 获得json对象
	 * @param jsonStr
	 * @return
	 */
	public static JSONObject getJSONObject(String jsonStr)
	{
		return JSONObject.parseObject(jsonStr);
	}

	/**
	 * 【json字符串】转成【Map<String, String>】
	 * @param jsonStr
	 * @return
	 * 2016年10月12日 下午6:50:43
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> transferFromJsonStrToMap(String jsonStr)
	{
		return JSONObject.parseObject(jsonStr, Map.class);
	}
	
	/**
	 * 【json字符串】转成【Map<String, Object>】
	 * @param jsonStr
	 * @return
	 * 2016年10月12日 下午6:50:43
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> transferFromJsonStrToObjectMap(String jsonStr)
	{
		return JSONObject.parseObject(jsonStr, Map.class);
	}
	
	public static TreeMap<String, String> transferFromJsonStrToTreeMap(String jsonStr)
	{
		getJSONObject(jsonStr);
		return null;
	}
	
	public static void main(String[] args) {
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("tag", "data");
		dataMap.put("hospitalName", "宜都市妇幼保健院");
		dataMap.put("name", "欧阳夏凡");
		dataMap.put("gender", "女");
		dataMap.put("age", "28");
		dataMap.put("code", "420502042");
		dataMap.put("examineDoc", "杨林");
		dataMap.put("examineDate", "2016-05-10");
		dataMap.put("verifyDoc", "王菲");
//		System.out.println(dataMap);
		
//		String jsonstr=JSONObject.toJSONString(dataMap);
		String jsonstr="{    \"charge\": {        \"order_no\": \"test1470654170170\",        \"channel\": \"cashier_pay\",        \"currency\": \"cny\",        \"amount\": 100,        \"subject\": \"测试商品\",        \"body\": null,        \"optional\": \"aaa=111,bbb=222,ccc=333\",        \"extra\": {            \"return_url\": \"http://www.baidu.com/returnUrl.html\"        },        \"notify_url\": \"http://www.baidu.com/notifyUrl.html\",        \"time_expire\": \"201605121430\"    }}";
//		System.out.println(jsonstr);
		Map<String,String> dataMap2=transferFromJsonStrToMap(jsonstr);
		Iterator<String> iterator = dataMap2.keySet().iterator();
		String key = null;
		while(iterator.hasNext())
		{
			key = iterator.next();
			System.out.println(key);
			System.out.println(dataMap2.get(key).toString());
		}
//		String str=dataMap2.get("return_url");
//		System.out.println(str);
	}
	
	
	
}
