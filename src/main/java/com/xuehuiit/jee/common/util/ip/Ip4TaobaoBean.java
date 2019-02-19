/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.util.ip;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class Ip4TaobaoBean {

	

	
	private String code;
	private Ip4TaobaoDataBean data;
	public String getCode() {
		return code;
	}
	public Ip4TaobaoDataBean getData() {
		return data;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setData(Ip4TaobaoDataBean data) {
		this.data = data;
	}
	public String toString() {
		return new ToStringBuilder(this).append("code", code).append("data",
				data).toString();
	}
	
	
	
	
	
	
}
