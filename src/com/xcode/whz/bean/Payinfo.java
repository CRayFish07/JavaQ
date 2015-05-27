package com.xcode.whz.bean;

import javax.servlet.http.HttpServletRequest;

public class Payinfo {
	private String payid;	
	private String custid;	
	private String starttime;	//å¼€å§‹æ—¶é—´
	private String endtime;	//åˆ°æœÿæ—¶é—´
	private String paytype;	//æ”¯ä»˜æ–¹å¼
	private String paymoney;	//é’±æ•°
	private String state;	


	public static Payinfo getRequestData(HttpServletRequest request){
		Payinfo payinfo=new Payinfo();
		payinfo.setPayid(request.getParameter("payid"));
		payinfo.setCustid(request.getParameter("custid"));
		payinfo.setStarttime(request.getParameter("starttime"));//å¼€å§‹æ—¶é—´
		payinfo.setEndtime(request.getParameter("endtime"));//åˆ°æœŸæ—¶é—´
		payinfo.setPaytype(request.getParameter("paytype"));//æ”¯ä»˜æ–¹å¼
		payinfo.setPaymoney(request.getParameter("paymoney"));//é’±æ•°
		payinfo.setState(request.getParameter("state"));
		return payinfo;
	}


	public String getPayid() {
 			return payid;
	 }
	public void setPayid(String payid) {
 		this.payid = payid;
 	}

	public String getCustid() {
 			return custid;
	 }
	public void setCustid(String custid) {
 		this.custid = custid;
 	}

	public String getStarttime() {
 			return starttime;
	 }
	public void setStarttime(String starttime) {
 		this.starttime = starttime;
 	}

	public String getEndtime() {
 			return endtime;
	 }
	public void setEndtime(String endtime) {
 		this.endtime = endtime;
 	}

	public String getPaytype() {
 			return paytype;
	 }
	public void setPaytype(String paytype) {
 		this.paytype = paytype;
 	}

	public String getPaymoney() {
 			return paymoney;
	 }
	public void setPaymoney(String paymoney) {
 		this.paymoney = paymoney;
 	}

	public String getState() {
 			return state;
	 }
	public void setState(String state) {
 		this.state = state;
 	}

}