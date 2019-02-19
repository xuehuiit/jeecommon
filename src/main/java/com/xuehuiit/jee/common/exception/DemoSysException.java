/*******************************************************************
 * The demo project uncheck exception,When take place this type exception the
 * programmer stop and have no use for it. 
 * Author Wing.Feng Created on
 * 2004-11-20 
 *******************************************************************
 */
package com.xuehuiit.jee.common.exception;

//import jdk API

public class DemoSysException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6738235894903119842L;

	public DemoSysException() {
		super();
	}

	public DemoSysException(String message) {
		super(message);
	}

	public DemoSysException(Throwable t) {
		super(t);
	}

	public DemoSysException(String message, Throwable t) {
		super(message, t);
	}
}