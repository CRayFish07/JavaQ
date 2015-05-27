package com.xcode.whz.mapper;

import java.util.List;

import com.xcode.whz.bean.EntrustInfo;
import org.springframework.stereotype.Repository;
@Repository 
public interface EntrustMapper {
	
	public List selectEntrustByCustid(EntrustInfo entrustInfo);
	
	public EntrustInfo selectEntrustById(EntrustInfo entrustinfo);
	
	public void insetEnt(EntrustInfo entrustInfo);
	
	public void upEnstate(EntrustInfo entrustInfo);
}
