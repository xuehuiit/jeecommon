/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.deamon;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.PropertyConfigurator;

import com.xuehuiit.jee.common.constant.CommonConstant;
import com.xuehuiit.jee.common.constant.HttpContant;
import com.xuehuiit.jee.common.util.Log4jUtils;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class ApplicationContext {


	
	/**
	 * 
	 * 初始化上下文
	 * 
	 * @param appDir  系统配置文件dir
	 * 
	 * 
	 */
	public static void initApplicationContext(String appDir){
		
		
		String configFile = appDir+"/config/appContext.xml";
		System.out.println("配置文件路径为-> " + configFile );
		CommonConstant.iniCommonConstant(configFile);
		
		CommonConstant.ApplicationContext.put("app_dir", appDir);
		
		//配置log4j
		String log4jLogDir = CommonConstant.getLog4jDir();
		
		if( null!=log4jLogDir && log4jLogDir.length()>0  ){
			//如果文件和目录不存在，则处理之
			File fileLog4jDir = new File(log4jLogDir);
			if (!fileLog4jDir.exists()) {
				fileLog4jDir.mkdir();
	        }
			
		}
		
		System.setProperty(CommonConstant.LOG4J_CONFIG_FLAG, log4jLogDir);
		String log4jconfigfile = appDir+"/config/log4j.properties";
		System.out.println("log4j 配置文件路 -> " + log4jconfigfile );
		String log4jlogfilePifx = "login";
		System.setProperty(CommonConstant.LOG4J_CONFIG_PROFIX, log4jlogfilePifx);
		PropertyConfigurator.configure(log4jconfigfile); 
		
		//System.setProperty("MONGO.POOLSIZE", "200"); 

	}
	
	
	
	/**
	 * 
	 * 初始化上下文
	 *
	 * 
	 * 
	 */
	public static void reflushApplicationContext4Http(){
		
		
		String appDir = (String)CommonConstant.ApplicationContext.get("app_dir");
	    String url = (String)CommonConstant.ApplicationContext.get("app_config_httpurl");
		
	    
		System.out.println("配置文件路径为-> " + url );
		
		String configstring = HttpContant.getContant4Server(url);
		CommonConstant.iniCommonConstant4String(configstring);
		
		
		CommonConstant.ApplicationContext.put("app_dir", appDir);
		//通过web路径获取配置信息的URL
		CommonConstant.ApplicationContext.put("app_config_httpurl", url);
		
		//配置log4j
		String log4jLogDir = CommonConstant.getLog4jDir();
		
		if( null!=log4jLogDir && log4jLogDir.length()>0  ){
			//如果文件和目录不存在，则处理之
			File fileLog4jDir = new File(log4jLogDir);
			if (!fileLog4jDir.exists()) {
				fileLog4jDir.mkdir();
	        }
			
		}
		
		System.setProperty(CommonConstant.LOG4J_CONFIG_FLAG, log4jLogDir);
		String log4jconfigfile = appDir+"/config/log4j.properties";
		System.out.println("log4j 配置文件路 -> " + log4jconfigfile );
		String log4jlogfilePifx = "logs";
		System.setProperty(CommonConstant.LOG4J_CONFIG_PROFIX, log4jlogfilePifx);
		//PropertyConfigurator.configure(log4jconfigfile); 
		
		String logLeveChage  = (String)CommonConstant.ApplicationContext.get("logLeveChage");
		if( null != logLeveChage  ){
			
			System.out.println("########## 【 准备调整日志级别 】 ############## " + logLeveChage  );
			
			if(  logLeveChage.equals("info")  ){
				Log4jUtils.enableInfo();
			}else if(  logLeveChage.equals("error")  ){
				Log4jUtils.enableError();
			}else if(  logLeveChage.equals("warn")  ){
				Log4jUtils.enableWarn();
			}else if(  logLeveChage.equals("debug")  ){
				Log4jUtils.enableDebug();
			}
			
		}
		
		
		//System.setProperty("MONGO.POOLSIZE", "200"); 

	}
	
	
	
	/**
	 * 
	 * 初始化上下文
	 * 
	 * @param appDir  系统配置文件dir
	 * @throws UnsupportedEncodingException 
	 * 
	 * 
	 */
	public static void initApplicationContext4Http(String appDir , String url) throws UnsupportedEncodingException{
		
		
		
		System.out.println("配置文件路径为-> " + url );
		
		url = url +"&dir="+URLEncoder.encode(appDir, "utf-8");
		
		
		String configstring = HttpContant.getContant4Server(url);
		
		CommonConstant.iniCommonConstant4String(configstring);
		
		CommonConstant.ApplicationContext.put("app_dir", appDir);
		//通过web路径获取配置信息的URL
		CommonConstant.ApplicationContext.put("app_config_httpurl", url);
		
		//配置log4j
		String log4jLogDir = CommonConstant.getLog4jDir();
		
		if( null!=log4jLogDir && log4jLogDir.length()>0  ){
			//如果文件和目录不存在，则处理之
			File fileLog4jDir = new File(log4jLogDir);
			if (!fileLog4jDir.exists()) {
				fileLog4jDir.mkdir();
	        }
			
		}
		
		System.setProperty(CommonConstant.LOG4J_CONFIG_FLAG, log4jLogDir);
		String log4jconfigfile = appDir+"/config/log4j.properties";
		System.out.println("log4j 配置文件路 -> " + log4jconfigfile );
		String log4jlogfilePifx = "login";
		System.setProperty(CommonConstant.LOG4J_CONFIG_PROFIX, log4jlogfilePifx);
		PropertyConfigurator.configure(log4jconfigfile); 
		
		//System.setProperty("MONGO.POOLSIZE", "200"); 

	}
	
	
	
	/**
	 * 
	 * 初始化上下文
	 * 
	 * @param appDir  系统配置文件dir
	 * @param springClassPath  spring配置文件的类路径 示例： "classpath:resource/spring/*"
	 * 
	 * 
	 */
	public static void initApplicationContext(String appDir , String springClassPath){
		
		
		String configFile = appDir+"/config/appContext.xml";
		System.out.println("配置文件路径为-> " + configFile );
		CommonConstant.iniCommonConstant(configFile);
		
		CommonConstant.ApplicationContext.put("app_dir", appDir);
		CommonConstant.ApplicationContext.put("springclass", springClassPath);
		
		//配置log4j
		String log4jLogDir = CommonConstant.getLog4jDir();
		
		if( null!=log4jLogDir && log4jLogDir.length()>0  ){
			//如果文件和目录不存在，则处理之
			File fileLog4jDir = new File(log4jLogDir);
			if (!fileLog4jDir.exists()) {
				fileLog4jDir.mkdir();
	        }
			
		}
		
		System.setProperty(CommonConstant.LOG4J_CONFIG_FLAG, log4jLogDir);
		String log4jconfigfile = appDir+"/config/log4j.properties";
		System.out.println("log4j 配置文件路 -> " + log4jconfigfile );
		String log4jlogfilePifx = "login";
		System.setProperty(CommonConstant.LOG4J_CONFIG_PROFIX, log4jlogfilePifx);
		PropertyConfigurator.configure(log4jconfigfile); 
		
		//System.setProperty("MONGO.POOLSIZE", "200"); 


	}
	
	
	/**
	 * 
	 * 初始化上下文
	 * 
	 * @param appDir  系统配置文件dir
	 * @param springClassPath  spring配置文件的类路径 示例： "classpath:resource/spring/*"
	 * 
	 * 
	 */
	public static void initApplicationContextChangeOS(String appDir , String springClassPath){
		
		String config_name = CommonConstant.getConfig();
		String configFile = appDir+"/config/"+config_name;

		System.out.println("配置文件路径为-> " + configFile );
		CommonConstant.iniCommonConstant(configFile);
		
		CommonConstant.ApplicationContext.put("app_dir", appDir);
		CommonConstant.ApplicationContext.put("springclass", springClassPath);
		
		//配置log4j
		String log4jLogDir = CommonConstant.getLog4jDir();
		
		if( null!=log4jLogDir && log4jLogDir.length()>0  ){
			//如果文件和目录不存在，则处理之
			File fileLog4jDir = new File(log4jLogDir);
			if (!fileLog4jDir.exists()) {
				fileLog4jDir.mkdir();
	        }
			
		}
		
		System.setProperty(CommonConstant.LOG4J_CONFIG_FLAG, log4jLogDir);
		String log4jconfigfile = appDir+"/config/log4j.properties";
		System.out.println("log4j 配置文件路 -> " + log4jconfigfile );
		String log4jlogfilePifx = "logs";
		System.setProperty(CommonConstant.LOG4J_CONFIG_PROFIX, log4jlogfilePifx);
		PropertyConfigurator.configure(log4jconfigfile); 
		
		//System.setProperty("MONGO.POOLSIZE", "200"); 


	}
	
	/**
	 * 
	 * 启动网络配置文件并且自动定时刷新
	 *
	 * 
	 */
	public static void initApplication4HttpAuthReflush(String appDir , String url){
		
		//首先初始化
		try {
			initApplicationContext4Http(appDir , url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//启动定时器，定时初始化相关的配置信息
		/*SchedulerCenter.init();
		SchedulerService scheduler = SchedulerCenter.getScheduler();
		
		JobInfo jobInfo = new JobInfo();
		jobInfo.setJobCommand("定时刷新配置文件组");
		jobInfo.setJobName("定时刷新配置文件");
		
		scheduler.addSchdule(jobInfo, "【自动通过集中服务器更新配置文件】", "自动通过集中服务器更新配置文件", 30, 30,ApplicationFlushJob.class);
		*/
	}
	
	
	public static void main(String[] args){
		
		initApplication4HttpAuthReflush("E:/wingfeng/project/nearmobile/common_statistics_page", "http://localhost/loveconfig/index.php/cfg/getcfg?id=5");
		
	}
	
	
}
