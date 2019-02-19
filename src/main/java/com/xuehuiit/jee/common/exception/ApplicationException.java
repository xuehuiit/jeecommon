/*******************************************************************************
 * This is the application Exception 
 * @author Wing.Feng Date 2004-11-20
 * **********************************************************************************
 */
//package
package com.xuehuiit.jee.common.exception;

//import java API
//import org.springframework.dao.DataAccessException;

public class ApplicationException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 778251760642908213L;

	/**
	 * @param message
	 */
	public ApplicationException(Throwable ex) {
		super("",ex);
	}
	
	/**
	 * @param message
	 */
	public ApplicationException(String message) {
		super(message);
	}
	/**
	 * @param message
	 * @param arg1
	 */
	public ApplicationException(String message, Throwable ex) {
		super(message, ex);
	}

	//jdk 1.4 support
	public ApplicationException(Throwable t, String message) {
		super(message, t);
	}
}