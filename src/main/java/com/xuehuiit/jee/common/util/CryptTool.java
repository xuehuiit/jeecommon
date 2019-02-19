package com.xuehuiit.jee.common.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** 
 * @author bonjovi
 * @version 1.0 2004-05-12
 */
public class CryptTool {
	/**
	 * Creates a new CryptTool object.
	 */
	public CryptTool() {
	}

	/**
	 * BASE64.
	 * 
	 * @param src
	 *            String inputed string
	 * @return String returned string
	 */
	public static String base64Decode(String src) {
		BASE64Decoder decoder = new BASE64Decoder();

		try {
			return new String(decoder.decodeBuffer(src));
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * BASE64 (to byte[]).
	 * 
	 * @param src
	 *            String inputed string
	 * @return String returned string
	 */
	public static byte[] base64DecodeToBytes(String src) {
		BASE64Decoder decoder = new BASE64Decoder();

		try {
			return decoder.decodeBuffer(src);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * BASE64
	 * 
	 * @param src
	 *            String inputed string
	 * @return String returned string
	 */
	public static String base64Encode(String src) {
		BASE64Encoder encoder = new BASE64Encoder();

		return encoder.encode(src.getBytes());
	}

	/**
	 * BASE64 (byte[]).
	 * 
	 * @param src
	 *            byte[] inputed string
	 * @return String returned string
	 */
	public static String base64Encode(byte[] src) {
		BASE64Encoder encoder = new BASE64Encoder();

		return encoder.encode(src);
	}

	/**
	 * 3DES (byte[]).
	 * 
	 * @param key
	 *            SecretKey
	 * @param crypt
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public static byte[] desDecrypt(javax.crypto.SecretKey key, byte[] crypt) {
		Cipher cipher = null;
		byte[] rs = null;

		try {
			cipher = Cipher.getInstance("DESede");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}

		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		}

		try {
			rs = cipher.doFinal(crypt);
		} catch (IllegalStateException e2) {
			e2.printStackTrace();
		} catch (IllegalBlockSizeException e2) {
			e2.printStackTrace();
		} catch (BadPaddingException e2) {
			e2.printStackTrace();
		}

		return rs;
	}

	/**
	 * 3DES (String).
	 * 
	 * @param key
	 *            SecretKey
	 * @param crypt
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public static String desDecrypt(SecretKey key, String crypt) {
		return new String(desDecrypt(key, crypt.getBytes()));
	}

	/**
	 * 3DES����(byte[]).
	 * 
	 * @param key
	 *            SecretKey
	 * @param src
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public static byte[] desEncrypt(SecretKey key, byte[] src) {
		Cipher cipher = null;
		byte[] rs = null;

		try {
			cipher = Cipher.getInstance("DESede");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}

		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		}

		try {
			rs = cipher.doFinal(src);
		} catch (IllegalStateException e2) {
			e2.printStackTrace();
		} catch (IllegalBlockSizeException e2) {
			e2.printStackTrace();
		} catch (BadPaddingException e2) {
			e2.printStackTrace();
		}

		return rs;
	}

	/**
	 * 3DES����(String).
	 * 
	 * @param key
	 *            SecretKey
	 * @param src
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public static String desEncrypt(SecretKey key, String src) {
		return new String(desEncrypt(key, src.getBytes()));
	}

	/**
	 * @param key_byte
	 *            seed key
	 * @throws Exception
	 * @return javax.crypto.SecretKey Generated DES key
	 */
	public static SecretKey genDESKey(byte[] key_byte) {
		//    javax.crypto.spec.DESKeySpec deskeyspec = new
		// javax.crypto.spec.DESKeySpec(
		//        key_byte);
		//    javax.crypto.SecretKeyFactory skf = javax.crypto.SecretKeyFactory.
		//        getInstance("DES", "SunJCE");
		//    return (javax.crypto.SecretKey) skf.generateSecret(deskeyspec);
		//KeyGenerator kg = KeyGenerator.getInstance("DESede");
		SecretKey k = null;
		k = new SecretKeySpec(key_byte, "DESede");

		return k;
	}

	/** Test crypt */
	public static void main(String[] args) {
		byte[] src_byte = "1234567812345678".getBytes();
		System.out.println(src_byte.length);

		byte[] key_byte = "123456781234567812345678".getBytes();

		// 3DES 24 bytes key
		try {
			
			javax.crypto.SecretKey deskey;

		
			//      javax.crypto.KeyGenerator key =
			// javax.crypto.KeyGenerator.getInstance(
			//          "DES");
			//      key.init(56);
			//      deskey = key.generateKey();
			deskey = genDESKey(key_byte);
			System.out.println("Generator DES KEY OK");

			// DES�
			byte[] encrypt;

			// DES�ӽ
			byte[] decrypt;

			//
			encrypt = desEncrypt(deskey, src_byte);
			System.out.println("encrypt=" + new String(encrypt));

			//����
			decrypt = desDecrypt(deskey, encrypt);
			System.out.println("decrypt=" + new String(decrypt));

			//      String s = "12345678";
			//      //
			//      s = desEncrypt(deskey, s);
			//      System.out.println("encrypt=" + s);
			//      //
			//      s = desDecrypt(deskey, s);
			//      System.out.println("decrypt=" + s);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("BASE64 Test:" + base64Decode(base64Encode("1234")));
	}

	/**
	 * MD5 (byte[]).
	 * 
	 * @param src
	 *            byte[]
	 * @throws Exception
	 * @return byte[] 16 bit digest
	 */
	public static byte[] md5Digest(byte[] src) {
		MessageDigest alg = null;

		try {
			alg = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// MD5 is 16 bit message digest
		return alg.digest(src);
	}

	/**
	 * MD5 (String).
	 * 
	 * @param src
	 *            String
	 * @throws Exception
	 * @return String
	 */
	public static String md5Digest(String src) {
		return new String(md5Digest(src.getBytes()));
	}

	/**
	 * @param src
	 *            String
	 * @return String
	 */
	public static String urlEncode(String src) {
		try {
			src = URLEncoder.encode(src, "GB2312");

			return src;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return src;
	}

	/**
	 * @param value
	 *           
	 * @return
	 */
	public String urlDecode(String value) {
		try {
			return URLDecoder.decode(value, "GB2312");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return value;
	}
}