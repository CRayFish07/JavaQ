package com.xcode.whz.bean;

public class User {

	private int userid;

	private String phoneno;

	private String password;

public User( int userid,String phoneno,String password){
	this.userid=userid;
	this.phoneno=phoneno;
	this.password=password;
	
}
public User(){
	
}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
