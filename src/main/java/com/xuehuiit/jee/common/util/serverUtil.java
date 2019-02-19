/*
 * Created on 2004-12-12
 * version 1.0
 * 
 */
package com.xuehuiit.jee.common.util;

/*import javax.naming.Context;*/
// import jdk API
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.xuehuiit.jee.common.exception.DemoSysException;

/**
 * Get the server JNDI property from server.properties file
 * 
 * @author wing.Feng
 */
public class serverUtil {

	private static String CONTEXT_INITIAL_CONTEXT_FACTORY = "java.naming.factory.initial";

	private static String CONTEXT_PROVIDER_URL = "java.naming.provider.url";

	private static String CONTEXT_SECURITY_PRINCIPAL = "java.naming.security.principal";

	private static String CONTEXT_SECURITY_CREDENTIALS = "java.naming.security.credentials";
	
	private static String SERVER_TYPE = "server.type";

	private static Properties properties = new Properties();

	private static String PROP_NAME = "server";

	//logger
	private static Logger logger = Logger
			.getLogger(com.xuehuiit.jee.common.util.serverUtil.class);

	static {
		try {
			ResourceBundle rb = ResourceBundle.getBundle(PROP_NAME);
			for (Enumeration en = rb.getKeys(); en.hasMoreElements();) {
				String key = (String) en.nextElement();
				properties.put(key, rb.getObject(key));
			}
		} catch (Throwable t) {
		    System.out.println("not find server.properties!");
			logger.error("load server properties file error!", t);
		}
	}
	
	/**
	 * Get application server config
	 * @return
	 * @throws Exception
	 * 
	 */
	public static Hashtable getjndiProperties() throws Exception {
		Hashtable jndiProperties = new Hashtable();
		String serverType = null;
		String key = null;
		String value = null;
		serverType = properties.getProperty(SERVER_TYPE);

		if ((null == serverType) && (serverType.length() < 0))
			throw new Exception("Not find application server type!");

		if ("weblogic".equals(serverType)) {
			jndiProperties.put("weblogic.jndi.createIntermediateContexts",
					new Boolean(true));

		} else if ("jboss".equals(serverType)) {
			jndiProperties.put("java.naming.factory.url.pkgs",
					"org.jboss.naming:org.jnp.interfaces");
		}

		//set url
		int port = 0;
		key = serverType + ".port";
		value = properties.getProperty(key, "");
		if (!value.equals("")) {
			try {
				port = Integer.parseInt(value);
			} catch (NumberFormatException ne) {
				throw new Exception("this port is not a number", ne);
			}
		}
		String url = null;
		key = serverType + ".url";
		value = properties.getProperty(key, "");

		if (value.equals("")) {
			throw new Exception("url is not find");
		} else {
			if ("sunri".equals(serverType)) {
				jndiProperties.put("org.omg.CORBA.ORBInitialHost", value);
				jndiProperties.put("org.omg.CORBA.ORBInitialPort", String
						.valueOf(port));
			} else {
				url = value + ":" + String.valueOf(port);
				jndiProperties.put(CONTEXT_PROVIDER_URL, url);
			}
		}

		// set initialfactory
		key = serverType + ".initialfactory";
		value = properties.getProperty(key, "");
		if (!value.equals("")) {
			jndiProperties.put(CONTEXT_INITIAL_CONTEXT_FACTORY, value);
		}

		if (!"jboss".equals(serverType)) {
			//set username

			key = serverType + ".username";
			value = properties.getProperty(key, "");
			if (!value.equals("")) {
				jndiProperties.put(CONTEXT_SECURITY_PRINCIPAL, value);
			}

			//set passward
			key = serverType + ".password";
			value = properties.getProperty(key, "");
			if (!value.equals("")) {
				//System.out.println(value);
				jndiProperties.put(CONTEXT_SECURITY_CREDENTIALS, value);
			}

		}

		return jndiProperties;
	}
	/**
	 * 
	 * @return
	 * 
	 * @ejb.interface-method 
	 * @ejb.transaction type="Required"
	 */
	public static String getHibernateConfigPath()
	{
	    String hibernateConfFile = null;
	    hibernateConfFile = properties.getProperty("hibernate.path");
	    if((null==hibernateConfFile)&& (hibernateConfFile.length()<0))
	    {    
	        logger.error("Not find hibernate config file");
	        throw new DemoSysException("Not find hibernate config file");
	    }
	    return hibernateConfFile;
	}
	 
	//test this class
	public static void main(String[] args) {

		/*
		 * System.out.println(Context.INITIAL_CONTEXT_FACTORY);
		 * System.out.println(Context.PROVIDER_URL);
		 * System.out.println(Context.SECURITY_PRINCIPAL);
		 * System.out.println(Context.SECURITY_CREDENTIALS);
		 */

		String key = null;
		String value = null;
		Object obj = null;
		try {
			Hashtable ht = getjndiProperties();
			
			for (Enumeration en = ht.keys(); en.hasMoreElements();) {
				key = (String) en.nextElement();
				obj = ht.get(key);
				if (obj instanceof String) {
					value = (String)obj;
					System.out.println(key + "=" + value);
				}
			}
			
			System.out.println(getHibernateConfigPath());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}