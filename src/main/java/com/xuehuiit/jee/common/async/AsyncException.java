/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.async;

import com.xuehuiit.jee.common.exception.ApplicationException;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class AsyncException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4425096109620424061L;

	/**
	 * @param ex
	 */
	public AsyncException(Throwable ex) {
		super(ex);
		// TODO 自动生成构造函数存根
	}

	/**
	 * @param message
	 */
	public AsyncException(String message) {
		super(message);
		// TODO 自动生成构造函数存根
	}

	/**
	 * @param message
	 * @param ex
	 */
	public AsyncException(String message, Throwable ex) {
		super(message, ex);
		// TODO 自动生成构造函数存根
	}

	/**
	 * @param t
	 * @param message
	 */
	public AsyncException(Throwable t, String message) {
		super(t, message);
		// TODO 自动生成构造函数存根
	}

}
