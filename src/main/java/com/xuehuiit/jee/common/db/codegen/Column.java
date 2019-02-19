/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.db.codegen;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class Column {

	
	private String columns;
	private String commit;
	private String datetype;
	public String getColumns() {
		return columns;
	}
	public String getCommit() {
		return commit;
	}
	public String getDatetype() {
		return datetype;
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
	public void setCommit(String commit) {
		this.commit = commit;
	}
	public void setDatetype(String datetype) {
		this.datetype = datetype;
	}
	public String toString() {
		return new ToStringBuilder(this).append("columns", columns).append(
				"commit", commit).append("datetype", datetype).toString();
	}
	
	
	
	
	
}
