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
        <style>
            .swiper-container-horizontal>.swiper-pagination-bullets, .swiper-pagination-custom, .swiper-pagination-fraction{
                height: auto;bottom: 0.78125rem;
            }
            .swiper-pagination-bullet{background: #fff}
        </style>
    </head>


<body class="play">
    <header class="black-header fixed">
        <span class="logo"><img src="http://ww4.sinaimg.cn/large/d2d743f1gw1f5x32xdn4dj203k03kt9e.jpg" alt=""></span>频道<a href="javascript:pay();" class="openVIP">开通VIP</a>
    </header>
    <!--第一排-->
    <section class="channelBox col-md-12">
        <div class="mainbox">
            <div class="col-md-8 relative">
                <div class="col-md-6">
                    <div class="c1"><img src="http://ww2.sinaimg.cn/large/d2d743f1gw1f5x0b4in32j205k05kt8j.jpg" alt=""></div>
                </div>
                <div class="col-md-6">
                    <div class="c1top">
                        <div class="pd20"  data-id="14">
                            <p class="title">亚洲经典</p>
                            <p><span class="red total">1528</span>部精品等您品鉴</p>
                            <p>今日更新<span class="red renew">21</span>部</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="v1"><img src="http://ww4.sinaimg.cn/large/d2d743f1gw1f5x0b61aeoj205k05kjr7.jpg" alt=""></div>
            </div>
        </div>
    </section>
    <section class="channelBox col-md-12">
        <div class="mainbox pt0">
            <div class="col-md-8 bluelineContent">&nbsp;</div>
            <div class="col-md-4">
                <div class="blueline"></div>
            </div>
        </div>
    </section>
    <section class="channelBox col-md-12">
        <div class="mainbox2">
            <div class="col-md-4">
                <div class="pl10">
                    <div class="c2top">
                        <div class="pd20"  data-id="16">
                            <p class="title">唯美短片</p>
                            <p><span class="red total">1528</span>部精品等您品鉴</p>
                            <p>今日更新<span class="red renew">21</span>部</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="pl10">
                    <img src="http://ww3.sinaimg.cn/large/d2d743f1gw1f5x0b6i1mvj205k05kwee.jpg" alt="">
                </div>
            </div>
            <div class="col-md-4">
                <div class="pl10">
                    <div class="c3top">
                        <div class="pd20"  data-id="15" >
                            <p class="title">唯美短片</p>
                            <p><span class="red total">1528</span>部精品等您品鉴</p>
                            <p>今日更新<span class="red renew">21</span>部</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section class="channelBox col-md-12 top01">
        <div class="mainbox2">
            <div class="col-md-4">
                <div class="pl10">
                    <div class="pinkline"></div>
                </div>
            </div>
            <div class="col-md-8 bluelineContent">&nbsp;</div>
        </div>
    </section>
    <section class="channelBox col-md-12">
        <div class="mainbox2">
            <div class="col-md-4">
                <div class="pl10">
                    <img src="http://ww2.sinaimg.cn/large/d2d743f1gw1f5x0b71z89j205k05k743.jpg" alt="">
                </div>
            </div>
            <div class="col-md-4">
                <div class="pl10">
                    <div class="c4top">
                        <div class="pd20"  data-id="17">
                            <p class="title">情趣诱惑</p>
                            <p><span class="red total">1528</span>部精品等您品鉴</p>
                            <p>今日更新<span class="red renew">21</span>部</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="pl10">
                    <img src="http://ww1.sinaimg.cn/large/d2d743f1gw1f5x0b78m4mj205k05ka9w.jpg" alt="">
                </div>
            </div>
        </div>
    </section>
    <section class="channelBox col-md-12">
        <div class="mainbox">
            <div class="col-md-8 relative">
                <div class="col-md-6">
                    <div class="pl10">
                        <div class="c5top">
                            <div class="pd20"  data-id="18">
                                <p class="title">寂寞少妇</p>
                                <p><span class="red total">1528</span>部精品等你品鉴</p>
                                <p>今日更新<span class="red renew">21</span>部</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="c1"><img src="http://ww3.sinaimg.cn/large/d2d743f1gw1f5x0b86lvaj205k05k3yc.jpg" alt=""></div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="pl10">
                    <div class="c6top">
                        <div class="pd20"  data-id="20">
                            <p class="title" >制服丝袜</p>
                            <p><span class="red total">1528</span>部精品等您品鉴</p>
                            <p>今日更新<span class="red renew">21</span>部</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section class="channelBox col-md-12 top01">
        <div class="mainbox pt0">
            <div class="col-md-8 purpleContent">&nbsp;</div>
            <div class="col-md-4">
                <div class="purpleline"></div>
            </div>
        </div>
    </section>
    <section class="channelBox col-md-12 top01">
        <div class="mainbox pt0">
            <div class="col-md-8 relative">
                <div class="col-md-6">
                    <div class="c1"><img src="http://ww3.sinaimg.cn/large/d2d743f1gw1f5x0b8zln4j205k05kdfn.jpg" alt=""></div>
                </div>
                <div class="col-md-6">
                    <div class="c7top">
                        <div class="pd20" data-id="21">
                            <p class="title" >热门自拍</p>
                            <p><span class="red total">1528</span>部精品等您品鉴</p>
                            <p>今日更新<span class="red renew">21</span>部</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="v1"><img src="http://ww4.sinaimg.cn/large/d2d743f1gw1f5x0b8zgr1j205k05kmx1.jpg" alt=""></div>
            </div>
        </div>
    </section>
    <section class="col-md-12 channelBoxBottom">
        <div class="mainbox pl10 relative" onclick="location.href='/tuku'">
            <img src="http://ww1.sinaimg.cn/large/d2d743f1gw1f5x0b9e6xlj20h805x0so.jpg" alt="">
            <div class="channelBoxBottomFont">
                <p class="title">激情美图</p>
                <p><span class="red">12896</span>套高清套图等您品鉴</p>
                <p>今日更新<span class="red">121</span>套</p>
            </div>
        </div>
    </section>

<%@ include file="./footer.jsp"%> 
<%@ include file="./paybox.jsp"%> 
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
        var c1_imgH = $('.c1 img').height();
        var c1_imgW = $('.c1 img').width();
        $('.c1top').css('height', $('.c1').width());
        $('.c2top').css('height', $('.c2top').width());
        $('.c3top').css('height', $('.c3top').width());
        $('.c4top').css('height', $('.c4top').width());
        $('.c5top').css('height', $('.c5top').width());
        $('.c6top').css('height', $('.c6top').width());
        $('.c7top').css('height', $('.c1').width());

        $('.blueline').css('width', c1_imgW);
        $('.purpleline').css('width', c1_imgW);
        $('.bluelineContent').css('height', $('.blueline').height());
        $('.purpleContent').css('height', $('.purpleline').height());
        $(".pd20").each(function (i, e) {
            var id = $(e).data("id");
            $(e).parent().parent().parent().parent().bind("click", function () {
                location.href="/movie/list_"+id+".html";
            });
            $.post("/index.php/index/channel_info", {id: id}, function (data) {
                $(e).find(".title").html(data.info.name);
                $(e).find(".total").html(data.info.count);
                $(e).find(".renew").html(data.info.renew);
            });
        });
    });
</script>
</body>
</html>