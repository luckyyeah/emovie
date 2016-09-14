window.top !== window.self && (window.top.location = window.location);
function gq(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
    return null
}
function sc(o, t, i) {
    i = i || "d1";
    var a = getsec(i), n = new Date;
    n.setTime(n.getTime() + 1 * a), document.cookie = o + "=" + escape(t) + ";path=/;expires=" + n.toGMTString()
}
function gc(o) {
    var t, i = new RegExp("(^| )" + o + "=([^;]*)(;|$)");
    var v = (t = document.cookie.match(i)) ? t[2] : null;
    if (v)v = unescape(v);
    return v
}
function isv(k) {
    return gc(k) == 1
}
if (gq("f"))sc('ios', 1, "d30");
var browser = {
    versions: function () {
        var o = navigator.userAgent;
        navigator.appVersion;
        return {
            mobile: !!o.match(/AppleWebKit.*Mobile/i) || !!o.match(/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/),
            mac: o.indexOf("Mac") > -1,
            ios: !!o.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
            android: o.indexOf("Android") > -1 || o.indexOf("Linux") > -1,
            weixin: !!o.match(/MicroMessenger/i),
            qq: !!o.match(/QQ/i)
        }
    }(), language: (navigator.browserLanguage || navigator.language).toLowerCase()
};
(function (window) {
    var u = {};
    u.jsonToStr = function (json) {
        if (typeof json === 'object') {
            return JSON && JSON.stringify(json)
        }
    };
    u.strToJson = function (str) {
        if (typeof str === 'string') {
            return JSON && JSON.parse(str)
        }
    };
    u.rand = function (n, m) {
        if (!m)m = n, n = 0;
        var w = m - n;
        Math.random() * w;
        Math.random() * w + n;
        return Math.round(Math.random() * w + n)
    };
    window.$api = u
})(window);
//点击视频播放
function play(o, t) {
    //获取试播次数。
    var trytime = gc("x_a_watch") || 0;
    trytime++;
    sc("x_a_watch", trytime);
    if (sbnum> trytime || isv('vip')) {
        window.location.href = APP+"/Index/detail?title=" + escape(o) + "&mp4=" + t;
    } else {
        pay()
    }
}
//提醒支付
function pay() {
    var o = $("#paybox").dialog("show");
    o.on("dialog:action", function (o) {
        1 == o.index && (window.location.href =  APP+"/Index/buy");
    })
}
//播放界面提示支付
function pay2() {
    var dia = $.dialog({
        title: '\u6e29\u99a8\u63d0\u793a',
        content: '\u975e\u4f1a\u5458\u53ea\u80fd\u89c2\u770b\u0032\u0030\u79d2\u54e6\u007e',
        button: ["\u7b49\u4e0b\u5145", "\u53bb\u5145\u503c"]
    });
    dia.on("dialog:action", function (e) {
        if (e.index == 0) {
            if (stop && isv('an'))Player.play()
        } else {
            window.location.href = APP+"/Index/buy";
        }
    })
}
//提示充值。
function getNo() {
    if (1 == Math.floor(2 * Math.random() + 1)) {
        var o = "x_a_no", t = 3985, i = gc(o);
        i ? (_ckno = parseInt(i) + 1, sc(o, _ckno, "d30")) : sc(o, t, "d30"), i = i ? _ckno : t;
        $("#showno").html(i);
        $(".ui-newstips-wrap").show().addClass("flip-top");
        setTimeout(function () {
            $(".ui-newstips-wrap").hide()
        }, 5e3)
    }
}
function getsec(o) {
    var t = 1 * o.substring(1, o.length), i = o.substring(0, 1);
    return "s" == i ? 1e3 * t : "h" == i ? 60 * t * 60 * 1e3 : "d" == i ? 24 * t * 60 * 60 * 1e3 : void 0
}
if (showjs) {
    var dataurl = APP+"/Index/jsonIndex/i/" + cid;
    var vlist = new Vue({
        el: "body", data: {slider: [], data: []}, methods: {
            play: function (o, t) {
                play(o, t)
            }
        }, ready: function () {
            var o = this;
            $.ajax({
                type: "get",
                async: true,
                url: dataurl,
                dataType: "jsonp",
                timeout: 90000,
                jsonpCallback: 'jsonp',
                success: function (json) {
					var t = json[0];
                    o.picurl = t.picurl || '';
                    o.data = t.data;
                    o.slider = t.slider
                }
            })
        }
    });
    !function () {
        new fz.Scroll(".ui-slider", {role: "slider", indicator: !0, autoplay: !0, interval: 3e3})
    }()
}
function goback() {
    // window.location.href = APP+"/Index/index";
   window.history.back();
}
function load_paylink(_cid) {
    sc('an', 1, "d30")
}
function payopen(topay) {
    if (isv('vip'))return;
    if (topay)window.location.href =  APP+"/Index/buy"; else pay2()
}
try{
    isplay==1;
}catch(e){
    isplay = false;
}
//如果是播放资源
if (isplay) {
    //不是vip，获取试播资源。
    if (!isv('vip')) {
        vl = sb
    }
    var playbox = $("#playbox");
    if (!isv('vip'))$('<div style="text-align:center;margin-top:10px;color:#eee">\u975e\u4f1a\u5458\u53ea\u80fd\u8bd5\u770b\u0032\u0030\u79d2\uff0c<a href="'+ APP+'/Index/buy'+'">\u6210\u4e3a\u4f1a\u5458</a>\u89c2\u770b\u5168\u90e8</div>').appendTo(playbox);
    $('<video autoplay="autoplay" preload="auto" id="player" controls="false" poster="./Public/sp/img/player2.png"><source id="source1" src="' + vl + '" type="video/mp4"></source></video>').appendTo(playbox);
     var doplay = function () {
        var trytime = gc("x_a_watch") || 0;
        if (sbnum > trytime && !_ispay && !isv('vip')) {
            setTimeout(gopay, 18000)
        } else {
            if (!isv('vip'))payout();
            try {
                window.wst.start()
            } catch (e) {
            }
        }
    };
    Player = document.getElementById("player");
    var _ispay = isv('vip');
    var mainheight = $(window).height() - 120;
    var mainwidth = $(window).width();
    $('#player').height(mainheight);
    $('#player').width(mainwidth);
    $('.container').on("click", function () {
        if (!_ispay) {
            gopay(true)
        }
    });
    if (!isv('vip'))$('#player').on("click", function () {
        try {
            document.getElementById("player").play()
        } catch (e) {
        }
        if (!_ispay) {
            gopay(true)
        }
    });
    Player.onplay = function () {
        doplay()
    };
    function payout() {
        Player.remove();
        var dia = $.dialog({
            title: '\u6e29\u99a8\u63d0\u793a',
            content: '\u60a8\u7684\u8bd5\u770b\u673a\u4f1a\u7528\u5b8c\u4e86\u007e',
            button: ["\u7b49\u4e0b\u5145", "\u53bb\u5145\u503c"]
        });
        dia.on("dialog:action", function (e) {
            if (e.index == 0) {
                window.location.href = 'javascript:history.back(-1)'
            } else {
                window.location.href =  APP+"/Index/buy";
            }
        })
    }

    function gopay(stop) {
        if (isv('an'))Player.pause(); else Player.remove();
        var dia = $.dialog({
            title: '\u6e29\u99a8\u63d0\u793a',
            content: '\u975e\u4f1a\u5458\u53ea\u80fd\u89c2\u770b\u0032\u0030\u79d2\u54e6\u007e',
            button: ["\u7b49\u4e0b\u5145", "\u53bb\u5145\u503c"]
        });
        dia.on("dialog:action", function (e) {
            if (e.index == 0) {
                if (stop && isv('an'))Player.play(); else window.location.href = 'javascript:history.back(-1)'
            } else {
                window.location.href =  APP+"/Index/buy";
            }
        })
    }

    $('#qqvideo-overlay-0,#qqvideobridge').remove()
}
function create_pay(i) {
    var el =$.loading({content:'正在提交订单...'});
    $.ajax({
        url:APP+'/Index/create_pay/type/'+i,
        dataType: "json",
        beforeSend:function (xhr, settings) {

        },
        complete:function (xhr, status) {
            el.hide();
            if(status!=200) {

            }
        },
        success:function (data) {
            if(data.status>0){
				var cont = JSON.parse(data.data);
				//alert(cont.paytype);
				if(cont.paytype==1){
					document.write(cont.data.pay);
				}else
					window.location.href= cont.data;
                var dia = $.dialog({
                    title: '提示',
                    content: '请在微信客户端完成支付！',
                    button: ["完成", "去支付"]
                });
                dia.on("dialog:action", function (e) {
                    if (e.index == 0) {
                        window.location.reload();
                    } else {
                        window.location.href = cont.data;
                    }
                })				
            }
            else{
                var dia = $.dialog({
                    title: '错误',
                    content: '订单创建失败！请重试！',
                    button: ["确定"]
                });
            }
        }

    });
}
// function callbackGame(data){
	// if(data.OrderStatus == 0){
		// $.ajax({
             // type: "GET",
             // url: APP+"/Index/save_buy/orderid/"+data.TransId,
             // dataType: "html",
             // success: function(data){
                 // if(data != 0){
					// alert(data);
					// location.href=APP+"/Index/about";
				 // }
            // }
         // });
	// }
