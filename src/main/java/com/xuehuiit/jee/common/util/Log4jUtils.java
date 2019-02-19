/**
 * 
 */
package com.xuehuiit.jee.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author reobert.feng
 *
 */
public class Log4jUtils {

	
	public static List<Logger> LOGLIST = new ArrayList<Logger>();
	
	
	
	public static void enableInfo(){
		for (Logger logger : LOGLIST) {
			logger.setLevel(Level.INFO);
		}
		//LogManager.getRootLogger().setLevel(Level.INFO);
	}

	public static void enableWarn(){
		
		for (Logger logger : LOGLIST) {
			logger.setLevel(Level.WARN);
		}
		//LogManager.getRootLogger().setLevel(Level.WARN);
	}

	public static void enableError(){
		
		for (Logger logger : LOGLIST) {
			logger.setLevel(Level.ERROR);
		}
		//LogManager.getRootLogger().setLevel(Level.ERROR) ;
	}

	public static void enableDebug(){
		for (Logger logger : LOGLIST) {
			logger.setLevel(Level.DEBUG);
		}
		//LogManager.getRootLogger().setLevel(Level.DEBUG) ;
	}

	
	
	public static void enableInfo(String target){
		LogManager.getLogger(target).setLevel(Level.INFO);
	}

	public static void enableWarn(String target){
		LogManager.getLogger(target).setLevel(Level.WARN);
	}

	public static void enableError(String target){
		LogManager.getLogger(target).setLevel(Level.ERROR) ;
	}

	public static void enableDebug(String target){
		LogManager.getLogger(target).setLevel(Level.DEBUG) ;
	}
	
}
