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
    </head>


<body class="list-video zuanshi play">
    <header class="black-header fixed">
        <a href="javascript:history.back()" class="back"></a>钻石专区<a href="javascript:pay();" class="openVIP">开通VIP</a>
    </header>
    <section class="videoList">
        <c:forEach items="${videoDataList}" var="columnvideo" varStatus="vs" >        
        <div class="listBox">
            <a href="wapv2/videoPlay/${pd.CHANNEL_NO}/${columnData.COLUMN_ID}/${columnvideo.VIDEO_ID}">
                <div class="relative">
                <img src="${columnvideo.IMG_ONE}" alt="">
                <span class="flag03"></span>
                
                </div>
                
                <div class="title">${columnvideo.NAME_TWO}</div>
            </a>
        </div>
        </c:forEach>        

        	
    </section>
<div class="more_centent" onClick="pay()">   更多精华资源，仅限会员专享。。。</div>
<%@ include file="./footer.jsp"%> 
<%@ include file="./paybox.jsp"%> 
<script type="text/javascript">resourceType=3;</script>
<script>
    $(function(){
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