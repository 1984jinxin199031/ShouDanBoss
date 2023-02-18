/**
 * 
 */
package com.jxsq.common.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.jxsq.common.service.impl.member.MemberService;

/**
 * @author JX
 * 头像【获取】工具
 */
public class HeadImgUtil {
	/**
	 * 大头像
	 */
	public static final String HEAD_SIZE_BIG = "big";
	/**
	 * 中头像
	 */
	public static final String HEAD_SIZE_MIDDLE = "middle";
	/**
	 * 小头像
	 */
	public static final String HEAD_SIZE_SMALL = "small";
	/**
	 * 头像的大小MAP
	 */
	private static HashMap<String, String> imgSizeMap = new HashMap<String, String>();
	static
	{
		imgSizeMap.put(HEAD_SIZE_BIG, "1");
		imgSizeMap.put(HEAD_SIZE_MIDDLE, "2");
		imgSizeMap.put(HEAD_SIZE_SMALL, "3");
	}
	/**
	 * 路径前补零的最大长度
	 */
	private static final int ADD_ZERO_MAX_LENGTH = 9;
	
	
	/**
	 * 获得用户头像
	 * 1、验证用户有无头像【中型头像】
	 * 2、没有用【默认头像】
	 * @param uid
	 * @return
	 */
	public static String getUserHeadUrl(String uid, boolean httpFlag)
	{
		String saveHeadImgPath = getSaveHeadImgPath(uid);
		String remoteHeadImgUrlGet = getHeadImgTotalUrl(PropertyUtil.getHeadImgRemoteFixationUrl(), uid, HEAD_SIZE_MIDDLE);
		if (new MemberService().getRemoteFlagByUid(uid))
			return remoteHeadImgUrlGet;
		else if (FileUtil.validateImgExists(saveHeadImgPath))
		{
			//oss没有返回本地地址，并上传全部大小的头像到远端
			new Thread(new UploadAllSizeHeadImgThread(uid)).start();
			return HeadImgUtil.getHeadImgUrl(uid, HeadImgUtil.HEAD_SIZE_MIDDLE, httpFlag);
		}
		else 
			return PropertyUtil.getDefaultHeadImgUrl(httpFlag);
	}
	
	/**
	 * 【上传全部大小头像】线程
	 * 1、上传大中小头像
	 * 2、更新用户头像为远程头像
	 * 【内部类】
	 * @author JX
	 * 2016年11月22日 下午12:09:56
	 */
	private static class UploadAllSizeHeadImgThread implements Runnable
	{
		private String uid = null;
		public UploadAllSizeHeadImgThread(String uid) {
			super();
			this.uid = uid;
		}

		@Override
		public void run() {
			uploadAllSizeHeadImg(uid);
			new MemberService().updateHeadImgToRemote(uid);
		}
		
		/**
		 * 上传全部大小的头像到远端
		 * @param uid
		 * 2017年2月24日 下午5:22:26
		 */
		private void uploadAllSizeHeadImg(String uid)
		{
			uploadHeadImg2Oss(getSaveHeadImgUrl(uid, HEAD_SIZE_BIG), uid, HEAD_SIZE_BIG);
			uploadHeadImg2Oss(getSaveHeadImgUrl(uid, HEAD_SIZE_MIDDLE), uid, HEAD_SIZE_MIDDLE);
			uploadHeadImg2Oss(getSaveHeadImgUrl(uid, HEAD_SIZE_SMALL), uid, HEAD_SIZE_SMALL);
		}
	}
	
	/**
	 * 验证用户头像是否已存在
	 * @param uid
	 * true -> 以存在
	 * @return
	 * 2016年10月19日 下午4:48:27
	 */
	public static boolean validateUserHeadUrlExists(String uid)
	{
		String saveHeadImgPath = getSaveHeadImgPath(uid);
		return FileUtil.validateImgExists(saveHeadImgPath);
	}
	
