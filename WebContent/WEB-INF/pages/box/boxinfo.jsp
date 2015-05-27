<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>box</title>
    <%@ include file="/common/taglibs.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   ${sessionScope.custid} ${message}<br/>
   保险箱中有${entrustlistsize}份加密文件,<a href="custinfo.asp">户主信息设置</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="getproduct.asp">付费</a>&nbsp;&nbsp;&nbsp;<a href="addinit.asp">添加</a>&nbsp;&nbsp;&nbsp;<a href="loginout.asp">放回</a><br/>
 <c:forEach items="${entrustlist}" var="entrust">
	<a href="getentr.asp?&entrustid=${entrust.entrustid}">${entrust.entrustid}</a>
</c:forEach> 
  </body>
</html>
