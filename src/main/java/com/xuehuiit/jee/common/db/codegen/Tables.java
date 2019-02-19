/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.db.codegen;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class Tables {

	private String tablename;

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String toString() {
		return new ToStringBuilder(this).append("tablename", tablename)
				.toString();
	}
	
	
	
	
}
