/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.async.test;

import com.xuehuiit.jee.common.async.AbstractAsync4LocalQueue;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class Async4LocalQueueTest extends AbstractAsync4LocalQueue {

	/**
	 * @param LogAdpart
	 * @param blocknum
	 * @param optthreadnum
	 * @param flag
	 * @param optName
	 */
	public Async4LocalQueueTest(String LogAdpart, int blocknum,
			int optthreadnum, int flag, String optName) {
		super(LogAdpart, blocknum, optthreadnum, flag, optName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param LogAdpart
	 * @param blocknum
	 * @param optthreadnum
	 * @param flag
	 */
	public Async4LocalQueueTest(String LogAdpart, int blocknum,
			int optthreadnum, int flag) {
		super(LogAdpart, blocknum, optthreadnum, flag);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param LogAdpart
	 * @param blocknum
	 * @param optthreadnum
	 */
	public Async4LocalQueueTest(String LogAdpart, int blocknum, int optthreadnum) {
		super(LogAdpart, blocknum, optthreadnum);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public Async4LocalQueueTest() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see AbstractAsync4LocalQueue#Operation(java.lang.Object)
	 */
	@Override
	public void Operation(Object obj) {
		
		System.out.println("队列中的消息数：" + getQueueSize());
	/*	try {
			Thread.currentThread().sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		try{
		Integer intv = (Integer)obj;
		int f = 100/intv.intValue();
		System.out.println(f);
		}catch( Exception ex ){
			ex.printStackTrace();
		}
	
		
		
	}

	/* (non-Javadoc)
	 * @see AbstractAsync4LocalQueue#closeFalg(java.lang.Object)
	 */
	@Override
	public boolean closeFalg(Object object) {
		return false;
	}

	/* (non-Javadoc)
	 * @see AbstractAsync4LocalQueue#setSentinel()
	 */
	@Override
	public void setSentinel() {
	}

}
