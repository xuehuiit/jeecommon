/*
 * Created on 2004-12-25
 *
 * TODO
 */
package com.xuehuiit.jee.common.util;

//import jdk API
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
/**
 * @author Wing.Feng
 *
 * TODO 
 */
public class webContentUtil {

    private static Logger logger = Logger.getLogger(webContentUtil.class);
    private static final String CFGFILENAME="webcontent";
    private static String webroot=null;
    private static String servletroot=null;
    static
    {
       try{
           ResourceBundle rb = ResourceBundle.getBundle(CFGFILENAME);
           webroot = rb.getString("webroot");
           servletroot = rb.getString("servletroot");    
       }catch(Throwable tb)
       {
           logger.error("Not find webcontent.properties or not find key",tb);
       }
        
        
    }
    
    public static String getWebroot()
    {
        return webroot;
    }
    
    public static String getServlet()
    {
        return servletroot;
    }
    
    /**
     * Test this class
     * @param args
     * 
     */
    public static void main(String[] args) {
        System.out.println(webContentUtil.getServlet());
        System.out.println(webContentUtil.getWebroot());
        }
}
