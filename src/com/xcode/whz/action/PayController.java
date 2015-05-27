package com.xcode.whz.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xcode.whz.alipay.config.AlipayConfig;
import com.xcode.whz.alipay.config.AlipayNotify;
import com.xcode.whz.alipay.config.AlipaySubmit;
import com.xcode.whz.bean.CustInfo;
import com.xcode.whz.bean.PayDetail;
import com.xcode.whz.bean.Payinfo;
import com.xcode.whz.bean.ProductInfo;
import com.xcode.whz.service.CustService;
import com.xcode.whz.service.PayDetailService;
import com.xcode.whz.service.PayinfoService;
import com.xcode.whz.service.ProductService;
import com.xcode.whz.service.SeqService;
import com.xcode.whz.util.DateTimeUtil;




@Controller
public class PayController {
	protected final static Log log = LogFactory.getLog(PayController.class);
	@Autowired
	PayinfoService payinfoService;
	@Autowired
	ProductService productService;
	@Autowired
	SeqService seqService;
	@Autowired
	PayDetailService payDetailService;
	@Autowired
	CustService custService;
	
	
	@RequestMapping(value="paynotify")
	public ModelAndView paynotify(Model model,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelandview=new ModelAndView();
	     String trade_status=request.getParameter("trade_status").trim();
	 	String payid=request.getParameter("out_trade_no");
		PayDetail paydetail=new PayDetail();
	 	 String status="";
			if("WAIT_BUYER_PAY".equals(trade_status)){status="4";}	//			WAIT_BUYER_PAY  等待买家付款 
			if("WAIT_SELLER_SEND_GOODS".equals(trade_status)){status="5";}	//			WAIT_SELLER_SEND_GOODS  买家已付款，等待卖家发货 
			if("WAIT_BUYER_CONFIRM_GOODS".equals(trade_status)){status="7";}	//			WAIT_BUYER_CONFIRM_GOODS  卖家已发货，等待买家确认 
			if("TRADE_FINISHED".equals(trade_status)){status="1";}	//			TRADE_FINISHED  交易成功结束 
			if("TRADE_CLOSED".equals(trade_status)){status="0";}	//			TRADE_CLOSED  交易中途关闭（已结束，未成功完成） 
		log.info("====="+payid+"======paynotify=========pay=========trade_status:"+status);
		Map reqMap=request.getParameterMap(); 
		Map result=new HashMap();
		 for (Object key : reqMap.keySet()) {
	            String value = ((String[])reqMap.get((String)key))[0];
 log.info("====="+payid+"======paynotify=========pay========="+(String)key+":"+value);
	            result.put((String)key, value);
	        }
		boolean f=AlipayNotify.verify(result);
		String url="pay/payfuck";
		if(f){
			modelandview.addObject("notify","success");
			url="pay/paynotify";
			log.info("====="+payid+"======paynotify=========pay=========notify:success");
		}else{
			url="pay/payfuck";
			modelandview.addObject("notify","fuck");
			log.info("====="+payid+"======paynotify=========pay=========notify:fuck");
		}
		String buyer= request.getParameter("buyer_email");
        String thirdtradeno=request.getParameter("trade_no");
        String gmt_refund=request.getParameter("gmt_refund");
        String gmt_payment=request.getParameter("gmt_payment");
        
        if(!"".equals(payid)&&payid!=null){
		paydetail.setPayid(payid);
        }
		 if(!"".equals(buyer)&&buyer!=null){
		paydetail.setBuyer(buyer);
		 }
		 if(!"".equals(thirdtradeno)&&thirdtradeno!=null){
		paydetail.setThirdtradeno(thirdtradeno);
		 }
		 if(!"".equals(gmt_payment)&&gmt_payment!=null){
			 paydetail.setGmtpayment(gmt_payment);
	        }
		 if(!"".equals(gmt_refund)&&gmt_refund!=null){
			 paydetail.setGmtrefund(gmt_refund);
		 }
		paydetail.setStatus(status);
		payDetailService.updatePayDetail(paydetail);
		Payinfo payinfo =new Payinfo();
		payinfo.setPayid(payid);
		payinfo.setState(status);
		payinfoService.updatePayinfoById(payinfo);
		if("1".equals(status)){//如果是1则更新客户资料表
			Payinfo	payinfo1=payinfoService.selectPayinfoById(payinfo);
			String custid=payinfo1.getCustid();
				CustInfo custinfo=new CustInfo();
				custinfo.setCustid(custid);
				custinfo.setEndtime(payinfo1.getEndtime());
				custinfo.setState("1");
			 custService.upCustById(custinfo);
			}
		modelandview.setViewName(url);
		return modelandview;
	}
	/**
	 * 同步对账
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * 因支付时间不一定传回,所以用notify_time通知时间作为支付时间
	 * 同步通知时间相差不大
	 */
	@RequestMapping(value="payreturn")
	public ModelAndView payreturn(Model model,HttpServletRequest request,HttpServletResponse response){
		String url="pay/payres";
		ModelAndView modelandview=new ModelAndView();
		modelandview.addObject("is_success",request.getParameter("is_success"));
		modelandview.addObject("partnerId",request.getParameter("partnerId"));
		modelandview.addObject("sign_type",request.getParameter("sign_type"));
		modelandview.addObject("sign",request.getParameter("sign"));
		modelandview.addObject("charset",request.getParameter("charset"));
		modelandview.addObject("buyer_email",request.getParameter("buyer_email"));
		modelandview.addObject("buyer_id",request.getParameter("buyer_id"));
		modelandview.addObject("notify_id",request.getParameter("notify_id"));
		modelandview.addObject("notify_time",request.getParameter("notify_time"));
		modelandview.addObject("notify_type",request.getParameter("notify_type"));
		modelandview.addObject("trade_no",request.getParameter("trade_no"));
		modelandview.addObject("subject",request.getParameter("subject"));
		modelandview.addObject("price",request.getParameter("price"));
		modelandview.addObject("total_fee",request.getParameter("total_fee"));
		modelandview.addObject("seller_email",request.getParameter("seller_email"));
		modelandview.addObject("seller_id",request.getParameter("seller_id"));
		modelandview.addObject("out_trade_no",request.getParameter("out_trade_no"));
	
//		WAIT_BUYER_PAY  等待买家付款 
//		WAIT_SELLER_SEND_GOODS  买家已付款，等待卖家发货 
//		WAIT_BUYER_CONFIRM_GOODS  卖家已发货，等待买家确认 
//		TRADE_FINISHED  交易成功结束 
//		TRADE_CLOSED  交易中途关闭（已结束，未成功完成） 
		Map reqMap=request.getParameterMap(); 
		String payid=request.getParameter("out_trade_no");
		String buyer= request.getParameter("buyer_email");
        String thirdtradeno=request.getParameter("trade_no");
        String trade_status=request.getParameter("trade_status").trim();
         
        String gmt_refund=request.getParameter("gmt_refund");
        String gmt_payment=request.getParameter("notify_time");//因支付时间不一定传回,所以用notify_time通知时间作为支付时间
		PayDetail paydetail=new PayDetail();
		paydetail.setPayid(payid);
		paydetail.setBuyer(buyer);
		paydetail.setThirdtradeno(thirdtradeno);
		 if(!"".equals(gmt_payment)&&gmt_payment!=null){
			 paydetail.setGmtpayment(gmt_payment);
	        }
		 if(!"".equals(gmt_refund)&&gmt_refund!=null){
			 paydetail.setGmtrefund(gmt_refund);
		 }
		 String status="";
			if("WAIT_BUYER_PAY".equals(trade_status)){status="4";}	//			WAIT_BUYER_PAY  等待买家付款 
			if("WAIT_SELLER_SEND_GOODS".equals(trade_status)){status="5";}	//			WAIT_SELLER_SEND_GOODS  买家已付款，等待卖家发货 
			if("WAIT_BUYER_CONFIRM_GOODS".equals(trade_status)){status="7";}	//			WAIT_BUYER_CONFIRM_GOODS  卖家已发货，等待买家确认 
			if("TRADE_FINISHED".equals(trade_status)){status="1";}	//			TRADE_FINISHED  交易成功结束 
			if("TRADE_CLOSED".equals(trade_status)){status="0";}	//			TRADE_CLOSED  交易中途关闭（已结束，未成功完成） 
			paydetail.setStatus(status);
		payDetailService.updatePayDetail(paydetail);
		Payinfo payinfo =new Payinfo();
		payinfo.setPayid(payid);
		payinfo.setState(status);
		payinfoService.updatePayinfoById(payinfo);
		if("1".equals(status)){//如果是1则更新客户资料表
			Payinfo	payinfo1=payinfoService.selectPayinfoById(payinfo);
				String custid=payinfo1.getCustid();
				CustInfo custinfo=new CustInfo();
				custinfo.setCustid(custid);
				custinfo.setEndtime(payinfo1.getEndtime());
				custinfo.setState("1");
			 custService.upCustById(custinfo);
			 HttpSession session = request.getSession(true);
			session.setAttribute("custState",custinfo.getState());
			session.setAttribute("endTime",custinfo.getEndtime());
			}
		
		log.info("====================pay");
		
		modelandview.setViewName(url);
		return modelandview;
	}
	
