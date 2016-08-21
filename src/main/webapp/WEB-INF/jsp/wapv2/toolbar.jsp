<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
    <header class="black-header fixed">
        <span class="logo"><img src="http://ww4.sinaimg.cn/large/d2d743f1gw1f5x32xdn4dj203k03kt9e.jpg" alt=""></span>撸撸影院<a href="javascript:pay();" class="openVIP">开通VIP</a>
    </header>
    <div class="online"></div>
    <nav>     
     		 <c:if test="${pd.COLUMN_ID==null}">
    		 <a href="index/${pd.CHANNEL_NO}" class="active">精品</a> 
    		 </c:if>
     		 <c:if test="${pd.COLUMN_ID!=null}">
    		 <a href="index/${pd.CHANNEL_NO}" >精品</a> 
    		 </c:if>
         <c:forEach items="${columnDataList}" var="column" varStatus="vs" >
         <c:if test="${column.DATA_TYPE==2}">
         <c:if test="${pd.COLUMN_ID==column.COLUMN_ID}">
          <a href='wapv2/listColumnVideo/${pd.CHANNEL_NO}/${column.COLUMN_ID}' class="active" >${column.NAME_ONE}</a>
         </c:if>
         <c:if test="${pd.COLUMN_ID!=column.COLUMN_ID}">
         <a href='wapv2/listColumnVideo/${pd.CHANNEL_NO}/${column.COLUMN_ID}'>${column.NAME_ONE}</a>
         </c:if>
         </c:if>

         </c:forEach>
          <a href="tuku/index/${pd.CHANNEL_NO}">美图</a>
    </nav>