/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.async.test;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class AsyncTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testQueue();
	}
	
	
	
	/**
	 * 
	 */
	public static void testQueue(){
		
		Async4LocalQueueTest d = new Async4LocalQueueTest("",2000,1,0,"测试");
		
		d.initMonitor();
		
		for( int i = 0 ; i < 2000 ; i++){
			d.setValue2Queru(Integer.valueOf(0));
		}
		
		
		
	}

}