	@RequestMapping(value="getproduct")
	public ModelAndView getproduct(Model model,HttpServletRequest request,HttpServletResponse response){
		String url="pay/product";
		ModelAndView modelandview=new ModelAndView();
		ProductInfo product=new ProductInfo();
		product.setState("1");
		List productList=productService.selectProduct(product);
		modelandview.addObject("productList",productList);
		modelandview.setViewName(url);
		return modelandview;
	}
	
	@RequestMapping(value="paytype")
	public ModelAndView selePayType(String productid,Model model,HttpServletRequest request,HttpServletResponse response){
		String url="pay/paytype";
		ProductInfo product=new ProductInfo();
		product.setProductid(productid);
		List productList=productService.selectProduct(product);
		ModelAndView modelandview=new ModelAndView();
		modelandview.addObject("productid",productid);
		ProductInfo productinfo=(ProductInfo)productList.get(0);
		modelandview.addObject("price",productinfo.getPrice());
		modelandview.addObject("productname",productinfo.getProductname());
		modelandview.addObject("explanation",productinfo.getExplanation());
		modelandview.setViewName(url);
		return modelandview;
	}
	
	
	
	@RequestMapping(value="setorders")
	public ModelAndView setorders(String paytype,String productid,Model model,HttpServletRequest request,HttpServletResponse response){
		String custid=(String)request.getSession().getAttribute("custid");
		String cusendTime=(String)request.getSession().getAttribute("endTime");
		String url="pay/orders";
		ModelAndView modelandview=new ModelAndView();
		ProductInfo product=new ProductInfo();
		product.setProductid(productid);
		List productList=productService.selectProduct(product);
		String payid=seqService.nextval("PaySeq");
		ProductInfo pro=(ProductInfo)productList.get(0);
		if("1".equals(paytype)){
			String sHtmlText=setp(pro,payid);
			modelandview.addObject("sHtmlText",sHtmlText);
		}
		log.info("===============向支付宝跳转=====本地流水号："+payid);
		PayDetail paydetail=new PayDetail();
		paydetail.setPayid(payid);
		paydetail.setTotalfee(pro.getPrice());
		paydetail.setProductid(pro.getProductid());
		paydetail.setSeller(AlipayConfig.zfbacct);
		paydetail.setPaychannel(paytype);
		Date date=new Date();
		paydetail.setGmtcreate(DateTimeUtil.formatDatetime(date));
		paydetail.setStatus("3");
		payDetailService.insertPayDetail(paydetail);

		String startime=DateTimeUtil.formatDate(date);
		Payinfo payinfo=new Payinfo();
		payinfo.setCustid(custid);
		payinfo.setPayid(payid);
		payinfo.setPaytype(paytype);
		payinfo.setPaymoney(pro.getPrice());
		String cycle=pro.getCycle();
		if(!"".equals(cusendTime)&&cusendTime!=null){
			startime=cusendTime;
		}
		payinfo.setStarttime(startime);
		String endtime=getEndDate(startime,Integer.parseInt(cycle));
//		request.getSession().setAttribute("newEndDate", endtime);
		payinfo.setEndtime(endtime);
		payinfo.setState("3");
		payinfoService.insertPayinfo(payinfo);
		modelandview.setViewName(url);
		return modelandview;
	}
	
	
//	public static boolean insertPayDetail(ProductInfo pro,String payid){
////		PAYID	INT(11)	NO	PRI		每笔交易唯一订单号
////		TOTALFEE	VARCHAR(45)	NO			总价
////		PRODUCTID	VARCHAR(45)	YES			商品编号
////		SELLER	VARCHAR(45)	YES			收款账号
////		BUYER	VARCHAR(45)	YES			付款方账号
////		GMTCREATE	VARCHAR(45)	YES			交易创建时间
////		GMTPAYMENT	VARCHAR(45)	YES			支付时间
////		GMTREFUND	VARCHAR(45)	YES			退款时间
////		STATUS	VARCHAR(2)	YES			交易状态
//	
//		return true;
//	}
	
	
	public static String setp(ProductInfo product,String payid){
				//支付类型
				String payment_type = "1";
				//必填，不能修改
				//服务器异步通知页面路径
				String notify_url = "http://112.126.75.161:8080/whz/paynotify.asp";
				//需http://格式的完整路径，不能加?id=123这类自定义参数

				//页面跳转同步通知页面路径
//				String return_url = "http://112.126.75.161:8080/whz/payreturn.asp";
				String return_url = "http://localhost:8080/whz/payreturn.asp";
				//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

				//卖家支付宝帐户
				String seller_email = AlipayConfig.zfbacct;
				//必填

				//商户订单号
				String out_trade_no = payid;
				//商户网站订单系统中唯一订单号，必填

				//订单名称
				String subject =  product.getProductname();
				//必填

				//付款金额
				String price = product.getPrice();
				//必填

				//商品数量
				String quantity = "1";
				//必填，建议默认为1，不改变值，把一次交易看成是一次下订单而非购买一件商品
				//物流费用
				String logistics_fee = "0.00";
				//必填，即运费
				//物流类型
				String logistics_type = "EXPRESS";
				//必填，三个值可选：EXPRESS（快递）、POST（平邮）、EMS（EMS）
				
				//物流支付方式
				String logistics_payment = "SELLER_PAY";
				//必填，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
				
				//订单描述
				String body = product.getExplanation();
				
				//商品展示地址
				String show_url =  "www.wohaizai.com";
				//需以http://开头的完整路径，如：http://www.xxx.com/myorder.html

				//收货人姓名
				String receive_name =  "qwe";
				//如：张三

				//收货人地址
				String receive_address = "XX省XXX市XXX区XXX路";
				//如：XX省XXX市XXX区XXX路XXX小区XXX栋XXX单元XXX号

				//收货人邮编
				String receive_zip = "111111";
				//如：123456

				//收货人电话号码
				String receive_phone =  "1234567890";
				//如：0571-88158090

				//收货人手机号码
				String receive_mobile =  "1234567890";
				//如：13312341234
				
				
				//////////////////////////////////////////////////////////////////////////////////
				
				//把请求参数打包成数组
				Map<String, String> sParaTemp = new HashMap<String, String>();
				sParaTemp.put("service", "trade_create_by_buyer");
		        sParaTemp.put("partner", AlipayConfig.partner);
		        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
				sParaTemp.put("payment_type", payment_type);
				sParaTemp.put("notify_url", notify_url);
				sParaTemp.put("return_url", return_url);
				sParaTemp.put("seller_email", seller_email);
				sParaTemp.put("out_trade_no", out_trade_no);
				sParaTemp.put("subject", subject);
				sParaTemp.put("price", price);
				sParaTemp.put("quantity", quantity);
				sParaTemp.put("logistics_fee", logistics_fee);
				sParaTemp.put("logistics_type", logistics_type);
				sParaTemp.put("logistics_payment", logistics_payment);
				sParaTemp.put("body", body);
				sParaTemp.put("show_url", show_url);
				sParaTemp.put("receive_name", receive_name);
				sParaTemp.put("receive_address", receive_address);
				sParaTemp.put("receive_zip", receive_zip);
				sParaTemp.put("receive_phone", receive_phone);
				sParaTemp.put("receive_mobile", receive_mobile);
				
				//建立请求
				String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"post","确认");
			return sHtmlText;
	}
	
	
	
	/**
	 * 指定日期增加几个月
	 * @param startDate
	 * @param len
	 * @return
	 */
	public static String  getEndDate(String startDate,int len) {
		String newDate="";
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");  
		try {
		    Date yourDate = sd.parse(startDate);
		    Calendar c = new GregorianCalendar();  
	        c.setTime(yourDate);  
	        System.out.println(sd.format(yourDate));  
	        c.add(Calendar.MONTH, len);  
	        yourDate = c.getTime();  
	        System.out.println(sd.format(yourDate));
	        newDate= sd.format(yourDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       return newDate;
	}
}