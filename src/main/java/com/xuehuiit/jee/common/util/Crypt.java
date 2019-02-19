/**
 * Copyright (c) linkwise 2007-2009 corporation.  
 * All rights reserved
 */
package com.xuehuiit.jee.common.util;

/**
 * @author </br> <a href="mailto:fx19800215@163.com"> robert.feng</a>
 *
 */
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * 提供加密和解密算法，在Linux系统中如果加密字符中含有汉字，解密后会出现乱码。
 * @author </br> <a href="mailto:fx19800215@163.com"> robert.feng</a>
 */
public class Crypt {

	private static String Algorithm = "DES"; //定义 加密算法,可用 DES,DESede,Blowfish

	static boolean debug = false;

	static {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
	}

	/**
	 * 生成密钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] getKey() throws Exception {
		KeyGenerator keygen = KeyGenerator.getInstance(Algorithm);
		SecretKey deskey = keygen.generateKey();
		if (debug)
			System.out.println("生成密钥:" + byte2hex(deskey.getEncoded()));
		return deskey.getEncoded();
	}

	/**
	 * 加密字节数组
	 * @param input
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encode(byte[] input, byte[] key) throws Exception {
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
		if (debug) {
			System.out.println("加密前的二进串:" + byte2hex(input));
			System.out.println("加密前的字符串:" + new String(input));
		}
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] cipherByte = c1.doFinal(input);
		if (debug) {
			System.out.println("加密后的二进串:" + byte2hex(cipherByte));
			System.out.println("加密后的字符:" + new String(cipherByte));
		}
		return cipherByte;
	}

	/**
	 * 解密字节数组
	 * @param input
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decode(byte[] input, byte[] key) throws Exception {
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
		if (debug)
			System.out.println("解密前的信息:" + byte2hex(input));
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.DECRYPT_MODE, deskey);
		byte[] clearByte = c1.doFinal(input);
		if (debug) {
			System.out.println("解密后的二进串:" + byte2hex(clearByte));
			System.out.println("解密后的字符串:" + (new String(clearByte)));
		}
		return clearByte;
	}

	//md5()信息摘要, 不可逆
	public static byte[] md5(byte[] input) throws Exception {
		java.security.MessageDigest alg = java.security.MessageDigest
				.getInstance("MD5"); //or "SHA-1"
		if (debug) {
			System.out.println("摘要前的二进串:" + byte2hex(input));
			System.out.println("摘要前的字符串:" + new String(input));
		}
		alg.update(input);
		byte[] digest = alg.digest();
		if (debug) {
			System.out.println("摘要后的二进串:" + byte2hex(digest));
			System.out.println("摘要后的字符串:" + new String(digest));
		}
		return digest;
	}

	//字节码转换成16进制字符串
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

	public static void main(String[] args) throws Exception {
		debug = false;
		//	    byte[] key = getKey();
		/* byte[] key = "好好学习".getBytes();
		 decode(encode("测试加密".getBytes(),key),key);
		 md5("测试加密".getBytes());*/
		byte[] key = "好好学习".getBytes();
		String d = "134134312897519275312";
		String jm;
		String jx;
		byte[] jmb;

		//jmb = encode(d.getBytes(),key);

		jmb = encode(d.getBytes(), key);
		/* System.out.println("加密前二进符串 " + encode(d.getBytes(),key) );
		 System.out.println("加密前在字符串 " + d );*/

		jmb = encode(d.getBytes(), key);
		System.out.println("加密后二进制 " + jmb);
		String jms = byte2hexString(jmb);
		System.out.println("加密后的字符串 " + jms);
		byte[] jmb1 = hexString2byte(jms);
		System.out.println("加密后的二进制 " + jmb1);

		System.out.println("解密前二进制 " + jmb1);
		System.out.println("解密后二进制 " + decode(jmb1, key));
		System.out.println("解密后字符串 " + new String(decode(jmb1, key)));
	}

	/**
	 * 将byte类型转化为string类型
	 * @param bytes
	 * @return
	 */
	public static String byte2String(byte[] bytes) {

		String byteString = null;
		ByteArrayInputStream byin = new ByteArrayInputStream(bytes);
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(byin));
		try {
			byteString = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteString;

	}

	/**
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * 
	 * @param hexString
	 * @return
	 */
	public static byte[] hexString2byte(String hexString) {
		if (hexString == null || hexString.length() % 2 != 0)
			return null;
		byte[] hanzi = new byte[hexString.length() / 2];
		for (int i = 0; i < hexString.length(); i += 2)
			hanzi[i / 2] = (byte) (Integer.parseInt(hexString.substring(i,
					i + 2), 16) & 0xff);
		return hanzi;
	}

}