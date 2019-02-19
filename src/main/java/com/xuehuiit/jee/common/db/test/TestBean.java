/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.db.test;

import java.util.Date;

import com.xuehuiit.jee.common.db.BaseDbBean;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class TestBean extends BaseDbBean {

	
	public static final String TABLE_NAME = "test";
	
	/**
	 * `id`  int(11) NOT NULL AUTO_INCREMENT ,
`ints`  int(11) NULL DEFAULT NULL ,
`longs`  bigint(20) NULL DEFAULT NULL ,
`varchars`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`datetimes`  datetime NULL DEFAULT NULL ,
	 */
	
	private Integer id;
	private Integer ints;
	private Long longs;
	private String varchars;
	private Date datetimes;
	
	
	
	
	
	
	public Integer getId() {
		return id;
	}

	public Integer getInts() {
		return ints;
	}

	public Long getLongs() {
		return longs;
	}

	public String getVarchars() {
		return varchars;
	}

	public Date getDatetimes() {
		return datetimes;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInts(Integer ints) {
		this.ints = ints;
	}

	public void setLongs(Long longs) {
		this.longs = longs;
	}

	public void setVarchars(String varchars) {
		this.varchars = varchars;
	}

	public void setDatetimes(Date datetimes) {
		this.datetimes = datetimes;
	}

	/* (non-Javadoc)
	 * @see BaseDbBean#getInsertParms()
	 */
	@Override
	public Object[] getInsertParms() {
		
		 Object values[] = {  this.ints ,this.longs,this.varchars,this.datetimes };
		return values;
	}

	/* (non-Javadoc)
	 * @see BaseDbBean#getInsertsql()
	 */
	@Override
	public String getInsertsql() {
		return "insert into test(ints,longs,varchars,datetimes) values (?,?,?,?)";
	}

	/* (non-Javadoc)
	 * @see BaseDbBean#getTableName()
	 */
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

/*	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("ints", ints)
				.append("longs", longs).append("varchars", varchars).append(
						"datetimes", datetimes).toString();
	}*/

	
	
}
