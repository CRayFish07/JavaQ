package com.xcode.whz.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;



import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class FuckUtil {

	
	public static String en(String sendkeys,String msgStr)throws UnsupportedEncodingException {
		String algorithm = "DESede"; // 定义加密算法,可用 DES,DESede,Blowfish
		String message=msgStr;
//		String beKey=encPass("3283782378237811", "000000");
		System.out.println("MD5:"+"beKey");
		CipherMessage cm = new CipherMessage();
		Key key1= KeyUtil.B2Key(sendkeys, algorithm);
		byte[] msg = cm.CipherMsg(message,key1,algorithm);
	     BASE64Encoder encoder = new BASE64Encoder();
	     String epStr =  encoder.encode(msg);
		System.out.println("加密后的密文为：");
		System.out.println(epStr);
		return epStr;
	}
	
	public static String dn(String sendkeys,String msgDB)throws IOException {
		String algorithm = "DESede"; // 定义加密算法,可用 DES,DESede,Blowfish
		byte[] msgdb=null;
		BASE64Decoder   b64d = new  BASE64Decoder();
        	msgdb =  b64d.decodeBuffer(msgDB);
		Key key2= KeyUtil.B2Key(sendkeys, algorithm);
		CipherMessage cm1 = new CipherMessage();
		String dnStr=cm1.EncipherMsg(msgdb,key2,algorithm);
		return dnStr;
	}
	
	
	public static String ek(String sendkeys)throws Exception {
			return RSAUtil.encrypt(sendkeys);
	}
	
	public static String dk(String sendkeys)throws Exception {
		return RSAUtil.decrypt(sendkeys);
	}
	
	public static String em(String str)throws Exception {
		return MD5Util.encrypt(str);
	}
	public static String emdb(String str)throws Exception {
		return MD5Util.encryptdb(str);
	}
}
