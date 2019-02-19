/**
 * 
 */
package com.xuehuiit.jee.common.constant;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Element;

import com.xuehuiit.jee.common.io.filemonitor.FileInfo;
import com.xuehuiit.jee.common.util.ResourcesBoundUtil;


/**
 * @author </br> <a href="mailto:fx19800215@163.com"> wing.feng</a>
 * 
 */
public class CommonConstant {


	public static String APPLICATION_CONFIG;
	
	public static final String DOIMSESSIONID = "58266shopnameid";
	
	public static final String APP_WORK_DIR = System.getProperty("user.dir");
	
	public static final String APPCONFIGFILE_SYSTEM_KEY = "appconfig";
	
	/** 以xml数据格式作为reponse内容的标记 */
	public static final String XMLREPONSEDATA = "xml";
	/** 以xml数据格式作为reponse内容的标记 */
	public static final String JSONREPONSEDATA = "json";
	/** */
	public static final String APPMODULENAME = "appconfig";
	/** */
	public static final String CONFIG_FILE_NAME = "name";
	/** */
	public static final String CONFIG_FILE_VALUE = "value";
	/** */
	public static final String HOST_MAIL_NAME_PROERTY = "host_mail_name";

	/** */
	public static final String HOST_MAIL_PASSWORD_PROPERTY = "host_mail_password";

	/** */
	public static final String SMTP_PROPERTY = "smtp";
	/** */
	public static final String WIN_TEMP_DIR = "wintempdir";

	/** */
	public static final String UNIX_TEMP_DIR = "unixempdir";
	/** */
	public static final String WIN_AD_DIR = "winaddir";
	/** */
	public static final String UNIX_AD_DIR = "unixaddir";
	/** */
	public static final String WIN_AD_PUT_DIR = "winadputdir";
	/** */
	public static final String UNIX_AD_PUT_DIR = "unixadputdir";
	/** */
	public static final String WINGDOWS_OPERATOR_SYSTEM = "windows";	
	/** */
	public static final String LOG4J_LOGDIR_WIN  = "log4jlogdirwin"; 	
	/** */
	public static final String LOG4J_LOGDIR_LINUX  = "log4jlogdirunix";
	/** */
	public static final String LOG4J_CONFIG_FLAG = "user.log4jdir";
	public static final String LOG4J_CONFIG_HTML_FLAG = "user.log4jdirhtml";
	/** log4j配置文件的前缀 */
	public static final String LOG4J_CONFIG_PROFIX = "usr.log4jprofix";
	/** */
	public static final String MAIL_VENDER = "mail_vender";
	/** */
	public static final String AD_PLAN_WINDIR = "windowadplandir";
	/** */
	public static final String AD_PLAN_UNIXDIR = "unixadplandir";
	/** */
	public static final String AGENT_WEB_PAGEUNIX = "unixagentWebFile";
	/** */
	public static final String AGENT_WEB_PAGEWIN = "winagentWebFile";
	/** */
	public static final String SOCKT_PORT = "socktport";
	
	/** */
	public static final String AGENT_CODE_WIN = "wincodedir";
	/** */
	public static final String AGENT_CODE_UNIX = "unixcodedir";
	
	public static final String AGENT_CODE_URL = "unixserverurl";
	public static final String AGENT_WIND_URL = "winserverurl";
	
	public static final String WEBBUG_WIN_DIRECTORY = "winwebbugdir";
	public static final String WEBBUG_UNIX_DIRECTORY = "unixwebbugdir";
	public static final String WEB_PAGECHECK_PATH = "pagecheckpath";
	public static final String WEB_PAGECHECK_UPATH = "webpagepath";
	
	/** 复杂报表的模板的路径 */
	public static final String REPORT_TEMPLATE_WIN_DIRECTORY = "winreporttemplatedir";
	public static final String REPORT_TEMPLATE_UNIX_DIRECTORY = "unixreporttemplatedir";
	/** 机器服务器会话标识配置参数 */
	public static final String DOIMF_SINGLE_KEY = "doimsessionid";
	/** */
	public static final String CURRENT_OPERATOR_SYSTEM = System.getProperty("os.name");

	/** xuchao create it on ? */
	public static final String CHARGE_TIME = "chargedays";
	
	public static final String DEFAULT_ORDER_PROID = "defaultProductid";
	
	public static final String DEFAULT_ORDER_AGENTID = "defaultAgentid";
	
	public static final String DEFAULT_ORDER_DOMID = "defaultDoimfid";
	
