/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.util;


import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class DesUtil {

	
	
    /***
     * 
     * @param plainText
     * @param desKey
     * @return
     * @throws Exception
     */
	public static byte[] desEncrypt(byte[] plainText , String desKey) throws Exception {
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = desKey.getBytes();
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);
		byte data[] = plainText;
		byte encryptedData[] = cipher.doFinal(data);
		return encryptedData;
	}

	/**
	 * 
	 * @param encryptText
	 * @param desKey
	 * @return
	 * @throws Exception
	 */
	public static  byte[] desDecrypt(byte[] encryptText ,  String desKey) throws Exception {
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = desKey.getBytes();
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key, sr);
		byte encryptedData[] = encryptText;
		byte decryptedData[] = cipher.doFinal(encryptedData);
		return decryptedData;
	}

	/**
	 * 
	 * @param input
	 * @param desKey
	 * @return
	 * @throws Exception
	 */
	public static  String encrypt(String input ,  String desKey) throws Exception {
		return base64Encode(desEncrypt(input.getBytes() ,desKey ));
	}

	/**
	 * 
	 * @param input
	 * @param desKey
	 * @return
	 * @throws Exception
	 */
	public  static  String decrypt(String input , String desKey ) throws Exception {
		byte[] result = base64Decode(input);
		return new String(desDecrypt(result,desKey));
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String base64Encode(byte[] s) {
		if (s == null)
			return null;
		BASE64Encoder b = new sun.misc.BASE64Encoder();
		return b.encode(s);
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public static byte[] base64Decode(String s) throws IOException {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(s);
		return b;
	}

	public static void main(String[] args) throws Exception {
		String key = "abcdefgh";
		String input = "a";
		/*DES crypt = new DES(key);
		System.out.println("Encode:" + crypt.encrypt(input));
		System.out.println("Decode:" + crypt.decrypt(crypt.encrypt(input)));*/
	}
	
	
}
