<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
  <%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增委托书</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css"> 
body {
background: #ccc url(images/wood.jpg) repeat top left;
font-family: Arial, Helvetica, sans-serif;
color: #444;
font-size: 12px;
color: #000;
}
</style>
<script language="javascript" type="text/javascript" src="scripts/jquery-1.7.1.min.js" charset="utf-8"></script>
<script language="javascript" type="text/javascript" src="scripts/area.js" charset="utf-8"></script>
<script  type="text/javascript">

</script>
  </head>
  
  <body onload="getArea('province',0,'');">
  <form action="en2db.asp" method="post">
   <table>
   <tr><td>接收人</td><td><input type="text" name="sendWho"/></td></tr>
   <tr><td>接收地址</td>
   <td>
  <select id="selprovince" name="selprovince" onchange="getArea('city',this.value,'');"> </select>
  <select id="selcity" name="selcity" onchange="getArea('district',this.value,'');" disabled="disabled">
  <option>-----</option>
   </select>
  <select id="seldistrict" name="seldistrict" disabled="disabled">
  <option>-----</option>
  </select>
  详细地址:<input type="text" name="detailed" id="detailed" value=""/>
   </td>
   </tr>
   <tr><td>联系方式</td><td><input type="text" name="sendphone"/></td></tr>
   <tr><td>内容</td><td></td></tr>
   <tr><td colspan="2"><textarea name="sendText" rows="20" cols="50"></textarea></td></tr>
   </table>
   <input type="submit" value="加密存放">
   <input type="text" name="sendkeys" value="">
   </form>
  <a href="boxinfo.asp">返回保险箱</a>
  </body>
</html>




