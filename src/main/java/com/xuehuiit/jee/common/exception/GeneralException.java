package com.xuehuiit.jee.common.exception;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;

/**
 * Base OFBiz Exception, provides nested exceptions, etc
 * 
 * @author <a href="mailto:jaz@ofbiz.org">Andy Zeneski </a>
 * @version $Revision: 1.2 $
 * @since 1.0
 */
public class GeneralException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3418220143197991742L;

	public static final String ERROR_LEVEL_USER = "user";

	public static final String ERROR_LEVEL_SYSTEM = "system";

	Throwable nested = null;

	private String code = null;

	private String errorLevel;

	private String errorTip;

	private String processHelp;

	public GeneralException(String code, String msg, Throwable exce) {
		this(msg, exce);
		this.code = code;
	}

	public GeneralException(String code, String msg) {
		this(msg);
		this.code = code;
	}

	public String getUserErrorTip() {
		if (StringUtils.isNotBlank(errorTip)) {
			return errorTip;
		} else {
			return super.getMessage();
		}
	}

	/**
	 * Creates new <code>GeneralException</code> without detail message.
	 */
	public GeneralException() {
		super();
	}

	/**
	 * Constructs an <code>GeneralException</code> with the specified detail
	 * message.
	 * 
	 * @param msg
	 *            the detail message.
	 */
	public GeneralException(String msg) {
		super(msg);
	}

	/**
	 * Constructs an <code>GeneralException</code> with the specified detail
	 * message and nested Exception.
	 * 
	 * @param msg
	 *            the detail message.
	 */
	public GeneralException(String msg, Throwable nested) {
		super(msg);
		this.nested = nested;
	}

	/**
	 * Constructs an <code>GeneralException</code> with the specified detail
	 * message and nested Exception.
	 * 
	 * @param nested
	 *            the detail message.
	 */
	public GeneralException(Throwable nested) {
		super();
		this.nested = nested;
	}

	public boolean isSystemLevel() {
		return ERROR_LEVEL_SYSTEM.equalsIgnoreCase(errorLevel);
	}

	public boolean isCustomerLevel() {
		return ERROR_LEVEL_USER.equalsIgnoreCase(errorLevel);

	}

	/**
	 * Returns the detail message, including the message from the nested
	 * exception if there is one.
	 */
	public String getMessage() {
		if (nested != null)
			return super.getMessage() + " (" + nested.getMessage() + ")";
		else
			return super.getMessage();
	}

	/**
	 * Returns the detail message, NOT including the message from the nested
	 * exception.
	 */
	public String getNonNestedMessage() {
		return super.getMessage();
	}

	/** Returns the nested exception if there is one, null if there is not. */
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

	public String getErrorLevel() {
		return errorLevel;
	}

	public void setErrorLevel(String errorLevel) {
		this.errorLevel = errorLevel;
	}

	public String getErrorTip() {
		return errorTip;
	}

	public void setErrorTip(String errorTip) {
		this.errorTip = errorTip;
	}

	public String getProcessHelp() {
		return processHelp;
	}

	public void setProcessHelp(String processHelp) {
		this.processHelp = processHelp;
	}
}
