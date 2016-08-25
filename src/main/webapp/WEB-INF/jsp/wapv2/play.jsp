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
            <img src="${videoData.IMG_TWO}" style="height:auto; width:100%;">
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
        <c:forEach items="${clientCommentDataList}" var="clientCommentData" varStatus="vs" >
          <div class="borderBottom clearfix">
            <div class="userface"><img src="${clientCommentData.clientIconUrl }" alt=""></div>
            <div class="usermsg font12">
                <p class="orange">${clientCommentData.clientId }</p>
                <p>${clientCommentData.clientComment }</p>
                <p>${clientCommentData.commentTime}秒前<span class="fr">热评</span></p>
            </div>
        </div>  
        </c:forEach>  
    </section>

<%@ include file="./footer.jsp"%> 
<%@ include file="./paybox.jsp"%> 
<script type="text/javascript">resourceType=${resourceType};</script>
<script type="text/javascript" src="http://kou.hyxxzzc.com/public/player/ckplayer/ckplayer.js"></script>     
<script>
    function loadMovie(type) {
        var w = document.body.clientWidth;        
		  $.get("<%=basePath%>wapv2/getPlayInfo/${videoData.VIDEO_ID}/" +type,function(data,status){
			 		 if(data!=0) $("#player").html(data);
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
            setInterval('pay()', 15000);
        }

    });

</script>
</body>
</html>