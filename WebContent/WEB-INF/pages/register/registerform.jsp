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
  
  <body>
  <form action="registerchk.asp" method="post">
  ${message }<br/>
  	<input type="radio" name="registertype" id="phoneregister" value="1" checked="checked">手机注册
  	<input type="radio" name="registertype" id="mailregister" value="2">邮箱注册
  	<br/>
   <input type="text" name="registerno" id="registerno" />
   <input type="submit" value="注册">
    
  </form>
 	
 	 
  </body>
</html>
