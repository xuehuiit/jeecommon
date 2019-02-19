package com.xuehuiit.jee.common.util;

import java.io.UnsupportedEncodingException;



/**
 *  ByteCodeUtil类提供了byte类型和java基本类型的互相转换
 *  @author </br> <a href="mailto:fx19800215@163.com"> robert.feng</a>
 *
 */
public class ByteCodeUtil {
	
	/**
	 * 	用于将short类型的值转成btye数组
	 *  @return byte数组
	 */
	public static byte[] shortToByte(short number) {
		short temp = number;
		byte[] b = new byte[2];
		b[0] = (byte)(number>>4);
		b[1] = (byte)(number&0x0f);
		return b;
	}
	
	/**
	 * 将byte数组转换成short类型的变量
	 * @param b
	 * @return
	 */
	public static short byteToShort(byte[] b) {
		short s = 0;
		if(b.length != 2){
			return s;
		}
		s = (short)(b[1] + (b[0]<<4));
		return s;
	}
	
	/**
	 * 
	 * @param b0
	 * @param b1
	 * @param byteLength
	 * @return
	 */
	public   static   short   bytesToshort2(byte   b0,   byte   b1,   int   byteLength)   {   
        if(byteLength   !=   2)   
            return   0;   
        int   i   =   0;   
        short   out   =   0;   
        if(b0<0)   i   =   256+b0;   else   i   =   b0;   
            out   +=   i;   
        if(b1<0)   i   =   256+b1;   else   i   =   b1;   
            out   +=   (i<<8);   
        return   out;   
    }  
	
	/**
	 * 	用于将int类型的值转成
	 * @return byte数组
	 */
	public static byte[] intToByte(int number) {
		int temp = number;
	    /**
	     * 由于int类型在java中定义为占32个bit
	     */
		byte[] b=new byte[4];
		for (int i=b.length-1;i>-1;i--){
		     b[i] = new Integer(temp&0xff).byteValue();      //将最高位保存在最低位
		     temp = temp >> 8;       //向右移8位
		   }
		return b;
	}
	
	/**
	 * 	当数值较小时可返回一位的byte
	 * @param number
	 * @return byte
	 */
	public static byte intToByte2(int number) {
		byte[] b = intToByte(number);
		return b[3];
	}
	
	public static byte[] longToByte(long number) {
		long temp = number;
		byte[] b = new byte[8];
		for (int i=b.length-1;i>-1;i--){
			b[i] = new Long(temp&0xff).byteValue();      //将最高位保存在最低位
		    temp = temp >> 8;
		}
		return b;
	}
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	public static byte[] longToCByte(long number) {
		long temp = number;
		/**
		 * 由于c++的long只占4个byte
		 */
		byte[] b = new byte[4];
		for (int i=b.length-1;i>-1;i--){
			b[i] = new Long(temp&0xff).byteValue();      //将最高位保存在最低位
		    temp = temp >> 8;
		}
		return b;
	}
	
	/**
	 * 	字节数组到整数的转换
	 * @param b
	 * @return int
	 */
	public static long byteToLong(byte[] b) {
		  long s = 0;
		  for (int i = 0; i < 7; i++) {
			if (b[i] >= 0){
		    	s = s + b[i];
		    }else{
		    	s = s + 256 + b[i];
		    }
		    s = s * 256;
		  }
		  if (b[7] >= 0){     //最后一个之所以不乘，是因为可能会溢出
		    s = s + b[7];
		  }else{
		    s = s + 256 + b[7];
		  }
		  return s;
	}
	
	/**
	 * 	字节数组到整数的转换(c++)
	 * @param b
	 * @return int
	 */
	public static long byteCToLong(byte[] b) {
		  long s = 0;
		  for (int i = 0; i < 3; i++) {
			if (b[i] >= 0){
		    	s = s + b[i];
		    }else{
		    	s = s + 256 + b[i];
		    }
		    s = s * 256;
		  }
		  if (b[3] >= 0){     //最后一个之所以不乘，是因为可能会溢出
		    s = s + b[3];
		  }else{
		    s = s + 256 + b[3];
		  }
		  return s;
	}
	
	/**
	 * 	字节数组到整数的转换
	 * @param b
	 * @return int
	 */
	public static int byteToInt(byte[] b) {
		  int s = 0;
		  for (int i = 0; i < 3; i++) {
			if (b[i] >= 0){
		    	s = s + b[i];
		    }else{
		    	s = s + 256 + b[i];
		    }
		    s = s * 256;
		  }
		  if (b[3] >= 0){     //最后一个之所以不乘，是因为可能会溢出
		    s = s + b[3];
		  }else{
		    s = s + 256 + b[3];
		  }
		  return s;
	}

	/**
	 * 字符到字节转换
	 * @param ch
	 * @return byte
	 */
	public static byte[] charToByte(char ch){
	  int temp=(int)ch;
	  /**
	   * 由于int类型在java中定义为占8个bit
	   */
	  byte[] b=new byte[2];
	  for (int i=b.length-1;i>-1;i--){
	    b[i] = new Integer(temp&0xff).byteValue();     //将最高位保存在最低位
	    temp = temp >> 8;     //向右移8位
	  }
	  return b;
	}
	
