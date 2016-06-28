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
		<%@ include file="./column.jsp"%> 
	<section id="slider" class="ui-slider">
		<ul class="ui-slider-content">
		 <c:forEach items="${bannerDataList}" var="bannerData" varStatus="vs" >
			<li data-vid="${bannerData.VIDEO_ID}"><span
				style="background-image: url(${bannerData.IMG_ONE})"><em>${bannerData.NAME_ONE}</em></span></li>
		 </c:forEach>	
		</ul>
	</section>
	<section class="ui-panel">
		<h2 class="ui-arrowlink">
			${columnData.NAME_ONE}系列<span class="ui-panel-subtitle">更多无码高清</span>
		</h2>
		<ul class="ui-grid-trisect" id="vlist">
		 <c:forEach items="${videoDataList}" var="videoData" varStatus="vs" >
			<li data-vid="${videoData.VIDEO_ID}"><div
					class="ui-grid-trisect-img cover">
					<span
						style="background-image: url(${videoData.IMG_ONE})"></span>
				</div>
				<h4 class="ui-nowrap">${videoData.NAME_ONE}</h4>
				<span class="cnl-tag tag">${videoData.VIDEO_CATEGORY1}</span></li>
		</c:forEach>
		</ul>
	</section>
	<%@ include file="./footer.jsp"%> 
</body>
</html>