/*
 * Created on 2004-12-24
 *
 * TODO
 */
package com.xuehuiit.jee.common.util;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * @author Wing.Feng
 *
 * TODO 
 */
public class DatabaseUtil {

    private static final String DBCFGFILENAME = "Database";
    private static Properties properties = null;
    private static ResourceBundle rb = null;
    private static String TranscationJndi = null;
    private static String MassTransactionTimeout = null;
    private static Logger logger = Logger.getLogger(DatabaseUtil.class);
    
   static
    {
       
        try{
            ResourceBundle rb = ResourceBundle.getBundle(DBCFGFILENAME); 
            TranscationJndi = rb.getString("transcation_jndi");
            MassTransactionTimeout = rb.getString("massTransactionTimeout");
        }catch(Throwable ta)
        {
            logger.error("Not find file Database.properties ",ta);
        }
      
    }
    
    public static String getTranscationJndi()
    {   
      
        return TranscationJndi;
    }
    
    public static String getMassTransactionTimeout()
    {
     
        return MassTransactionTimeout;
    }
    
    
    
    public static void main(String[] args) {
        
        System.out.println(DatabaseUtil.getTranscationJndi());
        System.out.println(DatabaseUtil.getMassTransactionTimeout());
    }
}
