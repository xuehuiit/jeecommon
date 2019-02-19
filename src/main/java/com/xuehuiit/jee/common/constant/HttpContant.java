/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.constant;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class HttpContant {

	
	
	public static String getContant4Server(String url){
		
		String config = null;
		
		
		
		try {
			
			
			
			Document doc;
			doc = Jsoup.connect(url).get();
			Element body= doc.getElementsByTag("doimf").first();
			
			
			config = body.toString();
			
			//System.out.println(doc.toString());
			
		}  catch (IOException e) {
			e.printStackTrace();
		} finally {

			//httpclient.getConnectionManager().shutdown();
		}
		
		return config;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String s = getContant4Server("http://localhost/loveconfig/index.php/cfg/getcfg?id=5");
		CommonConstant.iniCommonConstant4String(s);
		System.out.println(s);
		
	}

}
