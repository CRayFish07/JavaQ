<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>My JSP 'boxinfo.jsp' starting page</title>
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
  ${custid}号${message}
<form action="checkpwd.asp" method="post">
<input type="password"  name="pwd"  id="pwd" value="1111"/>
<input type="hidden"  name="phoneno"  id="phoneno" value="${phoneno}"/>
<input type="hidden"  name="custid"  id="custid" value="${custid}"/>
<input type="submit" name="" value="验证"/>
</form>
  </body>
</html>