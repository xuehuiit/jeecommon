/**
 * 
 */
package com.xuehuiit.jee.common.deamon;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 
 * 系统配置信息定时刷新
 * 
 * @author Administrator
 *
 */
public class ApplicationFlushJob implements Job {

	
	private final static Logger log = Logger.getLogger(ApplicationFlushJob.class);
	
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		String commandString = context.getJobDetail().getJobDataMap().getString("command"); // 命令字符串
		String jobname = context.getJobDetail().getKey().toString(); //任务名称
		String triggerName = context.getTrigger().getKey().toString(); //触发器命名
		
		//System.out.println("触发器  [ "+triggerName+" ]   任务 <"+jobname+">  command= "+commandString );
		log.info("触发器  [ "+triggerName+" ]   任务 <"+jobname+">  command= "+commandString );

		//initApplicationContext4Http("F:/wingfeng/project/nearmobile/common_statistics_static", "http://localhost:8082/loveconfig/index.php/cfg/getcfg?id=3");
		ApplicationContext.reflushApplicationContext4Http();
		
	}

}
