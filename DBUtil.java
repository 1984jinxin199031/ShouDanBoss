package com.jxsq.common.util;

import java.util.UUID;

/**
 * 数据库相关的工具类
 * @author jinxin
 */
public class DBUtil {
	
	/**
	 * 获得查询【指定表】所有数据的SQL
	 * @param tableName
	 * @return
	 */
	public static String getSelectAllSql(String tableName)
	{
		return "select * from " + tableName;
	}
	
	/**
	 * 获得查询【指定表】所有数据的SQL（有排序的版本）
	 * @param tableName
	 * @return
	 */
	public static String getSelectAllSqlByOrder(String tableName, String orderStr)
	{
		StringBuilder stringBuilder = new StringBuilder("select * from ");
		stringBuilder.append(tableName);
		stringBuilder.append(" ");
		stringBuilder.append(orderStr);
		return stringBuilder.toString();
	}
	
	/**
	 * 获得（查询【指定表】的【指定字段】的所有数据）的SQL
	 * @param fieldName
	 * @param tableName
	 * @return
	 */
	public static String getSelectAllSqlByOneField(String fieldName, String tableName)
	{
		StringBuilder stringBuilder = new StringBuilder("select ");
		stringBuilder.append(fieldName);
		stringBuilder.append(" from ");
		stringBuilder.append(tableName);
		return stringBuilder.toString();
	}
	
	/**
	 * 获得删除【指定表】全部数据的SQL
	 * @param tableName
	 * @return
	 */
	public static String getDeleteAllSql(String tableName)
	{
		return "delete from " + tableName; 
	}
	
	/**
	 * 验证是否到执行的批次数上了
	 * true  -> 执行sql
	 * false -> 不执行sql
	 * @param batchInsertNum
	 * @param countIndex
	 */
	public static boolean validateExecuteSql(int batchInsertNum, int countIndex)
	{
		return countIndex % batchInsertNum == 0;
	}
	
	/**
	 * 添加一段【字符串】到【SB对象】中
	 * @param str
	 * @param stringBuilder
	 */
	public static void addStringFromStringBuild(String str, StringBuilder stringBuilder)
	{
		stringBuilder.append("'").append(str).append("'").append(",");
	}
	
	/**
	 * 添加【int值】到【SB对象】中
	 * @param value
	 * @param stringBuilder
	 * 2016年12月15日 下午4:12:27
	 */
	public static void addIntFromStringBuild(int value, StringBuilder stringBuilder)
	{
		stringBuilder.append("'").append(value).append("'").append(",");
	}
	
	/**
	 * 添加【int值】到【SB对象】中
	 * @param value
	 * @param stringBuilder
	 * 2016年12月15日 下午4:12:27
	 */
	public static void addIntFromStringBuildNotComma(int value, StringBuilder stringBuilder)
	{
		stringBuilder.append("'").append(value).append("'");
	}
	
	/**
	 * 添加【long值】到【SB对象】中
	 * @param value
	 * @param stringBuilder
	 * 2016年12月15日 下午4:12:27
	 */
	public static void addLongFromStringBuild(long value, StringBuilder stringBuilder)
	{
		stringBuilder.append("'").append(value).append("'").append(",");
	}
	
	/**
	 * 添加【指数组】到【SB对象】里
	 * @param valuesArray
	 */
	public static void addValuesToSql(String[] valuesArray, StringBuilder valuesSB)
	{
		valuesSB.append("(");
		addStringFromStringBuild(getUUid(), valuesSB);
		for (int i = 0; i < valuesArray.length - 1; i++) {
			addStringFromStringBuild(valuesArray[i], valuesSB);
		}
		addStringFromStringBuildNotComma(valuesArray[valuesArray.length - 1], valuesSB);
		valuesSB.append("),");
	}
	
	/**
	 * 添加一段【字符串】到【SB对象】中
	 * 【不追加逗号】
	 * @param str
	 * @param stringBuilder
	 */
	public static void addStringFromStringBuildNotComma(String str, StringBuilder stringBuilder)
	{
		stringBuilder.append("'").append(str).append("'");
	}
	
	/**
	 * 获得【uuid】
	 * @return
	 */
	public static String getUUid()
	{
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	
	/**
	 * 获得递增更新SQL
	 * @param tableName
	 * @param addFieldList
	 * @param whereStr
	 * @return
	 */
	public static String getIncreaseUpdateSql(String tableName, String setStr, String whereStr)
	{
		StringBuilder stringBuilder = new StringBuilder("update ");
		stringBuilder.append(tableName);
		stringBuilder.append(" set ");
		stringBuilder.append(setStr);
		stringBuilder.append(whereStr);
		return stringBuilder.toString();
	}
	
	/**
	 * 拼写递增串
	 * @param stringBuilder
	 * @param increaseField
	 * @param increaseNum
	 */
	public static void increaseStr(StringBuilder stringBuilder, String increaseField, 
			String increaseNum)
	{
		stringBuilder.append(increaseField);
		stringBuilder.append(" = ");
		stringBuilder.append(increaseField);
		stringBuilder.append(" + ");
		stringBuilder.append(increaseNum);
		stringBuilder.append(", ");
	}
	
	/**
	 * 拼写递增串【最后一部分】
	 * @param stringBuilder
	 * @param increaseField
	 * @param increaseNum
	 */
	public static void increaseStrFinal(StringBuilder stringBuilder, String increaseField, 
			String increaseNum)
	{
		stringBuilder.append(increaseField);
		stringBuilder.append(" = ");
		stringBuilder.append(increaseField);
		stringBuilder.append(" + ");
		stringBuilder.append(increaseNum);
	}
	
	/**
	 * 拼【模糊查询条件值】
	 * @param conditionValue
	 * @return
	 */
	public static String getLikeSearchCondition(String conditionValue)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("%");
		stringBuilder.append(conditionValue);
		stringBuilder.append("%");
		return stringBuilder.toString();
	}
	
	/**
	 * 获得【MYSQL】的【limit串】
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public static String getLimitStr(int pageNo, String pageNum, String sql)
	{
		StringBuilder stringBuilder = new StringBuilder(sql);
		stringBuilder.append(" limit ");
		stringBuilder.append(pageNo);
		stringBuilder.append(",");
		stringBuilder.append(pageNum);
		return stringBuilder.toString();
	}
	
	/**
	 * 获得【MYSQL】的【limit串】
	 * @param pageNo
	 * @param pageNum
	 * @param sql
	 * @return
	 * 2016年11月8日 下午3:21:10
	 */
	public static String getLimitStr(int pageNo, String pageNum, StringBuilder sql)
	{
		sql.append(" limit ").append(pageNo).append(",").append(pageNum);
		return sql.toString();
	}
	
	/**
	 * 获得【SQL IN 条件】串
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public static String getInConditionStr(String[] conditionValue)
	{
		StringBuilder stringBuilder = new StringBuilder("(");
		int maxLength = conditionValue.length - 1;
		for (int i = 0; i < maxLength; i++) {
			addStringFromStringBuild(conditionValue[i], stringBuilder);
		}
		addStringFromStringBuildNotComma(conditionValue[maxLength], stringBuilder);
		stringBuilder.append(")");
		return stringBuilder.toString();
	}
	
	
	
	
	
}