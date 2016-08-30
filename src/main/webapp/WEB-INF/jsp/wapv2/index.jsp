<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en"  data-dpr="3" style="font-size: 124.5px;">

    <head>
			<%@ include file="./head.jsp"%> 
    </head>



</head>
<body class="index play">
	<%@ include file="./toolbar.jsp"%> 
    <section>
            <div class="swiper-container">
                <div class="swiper-wrapper">
 								<c:forEach items="${bannerDataList}" var="bannerData" varStatus="vs" end="4">
                 <div class="swiper-slide">
                    <a href="wapv2/videoPlay/${pd.CHANNEL_NO}/${bannerData.COLUMN_ID}/${bannerData.VIDEO_ID}">
                        <img src="${bannerData.IMG_TWO}" />
                				<div class="shaddow"></div>
                        <div class="shaddowTitle">${bannerData.NAME_ONE}</div>
                    </a>
                </div>    
                </c:forEach>                         
								</div>
                <div class="swiper-pagination"></div>
            </div>
        </section> 
 <c:forEach items="${columnDataList}" var="column" varStatus="vs" >
     <c:if test="${column.DATA_TYPE==2||column.DATA_TYPE==4}">
    <section class="picList clearfix">
    <h3>${column.NAME_TWO}<a href="wapv2/listColumnVideo/${pd.CHANNEL_NO}/${column.COLUMN_ID}" class="fr more">+MORE</a></h3>        
<c:forEach    items="${mapColumnvideoList}"    var="mymap"> 
      <c:if    test="${mymap.key==column.COLUMN_ID}"> 
      	<c:set var="columnvideoList"  value="${mymap.value}"/>
					<c:forEach items="${columnvideoList}" var="columnvideo" varStatus="vs" end="5">
                 <c:if test="${vs.index<columnvideoList.size()-1}">   
						    <div class="col-md-4">
						        <a href="wapv2/videoPlay/${pd.CHANNEL_NO}/${columnvideo.COLUMN_ID}/${columnvideo.VIDEO_ID}">
						            <div class="pl10">
						                <div class="relative">
						                 <img src="${columnvideo.IMG_ONE}" alt="">
						                       <c:if    test="${columnvideo.FREE_FLAG==1}"> 
                    									<span class="flag01"></span>
                 									 </c:if> 
                 									 <c:if    test="${columnvideo.FREE_FLAG!=1 && columnvideo.VIP_FLAG==2}"> 
                   								 <span class="flag02"></span>
               										 </c:if>  
						               </div>
						                <div class="title">${columnvideo.NAME_ONE}</div>
						            </div>
						        </a>
						    </div>   
    					</c:if>
    	</c:forEach>
    </c:if>
</c:forEach> 
</section>    
</c:if>
</c:forEach>
  
<%@ include file="./footer.jsp"%> 
<%@ include file="./paybox.jsp"%> 
<script type="text/javascript">resourceType=2;</script>
<script>
    $(function () {
         var mySwiper = new Swiper('.swiper-container', {
            loop: true,
            nextButton: '.swiper-button-next',
            prevButton: '.swiper-button-prev',
            pagination: '.swiper-pagination',
            paginationClickable: true
        });
        
        $('.online').css('left', ($(window).width() / 2) - (lib.flexible.rem2px(4.5) / 2));
        setInterval(getNo, 10000);
        function getNo() {
            i = parseInt(Math.random() * 100) + 3400;
            $(".online").html('<img src="http://h5.hyxxzzc.com/public/images/index-zs.png">当前在线人数<span class="red">' + i + '</span>人');
            $(".online").show().addClass("flip-top");
            setTimeout(function () {
                $(".online").hide();
            }, 5000);
        }    
        var picWidth = $(".relative img").width(), picHeight = parseInt(264 / 200 * picWidth);
        $(".relative img").height(picHeight);
        window.addEventListener("orientationchange", function () {
            var picWidth = $(".relative img").width(), picHeight = parseInt(264 / 200 * picWidth);
            $(".relative img").height(picHeight);
        });
        if("${pd.first}"!='0'){
        	checkPay();
        }
    });
</script>
<div style="display:none;"><script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1259746926'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1259746926%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script></div>

</body>
</html>