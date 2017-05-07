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
        <meta charset="UTF-8">
        <meta content="yes" name="apple-mobile-web-app-capable">
        <meta content="yes" name="apple-touch-fullscreen"> 
        <title>会员中心</title>
                <link rel="stylesheet" href="./static/css/activity/swiper-3.3.1.min.css">
        <link rel="stylesheet" href="./static/css/activity/style.css">
        <link rel="stylesheet" href="./static/css/activity/grid.css">
        <script src="./static/js/activity/flexible_0.3.4.js" type="text/javascript" charset="utf-8"></script>
        <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

        <style>
            .swiper-container-horizontal>.swiper-pagination-bullets, .swiper-pagination-custom, .swiper-pagination-fraction{
                height: auto;bottom: 0.78125rem;
            }
            .swiper-pagination-bullet{background: #fff}
        </style>
        
    </head>

<script type="text/javascript">
    $(function () {
        
        $(".userActive").click(function (e) {
            var activeBtn = $(this);
            activeBtn.html("正在登录");
            var orderNo = $("input[name='orderNo']").val();
            if ( orderNo == '') {
                activeBtn.html("请填写订单号");
                return false;
            } else {
								var url = "<%=basePath%>wapvv/checkOrderPayed?CHANNEL_NO=${pd.CHANNEL_NO}&orderNo=" + orderNo +"&uid="+getCookie("uid")+ "&tm="+new Date().getTime();
								$.get(url,function(data){
									if(data=="1"){
										 activeBtn.html("登录成功，页面正在跳转");
										 location.href ="<%=basePath%>wapvv/loadResult/${pd.CHANNEL_NO}";
									} else {
				              activeBtn.html("登录失败，请检查订单号");
				            }
									
								});

                
            }
        });
    });
    function getCookie(i) {
        var t, e = new RegExp("(^| )" + i + "=([^;]*)(;|$)");
        return t = document.cookie.match(e),
        t ? t[2] : null
    }
    function getsec(i) {
        var t = 1 * i.substring(1, i.length),
        e = i.substring(0, 1);
        return "s" == e ? 1e3 * t: "h" == e ? 60 * t * 60 * 1e3: "d" == e ? 24 * t * 60 * 60 * 1e3: void 0
    }
</script>
<body class="sefl_activation">
    <header class="black-header">
        <a href="javascript:history.back()" class="back"></a>自助激活
    </header>
    <section class="pd10 pd-bottom">
        <div class="orderBox">
            <p><input type="text" name="orderNo" class="inputOrder" placeholder="请输入您的微信或者支付宝商户单号"></p>
            <p><a href="#" class="userActive">激活</a></p>
        </div>        
        <div class="tip">温馨提示：如果您支付成功但没有成功开通会员服务，请您自助填写支付凭证中的“<span class="red">商户单号</span>”进行手工激活，如果有任何疑问或困难请及时联系客服。祝您生活愉快！</div>
    </section>
    <section class="pd10 activationImg">
        <p><img src="http://ww1.sinaimg.cn/large/d2d743f1gw1f5x0cn9fwdj20jb0fk40w.jpg" alt=""></p>
        <p><img src="http://ww2.sinaimg.cn/large/d2d743f1gw1f5x1kpp4y4j20jb0fyq5y.jpg" alt=""></p>
    </section>
</body>

</html>