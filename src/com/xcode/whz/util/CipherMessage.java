package com.xcode.whz.util;


import java.security.*;

import javax.crypto.*;

import java.io.*;

//对称加密器
public class CipherMessage {
	private String algorithm; // 算法，如DES
	private Key key; // 根据算法对应的密钥
	private String plainText; // 明文

	KeyGenerator keyGenerator;
//	Cipher cipher;

	// 函数进行初始化
//	CipherMessage(String alg, String msg) {
//		algorithm = alg;
//		plainText = msg;
//	}

	// 加密函数，将原文加密成密文
	public byte[] CipherMsg(String plainText,Key key,String algorithm) {
		byte[] cipherText = null;

		try {
			// 生成Cipher对象
			Cipher	cipher = Cipher.getInstance(algorithm);
			// 用密钥加密明文(plainText),生成密文(cipherText)
			cipher.init(Cipher.ENCRYPT_MODE, key); // 操作模式为加密(Cipher.ENCRYPT_MODE),key为密钥
			cipherText = cipher.doFinal(plainText.getBytes()); // 得到加密后的字节数组
			// String str = new String(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipherText;
	}

	// 解密函数，将密文解密回原文
	public String EncipherMsg(byte[] cipherText, Key k,String algorithm) {
		byte[] sourceText = null;

		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, k); // 操作模式为解密,key为密钥
			sourceText = cipher.doFinal(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(sourceText);

	}

	// 生成密钥
	public Key initKey() {
		try {
			// 初始化密钥key
			keyGenerator = KeyGenerator.getInstance(algorithm);
			keyGenerator.init(56); // 选择DES算法,密钥长度必须为56位
			key = keyGenerator.generateKey(); // 生成密钥
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return key;
	}

	/*
	 * //从新设置密钥 public void setKey(Key k){ key = k; }
	 */

	// 获取Key类型的密钥
	public Key getKey() {
		return key;
	}

//	// 获取Key类型的密钥
//	public Key getKey(byte[] k) {
//		try {
//			key = cipher.unwrap(k, algorithm, Cipher.DECRYPT_MODE);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return key;
//	}
//
//	// 获取密钥包装成byte[]类型的
//	public byte[] getBinaryKey(Key k) {
//		byte[] bk = null;
//		try {
//			cipher.init(Cipher.WRAP_MODE, k);   
//			bk = cipher.wrap(k);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return bk;
//	}
	
//	 public static SecretKey toKey(byte[] key) throws Exception {
//		        KeySpec dks = new DESKeySpec(key);
//		        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//		        return keyFactory.generateSecret(dks);
//	}
	
	
}



