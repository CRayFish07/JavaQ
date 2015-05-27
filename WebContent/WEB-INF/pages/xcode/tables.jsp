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
  <br/>
  <br/>
  <br/>
  <table width="80%" border="1" align="center">
  	<c:forEach items="${tables}" var="table">
	<tr><td><a href="xcodecolumns.asp?tablename=${table.tname}">${table.tname}</a></td><td>${table.tcomment}</td></tr>
  	</c:forEach>
  	</table>
  </body>
  
</html>
