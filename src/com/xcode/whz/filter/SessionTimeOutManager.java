package com.xcode.whz.filter;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xcode.whz.service.SystemService;


public class SessionTimeOutManager{
	
	//默认session失效时间 10分钟
	private long keepTime=600000;
	
	public boolean checkSessionTimeOut(HttpServletRequest request){
		HttpSession session=request.getSession();
		String  custid=(String)session.getAttribute("custid");
		if(custid!=null&&!"".equals(custid)){
		String s=(String)SystemService.systemmap.get("outtime");
			if(!"".equals(s)&&s!=null)
			{
				keepTime=Long.parseLong(s);
			}
			//系统当前时间
			long systime=System.currentTimeMillis();
			//获取上次请求时间
			long lastAccessTime=Long.parseLong((String)session.getAttribute("lastAccessTime"));
			//初始访问时间
			if(lastAccessTime==0)lastAccessTime=systime;
			//校验session失效
			if(validSessionTime(lastAccessTime,systime)){
				//保存本次请求时间
				session.setAttribute("lastAccessTime", String.valueOf(systime));
			}else{
				//session销毁
				session.removeAttribute("custid");
				session.invalidate();
				session = null;
				return false;
			}
		}else{
			
			return false;
		}
		return true;
	}
	
	/**
	 * 校验session是否超时
	 */
	private boolean validSessionTime(long lastAccessTime,long systime){

		if((systime-lastAccessTime)<keepTime)
			return true;
		
		return false;
		
	}
	
	public long getKeepTime() {
		return keepTime;
	}

	public void setKeepTime(long keepTime) {
		this.keepTime = keepTime;
	}

}
