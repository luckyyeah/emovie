<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
		<%@ include file="./common.jsp"%> 
			<style>
			#playTip {
			    color: #fff;
			    height: 40px;
			    line-height: 2;
			    text-align: center;
			    margin: 10px 0;
			}			
			#playTip2 {
		    margin: 5% auto 0;
		    border: 2px solid #d64f4f;
		    text-align: center;
		    width: 85%;
				}
				#playTip2 a {
				    font-size: 20px;
				    color: #d64f4f;
				    line-height: 48px;
				    overflow: hidden;
				    display: block;
				}
				#playTip2 a i {
				    color: #d64f4f;
				    display: inline-block;
				    line-height: 20px;
				    font-size: 40px;
				    margin-right: -5px;
				    vertical-align: middle;
				    margin-bottom: 8px;
				}				
			</style>	
</head>
<body id="play">
	<header class="ui-header ui-header-positive">
		<i class="ui-icon-return" id="return"></i>
		<h1 class="ui-nowrap ui-whitespace">视频播放</h1>
	</header>
	<div class="container">
		<input type="hidden" name="videoId" id="videoId" value="${pd.VIDEO_ID}">
		<div id="playTip"></div>
		<div id="playerwrap"></div>
		<div id="playTip2"></div>
	</div>
	<%@ include file="./footer.jsp"%> 
</body>
</html>