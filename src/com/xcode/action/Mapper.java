package com.xcode.action;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class Mapper {
	@XStreamAsAttribute
String resource;

public String getResource() {
	return resource;
}

public void setResource(String resource) {
	this.resource = resource;
}
}
