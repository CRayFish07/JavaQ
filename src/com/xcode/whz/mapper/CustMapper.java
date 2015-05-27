package com.xcode.whz.mapper;


import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Select;

import com.xcode.whz.bean.AddrInfo;
import com.xcode.whz.bean.CustInfo;
import org.springframework.stereotype.Repository;
@Repository 
public interface CustMapper {
	
	public CustInfo selectCust(CustInfo custinfo);
	
	@Select("select * from custinfo where custid=#{custid} and custpwd=#{password}") 
	public CustInfo checkPwd(@Param("custid") String custid,@Param("password") String password);
	
	public int insertCust(CustInfo custinfo);
	public int upCustById(CustInfo custinfo);
	public int insertAddr(AddrInfo addrInfo);
	public int upAddrById(AddrInfo addrInfo);
	public AddrInfo selectAddr(String addrid);
	
}
