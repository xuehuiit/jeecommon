/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.db;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public abstract class BaseDbBean {
	
	
	/**
	 * 
	 * 获取插入sql
	 * 
	 * @return
	 * 
	 */
	abstract public String getInsertsql();
	
	
	/**
	 * 
	 * 获取插入parms
	 * @return
	 * 
	 */
	abstract public Object[] getInsertParms();
	
	
	/**
	 * 
	 * 获取数据库表名字
	 * 
	 * @return
	 */
	abstract public String getTableName();
	
	
	
	

}
