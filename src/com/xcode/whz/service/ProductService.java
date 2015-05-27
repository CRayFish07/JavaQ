package com.xcode.whz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xcode.whz.action.AreaController;
import com.xcode.whz.bean.AreaInfo;
import com.xcode.whz.bean.ProductInfo;
import com.xcode.whz.mapper.AreaMapper;
import com.xcode.whz.mapper.ProductMapper;


@Service("ProductService")
public class ProductService {
	@Autowired
	ProductMapper Productapper;
	
	public List selectProduct(ProductInfo product){
		return Productapper.selectProduct(product);
	}
}
