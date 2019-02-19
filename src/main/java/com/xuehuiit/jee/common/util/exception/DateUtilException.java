/*
 * Created on 2004-12-19
 *
 */
package com.xuehuiit.jee.common.util.exception;

import com.xuehuiit.jee.common.exception.DemoSysException;

/**
 * @author Wing.Feng
 *
 */
public class DateUtilException extends DemoSysException {
    public DateUtilException() {
		super();
	}

	public DateUtilException(String message) {
		super(message);
	}

	public DateUtilException(Throwable t) {
		super(t);
	}

	public DateUtilException(String message, Throwable t) {
		super(message, t);
	}
}
