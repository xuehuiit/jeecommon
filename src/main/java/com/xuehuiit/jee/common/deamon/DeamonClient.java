/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.deamon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class DeamonClient {

	private static int port;
	private static BufferedReader commandLine;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if( null==args || args.length==0 )
			port = 8898;
		else
			port = Integer.valueOf(args[0]).intValue();
		
		commandLine();
		
	}
	
	
	public static void commandLine(){
		
		Socket client;
		PrintWriter clientOut = null;
		BufferedReader clientIn = null;
		
		
		
		try{
			
			
			//1、定义socket端口，获取网络资源
			client = new Socket(InetAddress.getLocalHost(), port);
		    // client = new Socket("192.168.0.40", socktint);	
			//2、定义输出字符流,该字符流的通过socke端口和远程服务器进行通信
			 clientOut = new PrintWriter(client.getOutputStream());
			//3、定义输入字符流，该字符流可以接受服务端的反馈
			clientIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//4、定义客户端命令处理
			//commandLine = new BufferedReader(new InputStreamReader(System.in));
			
			
			//5、发送命令
			clientOut.print(COMMAND_CLIENTLEAVE);
			clientOut.flush();
			
			
			   
		   if( null!= clientOut )
				clientOut.close();
		   if( null!= clientIn )
				clientIn.close();
		   if( null!= client )
				client.close();
			
			
		}catch (ConnectException connEx){
			System.out.println("服务器已经关闭");
		}catch (UnknownHostException e) {
			System.out.println("未知的主机名");
		} catch (IOException e) {
			System.out.println("应用程序发生致命错误已经退出");
		}
		
	}
	
	
	public static final String COMMAND_SHUTDOWN = "shutdown";
	public static final String COMMAND_CLIENTLEAVE = "shutdown";
	
	/**
     * 
     * @param clientOut
     * @param clientIn
     * @param command
     * @return    close if return false else continue     
	 * @throws IOException 
     */
	public static boolean operatorCommand(PrintWriter clientOut ,
			                             BufferedReader clientIn , 
			                             String command) throws IOException{
		
		String reponse;
		boolean result = true;
		boolean flag = true;
		String commandFile;
		
		if( "end".equals(command) ){
			clientOut.print(COMMAND_CLIENTLEAVE);
			clientOut.flush();
			result = false;
		}else if( "shutdown".equals(command) ){
			clientOut.println(COMMAND_SHUTDOWN);
			clientOut.flush();
				
			flag = true;
			
			while(flag){
			
				reponse = clientIn.readLine();
			    if( "success".equals(reponse) ){
			    	flag = false;
			    }
				
			}
		   result = false;
		
			
		}else if( "batchfile".equals(command) ){
			
			
			System.out.println("plealse set command file");
			flag = true;
			//commandFileDir = AreBackgroundConstant.getCommandFileDir();
			
			

				while (flag) {
					
					commandFile = commandLine.readLine();
					
					
					if(null!= commandFile  &&"end".equals(commandFile)){
						result = true;
						flag = false;
					}
					
						
				}

			
				
			//result = true;
		}
		
		return result;
		
	}

	

}
