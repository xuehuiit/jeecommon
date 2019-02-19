/*
 * Created on 2004-12-4
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.xuehuiit.jee.common.tools.codegen.exception;

import com.xuehuiit.jee.common.exception.DemoSysException;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CodegeneratorSysException extends DemoSysException {

	public CodegeneratorSysException(String message)
	{
		super(message);
	}
	public CodegeneratorSysException(Throwable tr)
	{
		super(tr);
	}
	public CodegeneratorSysException(String message,Throwable tr)
	{
		super(message,tr);
	}
}
