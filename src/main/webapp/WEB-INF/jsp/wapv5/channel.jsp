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
	<style>
.hb li {
	width: 49.9%;
}

.hb li .ui-grid-trisect-img {
	padding-top: 60%;
}

.ui-slider-indicators li {
	background-color: white;
}

.ui-slider-indicators li.current:before {
	background-color: #ff6537;
	left: 0px;
	top: 0px;
	width: 7px;
	height: 7px;
}

.ui-slider-indicators {
	-webkit-box-pack: center;
	right: 0;
}

.ui-arrowlink:before {
	display: none;
}
</style>	
	<div class="paytip" style="text-align: center; display: none;"></div>
	<section class="ui-panel">
		<h2 class="ui-arrowlink"
			onclick="javascript:goToPay();"
			style="color: #ff6537; font-weight: 200; height: 44px;">
			<img src="static/images/Bitmap.png" alt=""
				style="width: 20%; position: relative; top: 6px; margin-right: 6px;">
			精品推荐 <span class="ui-panel-subtitle"><img
				src="static/images/cwvip.png" alt=""
				style="height: 18px; position: absolute; top: 12px; right: 12px;"></span>
		</h2>
		<ul class="ui-grid-trisect hb" id="vlist">
		 <c:forEach items="${videoDataList}" var="videoData" varStatus="vs" >
			<li data-vid="${videoData.VIDEO_ID}"><div class="ui-grid-trisect-img">
					<span
						style="background-image: url(${videoData.IMG_ONE})"></span>
				</div>
				<h4 class="ui-nowrap">${videoData.NAME_ONE}</h4>
				<!-- <p class="ui-nowrap ui-txt-info">...</p> -->
				<span class="cnl-tag"><fmt:formatNumber value="${videoData.VIDEO_VIEW_TIMES}" type="number" pattern="#0"/>次</span>
			<compa>
			<img src="static/images/sb2x.png" alt="" class="zxjb"></compa></li>				
		</c:forEach>
		</ul>
	</section>
	<%@ include file="./footer.jsp"%> 
	<script type="text/javascript">
	setCookie("COLUMN_ID", "${pd.COLUMN_ID}", "d999");
	</script>
</body>
</html>