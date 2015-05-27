package com.xcode.whz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xcode.whz.bean.PayDetail;
import com.xcode.whz.mapper.PayDetailMapper;


@Service("PayDetailService")
public class PayDetailService {
	@Autowired
	PayDetailMapper paydetailmapper;
	
	
	public void insertPayDetail(PayDetail paydetail){
		paydetailmapper.insertPayDetail(paydetail);
	}
	public void updatePayDetail(PayDetail paydetail){
		paydetailmapper.updatePayDetail(paydetail);
	}
}
