package com.xcode.whz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.xcode.whz.bean.CustInfo;
import com.xcode.whz.service.CustService;
import com.xcode.whz.service.EntrustService;
import com.xcode.whz.util.FuckUtil;




@Controller
// 类似Struts的Action
public class LoginController {
	protected final static Log log = LogFactory.getLog(LoginController.class);
	@Autowired
	CustService custService;
	@Autowired
	EntrustService entrustService;
	
	
	
	@RequestMapping(value="getbox")
	public ModelAndView forLogin(String acc,String checkStr,Model model){
			ModelAndView modelandview=new ModelAndView("login/checkacc");
			return modelandview;
	}
	
	
	@RequestMapping(value="checkaccjq")
	public void phoneLoginjq(String acc,Model model,HttpServletRequest request,HttpServletResponse response){
	
		CustInfo custinfo1 =new CustInfo();
		custinfo1.setPhoneno(request.getParameter("acc"));
		custinfo1.setEmail(acc);
		CustInfo custinfo =custService.selectCust(custinfo1);
		HashMap map=new HashMap();
		if(custinfo!=null){
			map.put("next", "1");//添加一个带名的model对象
			map.put("message", "保险箱已找到");//添加一个带名的model对象
			map.put("custid", custinfo.getCustid());//添加一个带名的model对象
			map.put("acc", acc);//添加一个带名的model对象
			log.info(custinfo.getCustid()+"保险箱已找到");
	}else{
			map.put("next", "0");
			map.put("message", "号码未开通!");//添加一个带名的model对象
			log.info("号码未开通!");
	}
	JSONArray jsonArray = JSONArray.fromObject(map);
	PrintWriter out = null; 
		response.setContentType("text/html;charset=utf-8"); try {
		out = response.getWriter(); 
		out.print(jsonArray); 
		out.flush();
		
		} catch (IOException e) { e.printStackTrace();
		}finally { out.close();
		}
	}
	
	@RequestMapping(value="checkacc")//,method = RequestMethod.POST
	public ModelAndView phoneLogin(String acc,String checkStr,Model model){
		CustInfo custinfo1 =new CustInfo();
		custinfo1.setPhoneno(acc);
		custinfo1.setEmail(acc);
		CustInfo custinfo =custService.selectCust(custinfo1);
		if(custinfo!=null){
			ModelAndView modelandview=new ModelAndView("login/checkpwd");
			modelandview.addObject("message", "保险箱已找到");//添加一个带名的model对象
			modelandview.addObject("custid", custinfo.getCustid());//添加一个带名的model对象
			modelandview.addObject("acc", acc);//添加一个带名的model对象
			log.info(custinfo.getCustid()+"保险箱已找到");
			return modelandview;
		}else{
			ModelAndView modelandview=new ModelAndView("login/checkacc");
			modelandview.addObject("message", "号码未开通!");//添加一个带名的model对象
			log.info("号码未开通!");
			return modelandview;
		}
	}
	
	@RequestMapping("checkpwd")
	public ModelAndView checkPwd(String custid,String pwd,Model model, HttpServletRequest request){
		CustInfo custinfo1 =new CustInfo();
		custinfo1.setCustid(custid);
	try {
		String pwden=FuckUtil.em(custid+pwd);
		custinfo1.setCustpwd(pwden);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		CustInfo custinfo =custService.selectCust(custinfo1);
		if(custinfo!=null){
			HttpSession session = request.getSession(true);
			log.info("用户号："+custinfo.getCustid()+"登陆成功！");
			session.setAttribute("custid", custinfo.getCustid());
			session.setAttribute("custname", custinfo.getCustname());
			long systime=System.currentTimeMillis();
			session.setAttribute("lastAccessTime",String.valueOf(systime));
			session.setAttribute("custState",custinfo.getState());
			session.setAttribute("endTime",custinfo.getEndtime());
			ModelAndView modelandview=new ModelAndView("box/boxinfo");
			modelandview.addObject("message", "验证成功！");//添加一个带名的model对象
			return new ModelAndView(new RedirectView("boxinfo.asp"));
		}else{
			ModelAndView modelandview=new ModelAndView("login/checkpwd");
			modelandview.addObject("message", "密码不正确!");//添加一个带名的model对象
			modelandview.addObject("custid", custid);//添加一个带名的model对象
			return modelandview;
		}
	}
	
	
	@RequestMapping(value="loginout")
	public ModelAndView loginOut(Model model, HttpServletRequest request){
			HttpSession session = request.getSession(false);
			session.removeAttribute("custid");
			session.invalidate();
			session = null;
			ModelAndView modelandview=new ModelAndView("login/checkacc");
			modelandview.addObject("message", "已放回");//添加一个带名的model对象
			return modelandview;
	}
	@RequestMapping(value="nologin")
	public ModelAndView nologin(String acc,String checkStr,Model model){
		ModelAndView modelandview=new ModelAndView("exception/nologin");
		return modelandview;
	}
	
//		
//		@ModelAttribute("strr")
//		public String setStr(){
//			System.err.println("ModelAttribute('strr)");
//			return "fuck";
//		}
//		
//		
//	    @RequestMapping ( "setSessionAttribute")
//	    public void setSessionAttribute(@ModelAttribute ( "strr" ) String hello,Map<String, Object> map, Writer writer) throws IOException {
//	       User user = new User(1, "user" ,"1111");
//	       User user1 = new User(2, hello,"2222" );
//	       System.out.println("setSessionAttribute");
//	       map.put( "user" , user);
//	       map.put( "user1" , user1);
//	       writer.write( "over." );
//	    }
//
//	 
//
//	    @RequestMapping ( "useSessionAttribute" )
//	    public void useSessionAttribute(Writer writer, @ModelAttribute ( "user1" ) User user1) throws IOException {
//	       writer.write(user1.getUserid() + "--------" + user1.getPhoneno());
//	       writer.write( "\r" );
//	    }
//	    
//	    @ModelAttribute("strr2")
//		public String setStr1(){
//			System.err.println("ModelAttribute('strr2)");
//			return "fuck2";
//		}
//
//	    @RequestMapping ( "useSessionAttribute2" )
//	    public void useSessionAttribute(Writer writer, @ModelAttribute ( "user1" ) User user1, @ModelAttribute User user, HttpSession session) throws IOException {
//	    	 writer.write(user1.getUserid() + "--------" + user1.getPhoneno());
//	       writer.write( "\r" );
//	       writer.write( "\r" );
//	       writer.write(user.getUserid() + "--------" + user.getPhoneno());
//	       writer.write( "\r" );
//	       Enumeration enume = session.getAttributeNames();
//	       while (enume.hasMoreElements())
//	           writer.write(enume.nextElement() + " \r" );
//	    }
//
//	    @RequestMapping ( "useSessionAttribute3" )
//	    public void useSessionAttribute( @ModelAttribute ( "user2" ) User user) {
//
//	    }
	 
}