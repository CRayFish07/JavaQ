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
<script language="javascript">
// --列头全选框被单击---
function ChkAllClick(sonName, cbAllId){
    var arrSon = document.getElementsByName(sonName);
 var cbAll = document.getElementById(cbAllId);
 var tempState=cbAll.checked;
 for(i=0;i<arrSon.length;i++) {
  if(arrSon[i].checked!=tempState)
           arrSon[i].click();
 }
}

// --子项复选框被单击---
function ChkSonClick(sonName, cbAllId) {
 var arrSon = document.getElementsByName(sonName);
 var cbAll = document.getElementById(cbAllId);
 for(var i=0; i<arrSon.length; i++) {
     if(!arrSon[i].checked) {
     cbAll.checked = false;
     return;
     }
 }
 cbAll.checked = true;
}

// --反选被单击---
function ChkOppClick(sonName){
 var arrSon = document.getElementsByName(sonName);
 for(i=0;i<arrSon.length;i++) {
  arrSon[i].click();
 }
}
</script>
  </head>
  
  <body>
  <center>
  <form action="createbean.asp" method="post">
  <input type="hidden" name="tablename" value="${tablename}">
  <table border="1" width="80%">
  <tr><th colspan="8">${tablename}</th></tr> 
 <tr><th>Bean<INPUT name="chkAllB" id="chkAllB" title="全选" onClick="ChkAllClick('beans','chkAllB')" type="checkbox" />全选</th>
 <th>View<INPUT name="chkAllV" id="chkAllV" title="全选" onClick="ChkAllClick('views','chkAllV')" type="checkbox" />全选</th>
 <th>列名</th><th>类型</th><th>允许为空</th><th>主键</th><th>默认值</th><th>说明</th></tr> 
  	<c:forEach items="${columns}" var="col">
 <tr>
 <td align="center"><input name="beans" type="checkbox" value="${col.field}" onclick="ChkSonClick('beans','chkAllB')"/></td>
 <td align="center"><input name="views" type="checkbox" value="${col.field}" onclick="ChkSonClick('views','chkAllV')"/></td>
 <td>${col.field}</td><td>${col.type}</td><td>${col.isNULL}</td><td>${col.key}</td><td>${col.default}</td><td>${col.comment}</td></tr> 
 </c:forEach>
 <tr><td>生成文件：</td>
 <td colspan="7">
 <input name="fuckwho" type="checkbox" value="A" onclick="" checked="checked"/>Bean&nbsp;&nbsp;
 <input name="fuckwho" type="checkbox" value="B" onclick="" checked="checked"/>Mapper&nbsp;&nbsp;
 <input name="fuckwho" type="checkbox" value="C" onclick="" checked="checked"/>Service&nbsp;&nbsp;
 <input name="fuckwho" type="checkbox" value="D" onclick=""/>Controller&nbsp;&nbsp;
 <input name="fuckwho" type="checkbox" value="E" onclick=""/>Jsp&nbsp;&nbsp;
 </td></tr>
  	<tr><th colspan="8"><input type="submit" name="" value="生成"/>&nbsp;&nbsp;<input type="button" name="" value="返回" onclick="location.href='xcodetables.asp'"/></th></tr> 
  </table>
  </form>
  </center>
  </body>
  
</html>
