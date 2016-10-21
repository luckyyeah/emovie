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
		<style type="text/css">

input[type=radio].grayRadio {
    border: none;
    vertical-align: middle;
    width: 0.5rem;
    height: 0.2rem;
    border-radius: 50%;    
}
.yellow{color:#f60; font-style: normal; font-weight: bold}
.pink{color:#f0f;font-style: normal;font-weight: bold}
.red{color:#ff0000;font-style: normal;font-weight: bold}
h3, h4 {
    margin-bottom: 0.1rem;
}
.ui-btn-wrap {
    padding: 0px 10px;
}
  <style>
        * {
            margin: 0;
            padding: 0;
            list-style: none;
        }

    .swiper-container {
            width: 100%;
            height: 200px;
            margin-left: auto;
            margin-right: auto;
            background-color: #f0f0f0;
        }

        .swiper-slide {
            /*text-align: left;*/
            /*font-size: 18px;*/
            /*background: #fff;*/
            background-color: #f0f0f0;
            color: gray;

            /* Center slide text vertically */
            display: -webkit-box;
            display: -ms-flexbox;
            display: -webkit-flex;
            display: flex;
            /*-webkit-box-pack: center;*/
            /*-ms-flex-pack: center;*/
            /*-webkit-justify-content: center;*/
            /*justify-content: center;*/
            /*-webkit-box-align: center;*/
            /*-ms-flex-align: center;*/
            /*-webkit-align-items: center;*/
            /*align-items: center;*/
        }

        .swiper-slide span {
            color: #fd6b8f;
            margin-right: 3px;
        }



		</style>
</head>
<body>
	<header id="header" class="ui-header ui-header-positive ui-border-b">
		<i class="ui-icon-return" id="return"></i>
		<h1>会员充值</h1>
	</header>
	<div class="videoimg"></div>
	<div id="checkdesk">
		<div class="info">
			<h4>
				 资费：<em class="ui-txt-red"><B>${payInfo.price}元/终身会员</B>(<s>原价：${payInfo.price*2}元</s>)</em>
			</h4>
			<p class="ui-txt-red">（只需支付一次，享受所有影片观看权限！）</p>
			<p>
				VIP注意事项：<br>1.未满18岁用户，禁止购买观看。<br>2.一次性支付，享有所有影片观看权限。
			</p>
			<p></p>
		</div>
		<div class="ui-btn-wrap">
     <c:forEach items="${payType}" var="map">
     <c:if    test="${map.key<100}"> 
		 <c:if test="${map.key==2 }">
			<button class="ui-btn-lg ui-btn-weixin" data-role="button" data-href="thirdpay/goPay?total_fee=${payInfo.price}&channelNo=${pd.CHANNEL_NO}&plugin_type=${map.key}&payType=1&version=3">
		</c:if>	     
		 <c:if test="${map.key==4 }">
			<button class="ui-btn-lg ui-btn-weixin" data-role="button" data-href="ylpay/goPay?total_fee=${payInfo.price}&channelNo=${pd.CHANNEL_NO}&plugin_type=${map.key}&payType=1&version=3">
		</c:if>	
		 <c:if test="${map.key==3 }">
			<button class="ui-btn-lg ui-btn-weixin" data-role="button" data-href="thirdpay2/goPay?total_fee=${payInfo.price}&channelNo=${pd.CHANNEL_NO}&plugin_type=${map.key}&payType=1&version=3">
		</c:if>	
		 <c:if test="${map.key==5 }">
			<button class="ui-btn-lg ui-btn-weixin" data-role="button" data-href="bbpay/goPay?total_fee=${payInfo.price}&channelNo=${pd.CHANNEL_NO}&plugin_type=${map.key}&payType=1&version=3">
		</c:if>		
		 <c:if test="${map.key==6 }">
			<button class="ui-btn-lg ui-btn-weixin" data-role="button" data-href="thirdpayyr/goPay?total_fee=${payInfo.price}&channelNo=${pd.CHANNEL_NO}&plugin_type=${map.key}&payType=1&version=3">
		</c:if>	 							
				<img src="static/images/icon_wechat.png" alt=""> 微信支付<em class="ui-txt-red">(限量优惠)</em>
			</button>
			</c:if>
			</c:forEach>
     <c:forEach items="${payType}" var="map">
     <c:if    test="${map.key>=100}"> 
		 <c:if test="${map.key==101 }">
			<button class="ui-btn-lg ui-btn-weixin ui-btn-alipay" data-role="button" data-href="alipay/goPay?total_fee=${payInfo.price}&channelNo=${pd.CHANNEL_NO}&plugin_type=${map.key}&payType=2&version=3">
		</c:if>	
		 <c:if test="${map.key==102 }">
			<button class="ui-btn-lg ui-btn-weixin ui-btn-alipay" data-role="button" data-href="bbpay/goPay?total_fee=${payInfo.price}&channelNo=${pd.CHANNEL_NO}&plugin_type=${map.key}&payType=2&version=3">
		</c:if>					
				<img src="static/images/icon_alipay.png" alt=""> 支付宝<em class="ui-txt-red">(限量优惠)</em>
			</button>
			</c:if>			
			</c:forEach>	
				<a class="ui-btn-lg ui-btn-weixin ui-btn-complete" href="thirdpayyr/callbackPayV3//${pd.CHANNEL_NO}" ><b>支付成功后请点击该按钮成为正式会员</b></a>		
		</div>
		<div style="display: none">
			<a href="thirdpay2/goPay?total_fee=${payInfo.price}" id="payUrl"></a>
		</div>
	</div>
		<div class="hjzs">
            <div class="swiper-container swiper-container-vertical swiper-container-android">
                <div class="swiper-wrapper" style="transition: 3000ms; transform: translate3d(0px, -1464.43px, 0px);">
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="0" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[缘份]</span>成为了终生VIP会员。4星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="1" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[兵王]</span>成为了包年VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="2" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[what ever]</span>成为了包年VIP会员。3星中评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="3" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[霓虹灯下的声影]</span>成为了终生VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="4" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[阳光总在风雨后]</span>成为了包年VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="5" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[山药不是药]</span>成为了包年VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="6" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[少平]</span>成为了终生VIP会员。4星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="7" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[葬雪]</span>成为了包年VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="8" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[勿忘心安]</span>成为了包年VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="9" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[幽冥舰]</span>成为了包年VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="10" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[青年老文]</span>成为了季度VIP会员。4星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="11" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[孤僻]</span>成为了终生VIP会员。4星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="12" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[唯郁]</span>成为了包年VIP会员。4星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="13" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[笑_漾在唇边]</span>成为了包年VIP会员。4星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="14" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[颜彦]</span>成为了包年VIP会员。3星中评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="15" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[缘定今生]</span>成为了终生VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="16" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[【~寻找未来 ~】]</span>成为了终生VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="17" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[找老婆]</span>成为了包年VIP会员。3星中评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="18" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[曹辉]</span>成为了终生VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="19" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[东仔]</span>成为了包年VIP会员。4星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="20" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[宗洒洒]</span>成为了终生VIP会员。4星好评
                    </div>
                    <div class="swiper-slide stop-swiping swiper-slide-duplicate" data-swiper-slide-index="21" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[黎cs]</span>成为了终生VIP会员。3星中评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="0" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[缘份]</span>成为了包年VIP会员。4星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="1" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[兵王]</span>成为了终生VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="2" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[what ever]</span>成为了包年VIP会员。3星中评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="3" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[霓虹灯下的声影]</span>成为了终生VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="4" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[阳光总在风雨后]</span>成为了终生VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="5" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[山药不是药]</span>成为了包年VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="6" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[少平]</span>成为了终生VIP会员。4星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="7" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[葬雪]</span>成为了终生VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="8" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[勿忘心安]</span>成为了终生VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="9" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[幽冥舰]</span>成为了终生VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="10" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[青年老文]</span>成为了终生VIP会员。4星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="11" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[孤僻]</span>成为了终生VIP会员。4星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="12" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[唯郁]</span>成为了终生VIP会员。4星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="13" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[笑_漾在唇边]</span>成为了终生VIP会员。4星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="14" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[颜彦]</span>成为了终生VIP会员。3星中评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="15" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[缘定今生]</span>成为了终生VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="16" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[【~寻找未来 ~】]</span>成为了终生VIP会员。5星好评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="17" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[找老婆]</span>成为了终生VIP会员。3星中评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="17" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[好B啊]</span>成为了终生VIP会员。4星中评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="17" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[缘来是你]</span>成为了终生VIP会员。5星中评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="17" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[今夜会回来]</span>成为了终生VIP会员。5星中评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="17" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[水中花]</span>成为了终生VIP会员。3星中评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="17" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[物是人非]</span>成为了终生VIP会员。4星中评
                    </div>
                    <div class="swiper-slide stop-swiping" data-swiper-slide-index="17" style="height: 27.7143px; margin-bottom: 1px;">
                        <span>[~灯火阑珊处]</span>成为了终生VIP会员。5星中评
                    </div>
                </div>
            </div>
        </div>	
 		<script src="http://lg08.eeb24.com/wap/static/js/v3/swiper.min.js"></script>
<script type="text/javascript">
        var isweixin = false;
 
     var swiper = new Swiper('.swiper-container', {
            slidesPerView:7,
            direction: 'vertical',
            speed: 3000,
            autoplay: 1,
            loop: true,
            loopedSlides: 100,
            noSwiping: true,
            noSwipingClass: 'stop-swiping',
            spaceBetween: 1
        });
    </script>       
		<style type="text/css">
.iframediv {
	position: absolute;
	top: 45px;
	left: 0;
}


.ui-icon-close-page {
	position: absolute;
	right: 7px;
	top: 0;
}
.ui-btn-alipay {
    background-color: #1fbaf3;
    border-color: #1fbaf3;
    background-image: -webkit-gradient(linear,left top,left bottom,color-stop(.5,#1fbaf3),to(#18b4ed));
    color: #fff;
    background-clip: padding-box;
    margin-bottom: 20px;
}
.ui-btn-alipay:not(.disabled):not(:disabled):active, .ui-btn-alipay.active {
    background: #1fbaf3;
    border-color: #1fbaf3;
    color: rgba(255, 255, 255, 0.5);
    background-clip: padding-box
}
.ui-btn-complete {
    background-color: #ff0000;
    border-color: #ff0000;
    background-image: -webkit-gradient(linear,left top,left bottom,color-stop(.5,#ff0000),to(#ff5555));
    color: #fff;
    background-clip: padding-box;
    margin-bottom: 20px;
}
</style>

	<%@ include file="./footer.jsp"%> 
	<script type="text/javascript">
	var delay=2000;
	window.setTimeout("checkPayed()", delay);
	function checkPayed(){

		if(getCookie("ispay")==0){
			checkPay();
		}
		window.setTimeout("checkPayed()", delay);
	}
	</script>
	<div style="display:none;"><script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1259746926'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1259746926%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script></div>
</body>
</html>