<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!-- saved from url=(0040)http://sfhb.1yt63.com/Index/wxsmpay.html -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>微信扫码支付</title>
<%@ include file="./common.jsp"%> 
<style>
body {
	background-color: #f7f7f7;
	margin: 0;
	padding: 0;
}

.header {
	width: 100%;
	text-align: center;
	height: 45px;
	line-height: 45px;
	background-color: #8f48ea;
	position: relative;
	color: white;
}

.btpc {
	width: 100%;
}

.zfzt {
	text-align: center;
	margin-top: 20px;
	margin-bottom: 36px;
}

.zfan {
	width: 60%;
	margin-left: 20%;
	background-color: #E60039;
	color: white;
	font-size: 16px;
	height: 50px;
	line-height: 50px;
	font-weight: bold;
	text-align: center;
	border-radius: 5px;
	margin-bottom: 24px;
}

.wxts {
	display: inline-block;
	line-height: 30px;
	width: 75%;
	margin-left: 12.5%;
	font-weight: 900;
	font-size: 14px;
}

#zfm {
	width: 50%;
}

.smbt {
	width: 100%;
	text-align: center;
	font-size: 18px;
	margin-bottom: 10px;
}

.smzn {
	text-align: center;
	width: 100%;
}

.smzn img {
	width: 70%;
}

#ding {
	position: fixed;
	bottom: 10px;
	right: 10px;
	z-index: 999;
	display: none;
}

#ding a img {
	width: 80px;
}

.return {
	position: absolute;
	left: 5px;
	top: 8.5px;
	height: 28px;
}
</style>
</head>
<body>
	<header id="header" class="ui-header ui-header-positive ui-border-b" onclick="location.href='wapv3/checkPay?CHANNEL_NO=${pd.CHANNEL_NO}'">
		<i class="ui-icon-return" id="return"></i>
		<h1>扫描支付</h1>
	</header>
	<img class="btpc" src="static/images/payban.png" alt="">
	<div class="zfzt">
		<div style="display: inline-block; width: 100%;">
			<img id="zfm"
				src="${payUrl}" alt="">
		</div>
	</div>
	<div class="zfan" onclick="location.href='wapv3/loadResult/${pd.CHANNEL_NO}'">已支付</div>
	<div class="wxts">
		<div class="smbt">扫码使用说明</div>
		<div style="margin-bottom: 28px;">
			1.截图或者保存本界面，苹果手机同时按住“home”键和“关机”键进行截图。</div>
		<div style="margin-bottom: 36px;">
			2.打开微信，选择“扫一扫”，然后在选择相册，点击“相册”,选中二维码的界面。如图所示。</div>
	</div>
	<div class="smzn">
		<img src="static/images/secsao.png" alt="">
	</div>
	<div class="smzn">
		<img src="static/images/firsao.png" alt="">
	</div>
	<div id="ding" onclick="window.scroll(0,0)" style="display: none;">
		<a><img src="static/images/huiding.png" alt=""></a>
	</div>
<script type="text/javascript">
window.onscroll = function () {
    var t = document.documentElement.scrollTop || document.body.scrollTop;
    if (t >= 300) {
        document.getElementById("ding").style.display = "inline";
    } else {
        document.getElementById("ding").style.display = "none";
    }
}
</script>
	<%@ include file="./footer.jsp"%> 
</body>
</html>