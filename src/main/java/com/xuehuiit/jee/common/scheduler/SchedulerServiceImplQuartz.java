/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.scheduler;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.CalendarIntervalTrigger;
import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import com.xuehuiit.jee.common.util.DateUtil;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class SchedulerServiceImplQuartz implements SchedulerService {

	private final static Logger log = Logger.getLogger(SchedulerServiceImplQuartz.class);
	
	
	private Scheduler scheduler;
	
	
	/**
	 * @return scheduler
	 */
	public Scheduler getScheduler() {
		return scheduler;
	}

	/**
	 * @param scheduler 要设置的 scheduler
	 */
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public SchedulerServiceImplQuartz(){
		
	}
	
	public SchedulerServiceImplQuartz( Scheduler _scheduler){
		
		this.scheduler = _scheduler;
	}
	
	
	/* （非 Javadoc）
	 * @see com.xuehuiit.jee.common.scheduler.SchedulerService#addSchdule(com.xuehuiit.jee.common.scheduler.JobInfo, java.lang.String, java.lang.String, int, int, int)
	 */
	public void addSchdule(JobInfo jobInfo, String groupName,String triggerName, int afterS, int Interval, int runNum ,Class<? extends Job> _class) {
		
		  try {
				
					//构造job
					JobDetail jobDetail = newJob( _class).withIdentity(jobInfo.getJobName(), groupName ).build();
					jobDetail.getJobDataMap().put("command", jobInfo.getJobCommand());
					
					//构造trigger 
					SimpleTrigger trigger;
					TriggerBuilder<Trigger>  triggerBuilder = newTrigger().withIdentity( triggerName , groupName);
					
					if( afterS == 0   ){
						triggerBuilder = triggerBuilder.startNow();
						
					}else{
						
						Date startdate = DateBuilder.nextGivenSecondDate(new Date(), afterS );
						triggerBuilder = triggerBuilder.startAt(startdate);
					}
					
					if( runNum == 0  ){
						
						trigger = triggerBuilder.withSchedule(simpleSchedule().withIntervalInSeconds(Interval).repeatForever()).build();
					}else{
						trigger = triggerBuilder.withSchedule(simpleSchedule().withIntervalInSeconds(Interval).withRepeatCount(runNum)).build();
					}
					
					scheduler.scheduleJob(jobDetail, trigger);
					
			} catch (SchedulerException e) {
				//e.printStackTrace();
				log.error("添加时间间隔任务错误",e);
				throw new ScheduleException("",e);
			}
		
		
	}

	/* （非 Javadoc）
	 * @see com.xuehuiit.jee.common.scheduler.SchedulerService#addSchdule(com.xuehuiit.jee.common.scheduler.JobInfo, java.lang.String, java.lang.String, int, int)
	 */
	public void addSchdule(JobInfo jobInfo, String groupName,
			String triggerName, int afterS, int Interval,Class<? extends Job> _class) {
			
		addSchdule( jobInfo,  groupName, triggerName,  afterS,  Interval , 0 ,_class);
		
	}
	

	/* （非 Javadoc）
	 * @see com.xuehuiit.jee.common.scheduler.SchedulerService#addSchdule4Day(com.xuehuiit.jee.common.scheduler.JobInfo, java.lang.String, java.lang.String, int[])
	 */
	public void addSchdule4Day(JobInfo jobInfo, String groupName,
			String triggerName, int[] hours , Class<? extends Job> _class) {
		addSchdule4Days(jobInfo, groupName, triggerName,  hours, 1 ,_class);
	}
	
	public void addSchdule4Days(JobInfo jobInfo, String groupName, String triggerName, int[] hours, int days, Class<? extends Job> _class) {
		
		try {
			
				//构造job
				JobDetail jobDetail = newJob(_class).withIdentity(jobInfo.getJobName(), groupName ).build();
				jobDetail.getJobDataMap().put("command", jobInfo.getJobCommand());
				
				
				//int afterS = dayafter(hours);
				Date startdate = dayafter(hours);
				
				SimpleTrigger trigger = newTrigger()
			    .withIdentity(triggerName ,groupName)
			    .startAt(startdate)  // 15:00:00 tomorrow startAt(startdate) 
			    .withSchedule(simpleSchedule().withIntervalInHours(24*days) // interval is actually set at 24 hours' worth of milliseconds
			            .repeatForever()) // interval is set in calendar days
			    .build();
				
				scheduler.scheduleJob(jobDetail, trigger);
		
		} catch (SchedulerException e) {
			//e.printStackTrace();
			log.error("添加天间隔任务错误",e);
			throw new ScheduleException("添加天间隔任务错误",e);
		}
	}
	

	/* （非 Javadoc）
	 * @see com.xuehuiit.jee.common.scheduler.SchedulerService#addSchdule4Mon(com.xuehuiit.jee.common.scheduler.JobInfo, java.lang.String, java.lang.String, int, int[])
	 */
	public void addSchdule4Mon(JobInfo jobInfo, String groupName,
			String triggerName, int MonthDay, int[] hours , Class<? extends Job> _class) {
		
		addSchdule4Mons(jobInfo, groupName, triggerName, MonthDay,  hours, 1 , _class);
		
	}

	/* （非 Javadoc）
	 * @see com.xuehuiit.jee.common.scheduler.SchedulerService#addSchdule4Mons(com.xuehuiit.jee.common.scheduler.JobInfo, java.lang.String, java.lang.String, int, int[], int)
	 */
	public void addSchdule4Mons(JobInfo jobInfo, String groupName,
			String triggerName, int MonthDay, int[] hours, int months, Class<? extends Job> _class) {
		
		
		try {
		
			//构造job
			JobDetail jobDetail = newJob(_class).withIdentity(jobInfo.getJobName(), groupName ).build();
			jobDetail.getJobDataMap().put("command", jobInfo.getJobCommand());
			
			Date startdate = monthAfter(hours,MonthDay);
			
			CalendarIntervalTrigger trigger = (CalendarIntervalTrigger)newTrigger()
		    .withIdentity(triggerName ,groupName)
		    .startAt(startdate)  // 15:00:00 tomorrow
		    .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInMonths(months)) // interval is set in calendar months
		    .build();
	
			scheduler.scheduleJob(jobDetail, trigger);
			
		} catch (SchedulerException e) {
			//e.printStackTrace();
			log.error("添加月间隔任务错误",e);
			throw new ScheduleException("添加天间隔任务错误",e);
		}
	}

	/* （非 Javadoc）
	 * @see com.xuehuiit.jee.common.scheduler.SchedulerService#addSchdule4Week(com.xuehuiit.jee.common.scheduler.JobInfo, java.lang.String, java.lang.String, int, int[])
	 */
	public void addSchdule4Week(JobInfo jobInfo, String groupName,
			String triggerName, int weekDay, int[] hours, Class<? extends Job> _class) {
		
		addSchdule4Weeks(jobInfo, groupName,
				triggerName, weekDay, hours, 1 , _class );
		
	}

	/* （非 Javadoc）
	 * @see com.xuehuiit.jee.common.scheduler.SchedulerService#addSchdule4Weeks(com.xuehuiit.jee.common.scheduler.JobInfo, java.lang.String, java.lang.String, int, int[], int)
	 */
	public void addSchdule4Weeks(JobInfo jobInfo, String groupName,
			String triggerName, int weekDay, int[] hours, int weeks, Class<? extends Job> _class) {
		
		try {
			
			//构造job
			JobDetail jobDetail = newJob(_class).withIdentity(jobInfo.getJobName(), groupName ).build();
			jobDetail.getJobDataMap().put("command", jobInfo.getJobCommand());
			
			
			//int afterS = dayafter(hours);
			Date startdate = weeAfter(hours,weekDay);
			
			SimpleTrigger trigger = newTrigger()
		    .withIdentity(triggerName ,groupName)
		    .startAt(startdate)  // 15:00:00 tomorrow startAt(startdate) 
		    .withSchedule(simpleSchedule().withIntervalInHours(24*7*weeks) // interval is actually set at 24 hours' worth of milliseconds
		            .repeatForever()) // interval is set in calendar days
		    .build();
			
			scheduler.scheduleJob(jobDetail, trigger);
	
	} catch (SchedulerException e) {
		//e.printStackTrace();
		log.error("添加天间隔任务错误",e);
		throw new ScheduleException("添加天间隔任务错误",e);
	}
		
		
	}

	
	public void pauseTrigger(String triggerName, String group) {
		
		String keyString = group+"."+triggerName;
		
		try {
			TriggerKey key = new TriggerKey( triggerName , group );
			scheduler.pauseTrigger(key);//停止触发器
		} catch (SchedulerException e) {
			log.error("停止触发器 ["+keyString+"]失败" ,e);
			throw new ScheduleException("",e);
		}
		
	}

	public boolean removeTrigdger(String triggerName, String group) {
		
		String keyString = group+"."+triggerName;
		
		try {
			TriggerKey key = new TriggerKey( triggerName , group );
			pauseTrigger(triggerName, group);//停止触发器
			return scheduler.unscheduleJob(key);//移除触发器
			
		} catch (SchedulerException e) {
			log.error("移除触发器 ["+keyString+"]失败" ,e);
			throw new ScheduleException("",e);
		}
	}

	public void resumeTrigger(String triggerName, String group) {
		
		String keyString = group+"."+triggerName;
		
		try {
			TriggerKey key = new TriggerKey( triggerName , group );
			scheduler.resumeTrigger(key);//重启触发器
		} catch (SchedulerException e) {
			log.error("重启触发器 ["+keyString+"]失败" ,e);
			throw new ScheduleException("",e);
		}
		
		
	}

	
	
	private static Date dayafter(int[] hours){
		
		StringBuffer triggerdatebuff = new StringBuffer();
		triggerdatebuff.append(DateUtil.getDate2FormatString(new Date(), "yyyy-MM-dd"));
		triggerdatebuff.append("/");
		
		if( hours[0] > 9 )
			triggerdatebuff.append(hours[0]);
		else
			triggerdatebuff.append("0"+hours[0]);
		triggerdatebuff.append(":");
		
		if( hours[1] > 9 )
			triggerdatebuff.append(hours[1]);
		else
			triggerdatebuff.append("0"+hours[1]);
		triggerdatebuff.append(":");
		
		if( hours[2] > 9 )
			triggerdatebuff.append(hours[2]);
		else
			triggerdatebuff.append("0"+hours[2]);
		
		
		Date triggerdate = DateUtil.stringToDate(triggerdatebuff.toString(), "yyyy-MM-dd/HH:mm:ss");
		
		/*int diff = DateUtil.diffSeconds(triggerdate, new Date());
		
		
		log.info(" ------- " + triggerdatebuff.toString() + " ************  " +  diff );*/
		
		
		
		return triggerdate;
	}
	
	//dayafter(hours)
	private static Date weeAfter(int[] hours , int weekday){
		
		StringBuffer triggerdatebuff = new StringBuffer();
		
		int currentWeek = DateUtil.getWeekOfDate(new Date());
		int dffd = weekday - currentWeek ;
		Date triigerdate = DateUtil.addDate(new Date(), dffd);
		
		triggerdatebuff.append(DateUtil.getDate2FormatString(triigerdate, "yyyy-MM-dd"));
		triggerdatebuff.append("/");
		
		if( hours[0] > 9 )
			triggerdatebuff.append(hours[0]);
		else
			triggerdatebuff.append("0"+hours[0]);
		triggerdatebuff.append(":");
		
		if( hours[1] > 9 )
			triggerdatebuff.append(hours[1]);
		else
			triggerdatebuff.append("0"+hours[1]);
		triggerdatebuff.append(":");
		
		if( hours[2] > 9 )
			triggerdatebuff.append(hours[2]);
		else
			triggerdatebuff.append("0"+hours[2]);
		
        		
		Date triggerdate = DateUtil.stringToDate(triggerdatebuff.toString(), "yyyy-MM-dd/HH:mm:ss");
		
		/*int diff = DateUtil.diffSeconds(triggerdate, new Date());
		
		
		log.info(" ------- " + triggerdatebuff.toString() + " ************  " +  diff );*/
		
		log.info(" ------- " + triggerdatebuff.toString());
		
		return triggerdate;
	}
	
	
	//dayafter(hours)
	private static Date monthAfter(int[] hours , int monthday){
		
		StringBuffer triggerdatebuff = new StringBuffer();
		//int dffd = weekday - currentWeek ;
		
		String triggerdateString;
		
		if(   monthday == 0  )
			triggerdateString = DateUtil.dateToString(DateUtil.getLastMonthDate(new Date()), "yyyy-MM-dd");
		else{
			
			if( monthday > 9 )
				triggerdateString = DateUtil.dateToString(new Date(),"yyyy-MM")+"-"+String.valueOf(monthday);
			else
				triggerdateString = DateUtil.dateToString(new Date(),"yyyy-MM")+"-0"+String.valueOf(monthday);
			
		}
			
		
		
		triggerdatebuff.append(triggerdateString);
		triggerdatebuff.append("/");
		
		if( hours[0] > 9 )
			triggerdatebuff.append(hours[0]);
		else
			triggerdatebuff.append("0"+hours[0]);
		triggerdatebuff.append(":");
		
		if( hours[1] > 9 )
			triggerdatebuff.append(hours[1]);
		else
			triggerdatebuff.append("0"+hours[1]);
		triggerdatebuff.append(":");
		
		if( hours[2] > 9 )
			triggerdatebuff.append(hours[2]);
		else
			triggerdatebuff.append("0"+hours[2]);
		
        		
		Date triggerdate = DateUtil.stringToDate(triggerdatebuff.toString(), "yyyy-MM-dd/HH:mm:ss");
		
		/*int diff = DateUtil.diffSeconds(triggerdate, new Date());
		
		
		log.info(" ------- " + triggerdatebuff.toString() + " ************  " +  diff );*/
		
		log.info(" ------- " + triggerdatebuff.toString());
		
		return triggerdate;
	}
	
	
	public static void main(String[] args){
		
		
		int[] meints = new int[]{15,43,30};
		monthAfter(meints,3);
		
		
	}

}
