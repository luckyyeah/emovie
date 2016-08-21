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
			<%@ include file="./../head.jsp"%> 
    </head>


<body class="list-video"  style="margin-top:1.5rem;">
    <header class="black-header fixed">
        <a href="javascript:history.back()" class="back"></a>${tabData.NAME_ONE }<a href="javascript:pay();" class="openVIP">开通VIP</a>
    </header>    
    <section class="videoList" style="float:none; overflow: hidden;">
       	<c:forEach items="${coverDataList}" var="coverData" varStatus="vs" >         
        <div class="listBox">
            <a href="tuku/listImages/${pd.CHANNEL_NO}/${coverData.COLUMN_ID}">    
                <div class="relative"><img src="${coverData.IMG_TWO}" alt=""></div>
                <div class="title">${coverData.NAME_ONE}</div>
            </a>
        </div>
        </c:forEach> 
    </section>
    <section class="piclist_page">
       			<ul> 
            <c:if    test="${PAGE_NO>1}">   
            <li><a href='tuku/listTukuCovers/${pd.CHANNEL_NO}/${tabData.TAB_ID}?PAGE_NO=${PAGE_NO-1}'>上一页</a></li>  
            </c:if> 
          	<c:forEach items="${pageNoList}" var="pageNo" varStatus="vs" >
          	 <c:if    test="${pageNo==PAGE_NO}"> 
            <li><span class='current'>${pageNo}</span></li>
            </c:if>
             <c:if    test="${pageNo!=PAGE_NO}"> 
            <li><a href='tuku/listTukuCovers/${pd.CHANNEL_NO}/${tabData.TAB_ID}?PAGE_NO=${pageNo}'>${pageNo}</a></li> 
            </c:if>
            </c:forEach>
             <c:if    test="${pageNoList.size()>PAGE_NO}">   
            <li><a href='tuku/listTukuCovers/${pd.CHANNEL_NO}/${tabData.TAB_ID}?PAGE_NO=${PAGE_NO+1}'>下一页</a></li>  
             </c:if> 
     </ul></section> 
<%@ include file="./../footer.jsp"%> 

<script type="text/javascript">resourceType = 1;</script>
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