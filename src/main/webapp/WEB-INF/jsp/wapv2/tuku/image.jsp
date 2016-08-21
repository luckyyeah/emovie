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
        <div class="picTitle">性感美女南湘baby饱满酥胸很是诱人（<i class="nownub" style="font-style:normal">1</i>/11）</div>
        <div class="picTip">温馨提示：支持左右滑动翻页浏览</div>
        <div class="picContent">
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide firstimg"><img src="http://ww3.sinaimg.cn/large/006r311dgw1f4s6v0f7gjj305k07qgmm.jpg" alt=""></div>
                </div>
                <!-- 如果需要导航按钮 -->
                <div class="swiper-button-prev"></div>
                <div class="swiper-button-next"></div>
            </div>
        </div>
    </section>
    <section class="detailbox clearfix">
    <h3>猜你喜欢<a href="list_25.html" class="fr more">+MORE</a></h3>        
    <div class="videoList-play">
                <a href="image_28.html">
            <div class="listBox">
                <div class="relative"><img src="http://ww3.sinaimg.cn/large/006r311dgw1f4s6v0f7gjj305k07qgmm.jpg" alt=""><span class="flag01"></span></div>
                <div class="title">极品大胸妹刘娅希黑色蕾丝内衣人体艺术照</div>
            </div>
        </a>
                <a href="image_84.html">
            <div class="listBox">
                <div class="relative"><img src="http://ww4.sinaimg.cn/large/006r311dgw1f5egu15ljuj305k07qq3u.jpg" alt=""><span class="flag01"></span></div>
                <div class="title">巨乳美女Barbie可儿性感图片写真</div>
            </div>
        </a>
                <a href="image_29.html">
            <div class="listBox">
                <div class="relative"><img src="http://ww1.sinaimg.cn/large/006r311dgw1f4s6v17alcj305k07qdgs.jpg" alt=""><span class="flag01"></span></div>
                <div class="title">性感美女兜豆靓私房全裸雪白肌肤娇艳迷人</div>
            </div>
        </a>
            </div>
<%@ include file="./../footer.jsp"%> 
<%@ include file="./../paybox.jsp"%> 

<script type="text/javascript">resourceType=1;</script>
<script>
    $(function () {       
        $.getScript("http://user.zpiyi.com/index/loadimg1/id/86",function(){
            
            pics.forEach(function(i,e){
               var html='<div class="swiper-slide"><img src="http://ww1.sinaimg.cn/large/006r311dgw1f4s6v17alcj305k07qdgs.jpg" alt=""></div>';
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
                if(activeIndex==3&&vipType==0){                     
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