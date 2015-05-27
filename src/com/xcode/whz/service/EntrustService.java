package com.xcode.whz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xcode.whz.bean.EntrustInfo;
import com.xcode.whz.mapper.EntrustMapper;


@Service("EntrustService")
public class EntrustService {
	@Autowired
	EntrustMapper entrustMapper;
	
	public List selectEntrustByCustid(EntrustInfo entrustInfo){
		List list = entrustMapper.selectEntrustByCustid(entrustInfo);
		return list;
	}
	
	public void insetEnt(EntrustInfo entrustInfo){
		entrustMapper.insetEnt(entrustInfo);
	}
	public void upEnstate(EntrustInfo entrustInfo){
		entrustMapper.upEnstate(entrustInfo);
	}
	
	public EntrustInfo selectEntrustById(EntrustInfo entrustinfo){
		return entrustMapper.selectEntrustById(entrustinfo);

	}
}
