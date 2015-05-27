<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>漂流本子管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript" src="scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="scripts/jquery.form.js"></script>
<style type="text/css">
body {
	background: #E9E9E9;
}
</style>
<script type="text/javascript">
<!--
	$(function() {

		$('#form1').ajaxForm({
			beforeSubmit : checkForm,
			success : complete,
			dataType : 'json'
		});

		function complete(data) {
			if (data.status === 1) {
				window.location.href = "/admin/index.php?m=index&a=index";
			} else {
				alert(data.info);
				var timenow = new Date().getTime();
				var url = "captcha-image.asp?timenow=" + timenow+ "";
				$('#verifyImg').attr('src', url);
			}
		}
		function checkForm() {
			if ('' === $.trim($('#phoneno').val())) {
				//$('#result').html('用户名不能为空!').show();
				alert('用户名不能为空!');
				return false;
			}
			if ('' === $.trim($('#pwd').val())) {
				//$('#result').html('密码不能为空!').show();
				alert('密码不能为空!');
				return false;
			}
			if ('' === $.trim($('#verify').val())) {
				//$('#result').html('验证码不能为空!').show();
				alert('验证码不能为空!');
				return false;
			}
		}
		$('#verifyImg').click(function() {
			//重载验证码
			var timenow = new Date().getTime();
			var url = "captcha-image.asp?timenow=" + timenow + "";
			$('#verifyImg').attr('src', url);

		});

	});
//-->
</script>
<style type="text/css">
</style>
</head>
<body>
	<div class="login">
		<div class="version">新用户</div>
		<form method="post" name="login" id="form1" action="checkpwd.asp">
			<div class="loginbox">
<label>用户名：</label>
<input type="text" class="ipt3" id="phoneno" name="phoneno">
<label>密 码：</label>
<input type="password" class="ipt3" id="pwd" name="pwd">
<label>验证码：</label>
<input type="text" class="ipt2" id="verify" name="verify">
<img id="verifyImg" src="captcha-image.asp" border="0" alt="点击刷新验证码" style="cursor:pointer; margin-left: 1px;" align="absmiddle"> 
	<input type="hidden" name="ajax" value="1">
	<input type="submit" id="submit" value="登 录" class="btn4">
			</div>
		</form>
		<div id="result" class="red">797</div>
		<div class="shuom">
			<!-- 在操作过程中如果有问题，可以联系鱼福网寻求帮助！<a href="http://www.yufu5.com"
				target="_blank">www.yufu5.com</a> -->
		</div>
	</div>
	<style>
.tb_button {
	padding: 1px;
	cursor: pointer;
	border-right: 1px solid #8b8b8b;
	border-left: 1px solid #FFF;
	border-bottom: 1px solid #fff;
}

.tb_button.hover {
	borer: 2px outset #def;
	background-color: #f8f8f8 !important;
}

.ws_toolbar {
	z-index: 100000
}

.ws_toolbar .ws_tb_btn {
	cursor: pointer;
	border: 1px solid #555;
	padding: 3px
}

.tb_highlight {
	background-color: yellow
}

.tb_hide {
	visibility: hidden
}

.ws_toolbar img {
	padding: 2px;
	margin: 0px
}
</style>
	<div class="vimiumReset vimiumHUD"
		style="right: 150px; opacity: 0; display: none;"></div>
</body>
</html>