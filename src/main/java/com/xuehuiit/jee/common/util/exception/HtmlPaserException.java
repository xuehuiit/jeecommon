package com.xuehuiit.jee.common.util.exception;

import com.xuehuiit.jee.common.exception.GeneralException;

public class HtmlPaserException extends GeneralException{
	
	public static final String HTMLPARSER_CONNECT_ERROR = "htmlparser001";
	
	public static final String HTMLPARSER_PARSER_ERROR = "htmlparser002";
	
	public static final String HTMLPARSER_CONNECT_MSG = "cannot connect url bu httpclient!";
	
	public static final String HTMLPARSER_PARSER_MSG = "parser url content happen error!";
	
	public HtmlPaserException(String msg){
		super(msg);
	}
	
	public HtmlPaserException(String code,String msg) {
		super(code,msg);
	}
	
	public HtmlPaserException(String code, String msg, Throwable exce) {
		super(code, msg, exce);
	}
	
	public HtmlPaserException(String msg, Throwable nested) {
		super(msg,nested);
	}
	
	public HtmlPaserException(Throwable nested) {
		super(nested);
	}
	
}
