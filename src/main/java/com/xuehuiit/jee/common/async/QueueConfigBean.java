/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.async;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.Gson;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class QueueConfigBean implements Serializable {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5154396013521084701L;
	
	
	private String ipaddress;  // ip地址，带端口号 
	private String msequeuename; //消息队列名称 
	private String message; //消息内容
	
	
	public String toString() {
		return new ToStringBuilder(this).append("ipaddress", ipaddress).append(
				"msequeuename", msequeuename).append("message", message)
				.toString();
	}
	
	
	
	/**
	 * @return ipaddress
	 */
	public String getIpaddress() {
		return ipaddress;
	}
	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @return msequeuename
	 */
	public String getMsequeuename() {
		return msequeuename;
	}
	/**
	 * @param ipaddress 要设置的 ipaddress
	 */
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	/**
	 * @param message 要设置的 message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @param msequeuename 要设置的 msequeuename
	 */
	public void setMsequeuename(String msequeuename) {
		this.msequeuename = msequeuename;
	}
	
	
	
	/**
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args){
		
		QueueConfigBean q = new QueueConfigBean();
		q.setIpaddress("192.168.10.87:20122");
		q.setMsequeuename("jobmessage");
		q.setMessage("start");
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(q));
		
		
		
	}
	
	
	
	
}
