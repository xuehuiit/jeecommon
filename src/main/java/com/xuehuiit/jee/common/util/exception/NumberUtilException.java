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
public class NumberUtilException extends DemoSysException {
    public NumberUtilException() {
		super();
	}

	public NumberUtilException(String message) {
		super(message);
	}

	public NumberUtilException(Throwable t) {
		super(t);
	}

	public NumberUtilException(String message, Throwable t) {
		super(message, t);
	}
}
