/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.async;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class AsyncTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//testQueue();
		testblockqueue();
		
	}
	
	
	public static void testblockqueue(){
		
		BlockingQueue<String> TPLFILE_CHANGE_QUE = new LinkedBlockingQueue<String>(20);
		
		
		   for( int i = 0 ; i<30 ; i++ ){
			   
			   //if( TPLFILE_CHANGE_QUE.size()<20 )
			    boolean result =  TPLFILE_CHANGE_QUE.offer( "fffff" );
			    System.out.println(result);
		   }
	
		
	}
	
	
	
	public static void testnoblockqueue(){
		
		ConcurrentLinkedQueue<String>  linkq = new ConcurrentLinkedQueue<String> ();
		
		
		for( int i = 0 ; i<1000000000 ; i++  ){
		    if( linkq.size() < 100 )
			linkq.add("dd"+i);
		}
		
		
		
		
	}

	
	public static void testQueue(){
		
		Queue<String> queue = new LinkedList<String>();
		
		for( int i = 0 ; i<100 ; i++  ){
			
			   
		   	   queue.add("dd"+i);
		}

	}
	
	
}