// }
Zepto(function ($) {
    $('#qqvideo-overlay-0,#qqvideobridge').remove();
    if (browser.versions.weixin) {
        $('.inapptips').show();
    }
    setInterval(getNo, 10000);
    $(".ui-list li,.ui-tiled li").click(function () {
        $(this).data("href") && (location.href = $(this).data("href"))
    });
    $("header .ui-icon-return").click(function () {
        goback()
    });
    $('.toplay').on('click', function () {
		//获取试播次数。
		var trytime = gc("x_a_watch") || 0;
		trytime++;
		sc("x_a_watch", trytime);
                if(isv('vip')){
			window.location.href=$(this).attr('url');
			return false;
		}
		if (sbnum< trytime) {
			pay();
			return false;
		} else {
			window.location.href=$(this).attr('url');
			return false;
		}	
		
    });
    if (isv('vip')) {
        $('.paytip').html('\u60a8\u5df2\u7ecf\u662f\u0056\u0049\u0050\uff0c\u8bf7\u6b23\u8d4f\uff01')
    } else {
        $('.topay').on('click', function () {
            pay();
        })
    }
    $('#pay-weixin').on('click',function () {
        create_pay();
    })
    var ca = $('.ath_close_area')
    ca.on('click', function (e) {
        $(".u-layer-ath").hide();
    })
    if (isv('vip')) {
        $('.u-layer-ath').hide();
    }
    else {
        $('.u-layer-ath').show();
        try{
            if(isplay) {
                $('.u-layer-ath').hide();
            }
        }
        catch(e){
        }

    }
});


