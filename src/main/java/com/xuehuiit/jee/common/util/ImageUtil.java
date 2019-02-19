/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.util;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
/*import java.io.UnsupportedEncodingException;*/

import javax.imageio.ImageIO;

import com.keypoint.PngEncoder;

/*import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;*/


/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class ImageUtil {

	
	/**
	 * 获取图片的大小
	 */
	public static int[] getImageWidth(String imageFullPath){
		
		
		//String imageFullPath = "E:/wingfeng/website/cofetalk/cofetalk/WebRoot/images/0000000257_1.jpg";
		int[] imagesize = new int[2];
		
		try {
			
			 BufferedImage mImage;
			 FileInputStream in;
			 in = new FileInputStream( new File( imageFullPath ) );
			 
			 mImage = ImageIO.read(in);
	         int height = mImage.getHeight();
	         int width = mImage.getWidth();
	         
	         System.out.println(  width  + "  ==  " +  height );
	         
	         imagesize[0] = width;
	         imagesize[1] = height;
	         
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException ieEx) {
			
			ieEx.printStackTrace();
			
		}
       
		return imagesize;
		
	}
	
	
	
	/**
	 * 改变图片的尺寸
	 */
	public static void chageImageSize(String ogiFile , int newWidth , int newHeight, String newFile){
		
		
		/*String imageFullPath = ogiFile ;//"E:/wingfeng/website/cofetalk/cofetalk/WebRoot/images/0000000257_1.jpg";
		
		try {
			
			File _file = new File(imageFullPath); //读入文件
			Image src = ImageIO.read(_file); //构造对象
			
			BufferedImage tag = new BufferedImage(newWidth, newHeight,BufferedImage.TYPE_INT_RGB);

			tag.getGraphics().drawImage(src,0,0,newWidth,newHeight,null);
			FileOutputStream out = new FileOutputStream(newFile);//输出到文件流
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

			//PNGImageEncoder peng = PNGEncoder 
				
			
			encoder.encode(tag);

	         
	         
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException ieEx) {
			
			ieEx.printStackTrace();
			
		}*/
        
		
	}
	
	
	/**
	 * 改变图片的尺寸
	 */
	/*public static void chageImageSize4png(String ogiFile , int newWidth , int newHeight, String newFile){
		
		
		String imageFullPath = ogiFile ;//"E:/wingfeng/website/cofetalk/cofetalk/WebRoot/images/0000000257_1.jpg";
		
		try {
			
			File _file = new File(imageFullPath); //读入文件
			Image src = ImageIO.read(_file); //构造对象
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);

			BufferedImage tag = new BufferedImage(newWidth, newHeight,BufferedImage.TYPE_INT_RGB);

			tag.getGraphics().drawImage(src,0,0,newWidth,newHeight,null);
			FileOutputStream out = new FileOutputStream(newFile);//输出到文件流
			//PNGImageEncoder encoder =pngCod.createJPEGEncoder(out);

			encoder.encode(tag);

	         
	         
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException ieEx) {
			
			ieEx.printStackTrace();
			
		}
        
		
	}*/
	
	
	/**
	 * 将图片的base64格式的字符串转换成图片byte数组
	 * @param imageBase64String  base64格式的图片字符串
	 * @return
	 *//*
	public static byte[] convertBase64image(String imageBase64String){
		
		
		return null;
		
	}
	
	*/
	
	/**
	 * 将图片的base64格式的字符串转换成图片byte数组
	 * @param imageBase64String  base64格式的图片字符串
	 * @param picFilePath   存放文件的全路径
	 * @return
	 */
	public static void convertBase64image(String imageBase64String,String picFilePath){
		
		
		try{ 
			byte[] result = new sun.misc.BASE64Decoder().decodeBuffer(imageBase64String.trim()); 
			RandomAccessFile inOut = new RandomAccessFile(picFilePath, "rw"); // r,rw,rws,rwd 
			//用FileOutputStream亦可 
			inOut.write(result); 
			inOut.close(); 
			}catch(Exception e){ 
				e.printStackTrace(); 
			} 

		
	}
	
	
	/**
	 * 将图片文件转换成Base64格式的字符串
	 * 
	 * @param fileFullPath
	 * @return
	 */
	public static String convertImage4Base64(String fileFullPath){
		
		
		String content = null;
		
		try {
			
			
			FileInputStream fileForInput = new FileInputStream(fileFullPath);
			byte[] bytes = new byte[fileForInput.available()];
			
			fileForInput.read(bytes);
			content = new sun.misc.BASE64Encoder().encode(bytes); // 具体的编码方法
			
			fileForInput.close();
			System.out.println(content.length());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return content; 
		
		
	}
	
	
	
	public static void handlePic(String sourceFile,String outFile,int w,int h)
	 {
	  try
	  {
	  
	  double ratioH=0.0;
	  double ratioW=0.0;
	  File file=new File(sourceFile);
	  //File dbFile=new File(outFile);
	  
	  BufferedImage srcPic=ImageIO.read(file);
	  
	  Image itemp=srcPic.getScaledInstance(w,h,BufferedImage.SCALE_SMOOTH);
	  
	  ratioW=((double)w)/srcPic.getWidth();
	  ratioH=((double)h)/srcPic.getHeight();
	  
	  AffineTransformOp op=new AffineTransformOp(AffineTransform.getScaleInstance(ratioW,ratioH),null);
	  itemp=op.filter(srcPic,null);
	  
	  PngEncoder encoder=new PngEncoder(itemp);
	  
	  //encoder.setCompressionLevel(5);
	  encoder.setEncodeAlpha(false);
	  encoder.setCompressionLevel(9);
	  encoder.pngEncode();
	  
	  byte[] result=encoder.pngEncode();
	  FileOutputStream fos=new FileOutputStream(outFile);
	  fos.write(result);
	  fos.close();
	  /*System.out.println(System.currentTimeMillis()-t1);
	  Thread.sleep(1000L);*/
	  }
	  catch(Exception e)
	  {
	   e.printStackTrace();
	  }
	  
	 }
	
	
	
	/**
	 * 
	 */
	public static void  main(String[] args){
		
		
   //  convertBase64image("iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAFo9M/3AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA9FJREFUeNpi/vf/LwO3RcB/ht5jTxhevXrzHyCAmD///sew7hkHAwvfxPv/Gd9yMgAEACIA3f8AgajF/wAwYf/+/vz/QXaV/wH7+/8AdI+e//8oPABLHAYAAgBEALv/AKy4xAD4+fv/4vn/ANTZ3AAB/v///wLC7QACqMgALADVAALw9fUAAMr2AP9HSwApJyQAAPv7//8ALmD/AESB/6TB0wACiBHkknU7j/9nYWViKJ67iyHU3ZzBQUGQIX/Rfoa1ZaEMDAxr7vwHAYb1f/8zNd///xdoCcOZf/8Z3Rr/n7525z/LXqWfjHVzN/1nmr2D4Z+iJcPEZRcYGCfsYuhPNWcoWHmKkeE70GtA/L9/6ab/jx4//c8gG/m/fumu/3kz1v0HOQcggBj+///NENm/keHinQcMBx98+g/08P9Td58wMKjFM4BsZgK64j8If//59//kpRsZjJLKGdiZ/v2HigPB0n//8+bu/M+wBRQEv/8zbbr5n9GnFexwRtee/0yn9O4xTFp6lIFBFKjhPwvD/6NfGf47JYG1q0r/YmA6cvEyA+PHFwwMz/4xvHr9AqjmLwPD4UUMXz9/ZCguTmVk0ddSY+xMefe//MJNhoMcLxmYXnxn+Pf2BUN0dT/DWyF1BhY7XS2Gr4xcDP+LFzCsELRg+H92A0NBUyGDCf8/iBtB4VAwY9P/H3+Bfpr45//le0/+TztwExQG4HAACNAz2YREGYVR+LyfM5+iOOMUiiOGo6ETE0EK7sRdlIQi1KadmK3aGW5cSEYgEUGEm1q1EpUgNQha9GMU/hU1RdPCJr7wZ2QcHYtxpmSc03u/0guX997LPefCc94Lk4VhYXI1PLQnqEz46Plbfl3+RgzF+TgaN3kbPgxfHnEZmez534DW8Uts6rtFZyXJcN8wpbmXcuUJd1JbLLg4bhJdI2y9eoc/EgmeH/tMifT+y/jMtVEte27PSaSHMqOQl3TeiLtnxgED74iPanRvUwXXaR794qwSJ3tonbvQjbnYd6TS2nTFFriegzgCWBZEipT3PrRACyh5oMiL9FYGmewe7o/fhud0yU/J/QGfvZnXsBqA6CvAXwlRwcvFHQiVZm01MB2DfJgEK+rwYuE97i5uGMbiKfNa7qKx4RgvRpYQc6YQC/ZDlqN4OpdVSAJ+Kge2E2jyrqJFf9KDqA+lxbYYoacyXI+DYfvL8frhKI74bd2dgLnhJFPoaF9Hfd1ZFAoFDE/Moi0UOtR4asQ+WEtVMIRs/hd9+xVwNpLI7WaxspZGIBhAZvc3jvpKELDzMth56tDgL6a7/G02DKjVAAAAAElFTkSuQmCC","c://111.png");
		
		System.out.println(convertImage4Base64("D:/server/xampp/htdocs/eshowc/source/images/iphone.png"));
		//handlePic("E:/temp/tt1.png","E:/temp/tt2.png",320,240);
		//chageImageSize("E:/temp/tttt.jpg",320,480,"E:/temp/tttt1.jpg");
		
		
		//convertBase64image("aHR0cDovL2ltZzAxLnRhb2Jhb2Nkbi5jb20vYmFvL3VwbG9hZGVkL2kxL1Qxemx1cFhsNDlYWGNFZHpNWV8wMzAxNDcuanBnXzEyMHgxMjAuanBn", "E:/temp/base64.png");
		
//		try {
//			System.out.println(Base64.decode("aHR0cDovL2ltZzAxLnRhb2Jhb2Nkbi5jb20vYmFvL3VwbG9hZGVkL2kxL1Qxemx1cFhsNDlYWGNFZHpNWV8wMzAxNDcuanBnXzEyMHgxMjAuanBn", "GBK"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
	}
	
	
	
	
	
	
	
}
