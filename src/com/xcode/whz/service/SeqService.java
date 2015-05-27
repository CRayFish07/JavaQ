package com.xcode.whz.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xcode.whz.mapper.SeqMapper;


@Service("SeqService")
public class SeqService {
	@Autowired
	SeqMapper seqMapper;
	
	public String  nextval(String seqtype){
		return seqMapper.nextval(seqtype);
	}
	
}
