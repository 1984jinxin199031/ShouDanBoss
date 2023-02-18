/**
 * 
 */
package com.jxsq.common.util;

/**
 * @author JX
 * 用户相关工具
 */
public class MemberUtil {
	
	/**
	 * 【salt】长度
	 */
	private static final int SALT_LENGTH = 6;
	
	/**
	 * 映射uid
	 * 1、pre_common_member的mapUid
	 * 2、
	 * @param uid
	 * @return
	 */
	public static String mapUid(String uid)
	{
		return MD5Util.md5(StringUtil.getSerilNum() + uid);
	}
	
	/**
	 * 获得随机密码【处理过的】和 salt
	 * 0 -> 【salt】
	 * 1 -> 【MD5后的密码】
	 * @return
	 */
	public static String[] getRandomPasswordSalt()
	{
		String password = StringUtil.getStringRandom(8);
		String salt = getRandomSalt();
		String[] returnArray = new String[2];
		returnArray[0] = salt;
		returnArray[1] = getDBPassword(password, salt);
		return returnArray;
	}
	
	/**
	 * 生成随机默认邮箱
	 * @return
	 */
	public static String getRandomEmail()
	{
		return StringUtil.getStringRandom(10) + "@null.null";
	}
	
	/**
	 * 密码处理
	 * 1、解密成明文
	 * 2、MD5成数据库的形式【MD5(MD5(pwd) + salt)】
	 * @param password【请求传递过来的原密码】
	 * @param salt
	 * @return 返回数据库密码值
	 */
	public static String dealPassword(String password, String salt)
	{
		String pwd = ApiUtil.deCodePassword(password);
		return getDBPassword(pwd, salt);
	}
	
	/**
	 * 密码处理
	 * 1、生成随机【salt】
	 * 2、MD5成数据库的形式【MD5(MD5(pwd) + salt)】
	 * @param password【请求传递过来的原密码(密文密码)】
	 * @return 返回数据库密码值和生成的【salt】
	 * 0 -> salt
	 * 1 -> 密码
	 */
	public static String[] dealPassword(String password)
	{
		String salt = getRandomSalt();
		String pwd = dealPassword(password, salt);
		return new String[]{salt, pwd};
	}
	
	/**
	 * 获得随机【Salt】
	 * @return
	 */
	public static String getRandomSalt()
	{
		return StringUtil.getStringRandom(SALT_LENGTH);
	}
	
	/**
	 * 将【明文密码】转换成【数据库的密码形式】
	 * @param password
	 * @param salt
	 * @return 数据库的密码形式
	 */
	public static String getDBPassword(String password, String salt)
	{
		return MD5Util.md5(MD5Util.md5(password) + salt);
	}
	
	public static void main(String[] args) {
		System.out.println(dealPassword("jinxin", "45ed6e"));
	}
	
}