	public static final String NETSHOP_INDEX = "indexurl";
	
	public static final String ORDER_PICTURL = "picturl";
	
	public static String CURRENT_VISIT_IP;
	
	/** */
	public static String applicationConfigRoot;

	/** */
	public static Map ApplicationContext = new HashMap();

	/***/
	public static String applicationRoot;

	public static String WEB_INFO_DIR;
	
	/***/
	public static final String CHECK_CODE = "rand";
	
	/** system mail host config */
	public static String SMTP;
	/** */
	public static String SMTP_HOST_NAME;
	/** */
	public static String SMTP_HOST_PASSWORD;
	/** */
	public static String MAIL_VERDER_VALUE;
	
	
	/** cmpp */
	public final String BOSS_PROPERTIES = "/properties_boss/bossservice.properties";
	
	public static final String BOSS_HOST = "boss_host";
	
	public static final String BOSS_PORT = "boss_port";
	
	public static final String BOSS_STEP = ",";
	
	public static final String BOSS_TASK_DAILY = "tasks.daily";
	
	public static final String CMPP_URL = "cmpp_url";
	
	public static final String CMPP_PORT = "cmpp_port";
	
	public static final String CMPP_USER = "cmpp_user";
	
	public static final String CMPP_PASS = "cmpp_pass";
	
	public static final String CMPP_PHONE = "cmpp_phone";
	
	public static final String CMPP_VERSION = "cmpp_version";
	
	public static final String CMPP_TYPE = "cmpp_type";
	
	public static final String WORD_NUMBER = "word_number";
	

	
	public static void setMailVerder(String mailVender){
		MAIL_VERDER_VALUE = mailVender;
	}
	
	/**
	 * 
	 * @param appRoot
	 */
	public static void setApplicationRoot(String appRoot) {
		applicationConfigRoot = appRoot;
	}

	/**
	 * 
	 * @param map
	 *//*
/*	public static void setAppConfigValueMap(Map map) {

		ApplicationContext = map;

	}*/

	/**
	 * 
	 * @param smtp
	 */
	public static void setSmtp(String smtp) {
		SMTP = smtp;
	}

	/**
	 * 
	 * @param smtpHostName
	 */
	public static void setSmtpHostName(String smtpHostName) {
		SMTP_HOST_NAME = smtpHostName;
	}

	/**
	 * 
	 * @param smtpHostPasswd
	 */
	public static void setSmtpHostPasswd(String smtpHostPasswd) {
		SMTP_HOST_PASSWORD = smtpHostPasswd;
	}

	/**
	 * 广告存放路径
	 */
	public static String getAdDir() {

		/*
		 * if (CURRENT_OPERATOR_SYSTEM.indexOf(WINGDOWS_OPERATOR_SYSTEM)>=0)
		 * return (String)ApplicationContext.get(WIN_AD_DIR); return
		 * (String)ApplicationContext.get(UNIX_AD_DIR);
		 */

		return applicationRoot 	+ (String) ApplicationContext.get(WIN_AD_DIR);
	}

	/**
	 * 广告投放计划存放路径
	 */
	public static String getAdputPlanDir() {

		/*if (CURRENT_OPERATOR_SYSTEM.indexOf(WINGDOWS_OPERATOR_SYSTEM) >= 0)
			return (String) ApplicationContext.get(WIN_AD_PUT_DIR);

		return (String) ApplicationContext.get(UNIX_AD_PUT_DIR);*/
		
		return applicationRoot + (String) ApplicationContext.get(WIN_AD_PUT_DIR);
	}

	
	/**
	 * get the log4j log output dir
	 * @return
	 */
		
	public static String getLog4jDir(){
		 if (CURRENT_OPERATOR_SYSTEM.toLowerCase().indexOf(WINGDOWS_OPERATOR_SYSTEM)>=0)
			 return (String)ApplicationContext.get(LOG4J_LOGDIR_WIN);
		 return (String)ApplicationContext.get(LOG4J_LOGDIR_LINUX);
	}
	
	/**
	 * get the log4j log output dir
	 * @return
	 */
	public static String getConfig(){
		
		 if (CURRENT_OPERATOR_SYSTEM.toLowerCase().indexOf(WINGDOWS_OPERATOR_SYSTEM)>=0)
			 return "appContext_win.xml";
		 return "appContext.xml";
	}
	
	
	/**
	 * get the log4j log output dir
	 * @return
	 */
	public static String getLog4jHtmlDir(){
		
		 if (CURRENT_OPERATOR_SYSTEM.toLowerCase().indexOf(WINGDOWS_OPERATOR_SYSTEM)>=0)
			 return (String)ApplicationContext.get("log4jhtmllogdirwin");
		 return (String)ApplicationContext.get("log4jhtmllogdirunix");
	}
	
	
	
