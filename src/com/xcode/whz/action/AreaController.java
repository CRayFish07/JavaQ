package com.xcode.whz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xcode.whz.service.AreaService;




@Controller
public class AreaController {
	@Autowired
	AreaService areaService;
	
	@RequestMapping(value="selectprovince")
	public void selectprovince(Model model,HttpServletRequest request,HttpServletResponse response){
		List list=null;
		list=areaService.selectProvince();
		JSONArray jsonArray = JSONArray.fromObject(list);
		PrintWriter out = null; 
		response.setContentType("text/html;charset=utf-8"); try {
			out = response.getWriter(); 
			out.print(jsonArray); 
			out.flush();
			} catch (IOException e) { e.printStackTrace();
			}finally { out.close();
	}
	}
	
	@RequestMapping(value="selectcity")
	public void selectcity(String code,Model model,HttpServletRequest request,HttpServletResponse response){
		List list=null;
		list=areaService.selectCity(code);	
		JSONArray jsonArray = JSONArray.fromObject(list);
		PrintWriter out = null; 
		response.setContentType("text/html;charset=utf-8"); try {
			out = response.getWriter(); 
			out.print(jsonArray); 
			out.flush();
			} catch (IOException e) { e.printStackTrace();
			} finally { out.close();
			}
	}
	
	@RequestMapping(value="selectdistrict")
	public void selectdistrict(String code,Model model,HttpServletRequest request,HttpServletResponse response){
		List list=null;
		list=areaService.selectDistrict(code);	
		JSONArray jsonArray = JSONArray.fromObject(list);
		PrintWriter out = null; 
		response.setContentType("text/html;charset=utf-8"); try {
			out = response.getWriter(); 
			out.print(jsonArray); 
			out.flush();
			} catch (IOException e) { e.printStackTrace();
			} finally { out.close();
			}
	}	
}