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
  <form action="setorders.asp">
  <input type="hidden" name="productid" value="${productid}"/>
  	套餐名称：${productname}   费用：${price}  套餐内容：${explanation} <br/>
	  请选择支付方式：<br/>
  	<input type="radio" name="paytype" value="1"/>
  	<img id="img4" src="images/alipay.gif" /><br/>
  	<input type="submit" name="" value="去付款"/>
  </form>
  </body>
</html>
