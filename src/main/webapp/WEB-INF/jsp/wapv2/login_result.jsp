<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
<%@ include file="./head.jsp"%> 
<style>
body {background-color:#f4f4f4;}
#go {width:100%;height:480px;text-align:center; }

#go p {padding-top:15px; font-size:24px; font-family:"微软雅黑"; border:5px #FFC36F solid;  margin-right:0; }
/*#go p {margin-top:430px; font-size:24px; font-family:"微软雅黑";}*/
#go p a {text-decoration:underline;color:#FF6600;}
.mycolor {color:#FF6600;}

</style>

</head>
<body>
<div id="go">
  <p>
订单检查中......
  <br>请您等待<span class="mycolor" id="wait">1</span>秒页面会自动 <a id="href" href="index/${pd.CHANNEL_NO}">跳转</a> (如未跳转，请点击) </p>

</div>

<script src="static/js/v2/flexible_0.3.4.js" type="text/javascript" charset="utf-8"></script>
 <script src="static/js/v2/jquery.min.js" type="text/javascript"></script>
 <script src="static/js/v2/swiper-3.3.1.min.js" type="text/javascript"></script>
 <script src="static/js/v2/pay.js" type="text/javascript"></script> 	
<script type="text/javascript">
(function(){
var wait = document.getElementById('wait'),href = document.getElementById('href').href;
var interval = setInterval(function(){
	var time = --wait.innerHTML;
	if(time <= 0) {
		location.href = href;
		clearInterval(interval); 
	};
}, 1000);  
checkPay();
})();
</script>
</body></html>