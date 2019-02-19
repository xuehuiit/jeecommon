/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.scheduler;

import org.quartz.Job;

/**
 * 
 * 所有的调度任务接口
 * 
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public interface SchedulerService {

	
	/**
	 * 
	 * 有限次运行
	 * 
	 * @param jobInfo   任务信息
	 * @param groupName  任务组名称
	 * @param triggerName 触发器名称
	 * @param afterS      几秒后开始执行 0 为立即执行
	 * @param Interval    时间间隔
	 * @param runNum      运行次数  0 为无限次
	 * 
	 */
	public void addSchdule( JobInfo jobInfo , String groupName ,  String triggerName , int afterS , int Interval, int runNum  , Class<? extends Job> _class);
	
	
	/**
	 * 
	 * 无限制次运行
	 * 
	 * @param jobInfo   任务信息
	 * @param groupName  任务组名称
	 * @param triggerName 触发器名称
	 * @param afterS      几秒后开始执行 0 为立即执行
	 * @param Interval    时间间隔 
	 */
	public void addSchdule( JobInfo jobInfo , String groupName ,  String triggerName , int afterS , int Interval , Class<? extends Job> _class);
	
	
	/**
	 * 
	 * 指定每天指定时刻运行的任务
	 * 
	* @param jobInfo   任务信息
	 * @param groupName  任务组名称
	 * @param triggerName 触发器名称
	 * @param hours       用来表达时间的数组 int[] hours = {15,3,12}
	 */
	public void addSchdule4Day( JobInfo jobInfo , String groupName ,  String triggerName , int[]  hours , Class<? extends Job> _class);
	
	
	/**
	 * 
	 * 指定每天指定时刻运行的任务
	 * 
	* @param jobInfo   任务信息
	 * @param groupName  任务组名称
	 * @param triggerName 触发器名称
	 * @param hours       用来表达时间的数组 int[] hours = {15,3,12}
	 * @param days        间隔天数
	 */
	public void addSchdule4Days( JobInfo jobInfo , String groupName ,  String triggerName , int[]  hours , int  days  , Class<? extends Job> _class);
	
	
	/**
	 * 
	 * 每周指定天指定时刻运行的任务
	 * 
	 * @param jobInfo   任务信息
	 * @param groupName  任务组名称s
	 * @param triggerName 触发器名称
	 * @param hours       用来表达时间的数组 int[] hours = {15,3,12}
	 * @param weekDay     每周的日期  1-星期一 2-星期二 3-星期三 4-星期四 5-星期五 6-星期六 7 星期天
	 */
	public void addSchdule4Week( JobInfo jobInfo , String groupName ,  String triggerName , int weekDay , int[]  hours  , Class<? extends Job> _class);
	
	
	
	/**
	 * 
	 * 每几周指定天指定时刻运行的任务
	 * 
	 * @param jobInfo   任务信息
	 * @param groupName  任务组名称s
	 * @param triggerName 触发器名称
	 @param hours       用来表达时间的数组 int[] hours = {15,3,12}
	 * @param weekDay     每周的日期  1-星期一 2-星期二 3-星期三 4-星期四 5-星期五 6-星期六 7 星期天
	 * @param weeks       每隔几周执行一次
	 * 
	 */
	public void addSchdule4Weeks( JobInfo jobInfo , String groupName ,  String triggerName , int weekDay , int[]  hours , int weeks , Class<? extends Job> _class);
	
	
	/**
	 * 
	 * 每月指定天指定时刻运行的任务
	 * 
	 * @param jobInfo   任务信息
	 * @param groupName  任务组名称s
	 * @param triggerName 触发器名称
	 * @param hours       用来表达时间的数组 int[] hours = {15,3,12}
	 * @param MonthDay    0表示最后一天，其余的数字代指定天
	 */
	public void addSchdule4Mon( JobInfo jobInfo , String groupName ,  String triggerName , int MonthDay , int[]  hours , Class<? extends Job> _class);
	
	
	/**
	 * 
	 * 每几个月指定天指定时刻运行的任务
	 * 
	 * @param jobInfo   任务信息
	 * @param groupName  任务组名称s
	 * @param triggerName 触发器名称
	 * @param hours       用来表达时间的数组 int[] hours = {15,3,12}
	 * @param MonthDay    0表示最后一天，其余的数字代表指定天
	 * @param months  指定几个月执行一次
	 * 
	 */
	public void addSchdule4Mons( JobInfo jobInfo , String groupName ,  String triggerName , int MonthDay , int[]  hours , int months , Class<? extends Job> _class);
	
	
	/**
	 * 
	 * 暂停Trigger
	 * 
	 * @param triggerName
	 * @param group
	 * 
	 */
	public void pauseTrigger(String triggerName,String group);
	
	
	/**
	 * 恢复Trigger
	 * 
	 * @param triggerName
	 * @param group
	 */
	public void resumeTrigger(String triggerName,String group);
	
	/**
	 * 删除Trigger
	 * @param triggerName
	 * @param group
	 */
	public boolean removeTrigdger(String triggerName,String group);
}
