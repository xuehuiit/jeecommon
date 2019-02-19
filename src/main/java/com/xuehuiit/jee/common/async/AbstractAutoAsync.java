/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.async;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public abstract class AbstractAutoAsync {

	
	private  Logger log; 
	private  BlockingQueue TPLFILE_CHANGE_QUE;
	private ScheduledExecutorService timer;
	private ScheduledFuture awardSendTaskHandle = null;
	private ExecutorService sendThreadPoll = null;
	private String OPTION_NAME;
	
	private int start;
	private int interval;
	
	private int blockflag = 1;
	
	
	
	
	public String getOPTION_NAME() {
		return OPTION_NAME;
	}


	public void setOPTION_NAME(String option_name) {
		OPTION_NAME = option_name;
	}


	/**
	 * 获取队列的大小
	 * 
	 * @return
	 */
	public int getQueueSize(){
		
		if(  null != TPLFILE_CHANGE_QUE )
			return TPLFILE_CHANGE_QUE.size();
		else
			return 0;
		
	}
	
	
	/**
	 * 
	 * @param LongAdpart  日志处理器
	 * @param blocknum    队列的长度
	 * @param start       定时队列开始时间
	 * @param interval    定时队列间隔时间
	 * 
	 */
	public AbstractAutoAsync(String LongAdpart , int blocknum , int start , int interval , int blockfalg){
		log = Logger.getLogger(LongAdpart);
		TPLFILE_CHANGE_QUE = new LinkedBlockingQueue(blocknum);
		this.start = start;
		this.interval = interval;
		this.blockflag = blockfalg;
	}
	
	/**
	 * 
	 * @param LongAdpart  日志处理器
	 * @param blocknum    队列的长度
	 * @param start       定时队列开始时间
	 * @param interval    定时队列间隔时间
	 * 
	 */
	public AbstractAutoAsync(String LongAdpart , int blocknum , int start , int interval){
		log = Logger.getLogger(LongAdpart);
		TPLFILE_CHANGE_QUE = new LinkedBlockingQueue(blocknum);
		this.start = start;
		this.interval = interval;
	}
	
	
	public AbstractAutoAsync(){	
		log = Logger.getLogger(AbstractAsync.class);
		TPLFILE_CHANGE_QUE = new LinkedBlockingQueue(4000);
	}
	
	

	
	abstract public void monitor();
	
	/**
	 * 
	 * 异步操作
	 * 
	 * @param obj
	 */
	abstract public void Operation(Object obj);
	
	/**
	 * 放入表示队列结束的对象
	 */
	abstract public void setSentinel();
	
	
	/**
	 * 
	 * @return
	 */
	abstract public boolean closeFalg();
	
	
	public void shutdown(){
		
		setSentinel();
		timer.shutdownNow();
		awardSendTaskHandle.cancel(true);
		sendThreadPoll.shutdownNow();
		
		
	}
	
	/**
	 * 初始化
	 *
	 */
	public  void  initMonitor(){
		
		
		long awardSendStartTime = start ;
		long awardSendSplitTime = interval ;
		
		timer = Executors.newScheduledThreadPool(1);
		sendThreadPoll = Executors.newFixedThreadPool(1);
         
		MonitorThread moitor = new MonitorThread();
		awardSendTaskHandle = timer.scheduleAtFixedRate(moitor,awardSendStartTime, awardSendSplitTime, TimeUnit.SECONDS);
		
		
		OptThread opt = new OptThread();
		sendThreadPoll.submit(opt);
		
	}
	
	
	/**
	 * 放入一个值到队列中
	 * @param obj
	 */
	public void setValue2Queru(Object obj){
		
		try {
			
			if( blockflag == 1 ) //阻塞的方式
				   TPLFILE_CHANGE_QUE.put(obj);
			else if( blockflag == 0 )  //非阻塞的方式
				TPLFILE_CHANGE_QUE.offer(obj);
			
			
		} catch (InterruptedException e) {
			log.error(e);
			throw new AsyncException(e,"【"+OPTION_NAME+"】队列错误");
		}
		
	}
	
	
	/**
	 * 
	 * 用来监控的类
	 * 
	 * @author <a href="fx19800215@163.com">robert.feng</a> 
	 *
	 */
	class MonitorThread implements Runnable{

		private AbstractAsync async;
		
		public MonitorThread(AbstractAsync async){
			this.async = async;
		}
		
		public MonitorThread(){
			
		}
		
		public void run() {
			
			log.info("【"+OPTION_NAME+"】启动文件变化检查器");
			monitor();
			
		}
		
		
		
	}
	
	
	/**
	 * 
	 * 用来在后台进行相关处理的类
	 * 
	 * @author <a href="fx19800215@163.com">robert.feng</a> 
	 *
	 */
	class OptThread implements Runnable{

		private AbstractAsync async;
		
		public OptThread(AbstractAsync async){
			this.async = async;
		}
		
		public OptThread(){
			
		}
		
		public void run() {
			
		 	
			log.info("【"+OPTION_NAME+"】后台进程处理器");
			
				
				
				while(  true ){
					

					try {
						
							Object OptObj = TPLFILE_CHANGE_QUE.take();
							
							if( closeFalg()  ){  
								log.info("【"+OPTION_NAME+"】关闭后台进程处理器");
							    break;
							}else{
								Operation(OptObj);
							}
					
					} catch (InterruptedException e) {	
						log.error("【"+OPTION_NAME+"】对象放入队列时发生错误！",e);
					}	
			   
				}
				
			
			
		}
			
	}
	
}
