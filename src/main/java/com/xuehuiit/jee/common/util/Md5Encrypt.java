package com.xuehuiit.jee.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * ���ַ����MD5����
 * @param text ����
 * @return ����
 */
public class Md5Encrypt {
	
	public static String md5(String text) {
        MessageDigest msgDigest = null;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }
        msgDigest.update(text.getBytes());
        byte[] bytes = msgDigest.digest();
        byte   tb;
        char   low;
        char   high;
        char   tmpChar;
        String md5Str = new String();

        for (int i = 0; i < bytes.length; i++) {
            tb = bytes[i];
            tmpChar = (char) ((tb >>> 4) & 0x000f);
            if (tmpChar >= 10) {
                high = (char) (('a' + tmpChar) - 10);
            } else {
                high = (char) ('0' + tmpChar);
            }

            md5Str += high;
            tmpChar = (char) (tb & 0x000f);

            if (tmpChar >= 10) {
                low = (char) (('a' + tmpChar) - 10);
            } else {
                low = (char) ('0' + tmpChar);
            }

            md5Str += low;
        }
        return md5Str;
    }
	
	//�������е�md5�㷨
	/*public static String md5ByCCB(byte []bySourceByte){
		int len,i;
		byte tb;
		char high,tmp,low;
		String result=new String();
		len=bySourceByte.length;
		for(i=0;i<len;i++){
			tb=bySourceByte[i];
			
			tmp=(char)((tb>>>4)&0x000f);
			if(tmp>=10)
				high=(char)('a'+tmp-10);
			else
				high=(char)('0'+tmp);
			result+=high;
			tmp=(char)(tb&0x000f);
			if(tmp>=10)
				low=(char)('a'+tmp-10);
			else
				low=(char)('0'+tmp);
			
			result+=low;
		}
		return result;
	}*/
	
	public static void main(String[] args) {
		String aa = "12335";
		String aa1 = Md5Encrypt.md5(aa);
		System.out.println("The aa1 is:"+aa1);
		//String aa2 = Md5Encrypt.md5ByCCB(aa.getBytes());
		//System.out.println("The aa2 is:"+aa2);
		
		//Double aa = new Double(10);
		//System.out.println("The value is:"+aa.doubleValue());
	}
	
}
