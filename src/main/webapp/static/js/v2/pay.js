var uid, _uid;
// JavaScript Document
function setCookie(name, value, day) {
    var exp = new Date();
    exp.setTime(exp.getTime() + day * 24 * 60 * 60 * 1000);
    document.cookie = name + "= " + escape(value) + ";expires= " + exp.toGMTString() + ';path=/;';
}
function getCookie(objName) {
    var arrStr = document.cookie.split("; ");
    for (var i = 0; i < arrStr.length; i++) {
        var temp = arrStr[i].split("=");
        if (temp[0] == objName)
            return unescape(temp[1])
    }
}

function getQueryString(name, d) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return d;
}

function getDevice() {
    var sUserAgent = navigator.userAgent.toLowerCase();
    var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
    var bIsIphoneOs = sUserAgent.match(/iphone/i) == "iphone";
    var bIsWeixin = sUserAgent.match(/micromessenger/i) == "micromessenger";
    var bIsMidp = sUserAgent.match(/midp/i) == "midp";
    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
    var bIsAndroid = sUserAgent.match(/android/i) == "android";
    var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
    if (bIsWeixin) {
        return "weixin";
    } else if (bIsAndroid) {
        return "android";
    } else if (bIsIpad || bIsIphoneOs) {
        return "ios";
    } else
        return "pc"
}

function isSafari(){
    var sUserAgent = navigator.userAgent.toLowerCase();
    return sUserAgent.match(/safari/i) == "safari";   
}


function getUrl(m) {
    url = "javascript:pay();"
    return url;
}

function getId() {
    var _sid = getQueryString("sid"), _aid = getQueryString("aid"), _noapp = getQueryString("noapp");
    if (isNaN(_sid) || typeof (_sid) == 'undefined' || _sid == 'undefined' || _sid == null) {
        _sid = getCookie("sid");
        if (isNaN(_sid) || typeof (_sid) == 'undefined' || _sid == 'undefined' || _sid == null) {
            _sid = 1;
        }
    } else {
        setCookie("sid", _sid, 30);
    }
    if (isNaN(_aid) || typeof (_aid) == 'undefined' || _aid == 'undefined' || _aid == null) {
        _aid = getCookie("aid");
        if (isNaN(_aid) || typeof (_aid) == 'undefined' || _aid == 'undefined' || _aid == null) {
            _aid = 1; 
        }
    } else {
        setCookie("aid", _aid, 30);
    }
    aid = _aid;
    sid = _sid;
    if (_noapp == '1') {
        setCookie('noapp', 1, 30);
    }
}

function pay() {    
    var parentClass='layermmain';
    if ($("."+parentClass+" .popup").hasClass("active") || vipType >= resourceType)
        return false;   
    popPayDiv();
 
    if (resourceType > 1)
        $(".silver-right").hide();
    else
        $(".silver-right").show();
    var docH = $(document).height();     
    $("."+parentClass+" .popup").addClass("active");       
    var checked = $("."+parentClass+" input[name='vipType']:checked").val();    
    //弹出支付时，强制选择与当前资源对应的选项
    if (typeof(checked)=='undefined'||checked != (resourceType - 1)) {        
        $("."+parentClass+" input[name='vipType']:checked").removeAttr("checked");
        $("."+parentClass+" input[name='vipType']")[resourceType-1].checked = true;        
    }
    loadWeiXinLink();
/*    if (!checkTimer)
        checkTimer = setInterval(function () {			
        	checkPay();
        }, 3000);*/
}

function alipay_submit(payType){
    var parentClass='layermmain';
	  uid = getCookie('uid');
	  if(uid==null){
		  reuuid();
	  }
    var vipType = $("."+parentClass+" input[name='vipType']:checked").val();
    var url="";
    if(payType=='101'){
    	url="alipay/getAliPayLink?vipType="+vipType +"&channelNo=" + $("#CHANNEL_NO").val() + '&uid=' + uid + "&format=js"+"&plugin_type=" +payType+"&payType=2&version=2";
    }
    if(payType=='102'){
    	url="bbpay/getBBPayLink?vipType="+vipType +"&channelNo=" + $("#CHANNEL_NO").val() + '&uid=' + uid + "&format=js"+"&plugin_type=" +payType+"&payType=2&version=2";
    }
    location.href=url;
}

function show_wx() {
    var parentClass='layermmain';
    var device = getDevice();
    var vipType = $("."+parentClass+" input[name='vipType']:checked").val();
	  uid = getCookie('uid');
	  if(uid==null){
		  reuuid();
	  }
	  var payType = $(".weixin").attr('data-pay-type');
	  var out_trade_no =getCookie("out_trade_no")
		var url = 'wapv2/saveOrder?channelNo=' + $("#CHANNEL_NO").val() + '&out_trade_no=' + out_trade_no + '&format=js&vipType=' + vipType+"&plugin_type=" +payType;;
		$.get(url,function(data){
					location.href = $(".weixin").attr('data-pay');
	    });
		 

}
function close_wx() {
   $(".and").hide();
}

function submitIdea() {
    var formData = $("#ideaForm").serialize();    
   	var url = "wapv2/idea?" + formData;
    $.get(url,function(idea_e){
        gshow("#idea_result", "非常感谢您的意见反馈，我们将会及时处理！");
    });
}

function gshow(target, text) {    
    $(target).html(text).show();
    //return false;
    setTimeout(function () {
        $(target).fadeOut('fast', '', function () {
            $(target).html('');
        });
    }, 13000);
}
function loadWeiXinLink() {
	  uid = getCookie('uid');
	  if(uid==null){
		  reuuid();
	  }
    var device = getDevice();
    var parentClass='layermmain';
    var vipType = $("."+parentClass+" input[name='vipType']:checked").val();   
    var payType = $(".weixin").attr('data-pay-type');
	var url = '';
	if(payType==3){
		url='thirdpay2/getWxPayLink?&channelNo=' + $("#CHANNEL_NO").val() + '&uid=' + uid + '&format=js&vipType=' + vipType +"&plugin_type=" +payType+"&payType=1&version=2";
	}
	if(payType==4){
	url='ylpay/getWxPayLink?&channelNo=' + $("#CHANNEL_NO").val() + '&uid=' + uid + '&format=js&vipType=' + vipType+"&plugin_type=" +payType+"&payType=1&version=2";
	}
	if(payType==5){
		url="bbpay/getBBPayLink?vipType="+vipType +"&channelNo=" + $("#CHANNEL_NO").val() + '&uid=' + uid + "&format=js"+"&plugin_type=" +payType+"&payType=1&version=2";
		}
	$.get(url,function(data){
			var pay_request_return = eval('(' + data + ')');
        setCookie("out_trade_no",pay_request_return.out_trade_no,30);
        $(".weixin").attr('data-pay', pay_request_return.info);
    });

}

$(function () {
	
		if(getCookie('uid')==null){
			reuuid();
		}
		if(getCookie('openVIP')==null){
			setCookie("openVIP", 0, 30);
		}
    $("body").delegate("input[name='vipType']","change",function(){
        loadWeiXinLink();
    });   
    
    $("body").delegate(".laymshade","click",function(e){
        $(e.currentTarget).parent().remove();
    });        
});
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
		var out_trade_no = getCookie("out_trade_no");
		if(out_trade_no==null){
			return;
		}
    $.ajax({
    	type : "post",
    	url : "wapv2/checkPayed",
    	data: {out_trade_no:out_trade_no}, 
    	async : false,
    	success : function(data){
    		if(data){
    			setCookie("openVIP", data, 30);

    		} 
    	}
    	});
    return ;
}
