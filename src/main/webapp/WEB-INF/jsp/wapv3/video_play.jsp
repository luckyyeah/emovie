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
<style> /*body,html{overflow: hidden;width: 100%;}*/
html{width: 100%;}
.swiper-container {
	width: 100%;
	height: 100%;
}

.swiper-slide {
	text-align: center;
	font-size: 18px;
	background: #fff;
	width: 40%;
	/* Center slide text vertically */
	display: -webkit-box;
	display: -ms-flexbox;
	display: -webkit-flex;
	display: flex;
	-webkit-box-pack: center;
	-ms-flex-pack: center;
	-webkit-justify-content: center;
	justify-content: center;
	-webkit-box-align: center;
	-ms-flex-align: center;
	-webkit-align-items: center;
	align-items: center;
}

.swiper-slide:nth-child(2n) {
	width: 40%;
}

.swiper-slide:nth-child(3n) {
	width: 40%;
}

.swiper-slide {
	line-height: 1;
}

.swiper-slide img {
	width: 90%;
	padding: 5px 5px 0;
}

.swiper-slide p {
	font-size: 12px;
	color: #333;
	overflow: hidden;
	line-height: 1.5;
	height: 20px;
}
.container{
padding-top: 5px;
}
</style>
</head>
<body>
		<%@ include file="./column.jsp"%> 
	<header id="header" class="ui-header ui-header-positive ui-border-b">
		<i class="ui-icon-return" id="return"></i>
		<h1>视频播放</h1>
	</header>
	<div class="container" >
	<div style="background-color: white; padding-bottom: 10px; text-align: center; position: relative;">
		<input type="hidden" name="videoId" id="videoId" value="${pd.VIDEO_ID}">
		<div id="playerwrap"></div>
		<img src="${videoData.IMG_ONE}" alt=""
			style="width: 100%;display:none;" id="videoImage" data-vid="${videoData.VIDEO_ID}"><img src="http://0829img.xyzjtj.com/tuku/playmp4.png" class="playmp4"
			style="display:none;" id="vdcont" data-vid="${videoData.VIDEO_ID}">
	</div>
	</div>
	<div  style="background-color: white; padding-bottom: 10px; text-align: center; position: relative;">
		<div class="button01" onclick='javascript:goToPay()' id="noVipTimeInfo" >非会员只能试看20秒，成为会员观看完整版</div>
	</div>
	<div>
		<h1 class="pltil" style="height: 40px; line-height: 40px;">
			<p style="background-image: url('http://0829img.xyzjtj.com/tuku/left.png')"></p>
			猜你喜欢
			<p></p>
		</h1>
		<h5 class="pltil"
			style="color: #999; color: #999; padding-bottom: 10px;">
			今日更新<span id="gxsl">129</span>部高清大片
		</h5>
	</div>
	<div class="swiper-container">
		<div class="swiper-wrapper">
		 <c:forEach items="${recommendDataList}" var="videoData" varStatus="vs"  >
			<div class="swiper-slide" onclick="location.href='wapv3/videoPlay/${pd.CHANNEL_NO}/${videoData.VIDEO_ID}'">
				<div>
					<img src="${videoData.IMG_ONE}" alt="">
					<p>${videoData.NAME_ONE}</p>
				</div>
			</div>
			</c:forEach>
			
			</div>
	</div>

	<div
		style="background-color: white; padding-bottom: 15px; text-align: center;">
		<div
			style="height: 50px; line-height: 50px; background-color: #ededed; clear: both;">
			<span class="lastba"
				style="float: left; margin-left: 10px; color: #333; font-size: 18px;">最近评论</span><span
			id="clientComment"	class="lastba cwhy" onclick='javascript:goToPay();'><img
				src="http://0829img.xyzjtj.com/tuku/hy.png" alt="" class="hyzc">填写评论</span>
		</div>
		 <c:forEach items="${clientCommentDataList}" var="clientCommentData" varStatus="vs" >
		<div class="plnr">
			<img src="${clientCommentData.clientIconUrl}" alt="头像" class="picture">
			<div class="plun">
				<h2>${clientCommentData.clientId}</h2>
				<span><a class="ipt i-g" title="亚洲系列">${clientCommentData.commentTime}秒前</a></span>
				<div>${clientCommentData.clientComment}</div>
			</div>
		</div>		
		</c:forEach>

		<div id="noVipInfo" class="button01" onclick='javascript:goToPay()'>成为VIP，分享视频精彩评论</div>
	</div>
	<%@ include file="./footer.jsp"%> 
	<script src="http://lg08.eeb24.com/wap/static/js/v3/swiper.min.js"></script>
<script>
 	var myDate = new Date();
	var data1 = myDate.getDate();
	var str = 121 - data1;
	document.getElementById("gxsl").innerHTML = str;
	var swiper = new Swiper('.swiper-container', {
		pagination : '.swiper-pagination',
		nextButton : '.swiper-button-next',
		prevButton : '.swiper-button-prev',
		slidesPerView : 'auto',
		paginationClickable : true,
		spaceBetween : 5,
		loop : true
	}); 
	loadVipInfo();
	function loadVipInfo(){
		 var payType=getCookie("payType");
		 if(ispay > 0 &&(payType!=null && payType.length>=1) ){
			 $("#noVipInfo").css("display","none");
			 $("#noVipTimeInfo").html("您已经是VIP会员，畅游您的VIP之旅 ！");
			 $("#noVipTimeInfo").attr("onClick","");
			 $("#clientComment").attr("onClick","");
			 
		 }
	}
</script>

</body>
</html>