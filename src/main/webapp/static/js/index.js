function setCookie(name, value, day) { 
    var exp = new Date();
    exp.setTime(exp.getTime() + day * 24 * 60 * 60 * 1000);
    document.cookie = name + "= " + escape(value) + ";expires= " + exp.toGMTString()+';path=/;';
}


function getCookie(objName) {
    var arrStr = document.cookie.split("; ");
    for (var i = 0; i < arrStr.length; i++) {
        var temp = arrStr[i].split("=");
        if (temp[0] == objName) return unescape(temp[1])
    }
}

function getQueryString(name,d) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); 
	return d;
}

function getDevice(){
	var sUserAgent= navigator.userAgent.toLowerCase(); 
	var bIsIpad= sUserAgent.match(/ipad/i) == "ipad"; 
	var bIsIphoneOs= sUserAgent.match(/iphone/i) == "iphone";
	var bIsMidp= sUserAgent.match(/midp/i) == "midp";
	var bIsUc7= sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
	var bIsUc= sUserAgent.match(/ucweb/i) == "ucweb";
	var bIsAndroid= sUserAgent.match(/android/i) == "android";
	var bIsCE= sUserAgent.match(/windows ce/i) == "windows ce";
	var bIsWM= sUserAgent.match(/windows mobile/i) == "windows mobile";
	if(bIsAndroid){
		 return "android";
		}
	else if(bIsIpad||bIsIphoneOs){
		 return "ios";
		}
	else return "pc"	
	}

function getId(){
	var _sid=getQueryString("sid"),_aid=getQueryString("aid");		
	if(isNaN(_sid)||typeof(_sid)=='undefined'||_sid=='undefined'||_sid==null){
		_sid=getCookie("sid");	
		if(isNaN(_sid)||typeof(_sid)=='undefined'||_sid=='undefined'||_sid==null){
			_sid=1;
			}	
		}
	else{
		setCookie("sid",_sid,30);
		}	
	if(isNaN(_aid)||typeof(_aid)=='undefined'||_aid=='undefined'||_aid==null){
		_aid=getCookie("aid");	
		if(isNaN(_aid)||typeof(_aid)=='undefined'||_aid=='undefined'||_aid==null){
			_aid=1;
			}
		}
	else{
		setCookie("aid",_aid,30);
		}	
	aid=_aid;
	sid=_sid;	
	}


$(function(){ 
	try{
		vipType = parseInt($("#vipType").val());
		$("#fromUrl").val(window.location.href);
	}catch(e){
		
	}
	getId();
	var device=getDevice();
	if(device!="pc"&&vipType==0) location.href="/wap";
	$('#dl').click(function(){
		$('.gray,.loginbox').addClass("gray-show").show();       
	 });
	
// 支付窗口显示 
 $('.pay-trigger').click(function(){
	 showPay();
 });
//支付窗口显示 
 $('#ktvip').click(function(){
	 showPay();
 });
 function showPay(){
		var payType=parseInt($("#vipType").val());
		if($(".paybox").hasClass("gray-show")||vipType+payType>=2) return false;
		if(!checkTimer) checkTimer=setInterval(function(){
			$.post("movie/checkPayed",{},function(data){
				console.log(data);
				if(data!=0){			
					location.reload();
					}
				});
			},3000);  
			    
		loadPayInfo();
		$('.gray,.paybox').addClass("gray-show").show();	
 }
//支付窗口显示 
 $('#dl').click(function(){
	 $('.gray,.loginbox').addClass("gray-show").show();	
 });
$('.gray,.close').click(function(){
    $(".gray-show").removeClass("gray-show").hide();    
});


// 微信与支付宝切换
  $('#weixin').click(function(){
  	$('#p-zhifubao,#zfpic,.exp2').css('display','none');
  	$('#p-weixin,#wxpic,.exp1').css('display','block');
  });
  $('#zhifubao').click(function(){
  	$('#p-weixin,#wxpic,.exp1').css('display','none');
  	$('#p-zhifubao,#zfpic,.exp2').css('display','block');
  });



  // banner部分轮播图
  $('#list').append($('#list li:first').clone(true));

      var timer=null;
      var oIndex=0
      var uIndex=0;
      // -------------定时器模块--------
      timer=setInterval(move, 3000);
      function move(){
        oIndex++;
        uIndex++;
        if(oIndex>4){
          oIndex=0;
        }
        if(uIndex>5){
          uIndex=1;
          $('#list').css('left',0);
        }
        $('#list').stop().animate({'left':uIndex*(-1180)},400);
        $('#turn li').eq(oIndex).addClass('current').siblings().removeClass('current');
      }

      // ------------控制定时器---------
      $('.banner-big,.banner-small').hover(function(){
        clearInterval(timer);
      },function(){
        clearInterval(timer);
        timer=setInterval(move, 3000);
      });

      // ------------角标控制------------
      $('#turn li').click(function(){
        $(this).addClass('current').siblings().removeClass('current');
        $('#list').stop().animate({'left':$(this).index()*(-1180)},500);
        oIndex=$(this).index();
        uIndex=$(this).index();
      });

 // 右侧导航----------------
  $(window).scroll(function(){
      var st=$(window).scrollTop();
      var h=$(window).height();
      if(st>h){
        $('.right-nav').show();
      }else{
        $('.right-nav').hide();
      }
    });
    $('.right-nav .r-nav li').click(function(){
      var num=$(this).index()*330+660;
      if(num>2400){
        num=0;
      };
        $('html,body').animate({'scrollTop':num});
    });




    // 图片交互效果
    $('.pict li a,.video li a').hover(function() {
      $(this).addClass('bf');
    }, function() {
      $(this).removeClass('bf');
    });


});













