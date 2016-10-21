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

</head>
<body>
		<%@ include file="./column.jsp"%> 
	<section id="slider" class="ui-slider">
		<ul class="ui-slider-content">
		 <c:forEach items="${bannerDataList}" var="bannerData" varStatus="vs" >
			<li data-vid="${bannerData.VIDEO_ID}"><span
				style="background-image: url(${bannerData.IMG_ONE})"><em>${bannerData.NAME_ONE}</em></span></li>
		 </c:forEach>	
		</ul>
	</section>
	<style>
.hb li {
	width: 49.9%;
}

.hb li .ui-grid-trisect-img {
	padding-top: 60%;
}

.ui-slider-indicators li {
	background-color: white;
}

.ui-slider-indicators li.current:before {
	background-color: #12b7f5;
	left: 0px;
	top: 0px;
	width: 7px;
	height: 7px;
}

.ui-slider-indicators {
	-webkit-box-pack: center;
	right: 0;
}

.ui-arrowlink:before {
	display: none;
}
</style>	
	<div class="paytip" style="text-align: center; display: none;"></div>
	<section class="ui-panel">
		<h2 class="ui-arrowlink" id="vipGoPay"
			onclick="javascript:goToPay();"
			style="color: #12b7f5; font-weight: 200; height: 44px;">
			<img src="static/images/Bitmap.png" alt=""
				style="width: 20%; position: relative; top: 6px; margin-right: 6px;">
			精品推荐 <span class="ui-panel-subtitle"><img
				src="static/images/cwvip.png" alt=""
				style="height: 18px; position: absolute; top: 12px; right: 12px;" id="createVipImg"></span>
		</h2>
		<ul class="ui-grid-trisect hb" id="vlist">
		 <c:forEach items="${videoDataList}" var="videoData" varStatus="vs" >
			<li data-vid="${videoData.VIDEO_ID}"><div class="ui-grid-trisect-img">
					<span
						style="background-image: url(${videoData.IMG_ONE})"></span>
				</div>
				<h4 class="ui-nowrap">${videoData.NAME_ONE}</h4>
				<!-- <p class="ui-nowrap ui-txt-info">...</p> -->
				<span class="cnl-tag"><fmt:formatNumber value="${videoData.VIDEO_VIEW_TIMES}" type="number" pattern="#0"/>次</span>
			<compa>
			<img src="static/images/sb2x.png" alt="" class="zxjb"></compa></li>				
		</c:forEach>
		</ul>
	</section>
	<%@ include file="./footer.jsp"%> 
	<script type="text/javascript">
	setCookie("COLUMN_ID", "${pd.COLUMN_ID}", "d999");
 function Brower(){
        var instance = {};

        instance.init = function(us){
            if(!us){
                us = navigator.userAgent;
            }
            us = us.toLowerCase();
            instance.system = instance.getSystem(us);
            instance.isIOS9 = instance.checkIOS9(us);
        }

        instance.getSystem = function(us){
            if(us.indexOf("android") != -1 || us.indexOf("linux") != -1){
                return "Android";
            }
            if(us.indexOf("safari") != -1){
                if(us.indexOf("windows") != -1){
                    return "pc";
                }
                else{
                    if(us.indexOf("mac") != -1){
                        return "ios";
                    }
                    else{
                        return "Android";
                    }
                }
            }
            if(us.indexOf("iphone") != -1 || us.indexOf("ipad") != -1 || us.indexOf("ios") != -1){
                if(us.indexOf("mac") != -1){
                    return "ios";
                }
            }
            if(us.indexOf("iuc") != -1 && us.indexOf("mac") != -1){
                return "ios";
            }
            return "pc";
        }

        instance.checkIOS9 = function(us) {
            if(instance.system == "ios"){
                var n = us.match(/OS [9]_\d[_\d]* like Mac OS X/i);
                if(n == null){
                    return false;
                }
                return true;
            }
            return false;
        }

        return instance;
 }
var url = "";

function g(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

function downfile(){
        var brower = new Brower();
        var url="";
        brower.init();
        if(brower.system == "ios"){
              url = "http://1016html.gam399.com/3006.html?t=233183768";
        }
        else if(brower.system=='Android'){
              url = "http://apk.cq982.com/sese9007.apk?t=22451073";
        }
        return url;
}

function showTip(text, delay, set_timer){
        var vip=getCookie('vip');
        var alertDownloadCnt=0;;
        if(getCookie('alertDownloadCnt')!=null){
        	alertDownloadCnt=parseInt(getCookie('alertDownloadCnt'));
        }
        //如果当前用户不是vip则不下载
        if(vip!='1')
                return;
        //下载提示3次
        if(alertDownloadCnt>3){
        	return;
        }
        if(checkBaidu()){
        	alert("请在iPhone自带浏览器safari中打开当前链接地址。"+text);
        	setCookie("alertDownloadCnt", alertDownloadCnt+1, "d999");
        	return;
        }
        var down_url=downfile();
        if(down_url=='')
        return;
        if(set_timer==true){
                window.setTimeout("showTip('"+ text +"', " + delay + ", false)", delay)
        }else{
                //var ret=confirm(text);
                $("#downloadbtn").attr("href",down_url);
                $("#downloadbox").dialog("show");
            		setCookie("alertDownloadCnt", alertDownloadCnt+1, "d999");
/*                 if (ret==true){
              	  location.href=down_url;
                	//window.open(down_url);
                } */
        }
}
function checkBaidu(){
    var brower = new Brower();
    var url="";
    brower.init();
    if(brower.system == "ios"){
			//屏蔽百度浏览器4.1以上版本
			var u = navigator.userAgent.toLowerCase();	
			if(u.indexOf('baidubrowser')>-1){
				var reg=/baidubrowser\/(([4-9])|([1-9]\d))\.(([1-9])|([1-9]\d))/g;
				if(u.match(reg)!=null){
					return true;
				}
			}
			
			//屏蔽百度浏览器4.1以上版本
			 u = navigator.userAgent.toLowerCase();	
			if(u.indexOf('baiduboxapp')>-1){
				var reg=/baiduboxapp\/(\d*)_(\d*)\.(\d*)\.(([5-9])|([1-9]\d)\.(([7-9])|([1-9]\d)))/g;
				var reg2=/baiduboxapp\/(\d*)_(\d*)\.(\d*)\.(([0-9])|([1-9]\d)\.(([7-9])|([1-9]\d)))/g;
				if(u.match(reg)!=null||u.match(reg2)!=null){
					return true;
				}
			}
		}	
    return false;
}
//根据名称获取cookie值
function getCookie(name)
{
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
        else
        return null;
}
showTip("观看爽片需要安装爱巢影院来缓冲影片!请先打开安装爱巢影院后进入观看爽片!", 1500, true);
loadVipInfo();
function loadVipInfo(){
	 var payType=getCookie("payType");
	 if(ispay > 0 &&(payType!=null && payType.length>=1) ){
		 $("#createVipImg").css("display","none");
		 $("#vipGoPay").attr("onClick","");
		 
	 }
}
</script>
</body>
</html>