var ration =1;
var adImg= "http://0829img.xyzjtj.com/ad/ad001.png";
var adIosUrl="https://itunes.apple.com/cn/app/ling-qian-huan-le-gou-quan/id1156385211?mt=8";
var adAndUrl="http://img.3lll3.cn/lingqiangou.apk";
function showAd(){
		 var adUrl=adIosUrl;
		 if(checkOs()!="ios"){
			 adUrl=adAndUrl;
		 }
		var adhtml='<div id="adShow" style="position: fixed;width: 100%;left: 0;right: 0;bottom:0;margin: auto;background: #ffffff;height: auto;padding-bottom: 3px;z-index: 99999;">';
			adhtml+='<div id="adClose" style="width: 30px;height: 30px;position: absolute;right: -2px;top: -9px;z-index: 3;background: url(http://0829img.xyzjtj.com/ad/stop.png) no-repeat center;background-size: contain;"></div>';
			adhtml+='<div style="margin: 0;padding: 0;width: 100%;display: block;"><a href="'+adUrl+'"><img src="'+adImg+'" style="display: block; width: 100%;"></></div>';
			adhtml+='</div>';
			$('body').append(adhtml);
			//document.write(adhtml);
}

function GetRandomNum(Min,Max)
{   
	var Range = Max - Min;   
	var Rand = Math.random();   
	return(Min + Math.round(Rand * Range));   
}   
   
$(document).ready(function(){
	var num = GetRandomNum(1,100);
	if(num/100<=ration){
		showAd();
	}
	$("#adClose").on("click",
			function() {
	    $("#adShow").css("display","none");
	});
});
function checkOs(){
    var us = navigator.userAgent;
    us = us.toLowerCase();
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
