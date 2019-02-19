/**
 * 
 */
package com.xuehuiit.jee.common.tools.codegen;

// import JDK API
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;


import com.xuehuiit.jee.common.tools.codegen.exception.JeeVelocityException;


/**
 * @author wing.feng
 * 
 */
public class VelocityCenter {

	public static String CATEGORY_NAME = "CodeGenerator";

	public static String PAGE_TYPE_ENTRY = "ENTRY";

	public static String PAGE_TYPE_RESULT = "RESULT";

	public static String PAGE_TYPE_REMOVERESULT = "REMOVERESULT";

	public static String SEPARATOR = File.separator;

	public static String VELOCITY_TEMPLATE_PATH = System.getProperty("user.dir");
	
	private static String APP_WORK_DIR = System.getProperty("user.dir");
			
	private static Logger logger = Logger.getLogger(VelocityCenter.class);
	
	//public static VelocityEngine ve;

	public static VelocityEngine getVelocityEngine(String templentFilePath) throws Exception {

		//VELOCITY_TEMPLATE_PATH = System.getProperty("user.dir");
		/*if ( null != ve)
			return ve;*/
		VelocityEngine ve1;
		// set velocity logger config
		BasicConfigurator.configure();
		Category.getInstance(CATEGORY_NAME);

		ve1 = new VelocityEngine();

		ve1.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
				"org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
		ve1.setProperty("runtime.log.logsystem.log4j.category", CATEGORY_NAME);

		// set file loader config
		ve1.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
		ve1.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH,
				templentFilePath);

		// set class loader config
		/*
		 * ve.addProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		 * ve.setProperty("classpath." + RuntimeConstants.RESOURCE_LOADER +
		 * ".class",
		 * "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		 * ve.setProperty("classpath." + RuntimeConstants.RESOURCE_LOADER +
		 * ".cache", "false"); ve.setProperty("classpath." +
		 * RuntimeConstants.RESOURCE_LOADER + ".modificationCheckInterval",
		 * "2");
		 */
		// set incodeing ,just use GBK
		ve1.setProperty(RuntimeConstants.INPUT_ENCODING, "utf-8");
		ve1.setProperty(RuntimeConstants.OUTPUT_ENCODING, "utf-8");
		ve1.init();

