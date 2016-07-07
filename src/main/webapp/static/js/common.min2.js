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
    i.on("dialog:action",
    function(i) {
        0 === i.index && (window.location.href = "index.html")
    })
}
function gopay() {
    "undefined" != typeof player && player.remove();
    var i = $.dialog({
        title: "温馨提示",
        content: "非会员只能试看20秒视频哦~",
        button: ["待会充", "去充值"]
    });
    i.on("dialog:action",
    function(i) {
        0 === i.index ? window.location.href = "./index.html": window.location.href = "./check.html"
    })
}
function getNo() {
    var i = "x_a_no",
    e = 1285,
    o = getCookie(i);
    o ? (_ckno = parseInt(o) + 1, setCookie(i, _ckno, "d30")) : setCookie(i, e, "d30"),
    o = o ? _ckno: e,
    $("#showno").html(o);
    var t = $(".ui-newstips-wrap");
    t.show().addClass("flip-top"),
    setTimeout(function() {
        t.hide()
    },
    5e3)
}
function getQuery(i) {
    var e = new RegExp("(^|&)" + i + "=([^&]*)(&|$)", "i"),
    o = window.location.search.substr(1).match(e);
    return null !== o ? unescape(o[2]) : null
}
function getProid() {
    var i = location.pathname;
    return arr = i.split("/"),
    arr[1]
}
function setCookie(i, e, o) {
    var t = getsec(o),
    a = new Date;
    a.setTime(a.getTime() + 1 * t),
    document.cookie = i + "=" + escape(e) + ";expires=" + a.toGMTString()
}
function getCookie(i) {
    var e, o = new RegExp("(^| )" + i + "=([^;]*)(;|$)");
    return e = document.cookie.match(o),
    e ? e[2] : null
}
function getsec(i) {
    var e = 1 * i.substring(1, i.length),
    o = i.substring(0, 1);
    return "s" == o ? 1e3 * e: "h" == o ? 60 * e * 60 * 1e3: "d" == o ? 24 * e * 60 * 60 * 1e3: void 0
}
function uuid(i, e) {
    var o, t = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split(""),
    a = [];
    if (e = e || t.length, i) for (o = 0; i > o; o++) a[o] = t[0 | Math.random() * e];
    else {
        var n;
        for (a[8] = a[13] = a[18] = a[23] = "-", a[14] = "4", o = 0; 36 > o; o++) a[o] || (n = 0 | 16 * Math.random(), a[o] = t[19 == o ? 3 & n | 8 : n])
    }
    return a.join("")
}
function insertPlayer(i, e) {
    arguments[1] || (e = "player"),
    e = resourceDomain + "m/" + e + ".jpg";
    var o = '<video autoplay="autoplay" preload="auto" id="player" controls="false" poster="' + e + '" style="width:100%;height:100%;"><source src="' + i + '" type="video/mp4"></source></video>';
    $("#playerwrap").html(o)
}
function checkPay(i, e, o, t, a) {
    return debug && console.log("checkPay:" + i + "," + e + "," + o + "," + a),
    $.ajax({
        type: "get",
        url: i,
        async: !0,
        data: {
            apiurl: i,
            reqtype: "ispay",
            uid: e,
            proid: o,
            site: t,
            source: a
        },
        dataType: "jsonp",
        timeout: 9e4,
        success: function(i) {
            return debug && console.log("check ispay status:" + i.status),
            1e4 == i.status && (setCookie("ispay", 1, "d7"), vippop()),
            !0
        }
    }),
    !1
}
function requestPay(i, e, o, t, a, n, r) {
    debug && console.log("request paylink:" + i + "," + e + "," + o + "," + t + "," + a + "," + n),
    $.ajax({
        type: "get",
        url: i,
        async: !0,
        data: {
            apiurl: i,
            reqtype: "pay",
            paytype: e,
            proid: t,
            uid: o,
            site: a,
            source: n
        },
        dataType: "jsonp",
        timeout: 9e4,
        success: function(s) {
            1e4 == s.status ? (debug && console.log("get paylink status:" + s.status), paylink = s.result, setCookie("paylink", paylink, "s300"), $(".ui-btn-weixin").attr({
                href: paylink,
                target: "_blank"
            }), r && r.loading("hide")) : 3 > reqcount && (debug && console.log("get paylink status:" + s.status), i = i == apiurl1 ? apiurl2: apiurl1, requestPay(i, e, o, t, a, n), reqcount++)
        }
    })
}
function jumptowx(i) { - 1 == ispay && (ispay = 0),
    setCookie("ispay", ispay, "d7");
    var e = $.dialog({
        content: "支付成功后请点击完成按钮",
        diagid: "isok",
        button: ["取消", "完成"]
    });
    e.on("dialog:action",
    function(i) {
        1 == i.index ? window.location.href = "./index.html": window.location.href = "./check.html"
    })
}
var ispay = -1,
proid = 0,
resourceDomain = "//o9aokydtz.qnssl.com/",
uid = "",
apiurl = "https://api.pfb2.com/api/",
apiurl1 = "https://api.pfb2.com/api/",
apiurl2 = "https://api.pfb2.com/api/",
site = 2,
paytype = "sp",
source = "aw",
debug = !1,
inBrowser = "undefined" != typeof window && "[object Object]" !== Object.prototype.toString.call(window),
UA = inBrowser && window.navigator.userAgent.toLowerCase(),
isMQQBrowser = UA && UA.indexOf("mqqbrowser/") > 0,
_uid = getCookie("uid");
null !== _uid ? (uid = _uid, debug && console.log("get uid:" + uid)) : (_uid = uuid(16, 32), setCookie("uid", _uid, "d30"), debug && console.log("set uid cookie:" + _uid), uid = _uid);
var _proid = getProid();
null !== _proid ? (proid = _proid, setCookie("proid", _proid, "d30")) : getCookie("proid") && (proid = getCookie("proid"));
var _ispay = getCookie("ispay");
null !== _ispay && (ispay = parseInt(_ispay), 0 === ispay && checkPay(apiurl, uid, proid, site, source)),
debug && console.log("get ispay:" + ispay);
var iftry = getCookie("trymp4");
iftry || "" === iftry || setCookie("trymp4", "try1|try2", "d999"),
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
    window.location.href = "./index.html"
}), !/(check|about|play)/i.test(location.pathname)) {
    var slider = new fz.Scroll(".ui-slider", {
        role: "slider",
        indicator: !0,
        autoplay: !0,
        interval: 3e3
    });
    if ($("#slider li, #vlist li").on("click",
    function() {
        var i = resourceDomain + "mp4/" + $(this).data("vid") + ".mp4",
        e = $(this).children("h4").children("a").html();
        if ("" !== iftry || ispay > 0) window.location.href = "./play.html?title=" + e + "&mp4=" + i;
        else {
            var o = '<div id="paybox"class="ui-dialog"><div class="ui-dialog-cnt"><a class="ui-icon-close-page"data-role="button"></a><div class="info"><h4>试看结束,充值VIP观看更多高清爽片</h4><p class="ui-txt-red">（只需支付一次，享受所有影片观看权限！）</p><p>VIP注意事项：<br>1.未满18岁用户，禁止购买观看。<br>2.一次性支付，享有所有影片观看权限。<br><div class="payBtn"><a class="paybtn weixin"data-role="button">确定</a></div></div></div></div>';
            $("body").append(o);
            var t = $("#paybox").dialog("show");
            t.on("dialog:action",
            function(i) {
                1 == i.index && (window.location.href = "./check.html")
            })
        }
        return ! 1
    }), 1 > ispay) {
        var novipfooter = '<div id="novipfooter" style="text-align:center; font-size:16px;">更多精华资源，仅限会员专享。。。</div>';
        $(function() {
            $("body").append(novipfooter),
            $("#novipfooter").on("click",
            function() {
                window.location.href = "./check.html"
            })
        })
    }
}
if (/play/i.test(location.pathname)) if ($("#playerwrap").css({
    width: $(window).width() + "px",
    height: $(window).height() - 300 + "px"
}), 1 > ispay) {
    $("#playTip").html('非会员只能试看20秒，<a href="./check.html">成为会员</a>观看全部'),
    $("#playTip2").html('<a href="./check.html"><i class="ui-icon-star"></i>立即成为会员获得更多福利<i class="ui-icon-arrow"></i></a>');
    var trymp4 = decodeURI(getCookie("trymp4")).split("|"),
    idx = Math.floor(Math.random() * trymp4.length);
    if (trymp4[0]) {
        var _mp4 = resourceDomain + "mp4/" + trymp4[idx] + ".mp4";
        debug && console.log(_mp4),
        insertPlayer(_mp4, trymp4[idx]),
        player = document.getElementById("player"),
        player.onplay = function() {
            1 > ispay && setTimeout(gopay, 2e4)
        }
    } else gopay();
    trymp4.splice(idx, 1),
    _trymp4 = trymp4.join("|"),
    setCookie("trymp4", _trymp4, "d999")
} else if (ispay > 0) {
    $("#playTip").html("您正在观看会员专享视频，请耐心等待缓冲"),
    $("#playTip2").hide();
    var vipmp4 = getQuery("mp4");
    debug && console.log("get vip mp4:" + vipmp4),
    insertPlayer(vipmp4)
}
if (/check/i.test(location.pathname)) {
    var img = ["b1.gif", "b2.gif"],
    idx = Math.floor(2 * Math.random()),
    videoimgHeight = $(".videoimg").width() / 1.97;
    debug && console.log(videoimgHeight),
    $(".videoimg").css("height", videoimgHeight).html('<img src="' + resourceDomain + "m/" + img[idx] + '" width="100%">'),
    debug && console.log("get paylink:" + getCookie("paylink"));
    var paylink = getCookie("paylink") ? unescape(getCookie("paylink")) : "";
    if ("" !== paylink) $(".ui-btn-weixin").attr({
        href: paylink,
        target: "_blank"
    });
    else {
        debug && console.log("paylink=null");
        var loading = $('<div id="payloading" class="ui-loading-block show"><div class="ui-loading-cnt"><i class="ui-loading-bright"></i><p>若长时间未跳转<br><em onclick="javascript:location.reload();">请点此刷新</em></p></div></div>').loading();
        requestPay(apiurl, paytype, uid, proid, site, source, loading)
    }
    $(".ui-btn-weixin").on("click",
    function() {
        jumptowx(paylink),
        "undefined" != typeof ga && ga("send", "event", "check", "wxpay", paylink ? "1": "0")
    })
}
if (/about/i.test(location.pathname)) {
    var contactus = '<details><summary class="contactus"><h3 style="display:inline-block">联系我们</h3></summary><p>如有任何意见或疑问，请联系客服</p><p><img src="' + resourceDomain + '/m/contact.jpg" height="78" width="250" alt=""></p></details>';
    ispay > 0 && $(".about").prepend(contactus)
}
var reqcount = 0;