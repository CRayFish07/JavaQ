package com.xcode.whz.mapper;

import com.xcode.whz.bean.PayDetail;
import org.springframework.stereotype.Repository;
@Repository 
public interface PayDetailMapper {
	public void insertPayDetail(PayDetail paydetail);
	public void updatePayDetail(PayDetail paydetail);
}
