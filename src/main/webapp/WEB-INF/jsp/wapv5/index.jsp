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
<script language="javascript" type="text/javascript">
	if (navigator.userAgent.indexOf("indows") > 0) {
		window.location.href = "about:blank";
		alert("请用手机打开");
	};
</script>
<style> /*body,html{overflow: hidden;width: 100%;}*/
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
</style>
</head>
<body>
		<%@ include file="./column.jsp"%> 
	<div style="background-color: white; padding-bottom: 10px; text-align: center; position: relative;">
		 <c:forEach items="${recommendDataList}" var="videoData" varStatus="vs" begin="0" end="0">
		<img src="${videoData.IMG_ONE}" alt=""
			style="width: 100%;" id="recommendVideo" data-vid="${videoData.VIDEO_ID}"><img src="http://0829img.xyzjtj.com/tuku/playmp4.png" class="playmp4"
			id="vdcont" data-vid="${videoData.VIDEO_ID}">
			</c:forEach>
		<div class="button01" onclick='javascript:goToPay()'>非会员只能试看20秒，成为会员观看完整版</div>
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
		 <c:forEach items="${recommendDataList}" var="videoData" varStatus="vs" begin="1" >
			<div class="swiper-slide" onclick="document.getElementById('recommendVideo').src='${videoData.IMG_ONE}'">
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
				class="lastba cwhy" onclick='javascript:goToPay();'><img
				src="http://0829img.xyzjtj.com/tuku/hy.png" alt="" class="hyzc">成为会员</span>
		</div>
		<div class="plnr">
			<img src="http://0829img.xyzjtj.com/tuku/20160829icon_156.jpg" alt="头像" class="picture">
			<div class="plun">
				<h2>只留空城守旧梦。</h2>
				<span><a class="ipt i-g" title="亚洲系列">5秒前</a></span>
				<div>太搞笑了，肚子都笑痛了。。。</div>
			</div>
		</div>		
		<div class="plnr">
			<img src="http://0829img.xyzjtj.com/tuku/20160704tx498.jpg" alt="头像" class="picture">
			<div class="plun">
				<h2>夜丶颓废</h2>
				<span><a class="ipt i-g" title="亚洲系列">13秒前</a></span>
				<div>视频清晰度挺高的，速度也快。。</div>
			</div>
		</div>
		<div class="plnr">
			<img src="http://0829img.xyzjtj.com/tuku/20160704tx499.jpg" alt="头像" class="picture">
			<div class="plun">
				<h2>惹草笑．°</h2>
				<span><a class="ipt i-g" title="亚洲系列">30秒前</a></span>
				<div>我喜欢看萌宠，动物真是人类的朋友</div>
			</div>
		</div>
		<div class="plnr">
			<img src="http://0829img.xyzjtj.com/tuku/20160704tx500.jpg" alt="头像" class="picture">
			<div class="plun">
				<h2>hlwnds88</h2>
				<span><a class="ipt i-g" title="亚洲系列">2分钟前</a></span>
				<div>这个竟然不是骗人的啊，是真的！看了几个顶！！！！！！！和爱奇差不多，不错不错</div>
			</div>
		</div>
		<div class="plnr">
			<img src="http://0829img.xyzjtj.com/tuku/20160704tx501.jpg" alt="头像" class="picture">
			<div class="plun">
				<h2>◆舒雅望。</h2>
				<span><a class="ipt i-g" title="亚洲系列">3分钟前</a></span>
				<div>尼玛呀~害得我连续看了好几个小时，压根就停不下来啊</div>
			</div>
		</div>
		<div class="plnr">
			<img src="http://0829img.xyzjtj.com/tuku/20160704tx502.jpg" alt="头像" class="picture">
			<div class="plun">
				<h2>qcznan</h2>
				<span><a class="ipt i-g" title="亚洲系列">3分钟前</a></span>
				<div>太精彩了哇，看来充值是没白冲，物超所值，在别的地方都看不了。就你们这里有资源，而且质量高。爽！</div>
			</div>
		</div>
		<div class="plnr" style="border-bottom: none;">
			<img src="http://0829img.xyzjtj.com/tuku/20160704tx504.jpg" alt="头像" class="picture">
			<div class="plun">
				<h2>1081884</h2>
				<span><a class="ipt i-g" title="亚洲系列">5分钟前</a></span>
				<div>果然是高清全集的，红红火火恍恍惚惚哈哈哈哈，找这么久终于找到了，每天都会上来看几部。</div>
			</div>
		</div>
		<div class="button01" onclick='javascript:goToPay()'>成为VIP，分享视频精彩评论</div>
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
</script>

</body>
<div style="display:none;"><script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1259746926'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1259746926%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script></div>
	<script type="text/javascript">
	setCookie("COLUMN_ID", "", "d999");
	setCookie("alertDownloadCnt", 0, "d999");
	</script>
</html>