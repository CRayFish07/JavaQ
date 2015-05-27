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
  <form action="chkcode.asp">
  <input type="hidden" name="registerno" id="registerno" value="${registerno }">
  <input type="hidden" name="typeregister" id="typeregister" value="${registertype }">
  
	<c:if test="${registertype==1}">
	手机注册${registerno }<br/>
	验证码:<input type="text" name="chkcode" id="chkcode" />   <br/>
	<input type="submit" value="验证">
	</c:if>
	<c:if test="${registertype==2}">
	邮件注册${registerno }邮件已发送 ，请查收,并点击验证链接！
	</c:if>
	</form>
  </body>
</html>
