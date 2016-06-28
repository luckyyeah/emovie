<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
	<header id="header" class="ui-header ui-header-positive ui-border-b">
		<ul class="ui-tiled ui-border-t">
		 <c:forEach items="${columnDataList}" var="column" varStatus="vs" >
			<li data-href="wapmovie/listColumnVideo/${pd.CHANNEL_NO}/${column.COLUMN_ID}" <c:if test="${column.COLUMN_ID==pd.COLUMN_ID}">class="active" </c:if>>
			<c:if test="${vs.index==0}">
			<i class="ui-icon-emo"></i>${column.NAME_ONE}</li>
			</c:if>
			<c:if test="${vs.index==1}">
			<i class="ui-icon-dressup"></i>${column.NAME_ONE}</li>
			</c:if>
			<c:if test="${vs.index==2}">
			<i class="ui-icon-wallet"></i>${column.NAME_ONE}</li>
			</c:if>			
		 </c:forEach>
			<li data-href="wapmovie/about/${pd.CHANNEL_NO}"><i class="ui-icon-personal"></i>关于</li>
		</ul>
	</header>
