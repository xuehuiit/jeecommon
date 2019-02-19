/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.db;

import com.xuehuiit.jee.common.exception.ApplicationException;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class DBException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4314330385695312712L;

	/**
	 * @param ex
	 */
	public DBException(Throwable ex) {
		super(ex);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public DBException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param ex
	 */
	public DBException(String message, Throwable ex) {
		super(message, ex);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param t
	 * @param message
	 */
	public DBException(Throwable t, String message) {
		super(t, message);
		// TODO Auto-generated constructor stub
	}

}
