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
<script language="javascript" type="text/javascript" src="scripts/jquery-1.7.1.min.js" charset="utf-8"></script>
<script language="javascript" type="text/javascript" src="scripts/area.js" charset="utf-8"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css"> 
body {
background: #ccc url(images/wood.jpg) repeat top left;
font-family: Arial, Helvetica, sans-serif;
color: #444;
font-size: 12px;
color: #000;
}
</style>
  </head>
  
 <c:if test="${not empty addrInfo.provincecode }">
  <body onload="getArea('province',${addrInfo.provincecode},${addrInfo.provincecode });getArea('city',${addrInfo.provincecode },${addrInfo.citycode });getArea('district',${addrInfo.citycode },${addrInfo.districtcode });">
  </c:if>
   <c:if test="${empty addrInfo.provincecode }">
   <body onload="getArea('province',0,0);">
   </c:if>
  委托书编号：${entrustInfo.entrustid}<br/>
  接收人： <input type="text" value="${entrustInfo.toname}" name="" id=""  disabled="disabled"/><br/>
    地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:<select id="selprovince" name="selprovince" onchange="getArea('city',this.value,'');"> </select>
  <select id="selcity" name="selcity" onchange="getArea('district',this.value,'');" disabled="disabled">
  <option>-----</option>
   </select>
  <select id="seldistrict" name="seldistrict" disabled="disabled">
  <option>-----</option>
  </select>
  详细地址:<input type="text" name="detailed" id="detailed" value="${addrInfo.detailed }"/><br/> 
  委托书:<br/><textarea name="sendText" rows="20" cols="50" disabled="disabled">${entrustInfo.entrusttext}</textarea>
   <c:if test="${isen==1 }">
<form action="dn4db.asp" method="post">
<input type="hidden"  name="entrustid"  id="entrustid" value="${entrustInfo.entrustid}"/>
<input type="text" value="" name="sendkeys" id="sendkeys"/><input type="submit" name="" value="解密"/>
</form>
</c:if>
<c:if test="${isen==0 }">
<form action="dn4db.asp" method="post">
<input type="hidden"  name="entrustid"  id="entrustid" value="${entrustInfo.entrustid}"/>
<input type="hidden" name="sendkeys" id="sendkeys" value="${sendkeys}"/>
<a href="delentrust.asp?entrustid=${entrustInfo.entrustid}">销毁</a> <input type="submit" name="" value="修改"/>
</form>
</c:if>
<a href="boxinfo.asp">返回</a>
  </body>
</html>
