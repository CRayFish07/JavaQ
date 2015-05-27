package com.xcode.whz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xcode.whz.bean.Registertmp;
import com.xcode.whz.mapper.RegisterMapper;

@Service("RegisterService")
public class RegisterService {
	@Autowired
	RegisterMapper registerMapper;
	
	
	public Registertmp selectRegistertmp(Registertmp registertmp){
		return registerMapper.selectRegistertmp(registertmp);
	}
	
	public void insertRegistertmp(Registertmp registertmp){
		
		registerMapper.insertRegistertmp(registertmp);
	}
	
	public void deleteRegistertmp(Registertmp registertmp) throws Exception{
		try {
			registerMapper.deleteRegistertmp(registertmp);
		} catch (Exception e) {
			throw e;
		}
		
	}
}
