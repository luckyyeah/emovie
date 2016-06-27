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
		<ul class="ui-tiled ui-border-t">
		 <c:forEach items="${columnDataList}" var="column" varStatus="vs" >
			<li data-href="wapmovie/listColumnVideo/${pd.CHANNEL_NO}/${column.COLUMN_ID}" <c:if test="${column.COLUMN_ID==pd.COLUMN_ID}">class="active" </c:if>>
			<c:if test="${vs.index==0}">
			<i class="ui-icon-emo"></i>${column.NAME_ONE}</li>
			</c:if>
			<c:if test="${vs.index==1}">
			<i class="ui-icon-dressup"></i>${column.NAME_ONE}</li>
			</c:if>
			<c:if test="${vs.index==2}">
			<i class="ui-icon-wallet"></i>${column.NAME_ONE}</li>
			</c:if>			
		 </c:forEach>
			<li data-href="about.html"><i class="ui-icon-personal"></i>关于</li>
		</ul>
	</header>
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