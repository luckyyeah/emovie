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
		<%@ include file="../wap/common.jsp"%> 

</head>
<body id="play">
	<header class="ui-header ui-header-positive">
		<i class="ui-icon-return" id="return"></i>
		<h1 class="ui-nowrap ui-whitespace">视频播放</h1>
	</header>
	<div class="container">
		<input type="hidden" name="videoId" id="videoId" value="${pd.VIDEO_ID}">
		<div id="playTip"></div>
		<div id="playerwrap"></div>
		<div id="playTip2"></div>
	</div>
	<%@ include file="./footer.jsp"%> 
</body>
</html>