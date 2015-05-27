package com.xcode.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

public class CreateUtil {
	protected final static Log log = LogFactory.getLog(CreateUtil.class);
	static String urlproject="";
	static String urlbean="";
	static String urlaction="";
	static String urlmapper="";
	static String urlservice="";
	static String urlviewroot="";
	static String charset="UTF-8";
	static{
		ResourceBundle rb=ResourceBundle.getBundle("xcode");
		urlproject=rb.getString("url.project");
		urlbean=rb.getString("url.bean");
		urlaction=rb.getString("url.action");
		urlmapper=rb.getString("url.mapper");
		urlservice=rb.getString("url.service");
		urlviewroot=rb.getString("url.viewroot");
		charset=rb.getString("charset");
	}
	public static boolean createView(String tableName,ArrayList vcolumnsList){
		log.info("开始生成.jsp文件"+tableName+".jsp到"+urlviewroot+"中");
		String urltemp=urlproject+"/src/com/xcode/action/jsp.temp";
		String urlview=urlproject+"/"+urlviewroot+"/"+tableName;
		StringBuffer sbbean=new StringBuffer();
		String colName="";
		String colType="";
		String colComment="";
		HashMap hp=new HashMap();
		sbbean.append("<form name=\""+tableName+"Form\" action=\"\" method=\"post\">\n");
		for (int i = 0; i < vcolumnsList.size(); i++) {
			hp=(HashMap)vcolumnsList.get(i);
			colName=hp.get("field").toString().toLowerCase();
			colType=hp.get("type").toString().toLowerCase();
			colComment=hp.get("comment").toString().toLowerCase();
		sbbean.append("<input type=\"hidden\"  name=\""+colName+"\"  id=\""+colName+"\" value=\"${"+colName+"}\"/><!--"+colComment+"-->\n");
		}
		sbbean.append("<input type=\"submit\" name=\"\" value=\"\"/>\n");
		sbbean.append("</form>\n");
		isDir(urlview);
		XMLUtil.addview(urltemp, sbbean.toString(), urlview+"/"+tableName+".jsp");
		return true;
	}
//	 @this@
//	<form action="dn4db.asp" method="post">
//	<input type="hidden"  name="entrustid"  id="entrustid" value="${entrustInfo.entrustid}"/>
//	<input type="text" value="" name="sendkeys" id="sendkeys"/><input type="submit" name="" value="解密"/>
//	</form>
	public static boolean createController(String tableName,ArrayList columnsList){
		String serviceName=toUpperCaseFir(tableName)+"Service";
		String controllerName=toUpperCaseFir(tableName)+"Controller";
		String beanName=toUpperCaseFir(tableName);
		log.info("开始生成Controller.java文件"+controllerName+"Controller.java到"+urlaction+"中");
		//package com.xcode.whz.action;
		StringBuffer sbbean=new StringBuffer();
		sbbean.append("package "+urlaction+";\n\n");
		sbbean.append("import javax.servlet.http.HttpServletRequest;\n");
		sbbean.append("import javax.servlet.http.HttpServletResponse;\n");
		sbbean.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		sbbean.append("import org.springframework.stereotype.Controller;\n");
		sbbean.append("import org.springframework.ui.Model;\n");
		sbbean.append("import org.springframework.web.servlet.ModelAndView;\n");
		sbbean.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
		sbbean.append("import "+urlservice+"."+serviceName+";\n\n");
		sbbean.append("import "+urlbean+"."+beanName+";\n\n");
		sbbean.append("@Controller\n");
		sbbean.append("public class "+controllerName+" {\n");
		sbbean.append("\t//	protected final static Log log = LogFactory.getLog("+controllerName+".class);\n\n");
		sbbean.append("\t@Autowired\n");
		sbbean.append("\t"+serviceName+" "+toLowerCaseFir(serviceName)+";\n\n");
		sbbean.append("\t@RequestMapping(value=\"XXXXX\")\n");
		sbbean.append("\tpublic ModelAndView XXXXX(Model model,HttpServletRequest request,HttpServletResponse response){\n");
		sbbean.append("\t\tString url=\"\";//返回页面名称\n");
		sbbean.append("\t\t"+beanName+" "+toLowerCaseFir(beanName)+"="+beanName+".getRequestData(request);\n\n");
		sbbean.append("\t\tModelAndView modelandview=new ModelAndView(url);\n");
		sbbean.append("\t\treturn modelandview;\n");
		sbbean.append("\t}\n");
		sbbean.append("}\n");
		String url=urlproject+"/src/"+urlaction.replace(".", "/");
		outputFile(controllerName+".java",url,sbbean);
		return true;
	}
	
	
	public static String createSetforCtrl(String beanName,ArrayList columnsList){
		HashMap hp=null;
		String colName="";
		String colComment="";
		String comment="";
		String colType="";
		StringBuffer sbbean=new StringBuffer();
		sbbean.append("\tpublic static "+beanName+" getRequestData(HttpServletRequest request){\n");
		sbbean.append("\t\t"+beanName+" "+toLowerCaseFir(beanName)+"=new "+beanName+"();\n");
		
		for (int i = 0; i < columnsList.size(); i++) {
			 hp=(HashMap)columnsList.get(i);
				colName= hp.get("field").toString().toLowerCase();
				colComment= hp.get("comment").toString();
				colType= hp.get("type").toString().toLowerCase();
				comment="".equals(colComment)?"":"//"+colComment;
				if(colType.contains("varchar")){
					sbbean.append("\t\t"+toLowerCaseFir(beanName)+".set"+toUpperCaseFir(colName)+"(request.getParameter(\""+colName+"\"));"+comment+"\n");
				}
				if(colType.contains("int")){
					sbbean.append("\t\tString "+colName+"=request.getParameter(\""+colName+"\");\n");
					sbbean.append("\t\tif(!\"\".equals("+colName+")&&null!="+colName+"){\n");
					sbbean.append("\t\t"+toLowerCaseFir(beanName)+".set"+toUpperCaseFir(colName)+"(Integer.parseInt("+colName+"));"+comment+"\n");
					sbbean.append("\t\t}\n");
				}
			
		}
		sbbean.append("\t\treturn "+toLowerCaseFir(beanName)+";\n");
		sbbean.append("\t}\n");
		return sbbean.toString();
	}
	
