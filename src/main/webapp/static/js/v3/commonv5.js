function vippop() {
    debug && console.log("ispay==1");
    var i = $.dialog({
        title: "恭喜您成为VIP会员",
        diagid: "sayhi",
        content: "您现在已获得：<ul><li>精心挑选的美女直播视频与女优写真</li></ul><p><small>＊本站内容仅供欣赏，不涉及非法内容，请谅解</small></p>",
        button: ["我知道了"]
    });
    ispay = _ispay = 2,
    setCookie("ispay", _ispay, "d7"),
    setCookie("trymp4", "", "d7"),
    setCookie("vip", "1", "d7"),
    i.on("dialog:action",
    function(i) {
        0 === i.index && (window.location.href = "wapv3/index/"+$("#CHANNEL_NO").val())
    })
}
function gopay() {
    "undefined" != typeof player && player.remove();
    $("#videoImage").css('display','block');
    $("#vdcont").css('display','block');
    var i = $.dialog({
        title: "温馨提示",
        content: "非会员只能试看20秒视频哦~",
        button: [ "去充值"]
    });
    i.on("dialog:action",
    function(i) {
    		var CHANNEL_NO =$("#CHANNEL_NO").val();
         window.location.href = "wapv3/checkPay?CHANNEL_NO="+CHANNEL_NO
    })
}
function showPayError() {
	 var CHANNEL_NO =$("#CHANNEL_NO").val();
	window.location.href ="wapv3/checkPay?CHANNEL_NO="+CHANNEL_NO;
/*    var i = $.dialog({
        title: "温馨提示",
        content: "本次充值失败，请确认支付信息是否正确！",
        button: ["确定"]
    });
    i.on("dialog:action",
    function(i) {
    	window.location.href ="wapv3/checkPay";
    })*/
}
function getNo() {
    var i = "x_a_no",
    t = 1285,
    e = getCookie(i);
    e ? (_ckno = parseInt(e) + 1, setCookie(i, _ckno, "d30")) : setCookie(i, t, "d30"),
    e = e ? _ckno: t,
    $("#showno").html(e);
    var o = $(".ui-newstips-wrap");
    o.show().addClass("flip-top"),
    setTimeout(function() {
        o.hide()
    },
    5e3)
}
function getQuery(i) {
    var t = new RegExp("(^|&)" + i + "=([^&]*)(&|$)", "i"),
    e = window.location.search.substr(1).match(t);
    return null !== e ? unescape(e[2]) : null
}
function getProid() {
    var i = location.pathname;
    return arr = i.split("/"),
    arr[1]
}
function setCookie(i, t, e) {
    var o = getsec(e),
    a = new Date;
    a.setTime(a.getTime() + 1 * o),
    document.cookie = i + "=" + escape(t) +";path=/"+ ";expires=" + a.toGMTString()
}
function getCookie(i) {
    var t, e = new RegExp("(^| )" + i + "=([^;]*)(;|$)");
    return t = document.cookie.match(e),
    t ? t[2] : null
}
function getsec(i) {
    var t = 1 * i.substring(1, i.length),
    e = i.substring(0, 1);
    return "s" == e ? 1e3 * t: "h" == e ? 60 * t * 60 * 1e3: "d" == e ? 24 * t * 60 * 60 * 1e3: void 0
}
function uuid(i, t) {
    var e, o = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split(""),
    a = [];
    if (t = t || o.length, i) for (charS = [o[3], o[6], o[9], o[12], o[15]], e = 0; i > e; e++) 11 == e ? a[e] = charS[Math.floor(5 * Math.random())] : a[e] = o[0 | Math.random() * t];
    else {
        var n;
        for (a[8] = a[13] = a[18] = a[23] = "-", a[14] = "4", e = 0; 36 > e; e++) a[e] || (n = 0 | 16 * Math.random(), a[e] = o[19 == e ? 3 & n | 8 : n])
    }
    return a.join("")
}
function reuuid() {
    _uid = uuid(16, 32),
    setCookie("uid", _uid, "d30"),
    uid = _uid
}

