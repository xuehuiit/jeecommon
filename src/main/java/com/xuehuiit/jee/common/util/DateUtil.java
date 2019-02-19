/*
 * Created on 2004-12-19
 * Version 1.0
 */
package com.xuehuiit.jee.common.util;

// import jdk API
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.xuehuiit.jee.common.util.exception.DateUtilException;
import org.apache.log4j.Logger;

/**
 * 时间操作的帮助类
 * @author <a href="mail:fx19800215@163.com"> Wing.Feng </a>
 */
public class DateUtil {
	/**
	 * Logger for this class 
	 */
	private static final Logger logger = Logger.getLogger(DateUtil.class);
	
	public static final long millisInDay = 86400000;
	
	private static String datePattern = "yyyy-MM-dd";

	public static String DATETIMEPATTERN = "yyyy-MM-dd/HH:mm:ss";
	
	public static String DATETIMEPARTTERS = "yyyy-MM-dd/HH:mm:ss.S";
	
	private static String timePattern = "HH:mm";

	private static String chineseDatePattern = "TB(A)年L月U";
	
    private static String chineseTimePattern = "HH时";
	
	private static long currentTimeMillis = System.currentTimeMillis();

	private final static String[][] weekName = {
			{ "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" },
			{ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
					"Saturday" } };

	
	public static String getDefaultDateString(Date date){
		return DateUtil.getDate2FormatString(date, "yyyy-MM-dd HH:mm:ss.S");
	}

	
	public static String getDefaultDateString(){
		return DateUtil.getDate2FormatString(new Date(), "yyyy-MM-dd HH:mm:ss.S");
	}
	
	
	/**
	 * Get string of date value from user format
	 * 
	 * @param userDate
	 *            date what is be formated
	 * @param formatString
	 *            date format character string
	 * @return
	 */
	public static String getDate2FormatString(Date userDate, String formatString) {

		StringBuffer dateformatstring = new StringBuffer();

		// check formatString whether null
		if ((null == formatString) && (formatString.length() < 0))
			throw new DateUtilException(
					"Function getDate2FormatString error : not find date format");

		// check userDate whether
		if (null == userDate)
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
		dateFormat.format(userDate, dateformatstring, new FieldPosition(0));

		return dateformatstring.toString();
	}

	/**
	 * Get date from format character string
	 * 
	 * @param str
	 *            The date string
	 * @param dtfm
	 *            The date formateString
	 * @return
	 * 
	 */
	public static Date getFormatString2Date(String str, String dtfm) {

		Date date = null;

		if ((null == dtfm) || (dtfm.length() < 0))
			throw new DateUtilException(
					"Function getDate2FormatString error : not find date format");

		if ((null == str) && (str.length() < 0))
			return null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(dtfm);

		try {
			date = dateFormat.parse(str);
		} catch (java.text.ParseException pe) {
			// logger.error (pe);
		}
		return date;
	}

	/**
	 * 
	 * @param dt
	 * @return
	 */
	public static String dateToString(Date dt) {
		if (dt == null)
			return "";

		return dateToString(dt, "yyyy-MM-dd");
	}

	/**
	 * 
	 * @param fmt java Date and Time Patterns
	 */
	public static String dateToString(String fmt) {
		return dateToString(fmt, Locale.US);
	}

	/**
	 * 
	 * @param fmt java Date and Time Patterns
	 */
	public static String dateToString(String fmt, Locale lo) {
		Date date = new Date();
		return dateToString(date, fmt, lo);
	}

	/*
	 * @param fmt java Date and Time Patterns
	 */
	public static String dateToString(Date dt, String fmt) {
		if (dt == null)
			return "";

		return dateToString(dt, fmt, Locale.US);
	}

	/**
	 * 
	 * @param fmt java Date and Time Patterns
	 */
	public static String dateToString(Date dt, String fmt, Locale lo) {
		if (dt == null)
			return "";

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(fmt, lo);

		String retStr = "";
		try {
			return new String(sdf.format(dt, new StringBuffer(),
					new java.text.FieldPosition(0)));
		} catch (NullPointerException ne) {
			logger.error(ne);
			return "";
		}
	}

	public static Date stringToDate(String str) {
		if (null == str)
			return null;

		return stringToDate(str, "yyyy-MM-dd");
	}

