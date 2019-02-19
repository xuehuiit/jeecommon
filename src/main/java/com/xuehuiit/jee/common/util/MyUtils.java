package com.xuehuiit.jee.common.util;

//import com.opensymphony.util.TextUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author 边缘孤客edgeloner@163.com
 * 
 *  
 */
public class MyUtils extends org.apache.commons.lang.StringUtils {
	/**
	 * DOCUMENT ME!
	 * 
	 * @param str
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static boolean isBlank(final String str) {
		return (str == null) || (str.trim().length() <= 0);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param objs
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static boolean isBlank(final Object[] objs) {
		return (objs == null) || (objs.length <= 0);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param obj
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static boolean isBlank(final Collection obj) {
		return (obj == null) || (obj.size() <= 0);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param obj
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static boolean isBlank(final Set obj) {
		return (obj == null) || (obj.size() <= 0);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param obj
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static boolean isBlank(final Serializable obj) {
		return obj == null;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param obj
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static boolean isBlank(final Map obj) {
		return (obj == null) || (obj.size() <= 0);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param dist
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static String htmlEncode(String dist) {
		//dist = TextUtils.htmlEncode(dist);
		dist = replace(dist, "\r\n", "<br>");
		dist = replace(dist, "\n", "<br>");

		return dist;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param arg0
	 *            DOCUMENT ME!
	 * @param arg1
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static String join(final String arg0, final Collection arg1) {
		//return TextUtils.join(arg0, arg1);
		return "test";
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param args
	 *            DOCUMENT ME!
	 */
	public static void main(final String[] args) {
		int i = 0;
		String aa = "<br><table><script>/r/n</script>";
		System.out.println("a" + aa);

		//System.out.println("d" + TextUtils.htmlEncode(aa));
	}

	/**
	 * convert string to md5
	 * 
	 * @param str
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static String toMD5(final String str) {
		return MD5.toMD5(str);

		//linux下常有不同结果故弃之
		//return CryptTool.md5Digest(str);
	}
 
}