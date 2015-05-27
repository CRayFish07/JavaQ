package com.xcode.whz.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;



public class IsLoginFilter  extends OncePerRequestFilter{

	private String nologinurl="|chkcode|selectdistrict|selectcity|selectprovince|addcust|registerchk|checkpwd|checkacc|getbox|register|nologin|"; 
	public String getNologinurl() {
		return nologinurl;
	}
	public void setNologinurl(String nologinurl) {
		this.nologinurl = nologinurl;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException{
//		String url = request.getRequestURI();
////		/whz/checkpwd.asp
//		url = url.substring(url.lastIndexOf('/') + 1, url.lastIndexOf('.'));
//		if(!nologinurl.contains("|"+url+"|")){
//			SessionTimeOutManager sessiontimeoutmanager=new SessionTimeOutManager();
//				if(!sessiontimeoutmanager.checkSessionTimeOut(request)){
//					response.sendRedirect("nologin.asp");
//				}else{
//					filterChain.doFilter( request, response );
//					
//				}
//				
//				
//		}else{
			filterChain.doFilter( request, response );
//		}
		
		
	}

}