function checkPay() {
		var isPay = -1;
		var chkUid = getCookie("uid");
    $.ajax({
    	type : "post",
    	url : "wapv3/checkPayed",
    	data: {uid:chkUid}, 
    	async : false,
    	success : function(data){
    		if(data!="-1"){
    			setCookie("ispay", 1, "d7");
    			setCookie("payType", data, "d7");
    			vippop();
    			isPay=0;
    		} 
    	}
    	});

    return isPay;
}


function jumptowx(i) { - 1 == ispay && (ispay = 0),
    setCookie("ispay", ispay, "d7"),
    window.location.href = i;
/*    var t = $.dialog({
        content: "支付成功后请点击完成按钮",
        diagid: "isok",
        button: ["取消", "完成"]
    });
    t.on("dialog:action",
    function(i) {
        if(1 == i.index){
        	checkPay();
        }
    })*/
}
var ispay = -1,
proid = 0,
//resourceDomain = "http://c.5jrs.com/",
uid = "",
apiurl = "",
apiurl1 = "",
apiurl2 = "",
site = 1,
paytype = "my",
source = "iw",
debug = !1,
inBrowser = "undefined" != typeof window && "[object Object]" !== Object.prototype.toString.call(window),
UA = inBrowser && window.navigator.userAgent.toLowerCase(),
isMQQBrowser = UA && UA.indexOf("mqqbrowser/") > 0,
_proid = getProid();
null !== _proid ? (proid = _proid, setCookie("proid", _proid, "d30")) : getCookie("proid") && (proid = getCookie("proid"));
var _uid = getCookie("uid");
null !== _uid ? (uid = _uid, debug && console.log("get uid:" + uid)) : (_uid = uuid(16, 32), setCookie("uid", _uid, "d30"), debug && console.log("set uid cookie:" + _uid), uid = _uid);
var ts = "",
_ts = getCookie("ts");
null !== _ts ? (ts = _ts, debug && console.log("get ts:" + uid)) : (ts = (new Date).getTime(), setCookie("ts", ts, "d7"));
var tt = "",
_tt = getCookie("tt");
null !== _tt ? (tt = _tt, debug && console.log("get tt:" + uid)) : /check/i.test(location.pathname) ;
var _ispay = getCookie("ispay");
null !== _ispay && (ispay = parseInt(_ispay), 0 === ispay && checkPay()),
debug && console.log("get ispay:" + ispay);
var iftry = getCookie("trymp4");
iftry || "" === iftry || setCookie("trymp4", "try1|try2|try3|try4|try5", "d999"),
window.top !== window.self && (window.top.location = window.location),
debug && console.log("ispay before:" + ispay),
1 == ispay && vippop();
var newstips = '<div id="newstips" class="ui-newstips-wrap"><div class="ui-newstips"><i class="ui-icon-checked-s"></i><div>第<span id="showno"></span>位会员充值成功！</div></div></div>';
if ($(function() {
    1 > ispay && ($("body").append(newstips), setInterval(getNo, 1e4))
}), $(function() {
    $("#qqvideo-overlay-0,#qqvideobridge").remove()
}), $("#header li").on("click",
function() {
    $(this).data("href") && (location.href = $(this).data("href"))
}), $("#return").on("click",
function() {
	  if(getCookie("COLUMN_ID")==null || getCookie("COLUMN_ID")==""){
		  window.location.href = "wapv3/index/"+$("#CHANNEL_NO").val()
	  } else {
		  window.location.href = "wapv3/listColumnVideo/"+$("#CHANNEL_NO").val()+"/"+getCookie("COLUMN_ID")
	  }
}), !/(check|about|play)/i.test(location.pathname)) {
    var slider;
    try{
	    slider= new fz.Scroll(".ui-slider", {
	        role: "slider",
	        indicator: !0,
	        autoplay: !0,
	        interval: 3e3
	    });
    }catch( ex){
    	
    }
/*    var tab = new fz.Scroll('#header', {
        role: 'tab',
        autoplay: true,
        interval: 3000
    });

    tab.on('beforeScrollStart', function(from, to) {
    	//console.log(from,to);// from 为当前页，to 为下一页
    	
    });

    tab.on('scrollEnd', function(curPage) {
    //alert(curPage);
    	var channelNo =$("#COLUMN_NO").val();
    	if(channelNo !=""){
    		curPage =parseInt(channelNo);
    	} else {
    		channelNo =1;
    	}
    	var tabContent = $(".ui-tiled li")[curPage];
    	location.href=tabContent.attributes["data-href"].value+"?COLUMN_NO="+channelNo;
    });*/    
    
    if ($("#slider li, #vlist li , #recommendVideo, #vdcont").on("click",
    function() {
    		var type =1;
        var i = $(this).data("vid"),
        t = $(this).children("h4").html();       
        if ("" !== iftry || ispay > 0) window.location.href = "wapv3/videoPlay/"+$("#CHANNEL_NO").val() +"/"+i;
        else {
            var e = '<div id="paybox"class="ui-dialog"><div class="ui-dialog-cnt"><a class="ui-icon-close-page"data-role="button"></a><div class="info"><h4>试看结束,充值VIP观看更多高清爽片</h4><p class="ui-txt-red">（只需支付一次，享受所有影片观看权限！）</p><p>VIP注意事项：<br>1.未满18岁用户，禁止购买观看。<br>2.一次性支付，享有所有影片观看权限。<br><div class="payBtn"><a class="paybtn weixin"data-role="button">确定</a></div></div></div></div>';
            $("body").append(e);
            var o = $("#paybox").dialog("show");
            o.on("dialog:action",
            function(i) {
            	 var CHANNEL_NO =$("#CHANNEL_NO").val();
                1 == i.index && (window.location.href = "wapv3/checkPay?CHANNEL_NO="+CHANNEL_NO)
            })
        }
        return ! 1
    }), 1 > ispay) {
        var novipfooter = '	<div id="novipfooter" style="text-align: center; font-size: 16px; border: 1px solid #12b7f5; width: 80%; margin: auto; height: 30px; line-height: 30px; border-radius: 30px; font-weight: 200; color: #12b7f5;" class="topay paytip" >点击成为VIP查看更多</div>';
        $(function() {
            $("body").append(novipfooter),
            $("#novipfooter").on("click",
            function() {
            	  var CHANNEL_NO =$("#CHANNEL_NO").val();
                window.location.href = "wapv3/checkPay?CHANNEL_NO="+CHANNEL_NO
            })
        })
    }
}
if (/play/i.test(location.pathname)) if ($("#playerwrap").css({
	width: "100%"
}), 1 > ispay) {
	 var CHANNEL_NO =$("#CHANNEL_NO").val();
   // $("#playTip").html('非会员只能试看20秒，<a href="wapv3/checkPay?CHANNEL_NO='+CHANNEL_NO+'">成为会员</a>观看全部');
   // $("#playTip2").html('<a href="wapv3/checkPay?CHANNEL_NO='+CHANNEL_NO+'"><i class="ui-icon-star"></i>立即成为会员获得更多福利<i class="ui-icon-arrow"></i></a>');
    var trymp4 = decodeURI(getCookie("trymp4")).split("|"),
    idx = Math.floor(Math.random() * trymp4.length);
    var videoId = $("#videoId").val();
    var url = "wapv3/getPlayInfo/" +videoId +"/1";
    var trymp4times=getCookie("trymp4times");
    if (trymp4times ==null){
    	trymp4times = 0;
    }
    trymp4times=parseInt(trymp4times);
    if (trymp4[0]) {
        $.ajax({
        	type : "post",
        	url : url,
        	data :{trymp4times:trymp4times},
        	async : false,
        	success : function(data){
        		  $("#playerwrap").html(data);
        	}
        	});
        trymp4times =trymp4times+1;
        player = document.getElementById("player"),
        player.onplay = function() {
            1 > ispay && setTimeout(gopay, 2e4)
        }
    } else gopay();
    setCookie("trymp4times", trymp4times, "d999");
    trymp4.splice(idx, 1),
    _trymp4 = trymp4.join("|"),
    setCookie("trymp4", _trymp4, "d999")
   
} else if (ispay > 0) {
    $("#playTip").html("您正在观看会员专享视频，请耐心等待缓冲"),
    $("#playTip2").hide();
    var videoId = $("#videoId").val();
    var url = "wapv3/getPlayInfo/" +videoId +"/2";
    $.ajax({
    	type : "post",
    	url : url,
    	data :'',
    	async : false,
    	success : function(data){
    		  $("#playerwrap").html(data);
    	}
    	});
}
if (/check/i.test(location.pathname)) {
    var img = ["0714b1.gif", "0714b2.gif"],paylink,
    idx = Math.floor(2 * Math.random()),
    videoimgHeight = $(".videoimg").width() / 1.97;
    debug && console.log(videoimgHeight),
    $(".videoimg").css("height", videoimgHeight).html('<img src="http://0829img.xyzjtj.com/' + img[idx] + '" width="100%">');
    $(".ui-btn-weixin").on("click",
    function() {
        if ("" !== paylink) debug && console.log("got paylink:" + $(this).data("href")),
        paylink = $(this).data("href")+"&uid="+getCookie("uid"),
        jumptowx(paylink);
        else {
            var i = $('<div id="payloading" class="ui-loading-block show"><div class="ui-loading-cnt"><i class="ui-loading-bright"></i><p>若长时间未跳转<br><em onclick="javascript:reuuid();location.reload();">请点此刷新</em></p></div></div>').loading();
            debug && console.log("no paylink"),
            jumptowx(paylink);
        }
    })
}
if (/about/i.test(location.pathname)) {
    var contactus = '<details><summary class="contactus"><h3 style="display:inline-block">联系我们</h3></summary><p>如有任何意见或疑问，请联系客服</p><p><img src="' + $("#wxContractImg").val() + '" width="100%" alt=""></p></details>';
    var payType=getCookie("payType");
    if(payType!=null && payType.length>=3){
    	 contactus = '<details><summary class="contactus"><h3 style="display:inline-block">联系我们</h3></summary><p>如有任何意见或疑问，请联系客服</p><p><img src="' + $("#aliContractImg").val() + '" width="100%" alt=""></p></details>';
     }
    ispay > 0 &&(payType!=null && payType.length>=1) && $(".about").prepend(contactus)
}

