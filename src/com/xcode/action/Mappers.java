package com.xcode.action;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class Mappers {
	@XStreamImplicit(itemFieldName="mapper")
	List mapperList;

	public List getMapperList() {
		return mapperList;
	}

	public void setMapperList(List mapperList) {
		this.mapperList = mapperList;
	}


	


}
