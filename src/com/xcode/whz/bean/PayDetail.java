package com.xcode.whz.bean;

public class PayDetail {
String payid, totalfee, productid, seller, buyer, gmtcreate, gmtpayment, gmtrefund, status,paychannel,thirdtradeno;

public String getPayid() {
	return payid;
}

public void setPayid(String payid) {
	this.payid = payid;
}

public String getTotalfee() {
	return totalfee;
}

public void setTotalfee(String totalfee) {
	this.totalfee = totalfee;
}

public String getPaychannel() {
	return paychannel;
}

public void setPaychannel(String paychannel) {
	this.paychannel = paychannel;
}

public String getThirdtradeno() {
	return thirdtradeno;
}

public void setThirdtradeno(String thirdtradeno) {
	this.thirdtradeno = thirdtradeno;
}

public String getProductid() {
	return productid;
}

public void setProductid(String productid) {
	this.productid = productid;
}

public String getSeller() {
	return seller;
}

public void setSeller(String seller) {
	this.seller = seller;
}

public String getBuyer() {
	return buyer;
}

public void setBuyer(String buyer) {
	this.buyer = buyer;
}

public String getGmtcreate() {
	return gmtcreate;
}

public void setGmtcreate(String gmtcreate) {
	this.gmtcreate = gmtcreate;
}

public String getGmtpayment() {
	return gmtpayment;
}

public void setGmtpayment(String gmtpayment) {
	this.gmtpayment = gmtpayment;
}

public String getGmtrefund() {
	return gmtrefund;
}

public void setGmtrefund(String gmtrefund) {
	this.gmtrefund = gmtrefund;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}
}
