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


<body class="list-video">
	<%@ include file="./toolbar.jsp"%> 
    <section class="videoList" style="float:none; overflow:hidden;">
    		<c:forEach items="${videoDataList}" var="columnvideo" varStatus="vs" >
		          
        <div class="listBox">
            <a href="wapv2/videoPlay/${pd.CHANNEL_NO}/${columnData.COLUMN_ID}/${columnvideo.VIDEO_ID}">    
                <div class="relative">
                <img src="${columnvideo.IMG_ONE}" alt="">
                <c:if    test="${columnvideo.FREE_FLAG==1}"> 
                <span class="flag01"></span>
                </c:if>
                <c:if    test="${columnvideo.VIP_FLAG==2}"> 
                <span class="flag02"></span>
                </c:if>
                </div>
                <div class="title">${columnvideo.NAME_TWO}</div>
            </a>
        </div>

        </c:forEach> 
    </section>
    <section class="piclist_page"><ul> 
            <c:if    test="${PAGE_NO>1}">   
            <li><a href='wapv2/listColumnVideo/${pd.CHANNEL_NO}/${COLUMN_ID}?PAGE_NO=${PAGE_NO-1}'>上一页</a></li>  
            </c:if> 
          	<c:forEach items="${pageNoList}" var="pageNo" varStatus="vs" >
          	 <c:if    test="${pageNo==PAGE_NO}"> 
            <li><span class='current'>${pageNo}</span></li>
            </c:if>
             <c:if    test="${pageNo!=PAGE_NO}"> 
            <li><a href='wapv2/listColumnVideo/${pd.CHANNEL_NO}/${COLUMN_ID}?PAGE_NO=${pageNo}'>${pageNo}</a></li> 
            </c:if>
            </c:forEach>
             <c:if    test="${pageNoList.size()>PAGE_NO}">   
            <li><a href='wapv2/listColumnVideo/${pd.CHANNEL_NO}/${COLUMN_ID}?PAGE_NO=${PAGE_NO+1}'>下一页</a></li>  
             </c:if> 
     </ul></section> 

<%@ include file="./footer.jsp"%> 
<script type="text/javascript">resourceType = 2;</script>
<script>
    $(function () {
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