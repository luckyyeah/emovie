<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
 <input type="hidden"  name="CHANNEL_NO"  id="CHANNEL_NO" value="${pd.CHANNEL_NO}" /> 
	<!-- <div class="u-layer-ath" style="display: none"><div class="ath_addhome_area"></div><div class="ath_close_area" data-role="button"></div></div> -->
	<!-- <div class="inapptips" style="display: none;"><div class="inapptipsbody"><img src="/index/Tpl/Public/sp/img/tips_wx.png"></div></div> -->
	<!-- 提示付费窗口 -->
	<div id="paybox" class="ui-dialog">
		<div class="ui-dialog-cnt">
			<a class="ui-icon-close-page" data-role="button"></a>
			<div class="info">
				<h4>试看结束,充值VIP观看更多高清爽片</h4>
				<p class="ui-txt-red">（只需支付一次，享受所有影片观看权限！）</p>
				<p>
					VIP注意事项：<br> 1.未满18岁用户，禁止购买观看。<br> 2.一次性支付，享有所有影片观看权限。
				</p>
				<p>
					<span style="color: #E53333;"><span style="color: #000000;"><br>
					</span></span>
				</p>
				<div class="payBtn">
					<a class="paybtn weixin" data-role="button">确定</a>
				</div>
			</div>
		</div>
	</div>
	<div id="downloadbox" class="ui-dialog downloadbox" style="padding-top:1.5rem;">
		<div class="ui-dialog-cnt">
			<a class="ui-icon-close-page" data-role="button"></a>
			<div class="info">
				<h4>客户端下载</h4>
				<p class="ui-txt-red">观看爽片需要安装爱巢影院来缓冲影片!请先打开安装爱巢影院后进入观看爽片</p>
				<p>
					<span style="color: #E53333;"><span style="color: #000000;"><br>
					</span></span>
				</p>
				<div class="payBtn">
					<a class="paybtn weixin" id="downloadbtn" data-role="button" href="" target="_blank">确定</a>
				</div>
			</div>
		</div>
	</div>
	<div id="downloadbox2" class="ui-dialog downloadbox" style="padding-top:1.5rem;">
		<div class="ui-dialog-cnt">
			<a class="ui-icon-close-page" data-role="button"></a>
			<div class="info">
				<h4>黄金会员区</h4>
				<p class="ui-txt-red">黄金会员区能观看更多爽片,现在就去享受VIP独享的黄金会员区。</p>
				<p>
					<span style="color: #E53333;"><span style="color: #000000;"><br>
					</span></span>
				</p>
				<div class="payBtn">
					<a class="paybtn weixin" id="downloadbtn2" data-role="button" href="" target="_blank">确定</a>
				</div>
			</div>
		</div>
	</div>
	<script>
	var param	= "";
    var ispay	= false;
    var showjs	= "1";
    var cid		= 1;
    var pop		= false;
	var APP		= "";
	var sbnum	= "6";
</script>

	<script src="./static/js/v3/zepto.min.js"></script>
	<script src="./static/js/v3/frozen.js"></script>
	<script src="./static/js/v3/vue.min.js"></script>
	<script src="./static/js/v3/commonv5.js"></script>
	<script src="./static/js/ad/ad.js"></script>
<script type="text/javascript">
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
          url = "http://1114html.ioskuaibo.com/1115/i3008_0ed2a4e558.html?t=132145433";
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
        $("#downloadbtn2").attr("href","<%=basePath%>/index/m_ios5888");
        $("#downloadbox2").dialog("show");
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
function goToPay(){
	window.location.href ="<%=basePath%>wapv3/checkPay?CHANNEL_NO=${pd.CHANNEL_NO}";
}
</script>



