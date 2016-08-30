<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>         
				<meta charset="UTF-8">
				<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
        <meta content="yes" name="apple-mobile-web-app-capable">
        <meta content="yes" name="apple-touch-fullscreen"> 
        <title>撸撸影院</title>
        <link rel="stylesheet" href="http://lg08.eeb24.com/wap/static/css/v2/swiper-3.3.1.min.css">
        <link rel="stylesheet" href="http://lg08.eeb24.com/wap/static/css/v2/style.css">
        <link rel="stylesheet" href="http://lg08.eeb24.com/wap/static/css/v2/grid.css">  
        <style>
            .swiper-container-horizontal>.swiper-pagination-bullets, .swiper-pagination-custom, .swiper-pagination-fraction{
                height: auto;bottom: 0.78125rem;
            }
            .swiper-pagination-bullet{background: #fff}
        </style>
	      <script src="http://lg08.eeb24.com/wap/static/js/v2/flexible_0.3.4.js" type="text/javascript" charset="utf-8"></script>
        <script src="http://lg08.eeb24.com/wap/static/js/v2/jquery.min.js" type="text/javascript"></script>
        <script src="http://lg08.eeb24.com/wap/static/js/v2/swiper-3.3.1.min.js" type="text/javascript"></script>
        <script src="http://lg08.eeb24.com/wap/static/js/v2/pay.js" type="text/javascript"></script> 	
        <script src="http://lg08.eeb24.com/wap/static/js/v2/layer.js"></script>
        <script type="text/javascript">var sid = 1, aid = 1, checkTimer = 0, vipType = parseInt(getCookie("openVIP")), regTime = 0, resourceType = 0;</script>
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