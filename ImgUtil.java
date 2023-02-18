package com.jxsq.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/**
 * @author JX
 * 图片压缩工具
 */
public class ImgUtil {
	private static final Logger log = Logger.getLogger(ImgUtil.class);
	
	public static void main(String[] args) throws Exception {
		resize(120, 120, "D:\\Copy\\x.jpg", "D:\\Copy\\456.jpg");
	}
	
	/**
	 * 强制压缩/放大图片到固定的大小
	 * @param width
	 * @param height
	 * @param sourceFileName 	【源文件】
	 * @param destFileName		【目标文件】
	 * @throws IOException
	 * return 保存到的目标文件
	 * 2017年2月24日 下午3:10:22
	 */
	public static File resize(int width, int height, String sourceFileName, 
			String destFileName) throws IOException {
		log.info("【sourceFileName】" + sourceFileName + "【destFileName】" + destFileName);
		BufferedImage image = new BufferedImage(width, height, 
				BufferedImage.TYPE_INT_RGB); 
		File file = new File(sourceFileName);
		Image img = ImageIO.read(file);      
		image.getGraphics().drawImage(img, 0, 0, width, height, null);
		File destFile = new File(destFileName);
		FileOutputStream out = new FileOutputStream(destFile);
		ImageIO.write(image, "jpg" , destFile); 
		image.flush();
		out.close();
		return destFile;
	}
	
}