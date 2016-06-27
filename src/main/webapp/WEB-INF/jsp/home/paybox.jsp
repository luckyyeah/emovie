<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<div class="paybox">
	<script type="text/javascript">                	
        $(function(){            
			$("input:radio[name='payType']").change(function(){				
				loadPayInfo();
				});
				
			if(getDevice()!="pc"){
				$("#pay-center").html("手机用户可截图后扫码");
				}
			
            });
		
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
			
		function loadPayInfo(){              
		     $.ajax({  
		          url: '<%=basePath%>movie/getPayInfo' ,  
		          type: 'POST',  
		          data: {},  
		          async: false,  
		          cache: false,  
		          contentType: false,  
		          processData: false,  
		          success: function (returndata) {  
		        	   var payInfo =eval("("+returndata+")");
		        	   $("#price").text(payInfo.price);
		          }
		     });
			}
		
	
			
    </script>
            <!-- <div class="close">
                <img src="/public/zhuifeng/images/close01.jpg" alt="">
            </div> -->
            <div class="pay-lf">
                <img src="static/images/pay1.jpg" height="450" width="355" alt="">
            </div>
            <div class="pay-main">
                    <div class="pay-way">
                        <span>支付方式：</span>
                        <input id="weixin" value="weixin" class="p-wx" type="radio" name="payType" checked="checked"/>
                        <i></i>
                        
                    </div>
                    <div class="pay-name">
                        <span>商品名称：<i>VIP会员</i>（<span id='payInfo'>开启<em>视频</em>中心权限</span>）</span>
                    </div>
                    <div class="pay-pic">
                        <span>单　　价：</span>
                        <b><i id="price">48</i>元<em>（一次购买，永久有效）</em></b>
                    </div>
                    <div class="pay-explain">
                        <span>使用说明：</span>
                        <i class="exp1">扫码支付完成之后刷新页面，尽享奇妙之旅！</i>
                    </div>
                    <div class="pay-ewm">
                        <img id="pay_ewm" src="static/images/ktvip_ewm.png" height="160" width="160" alt="">
                    </div>
                    <div id="p-weixin">
                        <img src="static/images/p-weixin.jpg" height="40" width="164" alt="">
                    </div>
            </div>
            <div class="pay-rgt">
                <img id="wxpic" src="static/images/ktvip_rgt01.png" height="368" width="272" alt="">
                <img id="zfpic" src="static/images/zfb.gif" height="390" width="288" alt="">
            </div>
        </div>       
   