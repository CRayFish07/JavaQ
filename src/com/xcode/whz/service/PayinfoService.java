package com.xcode.whz.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xcode.whz.bean.Payinfo;
import com.xcode.whz.mapper.PayinfoMapper;


@Service("PayinfoService")
public class  PayinfoService {
	@Autowired
	PayinfoMapper payinfoMapper;

	public int insertPayinfo(Payinfo payinfo){//插入
		return payinfoMapper.insertPayinfo(payinfo);
	}

	public int deletePayinfo(Payinfo payinfo){//删除
		return payinfoMapper.deletePayinfo(payinfo);
	}

	public int updatePayinfoById(Payinfo payinfo){//根据主键修改
		return payinfoMapper.updatePayinfoById(payinfo);
	}

	public Payinfo selectPayinfoById(Payinfo payinfo){//根据主键查询一个结果集
		return payinfoMapper.selectPayinfoById(payinfo);
	}

	public List selectPayinfo(Payinfo payinfo){//根据不同参数查询
		return payinfoMapper.selectPayinfo(payinfo);
	}

}
