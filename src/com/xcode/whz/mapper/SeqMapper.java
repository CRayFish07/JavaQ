package com.xcode.whz.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import org.springframework.stereotype.Repository;
@Repository 
public interface SeqMapper {
	
	
	@Select("select nextval('${seqtype}') as seqno") 
	public String nextval(@Param("seqtype") String seqtype);

}
