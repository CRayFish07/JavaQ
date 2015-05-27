<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
  <head>
    <title>登陆</title>
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
<form action="checkacc.asp" method="post">
${message }
<input type="text"  name="acc"  id="acc" value="123456789"/>
<input type="hidden"  name="checkStr"  id="checkStr" value="ooko"/>
<input type="submit" name="" value="查找"/>
</form>
</body>
</html>