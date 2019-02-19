/**
 * 
 */
package com.xuehuiit.jee.common.util;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.xuehuiit.jee.common.util.url.Request;

/**
 * HttpRequestParser工具类
 * @author yy
 * @date Jun 21, 2009 2:13:25 PM
 */
public class UrlUtils {
	
	
	public static void main(String[] args){
		System.out.print(getHost4Url("http://item.taobao.com/item.htm?id=16266314807"));
		
	}
	
	public static String getHost4Url(String url){
		
		Pattern p = Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
		java.util.regex.Matcher matcher = p.matcher(url);
		matcher.find();
		String host = matcher.group();
		
		return host;
	}
	
	
  /**
   * 分析url字符串,采用utf-8解码
   * @param urlString
   * @return
   */
  public static Request parse(String urlString) {
    return parse(urlString, "utf-8");
  }

  /**
   * 分析url字符串,指定字符集进行解码
   * @param urlString
   * @param enc
   * @return
   */
  public static Request parse(String urlString, String enc) {
    if (urlString == null || urlString.length() == 0) {
      return new Request();
    }
    int questIndex = urlString.indexOf('?');
    if (questIndex == -1) {
      return new Request(urlString);
    }
    String url = urlString.substring(0, questIndex);
    String queryString = urlString.substring(questIndex + 1, urlString.length());
    return new Request(url, getParamsMap(queryString, enc));
  }

  private static Map<String, String[]> getParamsMap(String queryString, String enc) {
    Map<String, String[]> paramsMap = new HashMap<String, String[]>();
    if (queryString != null && queryString.length() > 0) {
      int ampersandIndex, lastAmpersandIndex = 0;
      String subStr, param, value;
      String[] paramPair, values, newValues;
      do {
        ampersandIndex = queryString.indexOf('&', lastAmpersandIndex) + 1;
        if (ampersandIndex > 0) {
          subStr = queryString.substring(lastAmpersandIndex, ampersandIndex - 1);
          lastAmpersandIndex = ampersandIndex;
        } else {
          subStr = queryString.substring(lastAmpersandIndex);
        }
        paramPair = subStr.split("=");
        param = paramPair[0];
        value = paramPair.length == 1 ? "" : paramPair[1];
        try {
          value = URLDecoder.decode(value, enc);
        } catch (UnsupportedEncodingException ignored) {
        }
        if (paramsMap.containsKey(param)) {
          values = paramsMap.get(param);
          int len = values.length;
          newValues = new String[len + 1];
          System.arraycopy(values, 0, newValues, 0, len);
          newValues[len] = value;
        } else {
          newValues = new String[] { value };
        }
        paramsMap.put(param, newValues);
      } while (ampersandIndex > 0);
    }
    return paramsMap;
  }

  /**
   * 请求对象
   * @author yy
   * @date Jun 21, 2009 2:17:31 PM
   */

}
