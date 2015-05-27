package com.xcode.whz.util;
import org.springframework.util.DigestUtils;
public class MD5Util {

	
	public static String encrypt(String str){
		String salt = "xcjdfu34i34u34-zmew8732dfhjd-"+str
	             +"dfhjdf1000000sdhxcye-ehjcbewsbn";
		String encodeStr = DigestUtils.md5DigestAsHex(salt.getBytes());
		return encodeStr;
	}
	public static String encryptdb(String str){
		String salt = "xcjdfu34i34u34-zmew8732dfhjd-"+str
				+"dfhjdf9000000sdhxcye-ehjcbewsbn";
		String encodeStr = DigestUtils.md5DigestAsHex(salt.getBytes());
		return encodeStr;
	}
	
	
	public static void main(String[] args) {
		System.out.println(encrypt("1111"));
		System.out.println(encryptdb("1111"));
	}
}
