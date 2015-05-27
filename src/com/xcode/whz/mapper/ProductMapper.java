package com.xcode.whz.mapper;

import java.util.List;

import com.xcode.whz.bean.AreaInfo;
import com.xcode.whz.bean.ProductInfo;
import org.springframework.stereotype.Repository;
@Repository 
public interface ProductMapper {
	public List selectProduct(ProductInfo product);
}
