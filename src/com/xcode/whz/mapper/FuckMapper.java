package com.xcode.whz.mapper;


import com.xcode.whz.bean.FuckInfo;
import org.springframework.stereotype.Repository;
@Repository 
public interface FuckMapper {
	public FuckInfo selectFuck(String entrustid);
	public void insertFuck(FuckInfo fuckinfo);
}
