/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.util.ip;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.xuehuiit.jee.common.util.JsonUtil;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class IpUtil {
	
	
	public static final String TAO_IP_URL = "http://ip.taobao.com/service/getIpInfo.php?ip=";
	
	
	/**
	 * 
	 * @param ip
	 * @return
	 */
	public  Ip4TaobaoBean getIpInfo4Taobao1(String ip){		
		
		
		Ip4TaobaoBean bean = null;
		
		try {
			
			
			
			
			Document doc;
			doc = Jsoup.connect(TAO_IP_URL+ip).get();
		
			
			Element body= doc.getElementsByTag("body").first();
			
			String code = JsonUtil.getString4Key(body.text(), "code");
			//System.out.println( body.text() ); 
			
			if( null != code && code.equals("0")  ){
				
				Gson gson = new Gson();
				bean = gson.fromJson(body.text(), Ip4TaobaoBean.class);
			}else if( null != code && code.equals("1")){
				bean = new Ip4TaobaoBean();
				bean.setCode("1");
				
			}
			
			
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return bean;
		
	}
	
	/**
	 * 
	 * @param ip
	 * @return
	 */
	public static Ip4TaobaoBean getIpInfo4Taobao(String ip){		
		
		
		Ip4TaobaoBean bean = null;
		
		try {
			
			
			
			
			Document doc;
			doc = Jsoup.connect(TAO_IP_URL+ip).get();
		
			
			Element body= doc.getElementsByTag("body").first();
			
			String code = JsonUtil.getString4Key(body.text(), "code");
			//System.out.println( body.text() ); 
			
			if( null != code && code.equals("0")  ){
				
				Gson gson = new Gson();
				bean = gson.fromJson(body.text(), Ip4TaobaoBean.class);
			}else if( null != code && code.equals("1")){
				bean = new Ip4TaobaoBean();
				bean.setCode("1");
				
			}
			
			
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return bean;
		
	}
	
	
	/**
     * ip地址转成整数.
     * @param ip
     * @return
     */
    public static long ip2long(String ip) {
    	String[] ips = ip.split("[.]");
    	long num =  16777216L*Long.parseLong(ips[0]) + 65536L*Long.parseLong(ips[1]) + 256*Long.parseLong(ips[2]) + Long.parseLong(ips[3]);
    	return num;
    }
    
    /**
     * 整数转成ip地址.
     * @param ipLong
     * @return
     */
    public static String long2ip(long ipLong) {
    	//long ipLong = 1037591503;
    	long mask[] = {0x000000FF,0x0000FF00,0x00FF0000,0xFF000000};
    	long num = 0;
    	StringBuffer ipInfo = new StringBuffer();
    	for(int i=0;i<4;i++){
    		num = (ipLong & mask[i])>>(i*8);
    		if(i>0) ipInfo.insert(0,".");
    		ipInfo.insert(0,Long.toString(num,10));
    	}
    	return ipInfo.toString();
    }

	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		
		System.out.println(long2ip(3419415912L));
		
		//System.out.println(getIpInfo4Taobao("117.32.230.68"));
		
	}
	

}