	/**
	 * 
	 * @param fmt java Date and Time Patterns
	 */
	public static Date stringToDate(String str, String fmt) {
		if (null == str)
			return null;

		return stringToDate(str, fmt, Locale.US);
	}
	
	/**
	 * 
	 * @param fmt java Date and Time Patterns
	 */
	public static Date ToDate(Date strdate, String fmt) {
		if (null == strdate)
			return null;
        String str =   dateToString(strdate, fmt);
		
		return stringToDate(str, fmt, Locale.CHINESE);
	}

	/**
	 * 
	 * @param fmt java Date and Time Patterns
	 */
	public static Date stringToDate(String str, String fmt, Locale lo) {
		if (null == str)
			return null;

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(fmt, lo);
		try {
			return sdf.parse(str);
		} catch (java.text.ParseException pe) {
			logger.error(pe);
		}

		return null;
	}

	/**
	 * 
	 * @param dt
	 * @return
	 */
	public static Date beginOfDate(Date dt) {
		int hour = 0;
		int minute = 0;
		int second = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		return cal.getTime();
	}

	/**
	 * 
	 * @param dt
	 * @return
	 */
	public static Date endOfDate(Date dt) {
		int hour = 23;
		int minute = 59;
		int second = 59;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		return cal.getTime();
	}

	/**
	 * 
	 * @param dt
	 * @return
	 */
	public static java.sql.Timestamp beginOfTimestamp(Date dt) {
		Date date = beginOfDate(dt);

		return new java.sql.Timestamp(date.getTime());
	}

	/**
	 * 
	 * @param dt
	 * @return
	 */
	public static java.sql.Timestamp endOfTimestamp(Date dt) {
		Date date = endOfDate(dt);

		return new java.sql.Timestamp(date.getTime());
	}

	/**
	 * @return the date encoded in the format based on locale
	 */
	public static String getFullSqlDateString(Date date, Locale locale) {
		return dateToString(date, "yyyy-MM-dd/HH:mm:ss", locale);
	}
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String getFullSqlDateString(Date date) {
		return dateToString(date, "yyyy-MM-dd/HH:mm:ss");
	}
	
