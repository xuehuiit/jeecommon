/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/**
 * 
 * 异步工作模板类
 * 
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public abstract class AbstractAsync {

	
	private  Logger log; 
	
	private  ExecutorService sendThreadPoll = null;
	private  int OPT_THREAD_NUM;
	private  String OPTION_NAME;
	
	

	public int getOptNumber(){
		return OPT_THREAD_NUM;
	}
	
	/**
	 * 
	 * @param LongAdpart  日志构建起的名字
	 * @param optthreadnum  操作线程数目
	 */
	public AbstractAsync(String LongAdpart){
		log = Logger.getLogger(LongAdpart);
		OPT_THREAD_NUM = 1;
		OPTION_NAME = "default";
		if(LongAdpart!=null)
			OPTION_NAME = LongAdpart;
		  
	}
	
	/**
	 * 
	 * @param LongAdpart  日志构建起的名字
	 * @param optthreadnum  操作线程数目
	 */
	public AbstractAsync(String LongAdpart , String optname){
		log = Logger.getLogger(LongAdpart);
		OPT_THREAD_NUM = 1;
		OPTION_NAME = optname;
	}
	
	/**
	 * 
	 * @param LongAdpart  日志构建起的名字
	 * @param optthreadnum  操作线程数目
	 */
	public AbstractAsync(String LongAdpart  , int optthreadnum){
		log = Logger.getLogger(LongAdpart);
		OPT_THREAD_NUM = optthreadnum;
		OPTION_NAME = "default";
	}
	
	
	public AbstractAsync(){
		log = Logger.getLogger(AbstractAsync.class);
		OPT_THREAD_NUM = 1;
	}
	
	
	/**
	 * 
	 * 接受到消息后的数据处理工作
	 * 
	 * @param obj
	 */
	abstract public void Operation(Object obj);
	
	
	/**
	 * 
	 * 放入表示队列结束的对象
	 * 
	 */
	abstract public void setSentinel();
	
	
	/**
	 * 判断结束的方法，在改方法中判断是否结束
	 * 
	 * @return
	 */
	abstract public boolean closeFalg(Object obj);
	
	
	/**
	 * 从队列中取出值
	 * @return
	 */
	abstract public Object getValue4Queue();
	
	
	/**
	 * 放入一个值到队列中
	 * @param obj
	 */
	abstract public void setValue2Queru(Object obj);
	
	
	public void shutdown(){
		
		setSentinel();
		sendThreadPoll.shutdown();
		
	}
	
	/**
	 * 初始化文件监听器
	 *
	 */
	public  void  initMonitor(){

		sendThreadPoll = Executors.newFixedThreadPool(OPT_THREAD_NUM);
		
		for( int i = 0 ; i<OPT_THREAD_NUM ; i++    ){
			OptThread opt = new OptThread();
			 sendThreadPoll.submit(opt);
		}
		
		
		//sendThreadPoll.
		
	}
	
	
	/**
	 * 
	 * 用来在后台进行相关处理的类
	 * 
	 * @author <a href="fx19800215@163.com">robert.feng</a> 
	 *
	 */
	public class OptThread implements Runnable{

		private AbstractAsync async;
		
		public OptThread(AbstractAsync async){
			this.async = async;
		}
		
		public OptThread(){
			this.async = async;
		}
		
		
		public void run() {
			
		 	
			log.info("[ "+OPTION_NAME+" ] 启动....");
						
				while(  true ){
					
					try{
						
						Object OptObj = getValue4Queue();
						
						if( closeFalg(OptObj)  ){
						     
							log.info("[ "+OPTION_NAME+" ] 关闭....");
						    break;
						    
						    
						}else{
						   
							Operation(OptObj);
							
						}
						
					}catch (Exception ex){
						ex.printStackTrace();
						log.error("后台定时任务 【 "+OPTION_NAME+"】 出现错误",ex);
					}catch( Throwable t ){
						log.error("后台定时任务 【 "+OPTION_NAME+"】 出现错误",t);
					}
			   
				
				}
				
			
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	
}
