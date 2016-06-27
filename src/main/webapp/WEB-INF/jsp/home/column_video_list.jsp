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
    	<div class="main">
    		<div class="main-in areaheart">
    			<div class="main-01 area">
    				<div class="main-top">
    					<div class="content-right">
    						
    					</div>
    					<div class="content-left">
    						<h2>${columnData.NAME_ONE }</h2>
    					</div>
    				</div>
    				<div class="main-show">
    					<ul class="video">
    					<c:forEach items="${videoDataList}" var="columnvideo" varStatus="vs" >
    					    <c:if    test="${videoDataList.size()%6!=0}">   
                   <li  >
                         <a href="movie/videoDetail/${columnData.COLUMN_ID}/${columnvideo.VIDEO_ID}">
				                 <c:if    test="${columnvideo.FREE_FLAG==1}"> 
				                    <i><img src="static/images/free.png" height="47" width="48" alt=""></i>
				                 </c:if> 
				                 <c:if    test="${columnvideo.VIP_FLAG==1}"> 
				                    <i><img src="static/images/VIP.png"" height="47" width="48" alt=""></i>
				                 </c:if>  
                             <img src="${columnvideo.IMG_ONE}" width="180" height="238">
                             <p>${columnvideo.NAME_ONE}</p>
                             <em></em>
                         </a>
                     </li>
                  </c:if>
                   <c:if    test="${videoDataList.size()%6==0}">   
                    <li class='last' >
                         <a href="movie/videoDetail/${columnData.COLUMN_ID}/${columnvideo.VIDEO_ID}">
				                 <c:if    test="${columnvideo.FREE_FLAG==1}"> 
				                    <i><img src="static/images/free.png" height="47" width="48" alt=""></i>
				                 </c:if> 
				                 <c:if    test="${columnvideo.VIP_FLAG==1}"> 
				                    <i><img src="static/images/VIP.png"" height="47" width="48" alt=""></i>
				                 </c:if>  
                             <img src="${columnvideo.IMG_ONE}" width="180" height="238">
                             <p>${columnvideo.NAME_ONE}</p>
                             <em></em>
                         </a>
                     </li>                   
                   </c:if>
              </c:forEach>  
                           						

                            
    					</ul>
                        <div class="page">
                            <ul class="p-list">
                              <c:if    test="${PAGE_NO>1}">   
                              <li><a href='movie/listColumnVideo/${COLUMN_ID}?PAGE_NO=${PAGE_NO-1}'>上一页</a></li>  
                              </c:if> 
                            	<c:forEach items="${pageNoList}" var="pageNo" varStatus="vs" >
                            	 <c:if    test="${pageNo==PAGE_NO}"> 
                              <li><span class='current'>${pageNo}</span></li>
                              </c:if>
                               <c:if    test="${pageNo!=PAGE_NO}"> 
                              <li><a href='movie/listColumnVideo/${COLUMN_ID}?PAGE_NO=${pageNo}'>${pageNo}</a></li> 
                              </c:if>
                              </c:forEach>
                               <c:if    test="${pageNoList.size()>PAGE_NO}">   
                              <li><a href='movie/listColumnVideo/${COLUMN_ID}?PAGE_NO=${PAGE_NO+1}'>下一页</a></li>  
                               </c:if>                     
                              </ul>
                        </div>
    				</div>
    			</div>
    		</div>
    	</div>

	<%@ include file="./footer.jsp"%> 		
	<%@ include file="./login.jsp"%> 
	<%@ include file="./paybox.jsp"%> 
    </body>
</html>