	public static String getWebBugDirectory() {
		if (CURRENT_OPERATOR_SYSTEM.toLowerCase().indexOf(WINGDOWS_OPERATOR_SYSTEM)>=0)
			 return (String)ApplicationContext.get(WEBBUG_WIN_DIRECTORY);
		 return (String)ApplicationContext.get(WEBBUG_UNIX_DIRECTORY);
	}
	
	public static String getReportTempalteDirectory() {
		if (CURRENT_OPERATOR_SYSTEM.toLowerCase().indexOf(WINGDOWS_OPERATOR_SYSTEM)>=0)
			return (String)ApplicationContext.get(REPORT_TEMPLATE_WIN_DIRECTORY);
		return (String)ApplicationContext.get(REPORT_TEMPLATE_UNIX_DIRECTORY);
	}
	
	/**
	 * 得到用于测试的spring配置文件的存放目录
	 * @return
	 */
	public static String getSpringConfig4UnitTest() {
		if (CURRENT_OPERATOR_SYSTEM.toLowerCase().indexOf(WINGDOWS_OPERATOR_SYSTEM)>=0)
			return (String)ApplicationContext.get("springtestconfigdif_win");
		return (String)ApplicationContext.get("springtestconfigdif");
	}
	
	/**
	 * 投诉的倒记时小时数
	 */
	public static String getChargeDays() {
		return (String) ApplicationContext.get(CHARGE_TIME);
	}
	
	public static String getDefaultProductid() {
		return (String)ApplicationContext.get(DEFAULT_ORDER_PROID);
	}
	
	public static String getDefaultAgentid() {
		return (String)ApplicationContext.get(DEFAULT_ORDER_AGENTID);
	}
	
	public static String getDefaultDoimfid() {
		return (String)ApplicationContext.get(DEFAULT_ORDER_DOMID);
	}
	
	public static String getDefaultValueByKey(String key) {
		String value = (String)ApplicationContext.get(key);
		return value;
	}
	
	public static String getIndexUrl() {
		return (String)ApplicationContext.get(NETSHOP_INDEX);
	}
	
	public static String getPictUrl() {
		return (String)ApplicationContext.get(ORDER_PICTURL);
	}
	
	public static String getPagecheckpath() {
		return (String)ApplicationContext.get(WEB_PAGECHECK_PATH);
	}
	
	public static String getWebPagePath() {
		return (String)ApplicationContext.get(WEB_PAGECHECK_UPATH);
	}
	
	/**
	 * 集群服务器当前会话的唯一标识
	 * @return
	 */
	public static String getDoimSingleKey() {
		return (String)ApplicationContext.get(DOIMF_SINGLE_KEY);
	}
	
	
	/**
	 * 得到session变量保存的方式，该方式决定session变量存放的位置，
	 * @return
	 */
	public static String getSessionSaveType(){
		return (String)CommonConstant.ApplicationContext.get(SESSION_TYPE_PROPERTY);
	}
	
	public static final String SESSION_TYPE_PROPERTY = "sessionsavetype";
	/** session 保存在单机 */
	public static final String SESSION_TYPE_PROPERTY_S = "s";
	/** session 保存在集中式缓存中 */
	public static final String SESSION_TYPE_PROPERTY_C = "c";
	
