/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.scheduler;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class ScheduleException extends RuntimeException {

	/**
	 * 
	 */
	public ScheduleException() {
		super();
	}

	/**
	 * @param message
	 */
	public ScheduleException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public ScheduleException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ScheduleException(String message, Throwable cause) {
		super(message, cause);
	}

}
