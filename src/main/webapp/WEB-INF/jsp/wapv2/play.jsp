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


<body class="play">
    <header class="black-header">
        <a href="javascript:history.back()" class="back"></a>详情播放
    </header>
    <section class="player pay-trigger" id="player" onClick="pay()">
        <div class="bg">
            <img src="http://kou.hyxxzzc.com/public/images/playbg.jpg" style="height:auto; width:100%;">
        </div>
        <!--加载图片-->
        <div class="movie-pic">
            <img src="${videoData.IMG_ONE}" style="height:auto; width:100%;">
        </div>
    </section>
    <section class="detailbox clearfix">
    <h3>${columnData.NAME_TWO}<a href="wapv2/listColumnVideo/${pd.CHANNEL_NO}/${pd.COLUMN_ID}" class="fr more">+MORE</a></h3>
    <div class="videoList-play">
    <c:forEach items="${recommenVideoDataList}" var="recommenVideoData" varStatus="vs" >
                <a href="wapv2/videoPlay/${pd.CHANNEL_NO}/${recommenVideoData.COLUMN_ID}/${recommenVideoData.VIDEO_ID}">
            <div class="listBox">
                <div class="relative"><img src="${recommenVideoData.IMG_ONE}" alt="">
								<c:if    test="${columnvideo.FREE_FLAG==1}"> 
								<span class="flag01"></span>
								</c:if> 
								<c:if    test="${columnvideo.VIP_FLAG==1}"> 
								<span class="flag02"></span>
								</c:if>  
             </div>
                <div class="title">${recommenVideoData.NAME_ONE}</div>
            </div>
        </a>
    </c:forEach>
       </div>
</section>    <section class="detailbox clearfix comment">
        <h3>狼友热评</h3>
            <div class="borderBottom clearfix">
            <div class="userface"><img src="http://kou.hyxxzzc.com/public/uploads/icons/1464853668.jpg" alt=""></div>
            <div class="usermsg font12">
                <p class="orange">Karatcka</p>
                <p>更新很快呀！真是为我们这些狼友提供了一个好的平台。大家一起顶起来~~~</p>
                <p>27秒前<span class="fr">热评</span></p>
            </div>
        </div>    
        <div class="borderBottom clearfix">
            <div class="userface"><img src="http://kou.hyxxzzc.com/public/uploads/icons/1464853930.jpg" alt=""></div>
            <div class="usermsg font12">
                <p class="orange">小小巫</p>
                <p>哇！不错不错，而且缓冲很快，点赞！</p>
                <p>47秒前<span class="fr">热评</span></p>
            </div>
        </div>    
        <div class="borderBottom clearfix">
            <div class="userface"><img src="http://kou.hyxxzzc.com/public/uploads/icons/1464854010.jpg" alt=""></div>
            <div class="usermsg font12">
                <p class="orange">喵喵</p>
                <p>尼玛呀~害得我又忍不住撸了几次，压根就停不下来啊</p>
                <p>52秒前<span class="fr">热评</span></p>
            </div>
        </div>    
        <div class="borderBottom clearfix">
            <div class="userface"><img src="http://kou.hyxxzzc.com/public/uploads/icons/1464854126.jpg" alt=""></div>
            <div class="usermsg font12">
                <p class="orange">中央空调</p>
                <p>太精彩了哇，看来充值是没白冲，物超所值，在别的地方都看不了。就你们这里有资源，而且质量高，真爱你们！</p>
                <p>75秒前<span class="fr">热评</span></p>
            </div>
        </div>    
        <div class="borderBottom clearfix">
            <div class="userface"><img src="http://kou.hyxxzzc.com/public/uploads/icons/1464854225.jpg" alt=""></div>
            <div class="usermsg font12">
                <p class="orange">唐唐</p>
                <p>果然是高清全集的，红红火火恍恍惚惚哈哈哈哈，找了这么久终于找到了，每天都会上来看几部。好怕被老婆知道</p>
                <p>108秒前<span class="fr">热评</span></p>
            </div>
        </div>    
        <div class="borderBottom clearfix">
            <div class="userface"><img src="http://kou.hyxxzzc.com/public/uploads/icons/1464854305.jpg" alt=""></div>
            <div class="usermsg font12">
                <p class="orange">电竞陈冠希</p>
                <p>雅蠛蝶~雅蠛蝶~够爽！！！</p>
                <p>123秒前<span class="fr">热评</span></p>
            </div>
        </div>    
        <div class="borderBottom clearfix">
            <div class="userface"><img src="http://kou.hyxxzzc.com/public/uploads/icons/1464854350.jpg" alt=""></div>
            <div class="usermsg font12">
                <p class="orange">再回首恍然如梦</p>
                <p>希望支持下载到手机，家里没有wifi啊，怎么办？想睡觉的时候看</p>
                <p>132秒前<span class="fr">热评</span></p>
            </div>
        </div>    
        <div class="borderBottom clearfix">
            <div class="userface"><img src="http://kou.hyxxzzc.com/public/uploads/icons/1464854428.jpg" alt=""></div>
            <div class="usermsg font12">
                <p class="orange">想你夜不能寐</p>
                <p>你们这个播放器的资源不会失效吧？</p>
                <p>167秒前<span class="fr">热评</span></p>
            </div>
        </div>    
        <div class="borderBottom clearfix">
            <div class="userface"><img src="http://kou.hyxxzzc.com/public/uploads/icons/1464854478.jpg" alt=""></div>
            <div class="usermsg font12">
                <p class="orange">蜡笔没有小新</p>
                <p>今晚好孤单，还好有你陪伴！</p>
                <p>182秒前<span class="fr">热评</span></p>
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
<script type="text/javascript" src="http://kou.hyxxzzc.com/public/player/ckplayer/ckplayer.js"></script>     
<script>
    function loadMovie(type) {
        var w = document.body.clientWidth;        
		  $.get("<%=basePath%>wapv2/getPlayInfo/${videoData.VIDEO_ID}/" +type,function(data,status){
			   if(player) $("#player").html(data);
			  });
    }
    $(function () {        
        var w = document.body.clientWidth;
        var pich = Math.floor(470 / 980 * w);
        $(".movie-pic").height(pich);
        $(".comment .content").css("width", (w - 110) + "px");
        if (vipType >= 2) {
            loadMovie(2);
        } else {
            loadMovie(1);
            setInterval('pay()', 5000);
        }

    });

</script>
</body>
</html>