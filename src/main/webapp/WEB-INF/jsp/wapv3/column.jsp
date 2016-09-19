<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
	<header id="header" class="ui-header ui-header-positive ui-border-b" style="height: 45px; background-image: none; font-size: 18px;">
		<ul class="ui-tiled ui-border-t" style="height: 44px; background-image: none; border: none;">
		 <c:forEach items="${columnDataList}" var="column" varStatus="vs" >
		  <c:if test="${column.DATA_TYPE !=9}">  
			<li style="height: 44px;"  data-href="wapv3/listColumnVideo/${pd.CHANNEL_NO}/${column.COLUMN_ID}?COLUMN_NO=${vs.index}" <c:if test="${column.COLUMN_ID==pd.COLUMN_ID}">class="active" </c:if>>
			${column.NAME_ONE}</li>	
			</c:if>
		 </c:forEach>
			<li data-href="wapv3/about/${pd.CHANNEL_NO}">关于</li>
		</ul>
	</header>	    
	<input type="hidden" name="COLUMN_NO" id="COLUMN_NO" value="${pd.COLUMN_NO}"  />
	<input type="hidden" name="COLUMN_ID" id="COLUMN_ID" value="${pd.COLUMN_ID}"  />

