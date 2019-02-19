/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.util.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.xuehuiit.jee.common.util.FileUtils;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class CommentFilter {
	
	private static final char TAG1 = '<';      
    
    private static final char TAG2 = '!';      
     
    private static final char TAG3 = '-';      
       
    private static final char TAG4 = '>';    
          
    //斜杠      
    private static final int TYPE_TAG1 = 1;      
          
  
     
    public static char[] del(char[] _target, int _start, int _end) {      
        char[] tmp = new char[_target.length - (_end - _start + 1)];      
        System.arraycopy(_target, 0, tmp, 0, _start);      
        System.arraycopy(_target, _end + 1, tmp, _start, _target.length - _end      
                - 1);      
        return tmp;      
    }      
     
    /**
     * 过来xml中的注释
     * @param _target
     * @return
     */
    public static String delComments(String _target) {      
        int preType = 0;      
        int cur = -1, token = -1;      
        char[] input =  _target.toCharArray();      
        for (cur = 0; cur < input.length; cur++) {      
            if (input[cur] == TAG1) {      
                if(preType == TYPE_TAG1){   
                    preType = TYPE_TAG1;   
                }else if((input[cur+1]==TAG2)&&(input[cur+2]==TAG3)&&(input[cur+3]==TAG3)){   
                    preType = TYPE_TAG1;   
                    token = cur;      
                }   
            } else if(input[cur] == TAG4){   
                if(preType == TYPE_TAG1){   
                    if((input[cur-1] == TAG3)&&(input[cur-2] == TAG3)){   
                        input = del(input, token, cur);      
                        cur = token - 1;      
                        preType = 0;      
                    }   
                }   
            }   
        }   
        return new String(input);      
    }      
     
    
    
    /**
     * 
     * 清理XML文档
     * 
     * @param xml
     * @return
     * 
     * 
     */
    public static String clearnXML(String xml){
    	
    		String clearxml = "";
    	
    	   try {
           	SAXReader reader = new SAXReader();
           	
           	Reader r = new StringReader(xml);
           	//reader.re
           	Document document = reader.read(r);
           	//reader.re
           	//reader.read(arg0)\
           	OutputFormat format = OutputFormat.createCompactFormat();
           	format.setEncoding("utf-8");
           	//System.out, format
           	OutputStream s  = new ByteArrayOutputStream();
           	
           	  StringWriter out = new StringWriter();
           	XMLWriter xmlWriter = new XMLWriter(out,format);
   			
           	
           	xmlWriter.write(document);  
            xmlWriter.flush();  
            clearxml = out.toString();
            
   		} catch (DocumentException e) {
   			e.printStackTrace();
   		} catch (UnsupportedEncodingException e) {
   			e.printStackTrace();
   		} catch (IOException e) {
   			e.printStackTrace();
   		}
   		
   		
   		return clearxml;
    	
    }
    
    
    
    public static void  main(String[] args){
    	
    	String xml = FileUtils.readFile2String("E:/wingfeng/project/nearmobile/taobao2/server/taobao2_server/taobao2_server/WebRoot/WEB-INF/tpl/login.xml");
    	//String xml = FileUtils.readFile2String("D:/ddddd");
    	try {
			System.out.println(xml.getBytes().length+" " + HtmlCompressor.compress(xml));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	
    }

}
