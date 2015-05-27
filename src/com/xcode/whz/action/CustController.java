package com.xcode.whz.action;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xcode.whz.bean.AddrInfo;
import com.xcode.whz.bean.CustInfo;
import com.xcode.whz.service.CustService;
import com.xcode.whz.service.SeqService;




@Controller
@Transactional("whz")
public class CustController {
	protected final static Log log = LogFactory.getLog(CustController.class);
	@Autowired
	CustService custService;
	@Autowired
	SeqService seqService;
	
	
	
	@RequestMapping(value="custinfo")
	public ModelAndView boxInfo(Model model,HttpServletRequest request){
		String custid=(String)request.getSession().getAttribute("custid");
		CustInfo custinfo=new CustInfo();
		custinfo.setCustid(custid);
		CustInfo custinfo1=custService.selectCust(custinfo);
		AddrInfo addrInfo=custService.selectAddr(custid);
		ModelAndView modelandview=new ModelAndView("cust/custform");
		modelandview.addObject("custinfo", custinfo1);//
		modelandview.addObject("addrInfo", addrInfo);//
		return modelandview;
		
	}
	@RequestMapping(value="custset" ,method=RequestMethod.POST)
	@Transactional("whz")
	public ModelAndView custset(String detailed,String seldistrict,String selcity,String selprovince,
			String email,String phoneno,String sex,String custname,Model model,HttpServletRequest request){
		String custid=(String)request.getSession().getAttribute("custid");
		String custState=(String)request.getSession().getAttribute("custState");
		
		CustInfo custinfo=new CustInfo();
		custinfo.setCustid(custid);
		custinfo.setCustname(custname);
		custinfo.setEmail(email);
		custinfo.setPhoneno(phoneno);
		custinfo.setSex(sex);
		custinfo.setState("7");
		AddrInfo addrInfo=new AddrInfo();
		addrInfo.setAddrid(custid);
		addrInfo.setCitycode(selcity);
		addrInfo.setDetailed(detailed);
		addrInfo.setProvincecode(selprovince);
		addrInfo.setDistrictcode(seldistrict);
		custService.upCustById(custinfo);
		if("8".equals(custState)){
			custService.insertAddr(addrInfo);
			request.getSession().setAttribute("custState", "7");
		}else{
			custService.upAddrById(addrInfo);
		}
		
		ModelAndView modelandview=new ModelAndView("cust/custres");
		return modelandview;
		
	}
	 
}