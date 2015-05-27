package com.xcode.whz.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
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
import org.springframework.web.servlet.view.RedirectView;

import com.xcode.whz.bean.AddrInfo;
import com.xcode.whz.bean.EntrustInfo;
import com.xcode.whz.bean.FuckInfo;
import com.xcode.whz.service.AreaService;
import com.xcode.whz.service.CustService;
import com.xcode.whz.service.EntrustService;
import com.xcode.whz.service.FuckService;
import com.xcode.whz.service.SeqService;
import com.xcode.whz.service.SystemService;
import com.xcode.whz.util.FuckUtil;




@Controller
// 类似Struts的Action
public class EntrustController {
	protected final static Log log = LogFactory.getLog(EntrustController.class);
	@Autowired
	CustService custService;
	@Autowired
	EntrustService entrustService;
	@Autowired
	FuckService fuckService;
	@Autowired
	SeqService seqService;
	
	
	
	@RequestMapping(value="boxinfo")
	public ModelAndView boxInfo(Model model,HttpServletRequest request){
		String custid=(String)request.getSession().getAttribute("custid");
		if(!"".equals(custid)&&null!=custid){
			ModelAndView modelandview=new ModelAndView("box/boxinfo");
			modelandview.addObject("message", "");//添加一个带名的model对象
			EntrustInfo entrustInfo=new EntrustInfo();
			entrustInfo.setCustid(custid);
			entrustInfo.setState("1");
			List list=entrustService.selectEntrustByCustid(entrustInfo);
			modelandview.addObject("entrustlist",list);
			modelandview.addObject("entrustlistsize",list.size());
			request.getSession().setAttribute("entrustlistsize",list.size()+"");
			return modelandview;
		}else{
			ModelAndView modelandview=new ModelAndView("exception/nologin");
			return modelandview;
		}
		
		
	}
	
	
	@RequestMapping(value="addinit")
	public ModelAndView addInit(Model model,HttpServletRequest request){
		String url="";
		ModelAndView modelandview=new ModelAndView();
		String custid=(String)request.getSession().getAttribute("custid");
		int entrustlistsize=Integer.parseInt((String)request.getSession().getAttribute("entrustlistsize"));
		String custstate=(String)request.getSession().getAttribute("custState");
		if("8".equals(custstate)){
			 url="box/entrustre";
			 modelandview.addObject("message", "请补齐资料并付费");
		}
		if("7".equals(custstate)){
			url="box/entrustre";
			 modelandview.addObject("message", "请付费");
		}
		if("1".equals(custstate)){
			String max=(String)SystemService.systemmap.get("maxentsize");
			int maxentsize=3;
			if(!"".equals(max)&&max!=null){
			 maxentsize=Integer.parseInt(max);
			}
			if(entrustlistsize<maxentsize){//每个箱子最多?份
				 url="box/addEnt";
			}
		}
		if("0".equals(custstate)){
			
		}
		
		if(!"".equals(custid)&&null!=custid){
			
		}else{
			url="exception/nologin";
		}
		modelandview.setViewName(url);
		return modelandview;
	}
	
	
	@RequestMapping(value="en2db",method = RequestMethod.POST)
	@Transactional("whz")
	public ModelAndView en2db(String detailed,String seldistrict,String selcity,String selprovince,String sendkeys,String sendWho,String sendphone,String sendText,Model model,HttpServletRequest request){
		String url="";
		String enmsg="";
		String enkeys="";
		String mkey="";
		String mkeydb="";
		ModelAndView modelandview=new ModelAndView();
		String custid=(String)request.getSession().getAttribute("custid");
		if(!"".equals(custid)&&null!=custid){
			url="box/";
			try {
				
				mkey=FuckUtil.em(sendkeys);
				mkeydb=FuckUtil.emdb(sendkeys);
				enmsg=FuckUtil.en(mkey,sendText);
				enkeys=FuckUtil.ek(sendkeys);
			} catch (Exception e) {
				log.info("加密委托书时出现错误，客户编号："+custid);
				modelandview.setViewName("box/entrustre");
				modelandview.addObject("message","加密失败");
				return modelandview;
			}
			String entrustid=seqService.nextval("EntrustSeq");
			EntrustInfo entrustInfo=new EntrustInfo();
//			sendWho+"@|@"+sendphone+"@|@"+
			entrustInfo.setEntrustid(entrustid);
			entrustInfo.setCustid(custid);
			entrustInfo.setEntrusttext(enmsg);
			entrustInfo.setToname(sendWho);
			entrustInfo.setTophone(sendphone);
			entrustInfo.setState("1");
			entrustService.insetEnt(entrustInfo);
			FuckInfo fuckinfo=new FuckInfo();
			fuckinfo.setEntrustid(entrustid);
			fuckinfo.setFuckenc(enkeys);
			fuckinfo.setMd5ens(mkeydb);
			fuckService.insertFuck(fuckinfo);
			AddrInfo addrInfo=new AddrInfo();
			addrInfo.setAddrid(entrustid);
			addrInfo.setCitycode(selcity);
			addrInfo.setDetailed(detailed);
			addrInfo.setProvincecode(selprovince);
			addrInfo.setDistrictcode(seldistrict);
			custService.insertAddr(addrInfo);
			return new ModelAndView(new RedirectView("boxinfo.asp"));
		}else{
			url="exception/nologin";
		}
		modelandview.setViewName(url);
		return modelandview;
	}

	
	
	
	@RequestMapping(value="getentr")
	public ModelAndView getentr(String entrustid,Model model,HttpServletRequest request){
		String custid=(String)request.getSession().getAttribute("custid");
		EntrustInfo entrustInfo1=new EntrustInfo();
		entrustInfo1.setCustid(custid);
		entrustInfo1.setEntrustid(entrustid);
		EntrustInfo entrustInfo=entrustService.selectEntrustById(entrustInfo1);
		AddrInfo addrInfo=custService.selectAddr(entrustid);
		ModelAndView modelandview=new ModelAndView();
		modelandview.setViewName("box/entrustinfo");
		modelandview.addObject("entrustInfo", entrustInfo);
		modelandview.addObject("addrInfo", addrInfo);//
		modelandview.addObject("isen", "1");
		return modelandview;
	}
	
	
	
	
	
