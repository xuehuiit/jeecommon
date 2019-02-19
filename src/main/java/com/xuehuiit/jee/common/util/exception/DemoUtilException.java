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
public class DemoUtilException extends DemoSysException {
    public DemoUtilException() {
		super();
	}

	public DemoUtilException(String message) {
		super(message);
	}

	public DemoUtilException(Throwable t) {
		super(t);
	}

	public DemoUtilException(String message, Throwable t) {
		super(message, t);
	}
}
