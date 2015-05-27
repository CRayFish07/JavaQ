package com.xcode.whz.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xcode.whz.bean.FuckInfo;
import com.xcode.whz.mapper.FuckMapper;


@Service("FuckService")
public class FuckService {
	@Autowired
	FuckMapper fuckMapper;
	
		public FuckInfo selectFuck(String entrustid){
			return fuckMapper.selectFuck(entrustid);
		}
		
		public void insertFuck(FuckInfo fuckinfo){
			fuckMapper.insertFuck(fuckinfo);
		}
}
