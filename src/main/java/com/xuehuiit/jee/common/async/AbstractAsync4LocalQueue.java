/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.async;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;


/**
 * be
 * 
 * 
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public abstract class AbstractAsync4LocalQueue {

	
	private  Logger log; 
	
	private  BlockingQueue TPLFILE_CHANGE_QUE;
	private  ExecutorService sendThreadPoll = null;
	protected  int OPT_THREAD_NUM;
	private  String OPTION_NAME; //处理器名称
	
	private int blockflag = 1;
	
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
	 * @param LogAdpart   		日志记录器
	 * @param blocknum    		缓冲队列长度
	 * @param optthreadnum 		操作线程数
	 * @param flag             队列设值类型  1-阻塞 0-非阻塞
	 * @param optName          操作线程名称
	 */
	public AbstractAsync4LocalQueue(String LogAdpart , int blocknum , int optthreadnum , int flag , String optName){
		if( null==LogAdpart || LogAdpart.length()==0 )
			log = Logger.getLogger(this.getClass());
		else
			log = Logger.getLogger(LogAdpart);
		this.TPLFILE_CHANGE_QUE = new LinkedBlockingQueue(blocknum);
		this.OPT_THREAD_NUM = optthreadnum;
		this.blockflag = flag;
		this.OPTION_NAME = optName;
		
	}
	
	
	/**
	 * 
	 * @param LogAdpart        日志记录器
	 * @param blocknum         缓冲队列长度
	 * @param optthreadnum     操作线程数 
	 * @param flag				队列设值类型  1-阻塞 0-非阻塞
	 * 
	 */
	public AbstractAsync4LocalQueue(String LogAdpart , int blocknum , int optthreadnum , int flag){
		if( null==LogAdpart || LogAdpart.length()==0 )
			log = Logger.getLogger(this.getClass());
		else
			log = Logger.getLogger(LogAdpart);
		this.TPLFILE_CHANGE_QUE = new LinkedBlockingQueue(blocknum);
		this.OPT_THREAD_NUM = optthreadnum;
		this.blockflag = flag;
		this.OPTION_NAME = LogAdpart;
		
	}
	
	
	
	/**
	 * 
	 * @param LogAdpart  日志构建起的名字
	 * @param blocknum    缓存队列的数目
	 * @param optthreadnum  操作线程数目
	 * 
	 */
	public AbstractAsync4LocalQueue(String LogAdpart , int blocknum , int optthreadnum){
		if( null==LogAdpart || LogAdpart.length()==0 )
			log = Logger.getLogger(this.getClass());
		else
			log = Logger.getLogger(LogAdpart);
		TPLFILE_CHANGE_QUE = new LinkedBlockingQueue(blocknum);
		OPT_THREAD_NUM = optthreadnum;
	}
	
	
	public AbstractAsync4LocalQueue(){	
		log = Logger.getLogger(AbstractAsync.class);
		TPLFILE_CHANGE_QUE = new LinkedBlockingQueue(4000);
		OPT_THREAD_NUM = 1;
	}

	
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
	 * 处理器关闭标志
	 * @return
	 */
	abstract public boolean closeFalg(Object object);
	
	
	/**
	 * 从队列中取出值
	 * @return
	 */
	public Object getValue4Queue(){
		
		Object returnvalue = null;
		
		try {
			
			returnvalue = TPLFILE_CHANGE_QUE.take();
			
			/*if (blockflag == 1) // 阻塞的方式
				returnvalue = TPLFILE_CHANGE_QUE.take();
			else if (blockflag == 0) // 非阻塞的方式
				returnvalue = TPLFILE_CHANGE_QUE.poll();*/
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.error(e);
			throw new AsyncException(e,"队列错误");
		}
		
		return returnvalue;
		
	}
	
	
	/**
	 * 放入一个值到队列中
	 * @param obj
	 */
	public void setValue2Queru(Object obj){
		
		try {
			
			if( blockflag == 1 ) //阻塞的方式
			   TPLFILE_CHANGE_QUE.put(obj);
			else if( blockflag == 0 ) //非阻塞的方式
				TPLFILE_CHANGE_QUE.offer(obj);
			
			
		} catch (InterruptedException e) {
			log.error(e);
			throw new AsyncException(e,"【"+OPTION_NAME+"】 队列错误");
		}
		
	}
	
	
	public void shutdown(){
		setSentinel();
		sendThreadPoll.shutdownNow();
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
		 	
			log.info("启动后台进【"+OPTION_NAME+"】 程处理器");
						
			
				while(  true ){
					
					try{
						
							Object OptObj = getValue4Queue();
							
							if( closeFalg(OptObj)  ){
								
								log.info("关闭后台【"+OPTION_NAME+"】 进程处理器");
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


	public String getOpterName() {
		return OPTION_NAME;
	}

	public void setOpterName(String opterName) {
		this.OPTION_NAME = opterName;
	}
	
	
	
	
	
	
	
	
}
