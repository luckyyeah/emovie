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
			<%@ include file="./../head.jsp"%> 
    </head>


<body class="picPlay play">
    <header class="black-header fixed">
        <a href="javascript:history.back()" class="back"></a>性感美女<a href="javascript:pay();" class="openVIP">开通VIP</a>
    </header>
    <section>
        <div class="picTitle">${columnData.NAME_ONE}（<i class="nownub" style="font-style:normal">1</i>/${imageCnt}）</div>
        <div class="picTip">温馨提示：支持左右滑动翻页浏览</div>
        <div class="picContent">
            <div class="swiper-container">
                <div class="swiper-wrapper">
                <c:forEach items="${imageDataList}" var="videoData" varStatus="vs" end="0"> 
                    <div class="swiper-slide firstimg"><img src="${videoData.IMG_ONE}" alt=""></div>
                </c:forEach>		 	
                </div>
                <!-- 如果需要导航按钮 -->
                <div class="swiper-button-prev"></div>
                <div class="swiper-button-next"></div>
            </div>
        </div>
    </section>
    <section class="detailbox clearfix">  
    <h3>猜你喜欢<a href="tuku/listTukuCovers/${pd.CHANNEL_NO}/${columnData.TAB_ID}" class="fr more">+MORE</a></h3>        

    <div class="videoList-play">
    <c:forEach items="${recommendCoversList}" var="recommendCovers" varStatus="vs" >   
                <a href="tuku/listImages/${pd.CHANNEL_NO}/${recommendCovers.COLUMN_ID}">
            <div class="listBox">
                <div class="relative"><img src="${recommendCovers.IMG_TWO }" alt=""><span class="flag01"></span></div>
                <div class="title">${recommendCovers.NAME_ONE }</div>
            </div>
        </a>
    </c:forEach>

            </div>
<%@ include file="./../footer.jsp"%> 
<%@ include file="./../paybox.jsp"%> 

<script type="text/javascript">resourceType=1;</script>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
        <script src="http://h5.hyxxzzc.com/public/js/swiper-3.3.1.min.js"></script>
<script>
    $(function () {     
    	
   			var url='<%=basePath%>tuku/getNextImage/${columnData.COLUMN_ID}'+"?vipType="+vipType;
    	 $.get(url,function(data){
    		     var pics=eval("("+data+")");;
    		     $.each(pics, function(i,val){
                 var html='<div class="swiper-slide"><img src="'+val.IMG_ONE+'" alt=""></div>';
                 $(html).appendTo($(".swiper-wrapper"));
    		     });

            $(".firstimg").remove();
            var mySwiper = new Swiper('.swiper-container', {
            loop: false,
            nextButton: '.swiper-button-next',
            prevButton: '.swiper-button-prev',
            observe : true,
            onSlideChangeStart: function(swiper){               
                var activeIndex=mySwiper.activeIndex;                 
                if(activeIndex>=2&&vipType==0){                     
                    interValPay();
                    mySwiper.off();
                    
                    } 
              },
            onSlideChangeEnd: function(swiper){
                var activeIndex=mySwiper.activeIndex+1;                
                $(".nownub").text(activeIndex);
              }  
                 
        });
        });
        
    });
    function interValPay(){        
        pay();
        setInterval('pay()',5000);
    }
</script>
</body>
</html>