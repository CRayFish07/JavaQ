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
${is_success}<br/>
${partnerId}<br/>
${sign_type}<br/>
${sign}<br/>
${charset}<br/>
${buyer_email}<br/>
${buyer_id}<br/>
${notify_id}<br/>
${notify_time}<br/>
${notify_type}<br/>
${trade_no}<br/>
${subject}<br/>
${price}<br/>
${total_fee}<br/>
${seller_email}<br/>
${seller_id}<br/>
${out_trade_no}<br/>
  支付结果页面！<a href="getbox.asp">提取*查看</a>
  </body>
</html>
