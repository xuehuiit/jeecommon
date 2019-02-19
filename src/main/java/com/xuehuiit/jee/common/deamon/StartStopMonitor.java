/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.deamon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;


/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
abstract public class StartStopMonitor implements Runnable {

	private static Logger log = Logger.getLogger(StartStopMonitor.class);	
	private int port;
	private String str;
	private ServerSocket server;
	private Socket socket;
	private boolean flag = true;
	private BufferedReader serverIn = null;
	private PrintWriter serverOut = null;
	private String clientRequestStr;
	private boolean batchflag = true;
	private String commandString;
	private boolean Serverflag = true;
	
	
	public StartStopMonitor(int listenSocket){
		
		port = listenSocket;
		
	}
	
	/**
	 * @throws IOException
	 */
	private void closeServer() throws IOException {
		if( null != serverIn )
			serverIn.close();
		if( null != serverOut )
			serverOut.close();
		if( null != socket )
			socket.close();
		
		Serverflag = false;
	}
	
	
	public void run() {
	
		try {
			
			
			server = new ServerSocket(port);
			
			while (Serverflag) {

			
				
				socket = server.accept();

				if (null != socket) {

						serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						serverOut = new PrintWriter(socket.getOutputStream());

						clientRequestStr = serverIn.readLine();

						if (null != clientRequestStr	&& "close".equals(clientRequestStr)) {
							
							serverOut.println("success");
							serverOut.flush();
							flag = false;
							Serverflag = false;
							closeServer();

							server.close();

						} else if (null != clientRequestStr && "shutdown".equals(clientRequestStr)) {
							flag = false;
							closeServer();
							
						}else{
							
							System.out.println("clientRequestStr");
							serverOut.println("收到");
							serverOut.flush();
							Serverflag = false;
							
						}

					}// end while(flag)

				}//end if
			    
				
				closeServer();
				opter(clientRequestStr);
				
			
		} catch (NumberFormatException numberEx) {
			log.error("端口格式错误",numberEx);
		} catch (IOException ioEx) {
			log.error("服务器不能连接",ioEx);
		}

		
		
		
	}
	
	
	abstract public void opter(String command);
	
	
	
	public static void main(String[] args){
		
	/*	StartStopMonitor Runnable = new StartStopMonitor(8898);
		Thread thread1 = new Thread(Runnable, "StartStopThread");  
		thread1.start();*/
		
	}
	
	
	

}
