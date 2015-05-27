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
  <form action="addcust.asp">
  <input type="hidden" name="registerno" id="registerno" value="${registerno }">
  <input type="hidden" name="registertype" id="registertype" value="${registertype }">
  验证成功，请设置您的密码<br/>
	设置密码:<input type="text" name="custpwd" id="custpwd" />   <br/>
	确认密码:<input type="text" name="custpwd2" id="custpwd2" />   <br/>
	<input type="submit" value="确定">
	</form>
  </body>
</html>
