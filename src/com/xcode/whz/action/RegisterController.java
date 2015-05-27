package com.xcode.whz.action;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xcode.whz.bean.CustInfo;
import com.xcode.whz.bean.Registertmp;
import com.xcode.whz.service.CustService;
import com.xcode.whz.service.RegisterService;
import com.xcode.whz.service.SeqService;
import com.xcode.whz.util.DateTimeUtil;
import com.xcode.whz.util.FuckUtil;
import com.xcode.whz.util.MailUtil;
import com.xcode.whz.util.sms.SMSUtil;

@Controller
public class RegisterController {
	protected final static Log log = LogFactory.getLog(RegisterController.class);
	@Autowired
	RegisterService registerService;
	@Autowired
	CustService custService;
	@Autowired
	SeqService seqService;
	
	
	@RequestMapping(value="register")
	public ModelAndView forregister(String phoneno,String checkStr,Model model){
			ModelAndView modelandview=new ModelAndView("register/registerform");
			return modelandview;
	}
	
	
	@RequestMapping(value="registerchk",method=RequestMethod.POST)
	public ModelAndView registerchk(String registertype,String registerno,Model model){
		//检查是否已经注册过
		CustInfo custinfo1 =new CustInfo();
		custinfo1.setPhoneno(registerno);
		custinfo1.setEmail(registerno);
		CustInfo custinfo=custService.selectCust(custinfo1);
		if(custinfo!=null)
		{
			ModelAndView modelandview=new ModelAndView("register/registerform");
			modelandview.addObject("registertype", registertype);
			modelandview.addObject("registerno", registerno);
			modelandview.addObject("message", "此账号已注册过");//添加一个带名的model对象
			return modelandview;
		}else{
		String chkcode="123456";//验证码
		if("1".equals(registertype)){
			//调用短信发送
			String apikey="fd9f69538cb785eb997bfe00b57e2b76";
			String text="您的验证码是"+chkcode+"。如非本人操作，请忽略本短信【我还在】";
			String mobile=registerno;
			try {
				String r=SMSUtil.sendSms(apikey, text, mobile);
				log.debug(r);
			} catch (IOException e) {
				log.debug("短信发送出现异常");
				e.printStackTrace();
			}
		}else if("2".equals(registertype)){
			//调用邮件发送
			MailUtil.sendMail(registerno,chkcode);
			
		}
		String tmpid=seqService.nextval("RegisterSeq");
		Registertmp registertmp=new Registertmp();
		registertmp.setTmpid(tmpid);
		registertmp.setRegisterno(registerno);
		registertmp.setRegistertype(registertype);
		registertmp.setChkcode(chkcode);
		Date date=new Date();
		String datestr=DateTimeUtil.formatDatetime(date);
		registertmp.setIntime(datestr);
		registertmp.setState("1");
		registerService.insertRegistertmp(registertmp);
		ModelAndView modelandview=new ModelAndView("register/registerchk");
		modelandview.addObject("registertype", registertype);
		modelandview.addObject("registerno", registerno);
		return modelandview;
		}
	}
	@RequestMapping(value="chkcode")//typeregister因链接转码问题
	public ModelAndView chkcode(String chkcode,String typeregister,String registerno,Model model){
		String url="/whz";
		Registertmp registertmp=new Registertmp();
		registertmp.setRegisterno(registerno);
		registertmp.setRegistertype(typeregister);
		registertmp.setChkcode(chkcode);
		registertmp.setState("1");
		Registertmp registertmp1=registerService.selectRegistertmp(registertmp);
		if(registertmp1==null||"".equals(registertmp1.getTmpid())){
			if("1".equals(typeregister)){
			//提示验证码不正确
				ModelAndView modelandview=new ModelAndView("register/registerchk");
				modelandview.addObject("registertype", typeregister);
				modelandview.addObject("registerno", registerno);
				return modelandview;
			}else if("2".equals(typeregister)){
			//提示邮件已失效
			}
		}else{
			
			ModelAndView modelandview=new ModelAndView("register/registerpwd");
			modelandview.addObject("registertype", typeregister);
			modelandview.addObject("registerno", registerno);
			return modelandview;
		}
		ModelAndView modelandview=new ModelAndView(url);
		return modelandview;
	}
	
	@RequestMapping(value="addcust")
	@Transactional("whz")
	public ModelAndView addcust(String custpwd,String custpwd2,String registertype,String registerno,Model model) throws RuntimeException{
		String url="";
			String custpwden = "";
			String custid=seqService.nextval("CustSeq");
			try {
				custpwden = FuckUtil.em(custid+custpwd);
			
			//向cust表插入数据
			CustInfo custinfo=new CustInfo();
			
			custinfo.setCustid(custid);
			custinfo.setCustpwd(custpwden);
			custinfo.setState("8");
			if("1".equals(registertype)){
				custinfo.setPhoneno(registerno);
				}else if("2".equals(registertype)){
				 custinfo.setEmail(registerno);
				}
			Registertmp registertmp=new Registertmp();
			registertmp.setRegisterno(registerno);
		custService.insertCust(custinfo);
		registerService.deleteRegistertmp(registertmp);

			} catch (Exception e) {
				throw new RuntimeException();
			}
		ModelAndView modelandview=new ModelAndView("register/registerres");
		return modelandview;
	}
	
}
