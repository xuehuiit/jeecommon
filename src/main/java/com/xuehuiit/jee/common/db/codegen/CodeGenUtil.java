/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.db.codegen;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.xuehuiit.jee.common.tools.codegen.CodeGenConstant;
import com.xuehuiit.jee.common.tools.codegen.exception.CodegeneratorSysException;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class CodeGenUtil {

	
	
	public static Map< String, String> MYSQL_JAVA_TYPE = new HashMap<String, String>();
	
	public static final String JAVA_SIMPLE_TYPE_INTEGER = "java.lang.Integer";
	public static final String JAVA_SIMPLE_TYPE_STRING = "java.lang.String";
	public static final String JAVA_SIMPLE_TYPE_DOUBLE = "java.lang.Double";
	public static final String JAVA_SIMPLE_TYPE_LONG = "java.lang.Long";
	public static final String JAVA_SIMPLE_TYPE_FLOAT = "java.lang.Float";
	public static final String JAVA_SIMPLE_TYPE_DATE = "java.util.Date";
	
	static{
		
		MYSQL_JAVA_TYPE.put("int", "java.lang.Long");
		MYSQL_JAVA_TYPE.put("varchar", "java.lang.String");
		MYSQL_JAVA_TYPE.put("bigint", "java.lang.Long");
		MYSQL_JAVA_TYPE.put("datetime", "java.util.Date");
		MYSQL_JAVA_TYPE.put("float", "java.lang.Float");
		MYSQL_JAVA_TYPE.put("double", "java.lang.Double");
		MYSQL_JAVA_TYPE.put("date", "java.util.Date");
		
		MYSQL_JAVA_TYPE.put("text", "java.lang.String");
		MYSQL_JAVA_TYPE.put("longtext", "java.lang.String");
		
		
		
	}
	
	
	public String getJavaType(String mysql){
		return MYSQL_JAVA_TYPE.get(mysql);
	}
	
	
	/**
	 * Change the property to getProperty() method.
	 * 
	 * @param beanProperty
	 *            changed bean's property.
	 * @return The name of bean's property "get" methord.
	 */
	public String getProGetMethordName(String beanProperty) {
		if (StringUtils.contains(beanProperty.substring(0, 1),
				CodeGenConstant.UPPER))
			throw new CodegeneratorSysException(
					"The bean second characate of property's name is not Upper");
		return "get" + beanProperty.substring(0, 1).toUpperCase()
				+ beanProperty.substring(1, beanProperty.length());

	}

	/**
	 * Change the property to setProperty() methord
	 * 
	 * @param beanProperty
	 *            change bean's property.
	 * @return The name of bean's property "set" methord.
	 */
	public String getProSetMethordName(String beanProperty) {
		if (StringUtils.contains(beanProperty.substring(0, 1),
				CodeGenConstant.UPPER))
			throw new CodegeneratorSysException(
					"The bean second characate of property's name is Upper");

		return "set" + beanProperty.substring(0, 1).toUpperCase()
				+ beanProperty.substring(1, beanProperty.length());
	}


	
	public String changeStrFirstCharToUpper(String string) {
		String fristChar;
		fristChar = string.substring(0, 1);
		fristChar = fristChar.toUpperCase()
				+ string.substring(1, string.length());
		return fristChar;
	}
	
	
	/**
	 * 
	 * 获取类名
	 * 
	 * @param tableName
	 * @return
	 * 
	 */
	public String getClassName4Table(String tableName){
		
		
		StringBuffer buffer = new StringBuffer();
		
		String[] d = tableName.split("_");
		
		if( null != d && d.length>0 ){
			
			for (String string : d) {
				
				if( string.length()>=2  )
					buffer.append(changeStrFirstCharToUpper(string));
				else
					buffer.append(string);
			}
			
		
			return buffer.toString();
		}else
		    return tableName;
			
			
		

	}
	
	
	
}
