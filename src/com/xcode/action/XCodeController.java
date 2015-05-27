package com.xcode.action;


import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.xcode.db.JDBCUtil;
import com.xcode.whz.bean.ProductInfo;



@Controller
public class XCodeController {
	protected final static Log log = LogFactory.getLog(XCodeController.class);
		
	
	@RequestMapping(value="xcode")
	public ModelAndView xcode(String str,Model model,HttpServletRequest request){
		if(str!=null){
			HttpSession session = request.getSession(true);
			log.info("xcode登陆成功！");
			long systime=System.currentTimeMillis();
			session.setAttribute("lastAccessTime",String.valueOf(systime));
			session.setAttribute("custid","xcj");
			return new ModelAndView(new RedirectView("xcodetables.asp"));
		}else{
			ModelAndView modelandview=new ModelAndView("xcode/index");
			modelandview.addObject("message", "验证失败！");//添加一个带名的model对象
			return modelandview;
		}
	}
		@RequestMapping(value="xcodetables")
		public ModelAndView xcodetables(Model model){
			ArrayList list=new ArrayList();
			list=JDBCUtil.getAllTable();
			ModelAndView modelandview=new ModelAndView("xcode/tables");
			modelandview.addObject("tables", list);//添加一个带名的model对象
			return modelandview;
	}
		@RequestMapping(value="xcodecolumns")
		public ModelAndView xcodecolumns(String tablename,Model model,HttpServletRequest request){
		
			ArrayList list=new ArrayList();
			list=JDBCUtil.getAllCol(tablename);
			ModelAndView modelandview=new ModelAndView("xcode/columns");
			modelandview.addObject("columns", list);//添加一个带名的model对象
			modelandview.addObject("tablename", tablename);
			HttpSession session = request.getSession();
			session.setAttribute("tablename", tablename);
			session.setAttribute("columnsList", list);
			return modelandview;
	}
	
		@RequestMapping(value="createbean")
		public ModelAndView createbean(String[] fuckwho,String[] beans,String[] views,ProductInfo pro,Model model,HttpServletRequest request){
			HttpSession session = request.getSession();
			String tablename=(String)session.getAttribute("tablename");
			ArrayList list=new ArrayList();
			list =(ArrayList)session.getAttribute("columnsList");
			HashMap hp=new HashMap();
			String colName="";
			ArrayList columnsList=new ArrayList();
			//获取选中列的属性
			if(beans!=null){
			for (int i = 0; i < beans.length; i++) {
				colName=beans[i];
				for (int j= 0; j < list.size(); j++) {
					hp=(HashMap)list.get(j);
					if(colName.equals(hp.get("field"))){
						columnsList.add(hp);
					}
				}
			}
			}
			ArrayList vcolumnsList=new ArrayList();
			//获取选中列的属性
			if(views!=null){
			for (int i = 0; i < views.length; i++) {
				colName=views[i];
				for (int j= 0; j < list.size(); j++) {
					hp=(HashMap)list.get(j);
					if(colName.equals(hp.get("field"))){
						vcolumnsList.add(hp);
					}
				}
			}
			}
			if(fuckwho!=null){
			for (int i = 0; i < fuckwho.length; i++) {
				if("A".equals(fuckwho[i])){
					CreateUtil.createBean(columnsList,tablename);
				}else if("B".equals(fuckwho[i])){
					CreateUtil.createMapper(columnsList,tablename);
					}else if("C".equals(fuckwho[i])){
						CreateUtil.createService(tablename);
						}else if("D".equals(fuckwho[i])){
							CreateUtil.createController(tablename,columnsList);
							}else if("E".equals(fuckwho[i])){
								CreateUtil.createView(tablename.toLowerCase(),vcolumnsList);
							}
			}
			}
			
			
			
			
			
			ModelAndView modelandview=new ModelAndView("xcode/res");
			modelandview.addObject("res",fuckwho);
			modelandview.addObject("tablename",tablename);
			return modelandview;
		}
		
}
