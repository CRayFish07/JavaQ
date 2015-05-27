package com.xcode.whz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xcode.whz.bean.SystemInfo;
import com.xcode.whz.mapper.SystemMapper;


@Service("SystemService")
public class SystemService {
	protected final static Log log = LogFactory.getLog(SystemService.class);
	@Autowired
	SystemMapper systemmapper;
	
	
	public static HashMap systemmap = new HashMap(); 
	public void selectSysInfo(){
		
		List list=systemmapper.selectSysInfo();
		log.info("====================加载系统参数开始====================");
		for (Object object : list) {
			SystemInfo sysinfo=(SystemInfo)object;
			systemmap.put(sysinfo.getSyskey(), sysinfo.getSysvalue());
			log.info("====系统参数：======="+sysinfo.getSystype()+"=========="+sysinfo.getSyskey()+"========="+sysinfo.getSysvalue());
		}
		log.info("====================加载系统参数结束====================");	
		
	}
	public static HashMap getSystemmap() {
		return systemmap;
	}
}