/**
 * Created on 2005-07-06
 */
package com.xuehuiit.jee.common.util;

//import jdk API
import java.math.BigDecimal;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xuehuiit.jee.common.util.exception.NumberUtilException;
import org.apache.log4j.Logger;

/**
 * @author Wing.Feng
 *
 * The class <code>NumberUtil</code> provider some help that  operator Number
 * <p>
 * In java, the velue of simple double or float can't operat, so we provider some method
 * operat these value.
 */

public class NumberUtil {

	 // Define the log4j instance.
	 private static final Logger logger = Logger.getLogger(NumberUtil.class);
     // The value of DEF_DIV_SCALE is the default division scale.
     private static final int DEF_DIV_SCALE = 10;

	 /**
	  *  Get the exact value of additive  
	  *
	  *  @param v1   The value of augend.
	  *  @param v2   The value of addend.
	  * 
	  *  @return   the ruselt of addtion.
	  */
	 public static double add(double v1,double v2){
         
	     BigDecimal b1 = new BigDecimal(Double.toString(v1));
		 BigDecimal b2 = new BigDecimal(Double.toString(v2));
		 return b1.add(b2).doubleValue();
	 }
	
	 /**
	  * Get the exact value of subtration
	  *
	  *  @param v1   The value of minuend.
	  *  @param v2   The value of subtrahend.
	  * 
	  *  @return   the ruselt of subtration.
	  */
	 public static double sub(double v1,double v2){

        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();

	 }
	
	 /**
	  *  Get the exact value of multiplication
	  *
	  *  @param v1   The value of faciend.
	  *  @param v2   The value of multiplier
	  * 
	  *  @return   the ruselt of multiplication.
	  */
	 public static double mul(double v1,double v2){
		
		 BigDecimal b1 = new BigDecimal(Double.toString(v1));
		 BigDecimal b2 = new BigDecimal(Double.toString(v2));
		 return b1.multiply(b2).doubleValue();
	 }

	/**
	  *  Get the default scale value of division.
	  *
	  *  @param v1   The value of dividend.
	  *  @param v2   The value of divisor.
	  * 
	  *  @return   the ruselt of division.
	  */
	 public static double div(double v1,double v2){

		 return div(v1,v2,DEF_DIV_SCALE);
	 }

     /**
	  *  Get the fact scale value of division.
	  *
	  *  @param v1   The value of dividend.
	  *  @param v2   The vdlue of divisor.
	  * 
	  *  @return  the ruselt of division.
	  */
	 public static double div(double v1,double v2,int scale){
		
		if(scale<0)
		{
		    logger.error("method div() error!  -- The scale must be a positive integer or zero ");
		    throw new NumberUtilException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();

	 }

     /**
	  *  Get the round value of double 
	  *
	  *  @param value   the value that is rounded.
	  *  @param scale   the round scale.
	  * 
	  *  @return   the value than has rounded.
	  */
     public static Double round(Double value, int scale ){
	       
		   if(scale<0)
			   throw new IllegalArgumentException("The scale must be a positive integer or zero");

			BigDecimal b = new BigDecimal(Double.toString(value));
			BigDecimal one = new BigDecimal("1");
			return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	 }	
     
     
     /**
      * 
      * @param value
      * @param scale
      * @return
      */
     public static double round(double value, int scale ){
    	 
    	 Double d = Double.valueOf(value);
    	 return round(d,scale).doubleValue();
    	 
     }
     
     
     /**
      * 
      * @param value
      * @param scale
      * @return
      */
     public static float round(float value,int scale){
    	 Double d = Double.valueOf(value);
    	 return round(d,scale).floatValue();
     }
     
     
 	/**
 	 * Get the random number
 	 * 
 	 * @param min   the rodaom start
 	 * @param max   the rodaom end
 	 * @return
 	 */
 	public static int getRandom(int min, int max) {
 		return min + (int) (Math.random() * (max - min + 1));
 	}
 	
 	
 	/**
 	 * Get the random number
 	 * 
 	 * @param min   the rodaom start
 	 * @param max   the rodaom end
 	 * @return
 	 */
 	public static long getLongRandom(long min, long max) {
 		return min + (long) (Math.random() * (max - min + 1));
 	}
 	

 	
 	
 	public static String setRand() { 
        String rad = "0123456789"; 
        StringBuffer result = new StringBuffer(); 
        Random rand = new Random(); 
        int length = 32; 
        for (int i = 0; i < length; i++) { 
            int randNum = rand.nextInt(10); 
            result.append(rad.substring(randNum, randNum + 1)); 
        } 
        return result.toString(); 
    } 

	
	public static boolean isNumeric(String str) {
		
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		
		if (!isNum.matches()) {
			return false;
		}
		
		return true;
		
	}
	
    
	final static char[] digits = {
	      '0', '1', '2', '3', '4', '5', '6', '7',
	      '8','9', 'a', 'b', 'c', 'd', 'e', 'f', 
	      'g', 'h','j', 'k', 'l','m', 'n',  'p',
	      'r', 's', 't', 'u', 'w', 'x', 'y','z' };

	 /**
	  * 将十进制的数字转换为指定进制的字符串。
	  * 
	  * @param i
	  *            十进制的数字。
	  * @param system
	  *            指定的进制，常见的2/8/16。
	  * @return 转换后的字符串。
	  */
	 public static String numericToString(int i, int system) {
		long num = 0;
		
		if (i < 0) {
			num = ((long) 2 * 0x7fffffff) + i + 2;
		} else {
			num = i;
		}
		
		char[] buf = new char[32];
		int charPos = 32;
		
		while ((num / system) > 0) {
			buf[--charPos] = digits[(int) (num % system)];
			num /= system;
		}
		
		buf[--charPos] = digits[(int) (num % system)];
		
		return new String(buf, charPos, (32 - charPos));
	}
	 
	 
	/**
	 * 将其它进制的数字（字符串形式）转换为十进制的数字。
	 * 
	 * @param s
	 *            其它进制的数字（字符串形式）
	 * @param system
	 *            指定的进制，常见的2/8/16。
	 * @return 转换后的数字。
	 */
	 public static int stringToNumeric(String s, int system) {
		
		char[] buf = new char[s.length()];
		s.getChars(0, s.length(), buf, 0);
		
		long num = 0;
		
		for (int i = 0; i < buf.length; i++) {
			for (int j = 0; j < digits.length; j++) {
				if (digits[j] == buf[i]) {
					num += j * Math.pow(system, buf.length - i - 1);
					break;
				}
			}
		}
		
		return (int) num;
	}


 	public static void  main(String[] args){
 		
 	 		/*	for(int i = 0 ; i<100 ; i++)
 	 		  System.out.println(getLongRandom(1,100));*/
 	 		/*System.out.println(new   Random().nextInt());*/
 			

 	 		//System.out.println(numericToString(2092661,32));

 	 		//System.out.println(numericToString(1015456,32));
 	 		//System.out.println(stringToNumeric("1z5sb1a",32)) ;

 	 		//System.out.println(Integer.toHexString(31189) );
 	 		//g13uu:1zukm
 	 		//168139472092661

 	 		System.out.println( getRandom(1, 2) );
 		

 	 	}
	
}