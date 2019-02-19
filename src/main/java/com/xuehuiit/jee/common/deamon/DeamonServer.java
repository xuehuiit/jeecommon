/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.deamon;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public abstract class DeamonServer {

	
	/**
	 * 
	 * 用户启动程序
	 * 
	 * @param appdir
	 * 
	 */
	abstract public void userStart(String appdir);
	
	
	abstract public void userStop();
	
	
	
	
}
