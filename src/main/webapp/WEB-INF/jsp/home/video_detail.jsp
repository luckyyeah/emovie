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
<link rel="stylesheet" href="static/css/details.css">
<script type="text/javascript">

$(function(){
	if(vipType==2){
		$(".try a").html("立即播放");
		$(".buy a").html("已开通VIP"); 
		}
	else{
		
		}	
	});

	
</script>
    	<div class="details clearfix">
            <div class="details-in areaheart">
                <div class="d-left">
                    <div class="matter01 mb">
                        <div class="pic-left">
                            <img src="${videoData.IMG_ONE}" width="200" height="264" alt="">
                        </div>
                        <div class="introduce">
                            <div class="video-h pl">
                                <h3>${videoData.NAME_ONE}</h3>
                            </div>
                            <div class="video-d pl">
                                <p>
                                    <span>状态：高清</span>
                                    <span>主演：</span>
                                    <span>时长：78 分钟</span>
                                </p>
                                <p>
                                    <span>年份：2016</span>
                                    <span>地区：中国</span>
                                    <span>语言：日语</span>
                                </p>
                                <p>
                                    <span>类型：${columnData.NAME_ONE}</span>
                                    <span>更新时间：1457508153</span>
                                </p>
                                <!--<p>
                                    <span>价格：<i>9积分</i></span>
                                    <span>VIP年会员：<i>免费</i></span>
                                </p>-->
                            </div>
                            <div class="video-p">
                                <ul class="pay">
                                	
                                    <li class="try"><a href="movie/videoPlay/${pd.CHANNEL_NO}/${videoData.COLUMN_ID}/${videoData.VIDEO_ID}">正片播放</a></li>
                                    <li class="buy pay-trigger"><a href="javascript:;">立即购买</a></li>
                                    <li class="download"><a href="play_2.html#download">立即下载</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="matter02 mb">
                        <h2>视频介绍</h2>
                        <p> ${videoData.SUMMARY_ONE}</p>
                   
                    </div>
                    
                    <div class="matter04">
                        <h2>相关推荐</h2>
                        <ul class="recommend">
                             <c:forEach items="${recommenVideoDataList}" var="columnvideo" varStatus="vs" end="9">
														 <c:if    test="${(vs.index+1)%5!=0}">   
															<li >
                                <a href="movie/videoDetail/${pd.CHANNEL_NO}/${columnvideo.COLUMN_ID}/${columnvideo.VIDEO_ID}">
                                    <img src="${columnvideo.IMG_ONE}" width="150" height="198" alt="">
                                    <p>${columnvideo.NAME_ONE}</p>
                                    <em></em>
                                </a>
                            </li>
                            </c:if>
 														 <c:if    test="${(vs.index+1)%5==0}">   
														<li class='last'>
                                <a href="movie/videoDetail/${pd.CHANNEL_NO}/${columnData.COLUMN_ID}/${columnvideo.VIDEO_ID}">
                                    <img src="${columnvideo.IMG_ONE}" width="150" height="198" alt="">
                                    <p>${columnvideo.NAME_ONE}</p>
                                    <em></em>
                                </a>
                            </li>
                            </c:if>                           
														</c:forEach> 


                        </ul>
                    </div>

                    
                </div>
                <div class="d-right">
                    <div class="aside01 mb pay-trigger" id="ktvip">
                        <a href="javascript:;">
                            <img src="static/images/kt_vip.fw.png" alt="">
                        </a>
                    </div>
                    <div class="aside02 mb">
                        <h2>热门排行</h2>
                        <ul class="hot">
			             <c:forEach items="${topVideoDataList}" var="topVideoData" varStatus="vs" end="9">               
			         		<c:if    test="${(vs.index+1) <=3}">  
			         		 <li>
			            <i  class="hotter" >${(vs.index+1)}</i>
			            <a href="movie/videoDetail/${pd.CHANNEL_NO}/${topVideoData.COLUMN_ID}/${topVideoData.VIDEO_ID}">${topVideoData.NAME_ONE}</a>
			       		  </li>	
			       		  </c:if>
			         		<c:if    test="${(vs.index+1) >3}">  
			         		 <li>
			            <i  >${(vs.index+1)}</i>
			            <a href="movie/videoDetail/${pd.CHANNEL_NO}/${topVideoData.COLUMN_ID}/${topVideoData.VIDEO_ID}">${topVideoData.NAME_ONE}</a>
			       		  </li>	
			       		  </c:if>			       		  
			       		  </c:forEach>
   
                          </ul>
                    </div>

                </div>
            </div>
        </div>
    	
	<%@ include file="./footer.jsp"%> 	
<div class="gray"></div>
	<%@ include file="./login.jsp"%> 
	<%@ include file="./paybox.jsp"%>  
    </body>
</html>