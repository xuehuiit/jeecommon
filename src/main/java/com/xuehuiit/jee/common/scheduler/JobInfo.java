/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.scheduler;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class JobInfo {
	
	private String jobName;
	private String jobCommand;
	/**
	 * @return jobCommand
	 */
	public String getJobCommand() {
		return jobCommand;
	}
	/**
	 * @return jobName
	 */
	public String getJobName() {
		return jobName;
	}
	/**
	 * @param jobCommand 要设置的 jobCommand
	 */
	public void setJobCommand(String jobCommand) {
		this.jobCommand = jobCommand;
	}
	/**
	 * @param jobName 要设置的 jobName
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	
	

}
