<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" data-dpr="3" style="font-size: 124.5px;">

    <head>
			<%@ include file="./head.jsp"%> 
    </head>


<body class="member play">
    <header class="black-header">
        <a href="javascript:history.back()" class="back"></a>会员中心
    </header>
	<section class="vip">
		<a href="javascript:pay();">
			<div class="vipimg"><img src="http://ww4.sinaimg.cn/large/d2d743f1gw1f5x1gkuwosj20ku07xt9a.jpg" alt=""></div>
		</a>
	</section>
	<section>
		<div class="userinfo dot">
			<p class="gray">会员类型</p>
			<p class="userType">游客</p>
		</div>
		<div class="userinfo" onclick="pay()">
			<p class="gray">支付日期</p>
			<p class="payDate">购买VIP</p>
		</div>
	</section>
	<section class="tip clearfix userInfo">激情视频等你来看！</section>
	<section class="listLink">
		<a href="wapv2/login/${pd.CHANNEL_NO}">
			<div class="list-icon1 list">自助激活<i></i></div>
		</a>
		<a href="javascript:pay();">
			<div class="list-icon2 list">购买会员<i></i></div>
		</a>
		<a href="wapv2/goIdea/${pd.CHANNEL_NO}">
			<div class="list-icon3 list">用户反馈<i></i></div>
		</a>
		<a href="wapv2/contract/${pd.CHANNEL_NO}">
			<div class="list-icon4 list">用户协议<i></i></div>
		</a>
	</section>
<%@ include file="./footer.jsp"%> 
<%@ include file="./paybox.jsp"%> 
</body>
<script type="text/javascript">resourceType=2;</script>
<script type="text/javascript">
    $(function(){
        var userType='',userInfo='';
	if(regTime) $(".payDate").html(regTime);	
	switch(vipType){
		case 1 : userType='白银VIP'; userInfo='可浏览美图';  break;
		case 2 : userType='黄金VIP'; userInfo='可浏览美图和非钻石视频'; break;
		case 3 : userType='钻石VIP'; userInfo='可浏览美图和所有视频'; break;
		default : userType='游客'; userInfo='激情视频等你来看！';
		}
	$(".userType").html(userType);
	$(".userInfo").html(userInfo);	
    });
</script>
</html> 