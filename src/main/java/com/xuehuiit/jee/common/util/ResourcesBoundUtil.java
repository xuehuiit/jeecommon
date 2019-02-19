/*
 * Created on 2005-5-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.xuehuiit.jee.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author fx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResourcesBoundUtil {
    
    //logger
    private static Logger logger = Logger
            .getLogger(com.xuehuiit.jee.common.util.ResourcesBoundUtil.class);

    
    /**
     * Get the key from value.
     * 
     * @param proFileName   the properties resources file.
     * @param value         the value of the key.
     * @return              the key.
     */
    public static String getKeyFromValue(String proFileName,String value){  
        return resourceBound(proFileName).getProperty(value);
    }
    
    
    /**
     * Get the value from key 
     * 
     * @param proFileName  the properties resource file.
     * @param key          the key of the value.
     * @return             the value
     */
    public static String getValueFormKey(String proFileName,String key){
        
        return resourceBound(proFileName).getProperty(key);
    }
    
    
    /**
     * Bound resource from calss path.
     * 
     * @param proFileName   the properties resource file name.
     * @return              the value and key's map.
     */
    private static Properties resourceBound(String proFileName){
        
    	Properties prop = new Properties();
    	
    	 InputStream is = new ResourcesBoundUtil().getClass().getResourceAsStream(proFileName);
    	 try {
			prop.load(is);
			if(is!=null)
	    	    is.close();
		} catch (IOException e) {
			logger.error(e);
		}

        return prop;
    }
    
    
    /**
     * 
     * @param fileName
     */
    public static Element paserXml(String fileName,String moduleName){
    	
    	 Element rootElement = null;
    	 Element element;
         String modulename;
    	try{
    	 SAXReader oRead = new SAXReader();
         Document oDocument = oRead.read(fileName);
         //System.out.println(oDocument.asXML());
         //Element rootElement = oDocument.getName();
         rootElement = oDocument.getRootElement();
         Iterator elements = rootElement.elementIterator();
        
         while( elements.hasNext())
         {
        	 element = (Element)elements.next();
        	 modulename = element.attributeValue(APPCONTEXT_CONFIG_FILE);
        	 if (moduleName.equals(modulename))
        	 {
        		 rootElement = element;
        		 break;
        	 }
         }
         
         
         
    	}catch(Exception ex){
    		ex.printStackTrace();
    		logger.error(ex);
    	}
    	
    	 return rootElement;
         
    }
    
    
    /**
     * 
     * @param fileName
     */
    public static Element paserXml4String(String fileName,String moduleName){
    	
    	 Element rootElement = null;
    	 Element element;
         String modulename;
    	try{
    	
    		StringReader myStringReader = new StringReader(fileName);
    		
    	 SAXReader oRead = new SAXReader();
         Document oDocument = oRead.read(myStringReader);
         //System.out.println(oDocument.asXML());
         //Element rootElement = oDocument.getName();
         rootElement = oDocument.getRootElement();
         Iterator elements = rootElement.elementIterator();
        
         while( elements.hasNext())
         {
        	 element = (Element)elements.next();
        	 modulename = element.attributeValue(APPCONTEXT_CONFIG_FILE);
        	 if (moduleName.equals(modulename))
        	 {
        		 rootElement = element;
        		 break;
        	 }
         }
         
         
         
    	}catch(Exception ex){
    		ex.printStackTrace();
    		logger.error(ex);
    	}
    	
    	 return rootElement;
         
    }
    
    
    public static final String APPCONTEXT_CONFIG_FILE = "name";
    
    
    
}
