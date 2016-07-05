<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
	<header id="header" class="ui-tab ui-header ui-header-positive ui-border-b">
		<ul class="ui-tab-nav ui-tiled ui-border-t">
		 <c:forEach items="${columnDataList}" var="column" varStatus="vs" >
			<li data-href="wapmovie/listColumnVideo/${pd.CHANNEL_NO}/${column.COLUMN_ID}?COLUMN_NO=${vs.index}" <c:if test="${column.COLUMN_ID==pd.COLUMN_ID}">class="active" </c:if>>
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
					 <ul class="ui-tab-content" style="width:300%">
					  <c:forEach items="${columnDataList}" var="column" varStatus="vs" >
			        <li>${column.NAME_ONE}</li>
						</c:forEach>
			    </ul>
	<input type="hidden" name="COLUMN_NO" id="COLUMN_NO" value="${pd.COLUMN_NO}"  />
	</header>
