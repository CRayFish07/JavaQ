package com.xcode.whz.datasource;
import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;

import com.xcode.whz.util.DesPlus;

public class PropertiesEncryptFactoryBean implements FactoryBean {

	private Properties properties;
	
	public Object getObject() throws Exception {
		return getProperties();
	}

	public Class getObjectType() {
		return java.util.Properties.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties inProperties) {
		this.properties = inProperties;
		String originalUsername = properties.getProperty("user");
		String originalPassword = properties.getProperty("password");
		if (originalUsername != null){
			String newUsername = deEncryptUsername(originalUsername);
			properties.put("user", newUsername);
		}
		if (originalPassword != null){
			String newPassword = deEncryptPassword(originalPassword);
			properties.put("password", newPassword);
		}
	}
	
	private String deEncryptUsername(String originalUsername){
		return deEncryptString(originalUsername);
	}
	
	private String deEncryptPassword(String originalPassword){
		return deEncryptString(originalPassword);
	}
	//简单加密
	private static String deEncryptString(String originalString){
		String newstr="";
		try {
			DesPlus desPlus=new DesPlus();
			newstr=desPlus.decrypt(originalString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newstr;
	}

	public static void main(String[] args) {
		try {
			DesPlus desPlus=new DesPlus();
			System.out.println(desPlus.encrypt("whzmima***"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

