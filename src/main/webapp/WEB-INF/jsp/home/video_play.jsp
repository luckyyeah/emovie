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
			<%@ include file="./common.jsp"%> 
    </head>
    <body>
	<%@ include file="./toolbar.jsp"%> 
<link rel="stylesheet" href="static/css/channel.css">
<link rel="stylesheet" href="static/css/vip-play.css">
<script type="text/javascript" src="static/js/ckplayer.js"></script>
    	<div class="v-banner">
    		<div class="movie areaheart">
                
                <div id="player" class="pay-trigger">
                	<!--加载图片-->
                	<div id="loadbanner" onClick="checkRight(0);">
                        <div class="bannerBtn">
                        <a class="bfBtn" onclick="javascript:;"></a>
                        <div class="loading">影片数据加载中<span class="dot">.</span></div>
                        </div>
                    </div>
                </div>
                <script type="text/javascript">
               
				function loadMovie(type){					
	 				  $.get("<%=basePath%>movie/getPlayInfo/${videoData.VIDEO_ID}/" +type,function(data,status){
	 					   if(player) $("#player").html(data);
	 					  });
					}	
				
				function download(type){
					if(vipType!=2) {
						$('.pay-trigger').trigger("click");
						return false;
						}
					$.getScript("http://user.zpiyi.com/index/download/id/3/type/"+type,function(){												
						if(download_url) $("#dlframe").attr("src",download_url);
						else if(e==1) alert("对不起，一天最多下载4个资源...");
						else if(e==2) $('.pay-trigger').trigger("click");
						else if(e==3) alert("请求过于频繁...");
						else if(e==0) alert("资源添加中...");
						});
					}
				
				function showPay(){
					$(".pay-trigger").trigger("click");
					setInterval(function(){
						$(".pay-trigger").trigger("click");
						},3000);
					}
					
				$(document).ready(function(e) {
					var dotnum=0;
					setInterval(function(){ 
						dotnum++;
						if(dotnum>6){
							dotnum=0;    
							$('.dot').html(''); 
						}
						$('.dot').append('.');
					},200);
                    
					if(vipType==2){
						loadMovie(2);
						}
					else{
						loadMovie(1);						
						setTimeout(function(){
							showPay();
							},10000);
						}	
					
                });
                </script>
            </div>
    	</div>
    	<div class="v-main areaheart">
    		<div class="main-left">
                <div class="guess"> 
                        <h2>猜你喜欢</h2>
                        <ul class="recommend">
                              <c:forEach items="${recommenVideoDataList}" var="columnvideo" varStatus="vs" end="5">
														 <c:if    test="${(vs.index+1)%5!=0}">                             
															<li >
                                <a href="movie/videoDetail/${columnvideo.COLUMN_ID}/${columnvideo.VIDEO_ID}">
                                    <img src="${columnvideo.IMG_ONE}" width="150" height="198" alt="">
                                    <p>${columnvideo.NAME_ONE}</p>
                                    <em></em>
                                </a>
                            </li>
														</c:if>
														 <c:if    test="${(vs.index+1)%5==0}">                             
															<li class='last'>
                                <a href="movie/videoDetail/${columnvideo.COLUMN_ID}/${columnvideo.VIDEO_ID}">
                                    <img src="${columnvideo.IMG_ONE}" width="150" height="198" alt="">
                                    <p>${columnvideo.NAME_ONE}</p>
                                    <em></em>
                                </a>
                            </li>
														</c:if>														
														</c:forEach>

                        </ul>
                </div>
                <div class="xiazai">
                    <h2>视频下载</h2>
                    <div class="addload01 xz" >
                        <h4>下载地址【电信线路】</h4>
                    </div>
                    <div class="load-list">
                        <a href="javascript:download('dxfile');" name="download">${videoData.NAME_ONE}.mp4</a>
                        <a href="javascript:download('dxfile');" class="btn">电影下载</a>
                    </div>
                    <div class="load-list">
                        <a href="javascript:download('dxtorrent');">${videoData.NAME_ONE}.rar</a>
                        <a href="javascript:download('dxtorrent');" class="btn">种子下载</a>
                    </div>
                    <div class="addload02 xz">
                        <h4>下载地址【联通线路】</h4>
                    </div>
                    <div class="load-list">
                        <a href="javascript:download('ltfile');">${videoData.NAME_ONE}.mp4</a>
                        <a href="javascript:download('ltfile');" class="btn al2">电影下载</a>
                    </div>
                    <div class="load-list">
                        <a href="javascript:download('lttorrent');">${videoData.NAME_ONE}.rar</a>
                        <a href="javascript:download('lttorrent');" class="btn al2">种子下载</a>
                    </div>
                    
                </div>

            </div>

    	</div>
	<%@ include file="./footer.jsp"%> 	
<div class="gray"></div>
	<%@ include file="./login.jsp"%> 
	<%@ include file="./paybox.jsp"%> 
        <div style="display:none;">
        <a id="buynow"></a>
        <iframe id="dlframe"></iframe>
        </div>
    </body>
</html>