/*$(function() {
	var startPosition, endPosition, deltaX, deltaY, moveLength;  
    $("#movite_content").bind('touchstart', function(e){  
        var touch = e.touches[0];  
        startPosition = {  
            x: touch.pageX,  
            y: touch.pageY  
        }  
    }) .bind('touchmove', function(e){  
        var touch = e.touches[0];  
        endPosition = {  
            x: touch.pageX,  
            y: touch.pageY  
        };  
        deltaX = endPosition.x - startPosition.x;  
        deltaY = endPosition.y - startPosition.y;  
        moveLength = Math.sqrt(Math.pow(Math.abs(deltaX), 2) + Math.pow(Math.abs(deltaY), 2));  
    }).bind('touchend', function(e){  
    	  if(Math.abs(deltaY)<Math.abs(deltaX)){
	        if(deltaX < 0) { // 向左划动  
	        	movetab(1);
	        } else if (deltaX > 0) { // 向右划动  
	        	movetab(-1);
	        }  
    	  }
    }); 
});*/
function movetab(step){
	var channelNo =$("#COLUMN_NO").val();
	var curPage =0;
	if(channelNo !=""){
		curPage =parseInt(channelNo)+step;
	} else {
		curPage =step;
	}
	if(curPage<0){
		curPage =0
	} else if(channelNo>=$(".ui-tiled li").length){
		curPage =$(".ui-tiled li").length-1;
	}
	var tabContent = $(".ui-tiled li")[curPage];
	location.href=tabContent.attributes["data-href"].value+"?COLUMN_NO="+curPage;
}
