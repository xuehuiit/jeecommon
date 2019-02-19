/**
 * 
 */
package com.xuehuiit.jee.common.util;

import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Element;

import com.xuehuiit.jee.common.constant.CommonConstant;

/**
 * 应用程序中启动时候的帮助类
 * @author </br> <a href="mailto:fx19800215@163.com"> wing.feng</a>
 *
 */
public class ApplicationUtil {

	
	 /**
     * init the application config file
     * @param appConfigFile 应用程序启动时的配置文件
     */
    public static  void initConfigMap(String appConfigFile){
    	
    	Element rootelement = null;
    	Element element;
    	String name;
    	String value;
    	java.util.Iterator iter = null;
    	
    	try{
    	rootelement = ResourcesBoundUtil.paserXml(appConfigFile, CommonConstant.APPMODULENAME);
    	iter = rootelement.elementIterator();
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	//java.util.Map map = new java.util.HashMap();
    	
    	while( iter.hasNext() )
    	{
    		element = (Element)iter.next();
    		name = element.attributeValue(CommonConstant.CONFIG_FILE_NAME);
    		value = element.attributeValue(CommonConstant.CONFIG_FILE_VALUE);
    		CommonConstant.ApplicationContext.put(name, value);
    	}
    	
    	//CommonConstant.setAppConfigValueMap(map);
    	CommonConstant.setSmtp((String)CommonConstant.ApplicationContext.get(CommonConstant.SMTP));
    	CommonConstant.setSmtpHostName((String)CommonConstant.ApplicationContext.get(CommonConstant.HOST_MAIL_NAME_PROERTY));
    	CommonConstant.setSmtpHostPasswd((String)CommonConstant.ApplicationContext.get(CommonConstant.HOST_MAIL_PASSWORD_PROPERTY));
    	CommonConstant.setMailVerder((String)CommonConstant.ApplicationContext.get(CommonConstant.MAIL_VENDER));
    	
    }
    
    
    
    public static void initLog4j(String configfile){
    	
		//System.out.println("*********** log4j config file is " + log4jconfigfile);
		PropertyConfigurator.configure(configfile); 
    	
    }
	
	
}