	public static byte charToCByte(char ch){
		 byte[] b = charToByte(ch);
		return b[1];
	}
	
	/**
	 * 字节到字符转换
	 * @param b
	 * @return
	 */
	public static char byteToChar(byte[] b){
	  int s=0;
	  if(b[0]>0){
	    s+=b[0];
	  }else{
	    s+=256+b[0];
	  }
	  s*=256;
	  if(b[1]>0){
	    s+=b[1];
	  }else{
	    s+=256+b[1];
	  }
	  char ch=(char)s;
	  return ch;
	}
	
	/**
	 * 浮点到字节转换
	 * @param d
	 * @return byte数组
	 */
	public static byte[] doubleToByte(double d){
		  byte[] b=new byte[8];
		  long l=Double.doubleToLongBits(d);
		  for(int i=0;i<b.length;i++){
		    b[i]=new Long(l).byteValue();
		    l=l>>8;
		  }
		  return b;
	}
	
	/**
	 * 字节到浮点转换
	 * @param b
	 * @return double
	 */
	public static double byteToDouble(byte[] b){
		  long l;
		  l=b[0];
		  l&=0xff;
		  l|=((long)b[1]<<8);
		  l&=0xffff;
		  l|=((long)b[2]<<16);
		  l&=0xffffff;
		  l|=((long)b[3]<<24);
		  l&=0xffffffffl;
		  l|=((long)b[4]<<32);
		  l&=0xffffffffffl;
		  l|=((long)b[5]<<40);
		  l&=0xffffffffffffl;
		  l|=((long)b[6]<<48);
		  l|=((long)b[7]<<56);
		  return Double.longBitsToDouble(l);
	}
	
	/**
	 * 将byte类型转化为string类型
	 * @param bytes
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String byte2String(byte[] bytes) throws UnsupportedEncodingException{
		
/*		String byteString = null;
		ByteArrayInputStream byin = new ByteArrayInputStream(bytes);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(byin));
		try {
			byteString = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		return byte2String(bytes,"utf-8");
		
	}
	
	
	/**
	 * 将byte类型转化为string类型
	 * @param bytes
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String byte2String(byte[] bytes , String charset) throws UnsupportedEncodingException{
		
		String string = new String(bytes,charset);
		
		return string;
	}

	
	
	/**
	 * 将byte[]转换成string
	 * 
	 * @param butBuffer
	 */
	public static String byteToString(byte[] b) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			stringBuffer.append((char) b[i]);
		}
		return stringBuffer.toString();
	}

	/**
	 * 将bytebuffer转换成string
	 * 
	 * @param str
	 *//*
	public static IoBuffer stringToIoBuffer(String str) {

		byte bt[] = str.getBytes();

		IoBuffer ioBuffer = IoBuffer.allocate(bt.length);
		ioBuffer.put(bt, 0, bt.length);
		ioBuffer.flip();
		return ioBuffer;
	}

	*//**
	 * 将byte []转换成IoBuffer
	 * 
	 * @param str
	 *//*
	public static IoBuffer byteToIoBuffer(byte[] bt, int length) {

		IoBuffer ioBuffer = IoBuffer.allocate(length);
		ioBuffer.put(bt, 0, length);
		ioBuffer.flip();
		return ioBuffer;
	}

	*//**
	 * 将IoBuffer转换成byte
	 * 
	 * @param str
	 *//*
	public static byte[] ioBufferToByte(Object message) {
		if (!(message instanceof IoBuffer)) {
			return null;
		}
		IoBuffer ioBuffer = (IoBuffer) message;
		byte[] b = new byte[ioBuffer.limit()];
		ioBuffer.get(b);
		return b;
	}

	*//**
	 * 将IoBuffer转换成string
	 * 
	 * @param butBuffer
	 *//*
	public static String ioBufferToString(Object message) {
		if (!(message instanceof IoBuffer)) {
			return "";
		}
		IoBuffer ioBuffer = (IoBuffer) message;
		byte[] b = new byte[ioBuffer.limit()];
		ioBuffer.get(b);
		StringBuffer stringBuffer = new StringBuffer("");

		for (int i = 0; i < b.length; i++) {

			stringBuffer.append((char) b[i]);
		}
		return stringBuffer.toString();
	}
	
	*/
	
	
	public static void main(String[] orgs){
		
		
		char c = 'b';
		
		byte[] cb = ByteCodeUtil.charToByte(c);
		
		
		byte t = 0;
		
		byte[] b= ByteCodeUtil.intToByte(0);
		
		System.out.println((char)91);
		int k = 1111111111;
		byte[] bb = ByteCodeUtil.longToByte(k);
		
		System.out.println(ByteCodeUtil.longToByte(1111111111).length);
		
		
	}
	
	
	
}
