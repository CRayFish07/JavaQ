package com.xcode.whz.mapper;

import java.util.List;



import com.xcode.whz.bean.AreaInfo;
import org.springframework.stereotype.Repository;
@Repository 
public interface AreaMapper {
	
	public List selectProvince();
	public List selectCity(String provincecode);
	public List selectDistrict(String citycode);
}
