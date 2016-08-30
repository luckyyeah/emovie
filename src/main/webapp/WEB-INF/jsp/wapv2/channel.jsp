<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" data-dpr="3" style="font-size: 124.5px;">

    <head>
			<%@ include file="./head.jsp"%> 
        <style>
            .swiper-container-horizontal>.swiper-pagination-bullets, .swiper-pagination-custom, .swiper-pagination-fraction{
                height: auto;bottom: 0.78125rem;
            }
            .swiper-pagination-bullet{background: #fff}
        </style>
    </head>


<body class="channel play">
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
                        <div class="pd20"  data-id="001">
                            <p class="title"><c:out value="${columnNameMap['001']}" /></p>
                            <p><span class="red total"><c:out value="${filmMap['001']}" /></span>部精品等您品鉴</p>
                            <p>今日更新<span class="red renew"><c:out value="${newFilmMap['001']}" /></span>部</p>
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
                        <div class="pd20"  data-id="003">
                            <p class="title"><c:out value="${columnNameMap['003']}" /></p>
                            <p><span class="red total"><c:out value="${filmMap['003']}" /></span>部精品等您品鉴</p>
                            <p>今日更新<span class="red renew"><c:out value="${newFilmMap['003']}" /></span>部</p>
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
                        <div class="pd20"  data-id="002" >
                            <p class="title"><c:out value="${columnNameMap['002']}" /></p>
                            <p><span class="red total"><c:out value="${filmMap['002']}" /></span>部精品等您品鉴</p>
                            <p>今日更新<span class="red renew"><c:out value="${newFilmMap['002']}" /></span>部</p>
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
                        <div class="pd20"  data-id="004">
                            <p class="title"><c:out value="${columnNameMap['004']}" /></p>
                            <p><span class="red total"><c:out value="${filmMap['004']}" /></span>部精品等您品鉴</p>
                            <p>今日更新<span class="red renew"><c:out value="${newFilmMap['004']}" /></span>部</p>
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
                            <div class="pd20"  data-id="005">
                                <p class="title"><c:out value="${columnNameMap['005']}" /></p>
                                <p><span class="red total"><c:out value="${filmMap['005']}" /></span>部精品等你品鉴</p>
                                <p>今日更新<span class="red renew"><c:out value="${newFilmMap['005']}" /></span>部</p>
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
                        <div class="pd20"  data-id="007">
                            <p class="title" ><c:out value="${columnNameMap['007']}" /></p>
                            <p><span class="red total"><c:out value="${filmMap['007']}" /></span>部精品等您品鉴</p>
                            <p>今日更新<span class="red renew"><c:out value="${newFilmMap['007']}" /></span>部</p>
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
    <section class="col-md-12 channelBoxBottom">
        <div class="mainbox pl10 relative" onclick="location.href='tuku/index/${pd.CHANNEL_NO}'">
            <img src="http://ww1.sinaimg.cn/large/d2d743f1gw1f5x0b9e6xlj20h805x0so.jpg" alt="">
            <div class="channelBoxBottomFont">
                <p class="title">激情美图</p>
                <p><span class="red"><c:out value="${filmMap['002']+10000}" /></span>套高清套图等您品鉴</p>
                <p>今日更新<span class="red"><c:out value="${newFilmMap['002']+100}" /></span>套</p>
            </div>
        </div>
    </section>

<%@ include file="./footer.jsp"%> 
<%@ include file="./paybox.jsp"%> 
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
            $(e).parent().parent().parent().bind("click", function () {
                location.href="wapv2/listColumnVideo/${pd.CHANNEL_NO}/"+id;
            });
        });
    });
</script>
</body>
</html>