	public static boolean createService(String tableName){
		String serviceName=toUpperCaseFir(tableName)+"Service";
		String mapperName=toUpperCaseFir(tableName)+"Mapper";
		String beanName=toUpperCaseFir(tableName);
		log.info("开始生成Service.java文件"+serviceName+"Service.java到"+urlservice+"中");
		HashMap hp=new HashMap();
		StringBuffer sbbean=new StringBuffer();
		String friName=toLowerCaseFir(mapperName);
		sbbean.append("package "+urlservice+";\n\n");
		sbbean.append("import java.util.List;\n");
		sbbean.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		sbbean.append("import org.springframework.stereotype.Service;\n");
		sbbean.append("import "+urlbean+"."+beanName+";\n");
		sbbean.append("import "+urlmapper+"."+mapperName+";\n\n\n");
		sbbean.append("@Service(\""+serviceName+"\")\n");
		sbbean.append("public class  "+serviceName+" {\n");
		sbbean.append("\t@Autowired\n");
		sbbean.append("\t"+mapperName+" "+friName+";\n\n");
		sbbean.append("\tpublic int insert"+beanName+"("+beanName+" "+beanName.toLowerCase()+"){//插入\n");
		sbbean.append("\t\treturn "+friName+".insert"+beanName+"("+beanName.toLowerCase()+");\n");
		sbbean.append("\t}\n\n");
		sbbean.append("\tpublic int delete"+beanName+"("+beanName+" "+beanName.toLowerCase()+"){//删除\n");
		sbbean.append("\t\treturn "+friName+".delete"+beanName+"("+beanName.toLowerCase()+");\n");
		sbbean.append("\t}\n\n");
		sbbean.append("\tpublic int update"+beanName+"ById("+beanName+" "+beanName.toLowerCase()+"){//根据主键修改\n");
		sbbean.append("\t\treturn "+friName+".update"+beanName+"ById("+beanName.toLowerCase()+");\n");
		sbbean.append("\t}\n\n");
		sbbean.append("\tpublic "+beanName+" select"+beanName+"ById("+beanName+" "+beanName.toLowerCase()+"){//根据主键查询一个结果集\n");
		sbbean.append("\t\treturn "+friName+".select"+beanName+"ById("+beanName.toLowerCase()+");\n");
		sbbean.append("\t}\n\n");
		sbbean.append("\tpublic List select"+beanName+"("+beanName+" "+beanName.toLowerCase()+"){//根据不同参数查询\n");
		sbbean.append("\t\treturn "+friName+".select"+beanName+"("+beanName.toLowerCase()+");\n");
		sbbean.append("\t}\n\n");
		sbbean.append("}\n");
		String url=urlproject+"/src/"+urlservice.replace(".", "/");
		outputFile(serviceName+".java",url,sbbean);
		return true;
	}
	/**
	 * 
	 * @param columnsList
	 * @param tableName
	 * @return
	 */
	public static boolean createMapper(ArrayList columnsList,String tableName){
		String mapperName=toUpperCaseFir(tableName)+"Mapper";
		String beanName=toUpperCaseFir(tableName);
		log.info("开始生成Mapper.java文件"+mapperName+"Mapper.java到"+urlmapper+"中");
		HashMap hp=new HashMap();
		StringBuffer sbbean=new StringBuffer();
	
		sbbean.append("package "+urlmapper+";\n\n\n");
		sbbean.append("import java.util.List;\n");
		sbbean.append("import org.springframework.stereotype.Repository;\n");
		sbbean.append("import "+urlbean+"."+beanName+";\n\n\n");
		sbbean.append("@Repository\n");
		sbbean.append("public interface "+mapperName+" {\n\n");
		sbbean.append("\t\tpublic int insert"+beanName+"("+beanName+" "+beanName.toLowerCase()+");//插入\n");
		sbbean.append("\t\tpublic int delete"+beanName+"("+beanName+" "+beanName.toLowerCase()+");//删除\n");
		sbbean.append("\t\tpublic int update"+beanName+"ById("+beanName+" "+beanName.toLowerCase()+");//根据主键修改\n");
		sbbean.append("\t\tpublic "+beanName+" select"+beanName+"ById("+beanName+" "+beanName.toLowerCase()+");//根据主键查询一个结果集\n");
		sbbean.append("\t\tpublic List select"+beanName+"("+beanName+" "+beanName.toLowerCase()+");//根据不同参数查询\n\n");
		sbbean.append("}");
		log.info("==============="+mapperName+".java==============start==============");
		log.info(sbbean.toString());
		log.info("==============="+mapperName+".java==============end==============");
		String url=urlproject+"/src/"+urlmapper.replace(".", "/");
		outputFile(mapperName+".java",url,sbbean);
		StringBuffer beangs=new StringBuffer();
		StringBuffer allcolsb=new StringBuffer();
		StringBuffer selectsb=new StringBuffer();
		StringBuffer selectsbid=new StringBuffer();
		StringBuffer insertsb=new StringBuffer();
		StringBuffer deletesb=new StringBuffer();
		StringBuffer updatesb=new StringBuffer();
		StringBuffer ifnulsb=new StringBuffer();
		log.info("开始生成Mapper.xml文件"+mapperName+"Mapper.xml到"+urlmapper+"中");
		String colName="";
		String PRI="";
	 beangs.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?> \n"
				+"<!DOCTYPE mapper\n"  
				+"PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n"  
				+"\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\"> \n\n");
	 beangs.append("<mapper namespace=\""+urlmapper+"."+mapperName+"\">\n");
	 selectsbid.append("\t<select id=\"select"+beanName+"ById\"  resultMap=\"result_"+beanName+"\">\n\t\tselect ");
	 selectsb.append("\t<select id=\"select"+beanName+"\"  resultMap=\"result_"+beanName+"\">\n\t\tselect ");
	 insertsb.append("\t<insert id=\"insert"+beanName+"\">\n");
	 insertsb.append("\t\tinsert into "+tableName.toLowerCase()+"\n");
	 deletesb.append("\t<delete id=\"delete"+beanName+"\">\n");
	 deletesb.append("\t\tdelete from "+tableName.toLowerCase()+"\n");
	 deletesb.append("\t\t<where>\n");
	 updatesb.append("\t<update id=\"update"+beanName+"ById\">\n");
	 updatesb.append("\t\tupdate  "+tableName.toLowerCase()+"\n\t\t<set>\n");
	 beangs.append("<resultMap type=\""+urlbean+"."+beanName+"\" id=\"result_"+beanName+"\">\n");
	 String byid="";
	 for (int i = 0; i < columnsList.size(); i++) {
		 hp=(HashMap)columnsList.get(i);
			colName=hp.get("field").toString().toLowerCase();
			PRI=hp.get("key").toString();
		 beangs.append("\t\t<result property=\""+colName+"\"    column=\""+colName+"\"/>\n");
		 if(i==columnsList.size()-1){
			 allcolsb.append(colName);
		 }else{
			 allcolsb.append(colName+",");
		 }
		 if("PRI".equals(PRI)){
			 byid="\t\twhere "+colName+"=#{"+colName+"}\n";
		 }else{
			 updatesb.append("\t\t<if test=\""+colName+"!=null and "+colName+"!=''\">"+colName+"=#{"+colName+"},</if>\t\n");
		 }
		 deletesb.append("\t\t<if test=\""+colName+"!=null and "+colName+"!=''\"> and "+colName+"=#{"+colName+"}</if>\n");
		 ifnulsb.append("\t\t<if test=\""+colName+"!=null and "+colName+"!=''\"> and "+colName+"=#{"+colName+"}</if>\n");
	 	}
	beangs.append("</resultMap>\n"); 
	 selectsb.append(allcolsb.toString()+" from "+tableName.toLowerCase()+"\n");
	 selectsb.append("\t\t<where>\n");
	 selectsb.append(ifnulsb.toString());
	 selectsb.append("\t\t</where>\n");
	 selectsbid.append(allcolsb.toString()+" from "+tableName.toLowerCase()+"\n");
	 selectsbid.append("\t\t"+byid+"\n");
	
	 
	 insertsb.append("\t\t("+allcolsb.toString()+") \n\t\tvalues \n\t\t(#{"+allcolsb.toString().replace(",", "}, #{")+"});\n");
	 insertsb.append("\t</insert>\n");
	 deletesb.append("\t</where>\n");
	 deletesb.append("\t</delete>\n");
	 updatesb.append("\t</set>\n"+byid);
	 updatesb.append("\t</update>\n");
	 selectsb.append("\t</select>\n");
	 selectsbid.append("\t</select>\n");
	 beangs.append(insertsb.toString());
	 beangs.append(deletesb.toString());
	 beangs.append(updatesb.toString());
	 beangs.append(selectsb.toString());
	 beangs.append(selectsbid.toString());
		beangs.append("</mapper>");
		log.info("==============="+mapperName+".xml==============start==============");
		log.info(beangs.toString());
		log.info("==============="+mapperName+".xml==============end==============");
//		String url=urlproject+"/src/"+urlmapper.replace(".", "/");
		outputFile(mapperName+".xml",url,beangs);
		createXML(mapperName);
		return true;
	}
	public static boolean createXML(String mapperName){
		String path=urlproject+"/src/mybatis_config.xml";
		String resource="\t\t<mapper resource=\""+urlmapper.replace(".","/")+"/"+mapperName+".xml\"/>\n\t</mappers>";
		String name="/"+mapperName+".xml";
		XMLUtil.addMapper(path, resource,name);
		return true;
	}
