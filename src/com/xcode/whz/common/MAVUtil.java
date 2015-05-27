package com.xcode.whz.common;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.web.servlet.ModelAndView;

public class MAVUtil {

	
	
	public static ModelAndView  getMAV(String pagename,Map var){
		String viewname="index";//默认返回首页
		if(!"".equals(pagename)&&null!=pagename){
			viewname=pagename;
		}
		
		ModelAndView modelandview=new ModelAndView(viewname);
		if(var!=null){
			Set set = var.entrySet();
	        for (Iterator it = set.iterator(); it.hasNext();) {
	            Map.Entry entry = (Map.Entry) it.next();
	            System.out.println(entry.getKey() + "--->" + entry.getValue());
	            modelandview.addObject((String)entry.getKey(),entry.getValue());
	        }
			
		}
		return modelandview;
	}
}
