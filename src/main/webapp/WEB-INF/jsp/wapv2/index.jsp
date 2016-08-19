<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">

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
                        <img src="${bannerData.IMG_ONE}" />
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
                 									 <c:if    test="${columnvideo.VIP_FLAG==1}"> 
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
    <script src="http://h5.hyxxzzc.com/public/js/layer/layer.js"></script>
<script type="text/javascript">        
        function popPayDiv(){
            var popHTML = $('.pop').html(); 
            layer.open({
                content: popHTML,
                success: function() {
                    var device=getDevice();
                    if(device=='android') $('#player video').hide();   
                },
                end: function(index) {
                    $('#player video').show(); 
                }
            });
        }
        
    </script> 
<style>
    .layermbox0 .layermchild {height: auto;border-radius: 30px;overflow: hidden}
    .layermcont{padding:0;}
</style>    


        <script>
            var $ = jQuery;
            $(function(){
                if(vipType==0){
                    $(".openVIP").html("开通VIP");
                }else if(vipType==1){
                    $(".openVIP").html("白银VIP");
                }else if(vipType==2){
                    $(".openVIP").html("黄金VIP");
                }else if(vipType==3){
                    $(".openVIP").html("钻石VIP");
                }                
            });
        </script>
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
    });
</script>
</body>
</html>