	@RequestMapping(value="dn4db")
	public ModelAndView dn4db(String sendkeys,String entrustid,Model model,HttpServletRequest request){
		String url="";
		String dnmsg="";
		String enmsg="";
		String enkeys="";
		String mkeydb="";
		String mkey="";
		ModelAndView modelandview=new ModelAndView();
		String custid=(String)request.getSession().getAttribute("custid");
		if(!"".equals(custid)&&null!=custid){
			try {
				mkeydb=FuckUtil.emdb(sendkeys);
				mkey=FuckUtil.em(sendkeys);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			FuckInfo fuckinfo=fuckService.selectFuck(entrustid);
			if(mkeydb.equals(fuckinfo.getMd5ens())){
				url="box/entrustinfo";
				
				EntrustInfo entrustInfo1=new EntrustInfo();
				entrustInfo1.setCustid(custid);
				entrustInfo1.setEntrustid(entrustid);
				EntrustInfo entrustInfo=entrustService.selectEntrustById(entrustInfo1);
				AddrInfo addrInfo=custService.selectAddr(entrustid);
				String entrusttext=entrustInfo.getEntrusttext();
				try {
					dnmsg=FuckUtil.dn(mkey,entrusttext);
				} catch (Exception e) {
					log.info("解密委托书时出现错误，客户编号："+custid);
					modelandview.setViewName("box/entrustre");
					modelandview.addObject("message","解密失败");
					return modelandview;
				}
				entrustInfo.setEntrusttext(dnmsg);
				modelandview.addObject("entrustInfo",entrustInfo);
				modelandview.addObject("addrInfo", addrInfo);
				modelandview.addObject("isen", "0");
				modelandview.setViewName(url);
			}else{
				log.info("解密委托书时出现错误，客户编号："+custid);
				modelandview.setViewName("box/entrustre");
				modelandview.addObject("message","解密失败");
				return modelandview;
				
			}
				
		}else{
			url="exception/nologin";
			modelandview.setViewName(url);
		}
		return modelandview;
	}
	
	@RequestMapping(value="delentrust")
	@Transactional("whz")
	public ModelAndView delEntrust(String sendkeys,String entrustid,Model model,HttpServletRequest request){
		String url="";
		ModelAndView modelandview=new ModelAndView();
		String custid=(String)request.getSession().getAttribute("custid");
		if(!"".equals(custid)&&null!=custid){
			url="box/entrustre";
			try {
				EntrustInfo entrustInfo1=new EntrustInfo();
				entrustInfo1.setCustid(custid);
				entrustInfo1.setEntrustid(entrustid);
				entrustInfo1.setState("0");
				entrustService.upEnstate(entrustInfo1);
				modelandview.addObject("message"," 主系统文件已销毁，备份系统文件将在3小时内销毁");
				modelandview.setViewName(url);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			url="exception/nologin";
			modelandview.setViewName(url);
		}
		return modelandview;
	}
	 
}