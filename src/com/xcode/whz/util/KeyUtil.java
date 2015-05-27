package com.xcode.whz.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.Provider;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xml.internal.security.utils.Base64;
public class KeyUtil {


	// 生成密钥
	public static  Key initKey(String algorithm) {
		Key key=null; // 根据算法对应的密钥
		try {
			// 初始化密钥key
			KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
			keyGenerator.init(56); // 选择DES算法,密钥长度必须为56位
			key = keyGenerator.generateKey(); // 生成密钥
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return key;
	}

	// 获取密钥包装成byte[]类型的
	public static byte[] getBinaryKey(Key k,String algorithm) {
		byte[] bk = null;
		Cipher cipher = null;
		try {
			// 生成Cipher对象
			cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.WRAP_MODE, k);   
			bk = cipher.wrap(k);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bk;
	}
	
	
	public static Key B2Key(String keyStr,String algorithm){
		SecretKey key=null;
		try {
			byte[] keyb=keyStr.getBytes();
//			Base64.encode(keyb);
//			DESKeySpec a=new DESKeySpec(keyb);//8位
			DESedeKeySpec a=new DESedeKeySpec(keyb);//24位
//			SecretKey a=new SecretKeySpec(keyb,1,18,algorithm);//自定义
//			return a;
//			SecretKeySpec a=new SecretKeySpec(keyb,1,18,algorithm);
			//a.getKey();
			SecretKeyFactory  keyFactory=SecretKeyFactory.getInstance(algorithm);
//			Provider pr=keyFactory.getProvider();
			key=keyFactory.generateSecret(a);
//			key=keyFactory.translateKey(a);
			
//			byte[] source_key =keyStr.getBytes();// new byte[16];
//			// source_key 初始化略...SecretKeySpec
//			SecretKeySpec des_keySpec = new SecretKeySpec(source_key,algorithm);
//			SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
//			key =  skf.generateSecret((KeySpec)des_keySpec);
//			Cipher cipher = Cipher.getInstance("DESede");
//			cipher.init(Cipher.ENCRYPT_MODE,secKey);
//			byte[] data = "1234    ".getBytes();
//			System.out.println(Integer.toHexString(cipher.getOutputSize(data.length)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

}