	/**
	 * 
	 * @param date
	 * @param locale
	 * @return
	 */
	public static String getShortSqlDateString(Date date, Locale locale) {
		return dateToString(date, "yyyy-MM-dd", locale);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String getShortSqlDateString(Date date) {
		return dateToString(date, "yyyy-MM-dd");
	}

	/**
	 * 格式化输出日期
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return 返回字符型日期
	 */
	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new java.text.SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static String format(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 返回年份
	 * 
	 * @param date
	 *            日期
	 * @return 返回年份
	 */
	public static int getYear(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}

	/**
	 * 返回月份
	 * 
	 * @param date
	 *            日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}

	/**
	 * 返回月份
	 * 
	 * @param date
	 *            日期
	 * @return 返回月份
	 */
	public static String getFormatMonth(Date date) {
		String month;
		int months;
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		
		months = getMonth(date);
		if( months < 10 )
			month = "0"+String.valueOf(months);
		else
			month = String.valueOf(months);
		
		return month;
	}
	
	/**
	 * 返回日份
	 * 
	 * @param date
	 *            日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 返回字符型日期
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期
	 */
	public static String getDate(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 返回字符型时间
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型时间
	 */
	public static String getTime(Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * 返回字符型日期时间
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期时间
	 */
	public static String getDateTime(Date date) {
		return format(date, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 日期相加
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}
	/**
	 * 日期相加（小时）
	 * 
	 * @param date
	 *            日期
	 * @param hour
	 *            小时
	 * @return 返回相加后的日期
	 */
	public static Date addDateHour(Date date, int hour) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) hour)  * 3600 * 1000);
		return c.getTime();
	}
	
	/**
	 * 日期相加（分钟）
	 * 
	 * @param date
	 *            日期
	 * @param minutes
	 *            分钟
	 * @return 返回相加后的日期
	 */
	public static Date addDateMinutes(Date date, int minutes) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) minutes) * 60 * 1000);
		return c.getTime();
	}
	
	/**
	 * 日期相加（秒）
	 * 
	 * @param date
	 *            日期
	 * @param Sec
	 *            秒钟
	 * @return 返回相加后的日期
	 */
	public static Date addDateSecond(Date date, int Sec) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) Sec)  * 1000);
		return c.getTime();
	}
	/**
	 * 日期相减
	 * 
	 */
	public static Date deffDate(Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	} 
	/**
	 * 日期减去分
	 * 
	 */
	public static Date deffMinute(Date date, int Mit) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - ((long) Mit)  * 60 * 1000);
		return c.getTime();
	} 
	/**
	 * 日期减去秒
	 * 
	 */
	public static Date deffSecond(Date date, int Sec) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - ((long) Sec) * 1000);
		return c.getTime();
	} 

	/**
	 * 日期相减
	 * 
	 * @param date
	 *            日期
	 * @param date1
	 *            日期
	 * @return 返回相减后的天数
	 */
	public static int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}
	
	/**
	 * 日期相减
	 * 
	 * @param date
	 *            日期
	 * @param date1
	 *            日期
	 * @return 返回相减后的小时
	 */
	public static int diffDateHour(Date date, Date date1) {
		return (int) ((getMillis(date)- getMillis(date1)) / (3600 * 1000)); 
	}
	/**
	 * 
	 * @param date
	 * @param date1
	 * @return 返回相减后的分钟
	 */
	public static int diffMinutes(Date date, Date date1)
	{
		return (int) ((getMillis(date) - getMillis(date1))/(60*1000));  

	}
	/**
	 * 
	 * @param date
	 * @param date1
	 * @return 返回相减后的秒
	 */
	public static int diffSeconds(Date date, Date date1)
	{
		return (int)((getMillis(date)- getMillis(date1))/1000); 

	}
	
	/**
	 * 日期相减
	 * 
	 * @param date
	 *            日期
	 * @param date1
	 *            日期
	 * @return 返回相减后的日期
	 */
	public static int diffDateMS(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)));
	}
	
	/**
	 * 
	 * @param day
	 * @return
	 */
	public static Date getScheduleOfDay(Date day) {
        return getScheduleOfDay(day,Calendar.getInstance());
    }
    
    /**
     * 
     * @param day
     * @param cal
     * @return
     */
    public static Date getScheduleOfDay(Date day,Calendar cal) {
        if (day == null) day = new Date();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE,      cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND,      cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }
    
    
    /**
     * 
     * @param date
     * @return
     */
    public static String getTheWeekDay(Date date){
    	SimpleDateFormat formatweek =  new SimpleDateFormat("E");
    	return formatweek.format(date);
    }
    
    /**
     * get the last month date.
     * @param date
     * @param months
     * @return
     */
    public static Date getLastMonthDate(Date date , int months){
    	
    	Calendar   cal   =   Calendar.getInstance();   
    	cal.add(Calendar.MONTH,   -1   );   
    	return cal.getTime();
    	
    }
    
    
    /**
     * get the last month date.
     * @param date
     * @param months
     * @return
     */
    public static Date getLastMonthDate(Date date){
    	
    	Calendar   cal   =   Calendar.getInstance();   
    	cal.add(Calendar.MONTH , 1 );
    	
    	String dates = dateToString(cal.getTime(), "yyyy-MM")+"-01";
    	Date lastDay = addDate(stringToDate(dates, "yyyy-MM-dd"), -1);
    	
    	
    	return lastDay;
    	
    }
    
    /**
     * get the last month date.
     * @param date
     * @param months
     * @return
     */
    public static Date addMonth(int months){
    	
    	Calendar   cal   =   Calendar.getInstance();   
    	cal.add(Calendar.MONTH,  months   );   
    	return cal.getTime();
    	
    }
    
    public static String getFullDateTime(Date date){
    	return format(date,"yyyy-MM-dd HH:mm:ss");
    }
    
    
    
    /**
     * 增加一个月后的时间
     * get the last month date.
     * @param date
     * @param months
     * @return
     */
    public static Date addMonth(Date date,int months){
    	
    	Calendar currentCalendar= Calendar.getInstance();
    	
    	currentCalendar.setTime(date);
    	currentCalendar.add(Calendar.MONTH, months);
    	Date addDateMonth = currentCalendar.getTime();
    	
    	return addDateMonth;
    	
    }
    
    /**
	 * 获取指定日期是星期几
	 * @param date
	 * @return
	 */
	public static int getWeekDay(Date date){
		
		Calendar currentCalendar;
		
		//将Date对象转化成Calendar对象以便于计算
		currentCalendar = Calendar.getInstance();
		int week ;
		currentCalendar.setTime(date);
		//--------------------------------------------------------------
		//取得当前日期是星期几
		//
		//week的值是一个数字为1-7 其中 1代表星期天，依次类推7代表星期六
		//根据西方的习惯，一周时从星期天开始的。
		//---------------------------------------------------------------
		week = currentCalendar.get(Calendar.DAY_OF_WEEK);
		
		return week;
		
	}
	
	
	public static int getWeekOfDate(Date dt) {
        //String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		int[] weekDays = {7,1,2,3,4,5,6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

	
	/**
	 * 指定时间加上分钟后的时间
	 * @return
	 */
	public static Date addMin(Date date , int mint){
		
		Calendar currentCalendar = Calendar.getInstance();;
		currentCalendar.setTime(date);
		currentCalendar.add(Calendar.MINUTE,mint);
		Date addDateM = currentCalendar.getTime();
		
		return addDateM;
		
		
	}
	
	
	/**
	 * 获取当前日期是所在月的第几周
	 * @param date
	 * @return
	 */
	public static int getWeekNum(Date date){
		
		int weeks;
		SimpleDateFormat formatweek =  new SimpleDateFormat("W");
		weeks = Integer.valueOf( formatweek.format(date));
		
		return weeks;
		
	}
	
	
	/**
	 * 获取当前日期是所在年的第几周
	 * @param date
	 * @return
	 */
	public static int getWeekYear(Date date){
		
		int weeks;
		SimpleDateFormat formatweek =  new SimpleDateFormat("w");
		weeks = Integer.valueOf( formatweek.format(date));
		
		return weeks;
		
	}
	
	
	/**
	 * 获取当前日期是所在月的第几周
	 * @param date
	 * @return
	 */
	public static int getDayYears(Date date){
		
		int weeks;
		SimpleDateFormat formatweek =  new SimpleDateFormat("D");
		weeks = Integer.valueOf( formatweek.format(date));
		
		return weeks;
		
	}
	
	
    /**
     * 给出一个日期计算给日期所在的周的第一天和第二天
     * @param date
     * @return
     */
	public static Date[] getScheduleweek(Date date){
		
		Date[] currentDate;
		Date   weekFrist;
		Date   weekLast;
		
		int weekNum;
		Date tempDate;
		
		weekNum = DateUtil.getWeekDay(date);
	    
		
		weekFrist = DateUtil.addDate(date, (-1)*(weekNum-1));
		weekLast  = DateUtil.addDate(date, 7-weekNum); 
		
		currentDate = new Date[2];
		
		currentDate[0]=weekFrist;
		currentDate[1]=weekLast;
		
		return currentDate;
		
	}
	
	
	
	/**
	 * 计算末年某周的第一天的日期
	 * @param year 要获得的年
	 * @param week 第几个星期
	 * @param flag 是否是第一天还是最后一天,当为true时返回第一天,false则返回最后一天
	 * @return java.uilt.Date 类型日期
	 * @例如 getDayByWeek(2002,2,true) 返回Tue Jan 08 14:11:57 CST 2002 
	 */
	public static Date getDayByWeek(int year,int week,boolean flag){
		
	  Calendar cal=Calendar.getInstance();
	  cal.set(Calendar.YEAR,year);
	  cal.set(Calendar.WEEK_OF_YEAR,week);
	  
	  if(!flag)
	      cal.setTimeInMillis(cal.getTimeInMillis()+6*24*60*60*1000);
	   
	  return cal.getTime();
	   
	   
	}
	/** 
	 * 字符串日期转换成日期
	* @author liuzailin
	* @param strDate: 
	* 字符串日期 
	* @param pattern: 
	* 转换的格式, 字符串日期要与转换的格式相匹配 
	* @return	返回把字符串日期转换成日期, 如果发生异常返回null 
	*/ 
	public static Date getDate(String strDate, String pattern) { 
	String errMsg = ""; 
	try { 
	SimpleDateFormat sdf = new SimpleDateFormat(); 
	sdf.applyPattern(pattern); 
	Date date = sdf.parse(strDate); 
	
	sdf = null; 
	return date; 
	} catch (Exception e) { 
	 e.printStackTrace();
	} 
	return null; 
	} 
	
	
	public static Date getDatTimediff(Date date , String timeZoneString){
		
        // 获得的formatter
        DateFormat formatter = DateFormat.getDateTimeInstance();
        TimeZone timezone = TimeZone.getTimeZone(timeZoneString);
		//设置DateFormat的时区 
		formatter.setTimeZone(timezone);
        // 获得格式化后的时间
        return DateUtil.stringToDate(formatter.format(date),"yyyy-M-d HH:mm:ss");
		
	}
	
	
	/**
	 * 
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date addYear(Date date , int year){
	
		Calendar currentCalendar;
	
		//将Date对象转化成Calendar对象以便于计算
		currentCalendar = Calendar.getInstance();

		
		//currentDate加上一个月后的日期
		currentCalendar.setTime(date);
		currentCalendar.add(Calendar.YEAR, year);
		Date addDateYear = currentCalendar.getTime();
		
		return addDateYear;
	}
	
	
    
    /**
     * 
     * @param aregs
     */
    public static void main(String[] aregs){
    	
    	long d1 = 1609257600000L;
    	Date d = new Date(d1);
    	
    
    	
    	int f =  diffSeconds(new Date() , stringToDate("20000101010101", "yyyyMMddHHmmss"));
    	System.out.println( getYearWeek() );
    	
    	/*try {
			
    		System.out.println(Base64.encode("Rg32CP0jV2/6g6uYdS70r90mxY8="));
    		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
    	
    	//System.out.println("-----------"+DateUtil.dateToString(getLastMonthDate(new Date())));
    	
    	
    	//System.out.println(DateUtil.stringToDate("2009-01-01 12:11:11", "yyyy-MM-dd HH:mm:ss"));
    	
    	//System.out.println(DateUtil.getDate2FormatString(new Date(), DATETIMEPARTTERS));
    	
    	
    	//System.out.println(getDate2FormatString(addYear(new Date(),-30), "yyyy-MM-dd"));
    	
    	/*Date date = new Date();
    	String month = dateToString(date, "MM");
		String year = String.valueOf(DateUtil.getYear(date));
*/
		//System.out.println(dateToString(new Date(), "yyyyMMddHHmmss"));
		
    	//System.out.println(DateUtil.dateToString(beginOfDate(new Date()),DATETIMEPATTERN));
    	
		//System.out.println(diffDate(new Date(),DateUtil.getFormatString2Date("2007-10-22", "yyyyMMddHHmmss")));
    	//System.out.println(System.currentTimeMillis());
		
    	/*Calendar   cal   =   Calendar.getInstance(); 
    	System.out.println(DateUtil.stringToDate("2007-01-11", "yyyy-MM-dd").getTime());
    	cal.setTimeInMillis(DateUtil.stringToDate("2007-01-11", "yyyy-MM-dd").getTime());
		System.out.println(getDateTime(cal.getTime()));
		*/
		/*Date d1 = new Date();
		for(int i = 0 ; i < 900000030 ; i++){}
		Date d2 = new Date();
		System.out.println(DateUtil.getDate2FormatString(d1, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(DateUtil.getDate2FormatString(d2, "yyyy-MM-dd HH:mm:ss"));
		System.out.println("ddddd "+ diffDateMS(d2,d1)/(1000*60));
*/    	
    	/*
    	String st = TaobaoAPIUtil.MoveDate("yyyy-MM-dd HH:mm:ss", "2011-08-01 02:12:52", -31);
		
    	System.out.println("st:"+st);
		int a = compare_date(st, "2011-08-01 02:12:52");
		System.out.println("result:"+a);*/
    	//Date[] dates = getScheduleweek(new Date()); //getDayByWeek(int year,int week,boolean flag)
    	
    	//System.out.println(DateUtil.getDate2FormatString(dates[0], "yyyy-MM-dd")+" - "+DateUtil.getDate2FormatString(dates[1], "yyyy-MM-dd"));
    	
    	//System.out.println(DateUtil.getDate2FormatString(getDayByWeek(2009,39,true), "yyyy-MM-dd"));
    	//(Date date)
    	//System.out.println(getWeekYear(DateUtil.stringToDate("2009-09-11")));
    	
    	//Date date = DateUtil.stringToDate("2009-09-11/12:30:00", "yyyy-MM-dd/HH:mm:ss");
    	
    	//date = new Date();
    	
    	//System.out.println(DateUtil.getDate2FormatString(date, "yyyy-MM-dd/HH:mm:ss"));
    	
    	/* // 获得的formatter
        DateFormat formatter = DateFormat.getDateTimeInstance();
        TimeZone timezone = TimeZone.getTimeZone("Asia/Tokyo");
		//设置DateFormat的时区 
		formatter.setTimeZone(timezone);
        // 获得格式化后的时间
        String dateTime = formatter.format(date);*/
        
        
        //System.out.println(dateTime);

    	//TimeZone.getDefault()
    	//System.out.println(getDatTimediff(date,"Australia/Adelaide"));
    	
    		
		
    }
    /**
     *  两个日期比较
     * @author liuzailin
     * @param DATE1 
     * @param DATE2
     * @return dt1在dt2前 返回0  dt1在dt2后 返回1  dt1与dt2相等 返回2 格式不对返回 -1
     * 如："1999-12-12 15:21:13"在"1999-12-12 15:21:12"前
     */
    public static int compare_date(String DATE1, String DATE2) {       
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
              //  System.out.println("dt1 在dt2前");
                return 0;
            } else if (dt1.getTime() < dt2.getTime()) {
              //  System.out.println("dt1在dt2后");
                return 1;
            } else if(dt1.getTime()==dt2.getTime())
            {
           //	 System.out.println("dt1与dt2相等");
           	   return 2;
           	 }else {
                return -1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    
    /**
     *  两个日期比较
     * @author liuzailin
     * @param DATE1 
     * @param DATE2
     * @return dt1在dt2前 返回0  dt1在dt2后 返回1  dt1与dt2相等 返回2 格式不对返回 -1
     * 如："1999-12-12 15:21:13"在"1999-12-12 15:21:12"前
     */
    public static int compare_date(Date DATE1, Date DATE2) {       
        
       // DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = DATE1;//df.parse(DATE1);
            Date dt2 = DATE2;//df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
              //  System.out.println("dt1 在dt2前");
                return 0;
            } else if (dt1.getTime() < dt2.getTime()) {
              //  System.out.println("dt1在dt2后");
                return 1;
            } else if(dt1.getTime()==dt2.getTime())
            {
           //	 System.out.println("dt1与dt2相等");
           	   return 2;
           	 }else {
                return -1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    
    
    
    
    /**
     * 取得两个时间段的时间间隔
     * @author color
     * @param t1 时间1
     * @param t2 时间2
     * @return t2 与t1的间隔天数
     * @throws ParseException 
     * @throws ParseException 如果输入的日期格式不是0000-00-00 格式抛出异常
     */
     public static int getBetweenDays(String t1,String t2) throws ParseException {
     DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
     //初始化为-2
     int betweenDays = -2;
     Date d1 = format.parse(t1);
     Date d2 = format.parse(t2);
     Calendar c1 = Calendar.getInstance();
     Calendar c2 = Calendar.getInstance();
     c1.setTime(d1);
     c2.setTime(d2);
     // 保证第二个时间一定大于第一个时间
     if(c1.after(c2)){
    	 return -2;
     }
     int betweenYears = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
     
     
     if(c2.get(Calendar.DAY_OF_YEAR)-c1.get(Calendar.DAY_OF_YEAR)>=0){
    	 betweenDays = c2.get(Calendar.DAY_OF_YEAR)-c1.get(Calendar.DAY_OF_YEAR);
     }
     
     for(int i=0;i<betweenYears;i++){
     c1.set(Calendar.YEAR,(c1.get(Calendar.YEAR)+1));
     betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
     }
     return betweenDays;
     } 

 
     
     
     public static String getYearWeek(){

    	 
    	 String year = getDate2FormatString(new Date(), "yy");
    	 int week = getWeekYear(new Date());
    	 
    	 return "message_"+year+week;
    	 
     }

     
     public static String getYearWeekp( String pfx , Date date ){

    	 
    	 String year = getDate2FormatString(date, "yy");
    	 int week = getWeekYear(date);
    	 
    	 return pfx+year+week;
    	 
     }


    
}