package com.xuehuiit.jee.common.tools.codegen;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xuehuiit.jee.common.exception.ConfigInitException;



public class VelocityPathConfig {
	
	protected static Log log = LogFactory.getLog(VelocityPathConfig.class);
	
	public static final String RUNTIME_PROPERTIES = "/properties_ckcms/velocitypath.properties";
	
	private static Properties appContext = new Properties();
	
	public static final String CURRENT_OPERATOR_SYSTEM = System.getProperty("os.name");
	public static final String WINGDOWS_OPERATOR_SYSTEM = "windows";
	
	private static VelocityPathConfig instance;
	
	/** Velocity配置文件的路径  **/
	public static final String VELOCITY_CONFIG_WINPATH = "velocity.config.winpath";
	public static final String VELOCITY_CONFIG_UNIXPATH = "velocity.config.unixpath";
	
	/** Velocity模板文件的路径  **/
	public static final String VELOCITY_TEMPLATE_WINPATH = "velocity.template.winpath";
	public static final String VELOCITY_TEMPLATE_UNIXPATH = "velocity.template.unixpath";
	
	public synchronized static VelocityPathConfig getInstance() {
		if(instance == null){
			instance = new VelocityPathConfig();
		}
		return instance;
	}
	
	private VelocityPathConfig() {
		try{
			loadProperties(getInutStrem(RUNTIME_PROPERTIES));
		}catch(ConfigInitException configEx){
			log.error(configEx, configEx);
			log.error("Velocity的配置文件初始化失败!请查看文件是否存在!");
		}
	}
	
	private static InputStream getInutStrem(String filePath) throws ConfigInitException {
		try{
			InputStream inputstream = VelocityPathConfig.class.getResourceAsStream(filePath);
			return inputstream;
		}catch(Exception e)
	    {
		      log.error(e, e);
		      ConfigInitException configEx 
		      		= new ConfigInitException(ConfigInitException.SYSTEM_ERRORCODE,e);
		      throw configEx;
		}
	}
	
	private static void loadProperties(InputStream inputStream) throws ConfigInitException
    {
        appContext = new Properties();
        if(inputStream!=null){
        	try{
        		appContext.load(inputStream);
        	}catch(IOException ioEx){
        		log.error(ioEx, ioEx);
        		ConfigInitException configEx 
    	  			= new ConfigInitException(ConfigInitException.SYSTEM_ERRORCODE,ioEx);
        		throw configEx;
        	}
        }else{
        	ConfigInitException configEx 
  				= new ConfigInitException("config file happen error!");
        	throw configEx;
        }
    }
	
	public String getVelocityConfigPath() {
		if(CURRENT_OPERATOR_SYSTEM.toLowerCase().indexOf(WINGDOWS_OPERATOR_SYSTEM) >= 0){
			return (String)appContext.getProperty(VELOCITY_CONFIG_WINPATH);
		}
		return (String)appContext.getProperty(VELOCITY_CONFIG_UNIXPATH);
	}
	
	public String getVelocityTemplatePath() {
		if(CURRENT_OPERATOR_SYSTEM.toLowerCase().indexOf(WINGDOWS_OPERATOR_SYSTEM) >= 0){
			return (String)appContext.getProperty(VELOCITY_TEMPLATE_WINPATH);
		}
		return (String)appContext.getProperty(VELOCITY_TEMPLATE_UNIXPATH);
	}
	
}
