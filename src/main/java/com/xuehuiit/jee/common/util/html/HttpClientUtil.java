/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.util.html;


/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class HttpClientUtil {

	
	/**
	 * 
	 * 通过get方法请求URL内容
	 * 
	 * @param url
	 * @param encoding
	 * @return
	 */
	public static String getHtml4Get(String url , String charset){
		
		   /* DefaultHttpClient httpclient = new DefaultHttpClient();
	        HttpProtocolParams.setUserAgent(httpclient.getParams(),
	                "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.9) Gecko/20100315 Firefox/3.5.9");
	        
	        if( null == charset || charset.length() == 0)
	             charset = "UTF-8";
	       
	        
	        HttpGet httpget = new HttpGet();
	        String content = "";
	        try {
	        	
				httpget.setURI(new java.net.URI(url));
				 HttpResponse response = httpclient.execute(httpget);
			        HttpEntity entity = response.getEntity();
			        
			        if (entity != null) {
			            //使用EntityUtils的toString方法，传递默认编码，在EntityUtils中的默认编码是ISO-8859-1
			            content = EntityUtils.toString(entity, charset);
			            httpget.abort();
			            httpclient.getConnectionManager().shutdown();
			        }
			       
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	       
			 return content;*/
		
		return "";
		
	}
	
	
}
