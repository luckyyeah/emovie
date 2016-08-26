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


<body class="index picture play">
    <header class="black-header fixed">
        <span class="logo"><img src="http://ww4.sinaimg.cn/large/d2d743f1gw1f5x32xdn4dj203k03kt9e.jpg" alt=""></span>激情美图<a href="javascript:pay();" class="openVIP">开通VIP</a>
    </header>
    <section>
            <div class="swiper-container">
                <div class="swiper-wrapper">

 								<c:forEach items="${bannerDataList}" var="bannerData" varStatus="vs" end="4">
     
                      <div class="swiper-slide">
                        <a href="tuku/listImages/${pd.CHANNEL_NO}/${bannerData.COLUMN_ID}">
                            <img src="${bannerData.IMG_ONE}">
                            <div class="shaddow"></div>
                            <div class="shaddowTitle">${bannerData.NAME_ONE}</div>
                        </a>
                    </div>    
                </c:forEach>                               
                
                    
                 
                </div>
                <div class="swiper-pagination"></div>
            </div>
        </section> 
 <c:forEach items="${tabDataList}" var="tab" varStatus="vs" >
     <c:if test="${tab.DATA_TYPE==2}">        
    <section class="picList clearfix">
    <h3>${tab.NAME}<a href="tuku/listTukuCovers/${pd.CHANNEL_NO}/${tab.TAB_ID}" class="fr more">+MORE</a></h3>
<c:forEach    items="${mapTabColumnList}"    var="mymap"> 
      <c:if    test="${mymap.key==tab.TAB_ID}"> 
      	<c:set var="tabColumnList"  value="${mymap.value}"/>
         <div class="col-md-12">
       			<c:forEach items="${tabColumnList}" var="tabColumn" varStatus="vs" end="1">
               <c:if test="${vs.index<tabColumnList.size()-1}">  
                  <c:if    test="${tabColumn.DATA_TYPE==3}">        
				        <a href="tuku/listImages/${pd.CHANNEL_NO}/${tabColumn.COLUMN_ID}">
				            <div class="col-md-8">
				                <div class="pl10">                   
				                    <div class="relative"><img class="top-pic" src="${tabColumn.IMG_ONE}" alt="" style="height: 325px;"></div>
				                    <div class="title">${tabColumn.NAME_ONE}</div>                   
				                </div>
				            </div>
				        </a>
				        </c:if>
				        <c:if    test="${tabColumn.DATA_TYPE==2}">        
				        <a href="tuku/listImages/${pd.CHANNEL_NO}/${tabColumn.COLUMN_ID}">
				            <div class="col-md-4">
				                <div class="pl10">                   
				                    <div class="relative"><img class="little-pic" src="${tabColumn.IMG_ONE}" alt="" style="height: 325px;"></div>
				                    <div class="title">${tabColumn.NAME_ONE}</div>                   
				                </div>
				            </div>
				        </a>
				        </c:if>
			        </c:if>
			     </c:forEach>
			    </div>
       			<c:forEach items="${tabColumnList}" var="tabColumn" varStatus="vs" begin="3" end="5">
               <c:if test="${vs.index<tabColumnList.size()-1}">  
                  <c:if    test="${tabColumn.DATA_TYPE==2}"> 
						        <a href="tuku/listImages/${pd.CHANNEL_NO}/${tabColumn.COLUMN_ID}">
						        <div class="col-md-4">
						            <div class="pl10">
						                <div class="relative"><img src="${tabColumn.IMG_ONE}" alt="" style="height: 325px;"></div>
						                <div class="title">${tabColumn.NAME_ONE}</div>
						            </div>
						        </div>
						    	</a>                
                  </c:if>
                </c:if>
            </c:forEach>			    
        </c:if>
     </c:forEach> 
</section>
</c:if>
</c:forEach>  
 
<%@ include file="./../footer.jsp"%> 
<%@ include file="./../paybox.jsp"%> 
<script type="text/javascript">resourceType = 1;</script>
<script>
    $(function () {
        var mySwiper = new Swiper('.swiper-container', {
            loop: true,
            nextButton: '.swiper-button-next',
            prevButton: '.swiper-button-prev',
            pagination: '.swiper-pagination',
            paginationClickable: true
        });
    })
</script>
<script type="text/javascript">
    $(function () {
        var picH = parseInt($(".little-pic").eq(0).width() * 278 / 200);
        $(".top-pic").height(picH);
        window.addEventListener("orientationchange", function () {
            var picH = parseInt($(".little-pic").eq(0).width() * 278 / 200);
            $(".top-pic").height(picH);
        });
    });
</script>
</body>
</html>