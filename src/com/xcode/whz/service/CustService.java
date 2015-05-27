package com.xcode.whz.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xcode.whz.bean.AddrInfo;
import com.xcode.whz.bean.CustInfo;
import com.xcode.whz.mapper.CustMapper;


@Service("CustService")
public class CustService {
	@Autowired
	CustMapper custmapper;
	
	public CustInfo selectCust(CustInfo custinfo){
		return  custmapper.selectCust(custinfo);
	
	}
	public CustInfo checkPwd(String custid,String password){
		CustInfo custinfo= custmapper.checkPwd(custid,password);
		return custinfo;
	}
	public void insertCust(CustInfo custinfo) throws Exception{
		try {
			custmapper.insertCust(custinfo);
		} catch (Exception e) {
			throw e;
		}
		
	}
	public void upCustById(CustInfo custinfo){
		custmapper.upCustById(custinfo);
	}
	public void upAddrById(AddrInfo addrInfo){
		custmapper.upAddrById(addrInfo);
	}
	public void insertAddr(AddrInfo addrInfo){
		custmapper.insertAddr(addrInfo);
	}
	public AddrInfo selectAddr(String addrid){
		return custmapper.selectAddr(addrid);
	}
}
