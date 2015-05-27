package com.xcode.whz.util;


import java.security.*;
import java.sql.DriverManager;

import javax.crypto.*;

//import org.acegisecurity.providers.encoding.Md5PasswordEncoder;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class TestMain {
	public static void main(String[] args) throws Exception 
	{
		String s=FuckUtil.ek("33333");
		System.out.println(s);
		System.out.println(FuckUtil.dk(s));
		String s1=FuckUtil.ek("33333");
		System.out.println(s1);
		System.out.println(FuckUtil.dk(s1));
	}
	public static void main1(String[] args) throws UnsupportedEncodingException {
		String algorithm = "DESede"; // 定义加密算法,可用 DES,DESede,Blowfish
		String message="史1宝宁xcjqwerxcj125901481625xcjqwerxcj盈年：看完这篇文，我久久无语。国情不同的关系么？我是万万做不到这样洒脱的。难道是爱的不够深的缘故？"
		+"nonono，我说服不了自己，和自己无边无际的想象力。我是有精神洁癖的那种女子。曾经和小妹还有好友们讨论过“婚外情”这" +
				"个话题。女性友人几乎众口一词，不可原谅肉体的背叛，精神出轨更甚。还有点自欺欺人，用小妹的话说，不管他在外面如何花天酒" +
				"地，回家之前，擦去口红印，洗去香水味，要给对方最基本的尊重，否则，离婚是板上钉钉的事。似乎不知道就可以当作没发生过，阿Q" +
				"的忠实粉丝。而，异性好友大部分的回答恰恰相反。激情是暂时的，家庭是另一回事，婚是绝对不会离的。恨得我很想问候他们的祖先。" +
				"如果是女方出轨，他们肯定是不会愿意带绿色帽子的吧。己所不欲勿施于人，凭什么要女方忠贞，自己”家里红旗不倒，外面彩旗飘飘。" +
				"个话题。女性友人几乎众口一词，不可原谅肉体的背叛，精神出轨更甚。还有点自欺欺人，用小妹的话说，不管他在外面如何花天酒" +
				"地，回家之前，擦去口红印，洗去香水味，要给对方最基本的尊重，否则，离婚是板上钉钉的事。似乎不知道就可以当作没发生过，阿Q" +
				"的忠实粉丝。而，异性好友大部分的回答恰恰相反。激情是暂时的，家庭是另一回事，婚是绝对不会离的。恨得我很想问候他们的祖先。" +
				"如果是女方出轨，他们肯定是不会愿意带绿色帽子的吧。己所不欲勿施于人，凭什么要女方忠贞，自己”家里红旗不倒，外面彩旗飘飘。" +
				"个话题。女性友人几乎众口一词，不可原谅肉体的背叛，精神出轨更甚。还有点自欺欺人，用小妹的话说，不管他在外面如何花天酒" +
				"地，回家之前，擦去口红印，洗去香水味，要给对方最基本的尊重，否则，离婚是板上钉钉的事。似乎不知道就可以当作没发生过，阿Q" +
				"的忠实粉丝。而，异性好友大部分的回答恰恰相反。激情是暂时的，家庭是另一回事，婚是绝对不会离的。恨得我很想问候他们的祖先。" +
				"如果是女方出轨，他们肯定是不会愿意带绿色帽子的吧。己所不欲勿施于人，凭什么要女方忠贞，自己”家里红旗不倒，外面彩旗飘飘。" +
				"个话题。女性友人几乎众口一词，不可原谅肉体的背叛，精神出轨更甚。还有点自欺欺人，用小妹的话说，不管他在外面如何花天酒" +
				"地，回家之前，擦去口红印，洗去香水味，要给对方最基本的尊重，否则，离婚是板上钉钉的事。似乎不知道就可以当作没发生过，阿Q" +
				"的忠实粉丝。而，异性好友大部分的回答恰恰相反。激情是暂时的，家庭是另一回事，婚是绝对不会离的。恨得我很想问候他们的祖先。" +
				"如果是女方出轨，他们肯定是不会愿意带绿色帽子的吧。己所不欲勿施于人，凭什么要女方忠贞，自己”家里红旗不倒，外面彩旗飘飘。" +
				"又是七年，莫非国外也有“七年之痒”之说？妈妈密呀，俺们也正处于痒年来着。阿门！不晓得众位U条是如何看待“婚外情”这个话题的，欢迎讨论。";
//		String beKey=encPass("3283782378237811", "000000");
		System.out.println("MD5:"+"beKey");
		CipherMessage cm = new CipherMessage();
//		Key key1= KeyUtil.initKey(algorithm);
		Key key1= KeyUtil.B2Key("d7fd7310f271ac5c7b90e3d9a50b3f83", algorithm);
//		System.out.println("加密方式:"+key1.getAlgorithm());
		byte[] msg = cm.CipherMsg(message,key1,algorithm);
		
//	    byte [] epByte = cipher.doFinal(sbt); 
	     BASE64Encoder encoder = new BASE64Encoder();
	     String epStr =  encoder.encode(msg);
	     
		System.out.println("加密后的密文为：");
		System.out.println(epStr);
////		byte [] keyByte =null;
//		
//
//		System.out.println("密钥为："+new String(KeyUtil.getBinaryKey(key1,algorithm)));
		//TestSql.TestInsert(epStr);
	//	String epStrdb=TestSql.TestQuery();
		String epStrdb=epStr;
		byte[] msgdb=null;
		BASE64Decoder   b64d = new  BASE64Decoder();
        try {
        	msgdb =  b64d.decodeBuffer(epStrdb);
		} catch (IOException e) {
			e.printStackTrace();
		} 
//       if(msg.length==msgdb.length){
//			System.out.println(100);
//			for (int i = 0; i < msg.length; i++) {
//				if(msg[i]!=msgdb[i]){
//					System.err.println(1);
//				}
//			}
//
//		}
		
		Key key2= KeyUtil.B2Key("d7fd7310f271ac5c7b90e3d9a50b3f83", algorithm);
//		if(key2!=key1){
//			System.out.println(22);
//		}
		CipherMessage cm1 = new CipherMessage();
		System.out.println("解密密文为：" + cm1.EncipherMsg(msgdb,key2,algorithm));
//		25980fa91ec748f96cab8acd9ba6c44c
//		123456789012345678901234
	}
	
//	public static String encPass(String certno,String pass){
//		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//		if(null!=certno){
//			certno = certno.toUpperCase();
//		}
//		
//		return md5.encodePassword(pass, salt);
//	}
//	
	
//	加密凭证明文--》RSA非对称 ‘公钥‘加密后存放数据库，用于对比密码是否正确（特殊情况使用’私钥‘解密）
//	   |----》MD5单向加密---》作为秘钥DESede加密内容，接收人，联系方式

}
//INSERT INTO `whz`.`custinfo`
//(`custid`,
//`custname`,
//`custpwd`,
//`phoneno`,
//`state`,
//`checktype`,
//`lastlogin`,
//`email`)
//VALUES
//(
//'10000001',
//'万红1',
//'1111',
//'1234567890',
//'1',
//'1',
//'',
//'0987771@sina.com'
//);