	/** Wingfeng create it on 2007-12-20  */
	public static final String JSONREPONSEFLAG_FAIL = "0";
	public static final String JSONREPONSEFLAG_TRUE = "1";
	public static final String JSONREPONSEFLAG_SESSIONTIMEOUT = "2";
	public static final String JSONREPONSEFLAG_NOTPREMITION = "3";
	
	
	/**
	 * 初始化应用程序的上下文空间
	 * @param appConfigFile
	 */
	public static void iniCommonConstant(String appConfigFile){
		
		Element rootelement;
		Element element;
		String name;
		String value;

		rootelement = ResourcesBoundUtil.paserXml(appConfigFile,
				CommonConstant.APPMODULENAME);
		java.util.Iterator iter = rootelement.elementIterator();
		//java.util.Map map = new java.util.HashMap();

		while (iter.hasNext()) {
			element = (Element) iter.next();
			name = element.attributeValue(CommonConstant.CONFIG_FILE_NAME);
			value = element.attributeValue(CommonConstant.CONFIG_FILE_VALUE);			
			ApplicationContext.put(name, value);
		}

		//CommonConstant.setAppConfigValueMap(map);
		CommonConstant.setSmtp((String) ApplicationContext.get(CommonConstant.SMTP));
		CommonConstant.setSmtpHostName((String) ApplicationContext.get(CommonConstant.HOST_MAIL_NAME_PROERTY));
		CommonConstant.setSmtpHostPasswd((String) ApplicationContext.get(CommonConstant.HOST_MAIL_PASSWORD_PROPERTY));
		CommonConstant.setMailVerder((String) ApplicationContext.get(CommonConstant.MAIL_VENDER));
	
		CommonConstant.APPLICATION_CONFIG =  CommonConstant.WEB_INFO_DIR + File.separatorChar + "config"+File.separatorChar+"appContext.xml";
		
	}
	
	
	/**
	 * 初始化应用程序的上下文空间
	 * @param appConfigFile
	 */
	public static void iniCommonConstant4String(String appConfigFile){
		
		Element rootelement;
		Element element;
		String name;
		String value;

		rootelement = ResourcesBoundUtil.paserXml4String(appConfigFile,
				CommonConstant.APPMODULENAME);
		java.util.Iterator iter = rootelement.elementIterator();
		//java.util.Map map = new java.util.HashMap();

		while (iter.hasNext()) {
			element = (Element) iter.next();
			name = element.attributeValue(CommonConstant.CONFIG_FILE_NAME);
			value = element.attributeValue(CommonConstant.CONFIG_FILE_VALUE);			
			ApplicationContext.put(name, value);
		}

		//CommonConstant.setAppConfigValueMap(map);
		CommonConstant.setSmtp((String) ApplicationContext.get(CommonConstant.SMTP));
		CommonConstant.setSmtpHostName((String) ApplicationContext.get(CommonConstant.HOST_MAIL_NAME_PROERTY));
		CommonConstant.setSmtpHostPasswd((String) ApplicationContext.get(CommonConstant.HOST_MAIL_PASSWORD_PROPERTY));
		CommonConstant.setMailVerder((String) ApplicationContext.get(CommonConstant.MAIL_VENDER));
	
		CommonConstant.APPLICATION_CONFIG =  CommonConstant.WEB_INFO_DIR + File.separatorChar + "config"+File.separatorChar+"appContext.xml";
		
	}
	
	 public static void initLog4j(String configfile){
	    	
			//System.out.println("*********** log4j config file is " + log4jconfigfile);
			PropertyConfigurator.configure(configfile); 
	    	
	}
	 
	/**
	 * 
	 * @return
	 */ 
	public static int getCareateMaxNumber(){
		
		String createnums = (String)CommonConstant.ApplicationContext.get("maxcreatenumber");
		int createnum = Integer.valueOf(createnums).intValue();
		return  createnum;
	}
		
	
	
	/**
	 * 将值放入到Web的会话中
	 * @param key
	 * @param value
	 */
	public static void setValue2WebContext(String key , Object value){
		
		
	}
	
	/**
	 * 从web会话中取出相关的值
	 * @param key
	 * @return
	 */
	public static String getValue4WebContext(String key){
		
		return null;
	}
	
	
	

	
	
	public static boolean checkSession(){
		
		String sessiontype;
		
		if (CURRENT_OPERATOR_SYSTEM.toLowerCase().indexOf(WINGDOWS_OPERATOR_SYSTEM)>=0)
			sessiontype = (String)CommonConstant.ApplicationContext.get("sessionsavetype_windows");
		else
			sessiontype = (String)CommonConstant.ApplicationContext.get("sessionsavetype");
		
		if( null!=sessiontype && "s".equals(sessiontype) ){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	/**
	 * 得到用于测试的spring配置文件的存放目录
	 * @return
	 */
	public static String getMemcachedServer() {
		if (CURRENT_OPERATOR_SYSTEM.toLowerCase().indexOf(WINGDOWS_OPERATOR_SYSTEM)>=0)
			return (String)ApplicationContext.get("memcachedserver_win");
		return (String)ApplicationContext.get("memcachedserver");
	}
	
	

	
	public static void AutoMonitorConfig(){
		
		
	}
	
}
