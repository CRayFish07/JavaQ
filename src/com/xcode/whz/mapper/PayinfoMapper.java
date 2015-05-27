package com.xcode.whz.mapper;


import java.util.List;
import org.springframework.stereotype.Repository;
import com.xcode.whz.bean.Payinfo;


@Repository
public interface PayinfoMapper {

		public int insertPayinfo(Payinfo payinfo);//插入
		public int deletePayinfo(Payinfo payinfo);//删除
		public int updatePayinfoById(Payinfo payinfo);//根据主键修改
		public Payinfo selectPayinfoById(Payinfo payinfo);//根据主键查询一个结果集
		public List selectPayinfo(Payinfo payinfo);//根据不同参数查询

}