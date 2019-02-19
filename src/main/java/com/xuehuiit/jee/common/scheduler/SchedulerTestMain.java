/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.scheduler;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class SchedulerTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		try {
			
				SchedulerCenter.init();
				SchedulerService scheduler = SchedulerCenter.getScheduler();
				
				JobInfo jobInfo = new JobInfo();
				jobInfo.setJobCommand("测试命令,每天执行的命令11111");
				jobInfo.setJobName("执行任务每天111");
				
				scheduler.addSchdule(jobInfo, "测试间隔一段时间后执行", "这是定时器", 10, 10,SendCommand2Queue.class);
				int[] meints = new int[]{18,40,30};
				//scheduler.addSchdule4Day(jobInfo, "每天", "执行任务每天trige11", meints);
				
				
				//scheduler.addSchdule4Mons(jobInfo, "测试月人呢无","月恩达爱的色放", 5, meints, 1 , SendCommand2Queue.class);
				
				
				
				Thread.sleep(100000000);
				//SchedulerCenter.stop();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}
