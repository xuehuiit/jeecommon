package com.xuehuiit.jee.common.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class ConfigInitException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8436296375051887332L;

	public static final String SYSTEM_ERRORCODE = "system";
	
	Throwable nested = null;
	
	private String code = null;
	
	public ConfigInitException(String msg) {
		super(msg);
	}
	
	public ConfigInitException(String msg, Throwable nested) {
		super(msg);
		this.nested = nested;
	}
	
	public ConfigInitException(String code, String msg, Throwable exce) {
		this(msg, exce);
		this.code = code;
	}

	public ConfigInitException(String code, String msg) {
		this(msg);
		this.code = code;
	}
	
	public Throwable getNested() {
		if (nested == null)
			return this;
		return nested;
	}

	/** Prints the composite message to System.err. */
	public void printStackTrace() {
		super.printStackTrace();
		if (nested != null)
			nested.printStackTrace();
	}

	/**
	 * Prints the composite message and the embedded stack trace to the
	 * specified stream ps.
	 */
	public void printStackTrace(PrintStream ps) {
		super.printStackTrace(ps);
		if (nested != null)
			nested.printStackTrace(ps);
	}

	/**
	 * Prints the composite message and the embedded stack trace to the
	 * specified print writer pw.
	 */
	public void printStackTrace(PrintWriter pw) {
		super.printStackTrace(pw);
		if (nested != null)
			nested.printStackTrace(pw);
	}

	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            The code to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
}
