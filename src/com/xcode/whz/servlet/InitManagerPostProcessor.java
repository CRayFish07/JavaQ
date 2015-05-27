package com.xcode.whz.servlet;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.xcode.whz.service.SystemService;

public class InitManagerPostProcessor implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object obj, String arg1)
			throws BeansException {
		if(obj instanceof SystemService) {
			((SystemService)obj).selectSysInfo(); //调用方法加载数据
		}
		return obj;
	}

	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		return arg0;
	}

}