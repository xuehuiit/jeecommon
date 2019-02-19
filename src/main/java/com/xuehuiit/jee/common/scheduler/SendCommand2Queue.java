/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.scheduler;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class SendCommand2Queue implements Job {

	private final static Logger log = Logger.getLogger(SendCommand2Queue.class);
	
	/* （非 Javadoc）
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		String commandString = context.getJobDetail().getJobDataMap().getString("command"); // 命令字符串
		String jobname = context.getJobDetail().getKey().toString(); //任务名称
		String triggerName = context.getTrigger().getKey().toString(); //触发器命名
		
		System.out.println("触发器  [ "+triggerName+" ]   任务 <"+jobname+">  command= "+commandString );
		log.info("触发器  [ "+triggerName+" ]   任务 <"+jobname+">  command= "+commandString );
		
		
		
	}

}
