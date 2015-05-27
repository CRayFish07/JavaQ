package com.xcode.whz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xcode.whz.action.AreaController;
import com.xcode.whz.bean.AreaInfo;
import com.xcode.whz.mapper.AreaMapper;


@Service("AreaService")
public class AreaService {
	@Autowired
	AreaMapper Areamapper;
	
	public List selectProvince(){
		return Areamapper.selectProvince();
	}
	public List selectCity(String provincecode){
		return Areamapper.selectCity(provincecode);
	}
	public List selectDistrict(String citycode){
		return Areamapper.selectDistrict(citycode);
	}
}
