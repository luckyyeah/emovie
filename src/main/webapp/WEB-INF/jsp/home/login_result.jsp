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
<title>追风电影</title>
<style>
body {background-color:#f4f4f4;}
#go {position: absolute;width:950px;height:485px;left:50%;top:50%;margin-left:-475px;margin-top:-243px;text-align:center;/*border:3px solid #FF9900;*/}

#go p {padding-top:15px; font-size:24px; font-family:"微软雅黑"; border:5px #FFC36F solid; border-bottom:0; margin:0; background-color:#fff; }
/*#go p {margin-top:430px; font-size:24px; font-family:"微软雅黑";}*/
#go p a {text-decoration:underline;color:#FF6600;}
.mycolor {color:#FF6600;}
#go #bottom { width:950px; height:420px; background:url(http://ww3.sinaimg.cn/large/006r311dgw1f3yiacqezaj30qe0dhwge.jpg) no-repeat 0 bottom; }
</style>

</head>
<body>
<div id="go">
  <p>
  <c:if test="${loginFlag!=null && loginFlag==1 }">
  	登录成功 
  </c:if>
  <c:if test="${loginFlag!=null && loginFlag==-1}">
  	退出成功 
  </c:if>  
  <c:if test="${loginFlag==null || (loginFlag!=1 && loginFlag!=-1) }">
  	登录失败 
  </c:if>  
  <br>请您等待<span class="mycolor" id="wait">1</span>秒页面会自动 <a id="href" href="${pd.fromUrl}">跳转</a> (如未跳转，请点击) </p>
  <div id="bottom"></div>
</div>
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
})();
</script>


</body></html>