	/**
	 * 获得头像的URL
	 * @param uid
	 * @param size
	 * @return
	 */
	public static String getHeadImgUrl(String uid, String size, boolean httpFlag)
	{
		if (validateSizeNotExists(size)) 
			return "";
		uid = StringUtil.addZeroToStr(uid, ADD_ZERO_MAX_LENGTH);
		return PropertyUtil.getHeadFixationUrl(httpFlag) + uid.substring(0, 3) + "/" + uid.substring(3, 5) + "/" + uid.substring(5, 7) + 
				"/" + uid.substring(7) + "_avatar_" + size + ".jpg";
	}
	
	/**
	 * 获得保存头像的路径
	 * 大小为【中】
	 * @param uid
	 * @param size
	 * @return
	 */
	private static String getSaveHeadImgPath(String uid)
	{
		uid = StringUtil.addZeroToStr(uid, ADD_ZERO_MAX_LENGTH);
		String headImgTotalPath = PropertyUtil.getSaveHeadImgPath() + uid.substring(0, 3) + "/" + uid.substring(3, 5) + "/" + uid.substring(5, 7) + 
				"/" + uid.substring(7) + "_avatar_middle.jpg";
		return headImgTotalPath; 
	}
	
	/**
	 * 获得头像的路径【物理路径】
	 * 【若路径中有不存在路径则创建】
	 * @param uid
	 * @param size
	 * @return
	 */
	public static String getSaveHeadImgUrl(String uid, String size)
	{
		if (validateSizeNotExists(size)) 
			return "";
		uid = StringUtil.addZeroToStr(uid, ADD_ZERO_MAX_LENGTH);
		String headImgTotalPath = getHeadImgTotalUrl(PropertyUtil.getSaveHeadImgPath(), uid, size);
		FileUtil.notExistCreatePath(headImgTotalPath);//不存在创建
		return headImgTotalPath; 
	}
	
	/**
	 * 获得头像整个地址
	 * @param headImgFixation【头像前缀】
	 * @param uid
	 * @param size
	 * @return
	 * 2017年2月24日 下午3:42:04
	 */
	private static String getHeadImgTotalUrl(String headImgFixation, String uid, String size)
	{
		uid = StringUtil.addZeroToStr(uid, ADD_ZERO_MAX_LENGTH);
		return headImgFixation + uid.substring(0, 3) + "/" + uid.substring(3, 5) + "/" + uid.substring(5, 7) + 
				"/" + uid.substring(7) + "_avatar_" + size + ".jpg";
	}
	
	/**
	 * 调整图片大小
	 * 【三种大小类型】
	 * @param uid
	 * @param size
	 * @param fileTotalPath
	 * @throws IOException 
	 */
	public static void resizeHeadImg(String uid, String size, String fileTotalPath) throws IOException
	{
		File localFile = ImgUtil.resize(PropertyUtil.getHeadWidthBySize(size), 
				PropertyUtil.getHeadHeightBySize(size), fileTotalPath, 
				getSaveHeadImgUrl(uid, size));
		uploadHeadImg2Oss(localFile.getPath(), uid, size);
	}
	
	/**
	 * 上传头像到远端oss
	 * @param localFilePath
	 * @param uid
	 * @param size
	 * 2017年2月24日 下午3:52:37
	 */
	public static void uploadHeadImg2Oss(String localFilePath, String uid, String size)
	{
		String remoteHeadImgUrl = getHeadImgTotalUrl(PropertyUtil.getSaveHeadImgRemotePath(), uid, size);
		OssUtil.uploadLocalFile(localFilePath, remoteHeadImgUrl);
	}
	
	/**
	 * 验证【size类型值】是否不存在
	 * @param size
	 * @return
	 */
	private static boolean validateSizeNotExists(String size)
	{
		if (imgSizeMap.get(size) == null)
			return true;
		return false;
	}
	
}
