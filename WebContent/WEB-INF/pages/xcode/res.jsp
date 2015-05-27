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
  <center>
  <table width="90%" border="1">
  <tr><th colspan="3">${tablename}</th></tr>
 <c:forEach items="${res}" var="r">
 <tr>
 <c:if test="${r=='A'}">
 <td><input name="fuckwho" type="checkbox" value="A" onclick=""  checked="checked"/></td><td>Bean</td><td style="background-color: green;">&nbsp;&nbsp;成功生成</td>
 </c:if>
 <c:if test="${r=='B'}">
 <td><input name="fuckwho" type="checkbox" value="B" onclick="" checked="checked"/></td><td>Mapper</td><td style="background-color: green;">&nbsp;&nbsp;成功生成</td>
 </c:if>
 <c:if test="${r=='C'}">
 <td><input name="fuckwho" type="checkbox" value="C" onclick="" checked="checked"/></td><td>Service</td><td style="background-color: green;">&nbsp;&nbsp;成功生成</td>
 </c:if>
 <c:if test="${r=='D'}">
 <td><input name="fuckwho" type="checkbox" value="D" onclick="" checked="checked"/></td><td>Controller</td><td style="background-color: green;">&nbsp;&nbsp;成功生成</td>
</c:if>
 <c:if test="${r=='E'}">
 <td><input name="fuckwho" type="checkbox" value="E" onclick="" checked="checked"/></td><td>Jsp</td><td style="background-color: green;">&nbsp;&nbsp;成功生成</td>
 </c:if>
 </tr>
 </c:forEach> 
<tr> <td colspan="3" align="center"><input type="button" name="" value="返回" onclick="location.href='xcodetables.asp'"/></td></tr>
  </table>
  </center>
  </body>
  
</html>
