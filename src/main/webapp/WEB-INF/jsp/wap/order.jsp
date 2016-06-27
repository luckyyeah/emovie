<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
		<%@ include file="./common.jsp"%> 
</head>
<body>
	<header id="header" class="ui-header ui-header-positive ui-border-b">
		<i class="ui-icon-return" id="return"></i>
		<h1>会员充值</h1>
	</header>
	<div class="videoimg"></div>
	<div id="checkdesk">
		<div class="info">
			<h4>
				资费：<em class="ui-txt-red">${payInfo.price}元/终身会员</em>
			</h4>
			<p class="ui-txt-red">（只需支付一次，享受所有影片观看权限！）</p>
			<p>
				VIP注意事项：<br>1.未满18岁用户，禁止购买观看。<br>2.一次性支付，享有所有影片观看权限。
			</p>
			<p></p>
		</div>
		<div class="ui-btn-wrap">
			<button class="ui-btn-lg ui-btn-weixin" data-role="button" data-href="thirdpay/goPay?total_fee=${payInfo.price}">
				<img src="static/images/icon_wechat.png" alt=""> 微信支付
			</button>
		</div>
		<div style="display: none">
			<a href="thirdpay/goPay?total_fee=${payInfo.price}" id="payUrl"></a>
		</div>
	</div>
	<style>
.iframediv {
	position: absolute;
	top: 45px;
	left: 0;
}

.scan {
	background: url(http://5jrs.com/361/img/loading.gif) no-repeat center
		25%;
	background-size: 120px 120px;
}

.ui-icon-close-page {
	position: absolute;
	right: 7px;
	top: 0;
}
</style>
	<%@ include file="./footer.jsp"%> 
</body>
</html>