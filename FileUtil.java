/**
 * 
 */
package com.jxsq.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author JX 文件流相关
 */
public class FileUtil {
	/**
	 * 日志
	 */
	private static final Logger log = Logger.getLogger(FileUtil.class);
	
	/**
	 * 通过【UID】获得文件名
	 * @param uid
	 * @return
	 */
	public static String getFileName(String uid)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(new Date().getTime());
		stringBuilder.append("_");
		stringBuilder.append(uid);
		stringBuilder.append(".jpg");
		return stringBuilder.toString();
	}
	
	/**
	 * 从【HTTP的URL】获取图片到本地
	 * @param url
	 * @param fileName
	 * @param tempFilePath 【临时文件的地址】
	 * @return
	 */
	public static boolean downloadPicFromUrl(String url, String fileName, 
			String tempFilePath) 
	{
		byte[] btImg = getImageFromNetByUrl(url);
		if (null != btImg && btImg.length > 0) 
		{
			writeImageToDisk(btImg, fileName, tempFilePath);
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * 验证【头像文件路径】是否正确存在
	 * @param filePath
	 * @return
	 */
	public static boolean validateImgExists(String filePath) {
		return new File(filePath).exists();
	}
	
	/**
	 * 将图片写入到磁盘
	 * 
	 * @param img
	 *            图片数据流
	 * @param fileName
	 *            文件保存时的名称
	 */
	public static void writeImageToDisk(byte[] img, String fileName, String path) {
		try {
			File file = new File(path + fileName);
			
			FileOutputStream fops = new FileOutputStream(file);
			fops.write(img);
			fops.flush();
			fops.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("【写入图片文件】出现异常", e);
		}
	}

	/**
	 * 根据地址获得数据的字节流
	 * 
	 * @param strUrl
	 *            网络连接地址
	 * @return
	 */
	public static byte[] getImageFromNetByUrl(String strUrl) {
		InputStream inStream = null;
		HttpURLConnection conn = null;
		try {
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			inStream = conn.getInputStream();// 通过输入流获取图片数据
			byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
			return btImg;
		}
		catch (FileNotFoundException fileNotFoundException)
		{
			fileNotFoundException.printStackTrace();
			log.error("【获取头像文件不存在】" + strUrl);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("【获取头像文件出异常】" + strUrl + "【expMsg】", e);
		}
		finally
		{
			try {
				if (inStream != null)
					inStream.close();
				if (conn != null)
					conn.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				log.error("【释放连接】出现异常", e);
			}
		}
		return null;
	}

	/**
	 * 从输入流中获取数据
	 * 
	 * @param inStream
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = null;
		try {
			outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			byte[] returnData = outStream.toByteArray();
			return returnData;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("【读文件流】出现异常");
		}
		finally 
		{
			if (inStream != null)
				inStream.close();
			if (outStream != null)
				outStream.close();
		}
		return null;
	}
	
	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	public static boolean copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			InputStream inStream = new FileInputStream(oldPath); // 读入原文件
			FileOutputStream fs = new FileOutputStream(newPath);
			byte[] buffer = new byte[1024];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread; // 字节数 文件大小
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
			fs.close();
			return true;
		} catch (Exception e) {
			log.error("【复制单个文件操作出错】【oldPath】" + oldPath + "【newPath】" + newPath, e);
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 复制单个文件
	 * @param tempFile【临时文件】
	 * @param destFilePath【目标文件完整目录】
	 * @return
	 * 2016年12月15日 下午2:34:02
	 */
	@SuppressWarnings("unused")
	public static boolean copyFile(File tempFile, String destFilePath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			InputStream inStream = new FileInputStream(tempFile); // 读入原文件
			FileOutputStream fs = new FileOutputStream(destFilePath);
			byte[] buffer = new byte[1024];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread; // 字节数 文件大小
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
			fs.close();
			return true;
		} catch (Exception e) {
			log.error("【复制单个文件操作出错】【tempFile】" + tempFile.getAbsolutePath() + "【destFilePath】" + destFilePath, e);
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 验证文件存在
	 * 不为空 -> 存在文件
	 * @param path
	 * @return
	 * 2016年8月29日 下午4:49:06
	 */
	public static File existsFile(String path)
	{
		File file = new File(path);
		if (file.exists())
			return file;
		return null;
	}
	
	/**
	 * 文件完整路径中没有的路径则创建
	 * 【以/分隔】
	 * @param filePath【文件完整路径】
	 * 2016年9月9日 上午11:28:06
	 */
	public static void notExistCreatePath(String filePath) {
		String paths[] = filePath.split("/");
		String dir = paths[0];
		for (int i = 0; i < paths.length - 2; i++) 
		{
			try {
				dir = dir + "/" + paths[i + 1];
				File dirFile = new File(dir);
				if (!dirFile.exists()) {
					dirFile.mkdir();
					log.info("创建目录为：" + dir);
				}
			} catch (Exception err) {
				log.error("ELS - Chart : 文件夹创建发生异常", err);
				err.printStackTrace();
			}
		}
	}
	
	/**
	 * 删除目录下文件及其目录
	 * 【一级嵌套】
	 * 1、删除目录里的所有文件(假设目录里没其他文件夹，全是文件)
	 * 2、删除这个目录
	 * @param filePath
	 * 2016年9月9日 上午11:28:06
	 */
	public static void deleteByFilePath(String filePath) {
		try {
			File file = new File(filePath);
			if (file.exists())
			{
				File[] fileArray = file.listFiles();
				if (fileArray != null)
				{
					File fileTemp = null;
					for (int i = 0; i < fileArray.length; i++) {
						fileTemp = fileArray[i];
						if (fileTemp.exists())
							fileTemp.delete();
					}
				}
				file.delete();
			}
		} catch (Exception e) {
			log.error("【删除目录下文件及目录】出现异常【filePath】" + filePath, e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件完整路径中没有的路径则创建
	 * 【按指定分隔符进行分隔】
	 * @param filePath【文件完整路径】
	 * @param splitSymbol【分隔符】
	 * 2016年12月16日 下午6:00:12
	 */
	public static void notExistCreatePath(String filePath, String splitSymbol) 
	{
		String[] paths = filePath.split(splitSymbol);
		String dir = paths[0];
		for (int i = 0; i < paths.length - 2; i++) 
		{
			try {
				dir = dir + "/" + paths[i + 1];
				File dirFile = new File(dir);
				if (!dirFile.exists()) {
					dirFile.mkdir();
					log.info("【文件完整路径中没有的路径则创建】创建目录为：" + dir);
				}
			} catch (Exception err) {
				log.error("【文件完整路径中没有的路径则创建】文件夹创建发生异常【目录】" + dir, err);
				err.printStackTrace();
			}
		}
	}
	
	/**
	 * 【文件流】转【字符串】
	 * @param inputStream
	 * @return
	 * 2016年11月17日 下午4:03:37
	 */
	public static String inputstreamToStr(InputStream inputStream)
	{
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		StringBuilder sb = new StringBuilder();
		String temp = "";
		try {
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			while ((temp = bufferedReader.readLine()) != null) {
				sb.append(temp);
			}
			return sb.toString();
		} catch (IOException e) {
			log.error("【文件流解析出现异常】", e);
			e.printStackTrace();
		}
		finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
				if (inputStreamReader != null)
					inputStreamReader.close();
			} catch (IOException e) {
				log.error("【文件流解析关闭文件流时出现异常】", e);
				e.printStackTrace();
			}
		}
		return "";
	}
	
	/**
	 * 不存在则创建目录
	 * @param dirPath
	 * 2016年12月13日 下午5:40:22
	 */
	public static void createDirNotExists(String dirPath)
	{
		File dirFile = new File(dirPath);
		if (!dirFile.exists()) 
			dirFile.mkdir();
	}
	
	/**
	 * 验证【目录】是否存在
	 * @param dirPath
	 * @return true -> 存在
	 * 2016年12月20日 下午3:39:42
	 */
	public static boolean dirExistsValidate(String dirPath)
	{
		File dirFile = new File(dirPath);
		return dirFile.exists(); 
	}
	
	/**
	 * 截取【文件名】
	 * 【不要(.后缀)】
	 * @param fileName
	 * @return
	 * 2016年12月15日 下午5:47:08
	 */
	public static String cutFileName(String fileName)
	{
		int pointIndex = fileName.lastIndexOf('.');
		if (pointIndex > 0)
			return fileName.substring(0, pointIndex);
		else
			return null;
	}
	
	public static void main(String[] args) throws IOException {
//		System.out.println(getImageFromNetByUrl("http://bbs.jx.vc/uc_server/data/avatar/000/00/00/01_avatar_big.jpg")==null);
//		String url = HeadImgUtil.getHeadImgUrl("26", HeadImgUtil.HEAD_SIZE_BIG);
//		String path = "d:\\copy\\";
//		String tempFileName = StringUtil.getRandomStrByLength(12) + ".jpg";
//		String tempFilePath = path + tempFileName;
//		downloadPicFromUrl(url, tempFileName, path);
//		ImgUtil.resize(48, 48, tempFilePath, "d:\\copy\\333.jpg");
		
//		System.out.println(downloadPicFromUrl("http://bbs.jx.vc/uc_server/data/avatar/000/00/00/01_avatar_big.jpg", "x.jpg", 
//				"d:\\copy\\"));
//		
//		copyFile("d:\\copy\\x.jpg", "d:\\x.jpg");
//		notExistCreatePath("d:\\copy\\data\\avatar\\000\\00\\00\\02_avatar_middle.jpg");
		
//		System.out.println(inputstreamToStr(new FileInputStream(new File("d:\\3333.txt"))));
		deleteByFilePath("D:/tempPath//");
//		File file = new File("d:/jinxin.jpg");
//		System.out.println(file.getPath());
//		System.out.println(file.getAbsolutePath());
	}
}
