package com.xcode.whz.util;



import com.xcode.whz.util.mail.MailSenderInfo;
import com.xcode.whz.util.mail.SimpleMailSender;

public class MailUtil {
	
	public static void sendMail(String ToAddress,String chkCode){
		//这个类主要是设置邮件   
	      MailSenderInfo mailInfo = new MailSenderInfo();    
	      mailInfo.setMailServerHost("smtp.qq.com");    
	      mailInfo.setMailServerPort("25");    
	      mailInfo.setValidate(true);    
	      mailInfo.setUserName("0@chalicer.com");    
	      mailInfo.setPassword("woshichuanqi");//您的邮箱密码    
	      mailInfo.setFromAddress("0@chalicer.com");    
	      mailInfo.setToAddress(ToAddress);    
	      mailInfo.setSubject("新用户确认通知信"); //设置邮箱标题   
	      mailInfo.setContent(setContent(ToAddress,chkCode));    
	         //这个类主要来发送邮件   
	      SimpleMailSender sms = new SimpleMailSender();   
	         // sms.sendTextMail(mailInfo);//发送文体格式    
	          sms.sendHtmlMail(mailInfo);//发送html格式   
		
	}
	
	public static void main(String[] args) {
		sendMail("21647921@qq.com","98989");
	}
	
	public static String setContent(String ToAddress,String chkCode){
		String str="亲爱的用户,离成功注册就差一步了！(请在48小时内完成)<br/>"
		+"请点击下面的链接完成注册：<br/>" 
		+"http://www.wohaizai.com/chkcode.asp?registerno="+ToAddress+"&typeregister=2&chkcode="+chkCode+"<br/><br/><br/>"
		+"也可以复制下面的链到浏览器地址栏中完成注册：<br/>"
		+"http://www.wohaizai.com/chkcode.asp?registerno="+ToAddress+"&typeregister=2&chkcode="+chkCode;
		return str;
	}
}

  
