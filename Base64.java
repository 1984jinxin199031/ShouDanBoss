package com.jxsq.common.util;

import java.io.UnsupportedEncodingException;

/**
*
* @author: XieminQuan
* @time  : 2007-11-20 下午04:10:22
*
* DNAPAY
*/

public class Base64 {

	private static char[] codec_table = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
		'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
		'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
		'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
		'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
		'7', '8', '9', '+', '/' };

	public Base64() {

	}
	
	@SuppressWarnings("restriction")
	public static byte[] decode(String data) throws Exception {
		if (ValidateUtil.validateStrNull(data))
            return null;
        return new sun.misc.BASE64Decoder().decodeBuffer(data);
	}

	public static String encode(byte[] a) {
		
		int totalBits = a.length * 8;
		int nn = totalBits % 6;
		int curPos = 0;// process bits
		StringBuffer toReturn = new StringBuffer();
		while (curPos < totalBits) {
			int bytePos = curPos / 8;
			switch (curPos % 8) {
			case 0:
				toReturn.append(codec_table[(a[bytePos] & 0xfc) >> 2]);
				break;
			case 2:

				toReturn.append(codec_table[(a[bytePos] & 0x3f)]);
				break;
			case 4:
				if (bytePos == a.length - 1) {
					toReturn.append(codec_table[((a[bytePos] & 0x0f) << 2) & 0x3f]);
				} else {
					int pos = (((a[bytePos] & 0x0f) << 2) | ((a[bytePos + 1] & 0xc0) >> 6)) & 0x3f;
					toReturn.append(codec_table[pos]);
				}
				break;
			case 6:
				if (bytePos == a.length - 1) {
					toReturn.append(codec_table[((a[bytePos] & 0x03) << 4) & 0x3f]);
				} else {
					int pos = (((a[bytePos] & 0x03) << 4) | ((a[bytePos + 1] & 0xf0) >> 4)) & 0x3f;
					toReturn.append(codec_table[pos]);
				}
				break;
			default:
				//never hanppen
				break;
			}
			curPos+=6;
		}
		if(nn==2)
		{
			toReturn.append("==");
		}
		else if(nn==4)
		{
			toReturn.append("=");
		}
		return toReturn.toString();

	}
	public static void main(String[] args) throws UnsupportedEncodingException, Exception {
//		String aid1="MTQyMzJ8OWFjZTFjZGF8MTQ3MTY5MTk5N3wxfDM4NjA%3D";
//		String aid2="MTQyMzJ8ZGI2NDAwNTZ8MTQ3MTY5MjEwM3wwfDM4NjA%3D";
//		String uid1s=new String(Base64.decode(URLDecoder.decode(aid1,"utf-8")));
//		String uidnull1=new String(Base64.decode(URLDecoder.decode(aid2,"utf-8")));
//		System.out.println("uid1:"+uid1s);
//		System.out.println("uidnull:"+uidnull1);
//		//authkey c135aaatk3L7jXem
//		//aid|key|time|uid|tableid
//		String [] v1=uid1s.split("\\|");
//		String [] v2=uidnull1.split("\\|");
//		System.out.println((MD5Util.md5(v1[0]+MD5Util.md5("c135aaatk3L7jXem")+v1[2]+v1[3])).substring(0,8));
//		System.out.println((MD5Util.md5(v2[0]+MD5Util.md5("c135aaatk3L7jXem")+v2[2]+v2[3])).substring(0,8));
//		String url=WechatConstant.USER_INFO_URL+"access_token="+AccessTokenTask.getTokenId().getAccess_token()+"&openid=oXS6mt9Q25BcczSdNEG0y5CMYjNg&ang=zh_CN";
//		System.out.println("查询用户信息的url:"+url);
//		 String respMsg=  HttpUtil.doGetSSL(url,"");
//		 System.out.println("获取用户信息的结果："+respMsg);
//	        JSONObject jsonObject = JSONObject.parseObject(respMsg);
	}
	
}
