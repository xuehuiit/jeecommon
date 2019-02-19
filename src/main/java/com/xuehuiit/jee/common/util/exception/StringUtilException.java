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
public class StringUtilException extends DemoSysException {
    public StringUtilException() {
		super();
	}

	public StringUtilException(String message) {
		super(message);
	}

	public StringUtilException(Throwable t) {
		super(t);
	}

	public StringUtilException(String message, Throwable t) {
		super(message, t);
	}
}
