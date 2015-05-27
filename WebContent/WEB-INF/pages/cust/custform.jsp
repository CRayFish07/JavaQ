<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>register</title>
    <%@ include file="/common/taglibs.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="javascript" type="text/javascript" src="scripts/jquery-1.7.1.min.js" charset="utf-8"></script>
<script language="javascript" type="text/javascript" src="scripts/area.js" charset="utf-8"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <c:if test="${not empty addrInfo.provincecode }">
  <body onload="getArea('province',${addrInfo.provincecode},${addrInfo.provincecode });getArea('city',${addrInfo.provincecode },${addrInfo.citycode });getArea('district',${addrInfo.citycode },${addrInfo.districtcode });">
  </c:if>
   <c:if test="${empty addrInfo.provincecode }">
   <body onload="getArea('province',0,0);">
   </c:if>
     <form action="custset.asp" method="post">
  	
  户主姓名: <input type="text" name="custname" id="custname" value="${custinfo.custname }"/><br/>
  性别：	<input type="radio" name="sex"  value="1" checked="checked">男
  		<input type="radio" name="sex"  value="0">女<br/>
  手机号码: <input type="text" name="phoneno" id="phoneno" value="${custinfo.phoneno }"/><br/>
  电子邮箱: <input type="text" name="email" id="email" value="${custinfo.email }"/><br/>
  地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:<select id="selprovince" name="selprovince" onchange="getArea('city',this.value,'');"> </select>
  <select id="selcity" name="selcity" onchange="getArea('district',this.value,'');" disabled="disabled">
  <option>-----</option>
   </select>
  <select id="seldistrict" name="seldistrict" disabled="disabled">
  <option>-----</option>
  </select>
  详细地址:<input type="text" name="detailed" id="detailed" value="${addrInfo.detailed }"/><br/> 
   <input type="submit" value="修改">  <a href="boxinfo.asp">返回保险箱</a>
    
  </form>
 	
 	 
  </body>
</html>
