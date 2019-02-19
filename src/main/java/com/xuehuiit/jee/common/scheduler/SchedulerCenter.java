/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.scheduler;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class SchedulerCenter {
	
	private static SchedulerService schedulerservice;
	private static Scheduler scheduler;
	private static Logger log = Logger.getLogger(SchedulerCenter.class);
	
	
	/**
	 * 初始化定时器
	 *
	 */
	public static void init(){
		
		try {
			
				if(  null == schedulerservice  ){
					
					 SchedulerFactory schedulerFactory = new StdSchedulerFactory();      
			         // Retrieve a scheduler from schedule factory
					 if( null == scheduler )
					   scheduler = schedulerFactory.getScheduler();
					 
					 scheduler.start();
					 schedulerservice = new SchedulerServiceImplQuartz(scheduler);
				}
				
				
		} catch (SchedulerException e) {
			//e.printStackTrace();
			log.error("",e);
			throw new ScheduleException("",e);
		}	 
	}
	
	
	
	/**
	 * 初始化定时器
	 *
	 */
	public static void init(String configfile){
		
		try {
			
			System.setProperty(StdSchedulerFactory.PROPERTIES_FILE, configfile);
			
				if(  null == schedulerservice  ){
					
					 SchedulerFactory schedulerFactory = new StdSchedulerFactory();      
			         // Retrieve a scheduler from schedule factory
					 if( null == scheduler )
					   scheduler = schedulerFactory.getScheduler();
					 
					 scheduler.start();
					 schedulerservice = new SchedulerServiceImplQuartz(scheduler);
				}
				
				
		} catch (SchedulerException e) {
			//e.printStackTrace();
			log.error("",e);
			throw new ScheduleException("",e);
		}	 
	}
	
	
	
	
	
	
	public static void stop(){
		
		try {
			
				if(  null != scheduler  ){
					scheduler.shutdown();
				}
				
				
		} catch (SchedulerException e) {
			//e.printStackTrace();
			log.error("",e);
			throw new ScheduleException("",e);
		}	 
	}
	
	/**
	 * 获取定时器
	 * @return
	 */
	public static SchedulerService getScheduler(){
		
		try {
				
				if(  null == schedulerservice  ){
					
					 SchedulerFactory schedulerFactory = new StdSchedulerFactory();      
			         // Retrieve a scheduler from schedule factory
					 Scheduler scheduler = schedulerFactory.getScheduler();
					 schedulerservice = new SchedulerServiceImplQuartz(scheduler);
				}
				
				
		} catch (SchedulerException e) {
			//e.printStackTrace();
			log.error("",e);
			throw new ScheduleException("",e);
		}	 
		
		return schedulerservice;
	
	}

	
	
	
	
}