//	public static boolean createXML2(String mapperName){
//		String path=urlproject+"/WebContent/WEB-INF/mapper.xml";
//		String resource=urlmapper+"."+mapperName;
//		String mapperbean="\t<bean id=\""+mapperName+"\" class=\"org.mybatis.spring.mapper.MapperFactoryBean\">\n"
//				+"\t\t<property name=\"mapperInterface\" value=\""+resource+"\" />\n"
//				+"\t\t<property name=\"sqlSessionFactory\" ref=\"sqlSessionFactory\" />\n"
//				+"\t</bean>\n"
//				+"</beans>\n";
//				
//		XMLUtil.addMapper(path, mapperbean);
//		return true;
//	}
	/**
	 * 生成JAVABEAN文件
	 * @param columnsList
	 * @param tableName
	 * @return
	 */
	public static boolean createBean(ArrayList columnsList,String tableName){
	String beanName=toUpperCaseFir(tableName);
	log.info("开始生成JAVABEAN文件"+beanName+".java到"+urlbean+"中");
	HashMap hp=new HashMap();
	StringBuffer sbbean=new StringBuffer();
	StringBuffer beangs=new StringBuffer();
	sbbean.append("package "+urlbean+";\n\n");
	sbbean.append("import javax.servlet.http.HttpServletRequest;\n\n");
	sbbean.append("public class "+beanName+" {\n");
	String colName="";
	String colType="";
	String colComment="";
	
	for (int i = 0; i < columnsList.size(); i++) {
		hp=(HashMap)columnsList.get(i);
		colName=hp.get("field").toString().toLowerCase();
		colType=hp.get("type").toString().toLowerCase();
		colComment=hp.get("comment").toString().toLowerCase();
		sbbean.append(createProperties(colName,colType,colComment));
		beangs.append(createGetSet(colName, colType));
	}
	sbbean.append("\n\n");
	sbbean.append(createSetforCtrl( beanName, columnsList));
	sbbean.append("\n\n");
	sbbean.append(beangs.toString());
	sbbean.append("}");
	log.info("==============="+beanName+".java==============start==============");
	log.info(sbbean.toString());
	log.info("==============="+beanName+".java==============end==============");
	String url=urlproject+"/src/"+urlbean.replace(".", "/");
	outputFile(beanName+".java",url,sbbean);
	return true;
	}
	
	/**
	 * 
	 * @param colName
	 * @param colType
	 * @param colComment
	 * @return
	 */
	public static String createProperties(String colName,String colType,String colComment){
		StringBuffer beangs=new StringBuffer();
		colName=colName.toLowerCase();
		String comment="";
		if(colType.contains("varchar")){
			comment="".equals(colComment)?"":"//"+colComment;
			beangs.append("\tprivate String "+colName+";\t"+comment+"\n");
		}
		if(colType.contains("int")){
			comment="".equals(colComment)?"":"//"+colComment;
			beangs.append("\tprivate int "+colName+";\t"+comment+"\n");
		}
		return beangs.toString();
	}
	/**
	 * 获取get和 set
	 * @param colName
	 * @param type
	 * @return
	 */
	public static String createGetSet(String colName,String colType){
		StringBuffer beangs=new StringBuffer();
		colName=colName.toLowerCase();
		if(colType.contains("varchar")){
			beangs.append("\tpublic String get"+toUpperCaseFir(colName)+"() {\n");
			beangs.append(" \t\t\treturn "+colName+";\n");
			beangs.append("\t }\n");
			beangs.append("\tpublic void set"+toUpperCaseFir(colName)+"(String "+colName+") {\n");
			beangs.append(" \t\tthis."+colName+" = "+colName+";\n");
			beangs.append(" \t}\n\n");
		}
		if(colType.contains("int")){
			beangs.append("\tpublic int get"+toUpperCaseFir(colName)+"() {\n");
			beangs.append(" \t\treturn "+colName+";\n");
			beangs.append(" \t}\n");
			beangs.append("\tpublic void set"+toUpperCaseFir(colName)+"(int "+colName+") {\n");
			beangs.append("\t\t this."+colName+" = "+colName+";\n");
			beangs.append("\t }\n\n");
		}
		//TODO 其他类型的字段
		return beangs.toString();
	}
	
	/**
	 * 首字母大写
	 * @param oldStr
	 * @return
	 */
	public static String  toUpperCaseFir(String oldStr){
		String newString=oldStr.toLowerCase().substring(0,1).toUpperCase()+oldStr.toLowerCase().substring(1);
		return newString;
	}
	/**
	 * 首字母小写
	 * @param oldStr
	 * @return
	 */
	public static String  toLowerCaseFir(String oldStr){
		String newString=oldStr.substring(0,1).toLowerCase()+oldStr.substring(1);
		return newString;
	}
	/**
	 * 
	 * @param fileName
	 * @param url
	 * @param filesb
	 */
	public  static void outputFile(String fileName,String url,StringBuffer filesb){
		FileOutputStream out;
		try {
			isDir(url);
			out = new FileOutputStream(url+"/"+fileName);
			BufferedOutputStream bos=new BufferedOutputStream(out);
			bos.write(filesb.toString().getBytes(charset));
			bos.close();
		} catch (Exception e) {
			log.info("写入文件"+fileName+"失败");
			e.printStackTrace();
		}
		log.info("写入文件"+fileName+"成功");
	}
	/***
	 * 目录不存在则创建，有就跳过
	 * @param url
	 */
	public static void isDir(String url){
		File dir=new File(url);
		if(!dir.exists()){
			dir.mkdir();
		}
	}
	
	public static void main(String[] args) {
		toLowerCaseFir("AhjdhfkdjAHHHjhkj");
//			/String	project="C:\Codex\whz";
//				String url=project.replace("\\", "\\\\");
	}
	

}
