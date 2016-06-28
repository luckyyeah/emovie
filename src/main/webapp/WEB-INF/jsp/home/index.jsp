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
    	<div class="banner">
    		<div class="banner-bg">
            </div>
            <div class="banner-big">
                <ul class="list" id="list">
								<c:forEach items="${bannerDataList}" var="bannerData" varStatus="vs" end="4">
                <li>
                    <a href="movie/videoPlay/${pd.CHANNEL_NO}/${bannerData.COLUMN_ID}/${bannerData.VIDEO_ID}" target="_blank">
                        <img src="${bannerData.IMG_ONE}" height="446" width="1180"/>
                    </a>
                </li>
                </c:forEach>
                </ul>
            </div>
            <div class="banner-small">
                <ol class="turn" id="turn">
			                <c:forEach items="${bannerDataList}" var="bannerData" varStatus="vs" end="4">
			                	<c:if test="${vs.index<bannerDataList.size()-1}">
 													<li class='current' >
 													<img src="${bannerData.IMG_ONE}" height="88" width="232"/>
 													</li>
                         </c:if>
			                	<c:if test="${vs.index==bannerDataList.size()-1}">
 													<li class='last' >
 													<img src="${bannerData.IMG_ONE}" height="88" width="232"/>
 													</li>
                         </c:if>                         
			                </c:forEach>
                </ol>
            </div>
    	</div>    
    		<div class="main">
    		<div class="main-in areaheart">
          <c:forEach items="${columnDataList}" var="column" varStatus="vs" >
              <c:if test="${column.DATA_TYPE==2}">
    			<div class="main-01 area">
    <div class="main-top">
        <div class="content-right">
            <a href="movie/listColumnVideo/${pd.CHANNEL_NO}/${column.COLUMN_ID}" class="more">更多+</a>
        </div>
        <div class="content-left">
            <h2>${column.NAME_ONE}</h2>
        </div>
    </div>
    <div class="main-show">
        <ul class="video">
<c:forEach    items="${mapColumnvideoList}"    var="mymap"> 
      <c:if    test="${mymap.key==column.COLUMN_ID}"> 
      	<c:set var="columnvideoList"  value="${mymap.value}"/>
					<c:forEach items="${columnvideoList}" var="columnvideo" varStatus="vs" end="4">
                 <c:if test="${vs.index<columnvideoList.size()-1}">
                 <li >
                <a href="movie/videoPlay/${pd.CHANNEL_NO}/${columnvideo.COLUMN_ID}/${columnvideo.VIDEO_ID}">
                 <c:if    test="${columnvideo.FREE_FLAG==1}"> 
                    <i><img src="static/images/free.png" height="47" width="48" alt=""></i>
                 </c:if> 
                 <c:if    test="${columnvideo.VIP_FLAG==1}"> 
                    <i><img src="static/images/VIP.png"" height="47" width="48" alt=""></i>
                 </c:if>                    
                    <img src="${columnvideo.IMG_ONE}" class="litpic">
                    <p>${columnvideo.NAME_ONE}</p>
                    <em></em>
                </a>
           		 </li>
           		 </c:if>    
               <c:if test="${vs.index==4}">
                  <li class='last'>
              		  <a href="movie/videoPlay/${pd.CHANNEL_NO}/${columnvideo.COLUMN_ID}/${columnvideo.VIDEO_ID}">
	                 <c:if    test="${columnvideo.FREE_FLAG==1}"> 
	                    <i><img src="static/images/free.png" height="47" width="48" alt=""></i>
	                 </c:if> 
	                 <c:if    test="${columnvideo.VIP_FLAG==1}"> 
	                    <i><img src="static/images/VIP.png"" height="47" width="48" alt=""></i>
	                 </c:if>   
                    <img src="${columnvideo.IMG_ONE}" class="litpic">
                    <p>${columnvideo.NAME_ONE}</p>
                    <em></em>
                </a>
           		 </li>
               </c:if>
            </c:forEach>
      </c:if> 
</c:forEach>
        </ul>
    </div>
</div> 
</c:if>
</c:forEach>


</div>    		</div>


	<%@ include file="./footer.jsp"%> 	 	
<!-------------------- 右侧导航 ------------------>
<div class="right-nav">
    <ul class="r-nav">
        <li class="part01"></li>
        <li class="part02"></li>
        <li class="part03"></li>
        <li class="part04"></li>
        <li class="part05"></li>
        <li class="part06"></li>
        <li class="gotop"></li>
    </ul>
</div>
	<%@ include file="./login.jsp"%> 
	<%@ include file="./paybox.jsp"%> 
    </body>
</html>