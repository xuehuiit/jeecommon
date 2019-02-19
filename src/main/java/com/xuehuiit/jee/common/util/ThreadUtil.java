/**
 * Copyright (c) linkwise 2007-2009 corporation.  
 * All rights reserved
 */
package com.xuehuiit.jee.common.util;

import java.io.File;

import org.apache.log4j.Logger;


/**
 * 在多线程中用的一些方法
 * 
 * @author </br> <a href="mailto:fx19800215@163.com"> robert.feng</a>
 *
 */
public class ThreadUtil {

	private static final Logger logger = Logger.getLogger(ThreadUtil.class);
	
	
	/**
	 * 
	 *  校验某个线程在读文件的时候该文件是否在被其他的线程在写入这里采用的校验
	 *  方法是：先读取某个文件的长度，将线程休眠一定的时候再次读取文件的长度，如果
	 *  文件的长度没哟发生变化则该文件是完整的。
	 * 
	 * @param fileName   需要校验的文件名称
	 * @param thread     读取文件的线程
	 * @param sleepTime  线程休眠时间
	 * @return           文件是否有其他线程在写入 true - 没有， false-有
	 */
	public static boolean fileCheck(String fileName , Thread thread , int sleepTime){
		
		
		File file = new File(fileName);
		long fristFileLength;
		long lastFileLength;
		
		
		fristFileLength = file.length();
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			
			logger.error("fiand a error!",e);
		}
		
		 lastFileLength = file.length();
		
		if( fristFileLength == lastFileLength )
		    return true;
		else
		    return false;
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//fileCheck("",1);
		new Testreadfile().run();

	}
	
	
}



class Testreadfile implements java.lang.Runnable{
	
	
	public void run(){
		
		File file = new File("D:\\dowloand\\10201_client_win32.zip");
		
		System.out.println(file.length());
		
	    if(ThreadUtil.fileCheck("D:\\dowloand\\10201_client_win32.zip", Thread.currentThread(),10000))
	    	System.out.println("文件没有发生变化");
	    else
	    	System.out.println("文件没有发生变化");
				
	}
	
}