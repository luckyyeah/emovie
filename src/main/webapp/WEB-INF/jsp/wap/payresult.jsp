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
		<h1>跳转中</h1>
	</header>
	<div id="checkdesk">
		<div class="info">
			<h4>
				<em class="ui-txt-red">跳转中请等待。。。。。。</em>
			</h4>

			<p></p>
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
	<script>
	$(function() {
		var isPay=checkPay();

		if(isPay==0){
			
		} else {
			
			showPayError();
		}
	});
	</script>
</body>
</html>