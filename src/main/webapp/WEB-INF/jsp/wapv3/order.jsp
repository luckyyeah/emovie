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
		<style type="text/css">

input[type=radio].grayRadio {
    border: none;
    vertical-align: middle;
    width: 0.5rem;
    height: 0.2rem;
    border-radius: 50%;    
}
.yellow{color:#f60; font-style: normal; font-weight: bold}
.pink{color:#f0f;font-style: normal;font-weight: bold}
.red{color:#ff0000;font-style: normal;font-weight: bold}
h3, h4 {
    margin-bottom: 0.1rem;
}
.ui-btn-wrap {
    padding: 0px 10px;
}
		</style>
</head>
<body>
	<header id="header" class="ui-header ui-header-positive ui-border-b">
		<i class="ui-icon-return" id="return"></i>
		<h1>会员充值</h1>
	</header>
	<div class="videoimg"></div>
	<div id="checkdesk">
		<div class="info">
			<h2>
			会员选择：
			</h2>
			<h4>
			 <input type="radio" name="vipType" class="grayRadio" value="${payInfo.monthPrice}" checked="checked">
				<em class="yellow">季度会员：${payInfo.monthPrice}元</em>
			</h4>
			<h4>
			 <input type="radio" name="vipType" class="grayRadio" value="${payInfo.yearPrice}" >
				<em class="red">包年会员：${payInfo.yearPrice}元</em>
			</h4>
			<h4 style="margin-bottom: 0rem">
			 <input type="radio" name="vipType" class="grayRadio" value="${payInfo.price}" >
				<em class="pink">终生会员：${payInfo.price}元</em>
			</h4>
			<p></p>
		</div>
		<div class="ui-btn-wrap">
     <c:forEach items="${payType}" var="map">
     <c:if    test="${map.key<100}"> 
		 <c:if test="${map.key==2 }">
			<button class="ui-btn-lg ui-btn-weixin" data-role="button" data-href="thirdpay/goPay?channelNo=${pd.CHANNEL_NO}&plugin_type=${map.key}&payType=1&version=3">
		</c:if>	     
		 <c:if test="${map.key==4 }">
			<button class="ui-btn-lg ui-btn-weixin" data-role="button" data-href="ylpay/goPay?channelNo=${pd.CHANNEL_NO}&plugin_type=${map.key}&payType=1&version=3">
		</c:if>	
		 <c:if test="${map.key==3 }">
			<button class="ui-btn-lg ui-btn-weixin" data-role="button" data-href="thirdpay2/goPay?channelNo=${pd.CHANNEL_NO}&plugin_type=${map.key}&payType=1&version=3">
		</c:if>	
		 <c:if test="${map.key==5 }">
			<button class="ui-btn-lg ui-btn-weixin" data-role="button" data-href="bbpay/goPay?channelNo=${pd.CHANNEL_NO}&plugin_type=${map.key}&payType=1&version=3">
		</c:if>							
				<img src="static/images/icon_wechat.png" alt=""> 微信支付
			</button>
			</c:if>
			</c:forEach>
     <c:forEach items="${payType}" var="map">
     <c:if    test="${map.key>=100}"> 
		 <c:if test="${map.key==101 }">
			<button class="ui-btn-lg ui-btn-weixin ui-btn-alipay" data-role="button" data-href="alipay/goPay?channelNo=${pd.CHANNEL_NO}&plugin_type=${map.key}&payType=2&version=3">
		</c:if>	
		 <c:if test="${map.key==102 }">
			<button class="ui-btn-lg ui-btn-weixin ui-btn-alipay" data-role="button" data-href="bbpay/goPay?channelNo=${pd.CHANNEL_NO}&plugin_type=${map.key}&payType=2&version=3">
		</c:if>					
				<img src="static/images/icon_alipay.png" alt=""> 支付宝
			</button>
			</c:if>			
			</c:forEach>			
			<a class="ui-btn-lg ui-btn-weixin" href="wapv3/index/${pd.CHANNEL_NO}" >支付完成</a>
		</div>
		<div style="display: none">
			<a href="thirdpay2/goPay?total_fee=${payInfo.price}" id="payUrl"></a>
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
.ui-btn-alipay {
    background-color: #44b549;
    border-color: #44b549;
    background-image: -webkit-gradient(linear,left top,left bottom,color-stop(.5,#1fbaf3),to(#18b4ed));
    color: #fff;
    background-clip: padding-box;
    margin-bottom: 20px;
}
</style>

	<%@ include file="./footer.jsp"%> 
	<div style="display:none;"><script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1259746926'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1259746926%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script></div>
</body>
</html>