		return ve1;
	}

	
	/**
	 * output velocity megerfile to a string
	 * 
	 * @param ve
	 * @param templateFilePath
	 * @param vc
	 */
	public static String megerFile(VelocityEngine ve, String templateFilePath, VelocityContext vc) {

		BufferedWriter writer = null;
		FileOutputStream ops = null;
		Template template = null;
		java.io.StringWriter write = new java.io.StringWriter();
		try {
			if (null == ve){
			 //ve = getVelocityEngine(VELOCITY_TEMPLATE_PATH);
				VelocityPathConfig vpcc=  VelocityPathConfig.getInstance();
				ve = getVelocityEngine(vpcc.getVelocityTemplatePath());
			}
			//ops = new FileOutputStream(outputFilePth);
			writer = new BufferedWriter(write);
			template = ve.getTemplate(templateFilePath);

			template.merge(vc,writer);
	        writer.flush();
	        writer.close();
	        
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return  write.toString();

	}
	
	/**
	 * output velocity megerfile to a string
	 * 

	 * @param templateFilePath

	 * @param vc
	 */
	public static String megerFile( String templateFilePath, VelocityContext vc) {

		VelocityEngine ve = null;
		return megerFile( ve, templateFilePath,  vc);

	}
	
	
	/**
	 * 
	 * @param ve
	 * @param templateFilePath
	 * @param outputFilePth
	 * @param vc
	 */
	public static void megerFile(VelocityEngine ve, String templateFilePath,
			String outputFilePth, VelocityContext vc) {

		Writer writer = null;
		FileOutputStream ops = null;
		Template template = null;

		try {
			if (null == ve)
				ve = getVelocityEngine(VELOCITY_TEMPLATE_PATH);

			ops = new FileOutputStream(outputFilePth);
			writer = new BufferedWriter(new OutputStreamWriter(ops));
			template = ve.getTemplate(templateFilePath);

			template.merge(vc,writer);
	        writer.flush();
	        writer.close();
	        
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 
	 * @param templateFilePath
	 * @param outputFilePth
	 * @param vc
	 */
	public static void megerFile(String templateFilePath, String outputFilePth,
			VelocityContext vc) {

		megerFile(null, templateFilePath, outputFilePth, vc);
	}

	
	public static void main(String[] args){
		
		//genStrutsFromBean4Model();
		//testPage();
		 /*  
		
		   
       */	
		VelocityContext context = new VelocityContext();
		context.put("project","jonathan's velocity");
		context.put("name","first example");
		System.out.println(getContest4StringTpl("We are using string velocity to generate $project $name",context));
	}
	

	

	
	/**
	 * 根据字符串模板得到相应的值
	 * @param stringTpl
	 * @return
	 */
	public static String getContest4StringTpl(String stringTpl,VelocityContext context){
		
		Map<String , String > map = getDefaultConfig(null);
		return getContest4StringTpl(stringTpl, context, map);
	}
	
	
	/**
	 * 根据字符串模板得到相应的值
	 * @param stringTpl
	 * @return
	 */
	public static String getContest4StringTplNoInit(String stringTpl,VelocityContext context){
		
		try {
			Velocity.init();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringWriter w = new StringWriter();
	   
		try {
			
			Velocity.evaluate(context,w,"mystring",stringTpl);
			
		} catch (ParseErrorException e) {
			logger.error("",e);
			throw new JeeVelocityException(e);
		} catch (MethodInvocationException e) {
			logger.error("",e);
			throw new JeeVelocityException(e);
		} catch (ResourceNotFoundException e) {
			logger.error("",e);
			throw new JeeVelocityException(e);
		} catch (IOException e) {
			logger.error("",e);
			throw new JeeVelocityException(e);
		}
			 
		return w.toString();
	}
	
	

    
	/**
	 * 初始化Velocity，用默认属性
	 * @throws Exception
	 */
	public static void initVelocityDefault( String templentFilePath  ) throws Exception{
		

		Map<String , String > map = getDefaultConfig(templentFilePath);
		initVelocity(map);
		
	}
 
	
	
	/**
	 * 初始化Velocity
	 * @param property
	 * @throws Exception
	 */
	public static void initVelocity(  Map<String, String> property  ) throws Exception{
		

	 	   if( null != property  ){
	 		   
	 		   String value;
	 		   for (String key  : property.keySet()){     
	 		          value = property.get( key );
	 		          Velocity.setProperty( key , value );
	 		    }   
	 	   }
	 	   
			
		   Velocity.init();
		
	}
	
	
	/**
	 * @return
	 */
	public static Map<String , String > getDefaultConfig(String templentFilePath){
		
		Map<String, String> map = new HashMap<String, String>();
		
		if( null!=templentFilePath && templentFilePath.length() > 0 )
		    map.put(RuntimeConstants.FILE_RESOURCE_LOADER_PATH,	templentFilePath);
		map.put(RuntimeConstants.INPUT_ENCODING, "utf-8");
		map.put(RuntimeConstants.OUTPUT_ENCODING, "utf-8");
		
		/**
		 * 
		 *  file.resource.loader.cache = true   
			file.resource.loader.modificationCheckInterval = 0 #不检查   
			resource.manager.defaultcache.size=0 #无限制   
			velocimacro.library.autoreload=false   
			runtime.introspector.uberspect = xxxx.velocityx.CustomUberspectImpl  

		 * 
		 * 
		 */
		
		
		return map;
	}
	
	
	/**
	 * 根据字符串模板得到相应的值
	 * @param stringTpl
	 * @return
	 */
	public static String getContest4StringTpl(String stringTpl , VelocityContext context , String templentFilePath ){
		Map<String , String > map = getDefaultConfig(templentFilePath);
		return getContest4StringTpl(stringTpl, context, map);
	}
	
	
	/**
	 * 根据字符串模板得到相应的值
	 * @param stringTpl
	 * @return
	 */
	public static String getContest4StringTpl(String stringTpl , VelocityContext context , Map<String, String> property ){
		
		StringWriter w = new StringWriter();
		
		
	    try{
		
	    	   if( null != property  ){
	    		   
	    		   String value;
	    		   for (String key  : property.keySet()){     
	    		          value = property.get( key );
	    		          Velocity.setProperty( key , value );
	    		    }   
	    	   }
	    	   
	    	   Velocity.init();
			   Velocity.evaluate(context,w,"mystring",stringTpl);
			   
		  }catch (ParseErrorException e) {
				logger.error("",e);
				throw new JeeVelocityException(e);
			} catch (MethodInvocationException e) {
				logger.error("",e);
				throw new JeeVelocityException(e);
			} catch (ResourceNotFoundException e) {
				logger.error("",e);
				throw new JeeVelocityException(e);
			} catch (IOException e) {
				logger.error("",e);
				throw new JeeVelocityException(e);
			} catch (Exception ex){
			   logger.error("",ex);
			   throw new JeeVelocityException(ex);
		   }
			 
		return w.toString();
	}
	
	
	/**
	 * Gen the SQL delete 
	 *
	 */
	public static void genDeleteDDL(){
		
		
	}
	
	
	public static void testPage(){
		

		
		
	}

	
}
