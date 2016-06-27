// JavaScript Document
if(!isLogin){
	alert("未登录");
	window.location.href="/";
	}

$(function(){
	$("#score").html(score);
	$("span.username").html(userName);
	if(isVip){
		$(".vip-icon").addClass("on");
		if(vipType==2){
			$(".year-icon").addClass("on");
			$(".m-name .user-tip").html("您已开通包年VIP会员，到期时间："+lifeTime+"。<br/>您可以随意浏览或下载本站所有资源。");
			$(".m-name #ktvip01 a").html("续费");
			$(".m-name #ktvip01").data("target","1");
			$("#pay-center").html("<i>VIP</i>续费充值");	
			$("#vipTypeImg").attr("src","static/images/vip02.jpg");		
			}
		else{
			$(".m-name .user-tip").html("您已开通普通VIP会员，您可以使用账号积分购买本站资源");
			$(".m-name #ktvip01 a").html("充值");
			$(".m-name #ktvip02").show();
			$("#pay-center").html("<i>VIP</i>续费充值");
			$("#vipTypeImg").attr("src","static/images/vip01.jpg");					
			
			}	
		}
	else{
		$("#vipTypeImg").attr("src","static/images/headpic.fw.png");					
		}
	});

function loadPorfile(){
	$("#name").val(userName);
	$("#phone").val(phone);
	}	

function saveFrofile(){
	var formData=$("#profileForm").serialize();
	$.getScript("http://user.zpiyi.com/index/profile_save/t/"+formData,function(){
		gshow("#profile_save_result",profile_e);
		});
	}

function submitIdea(){
	var formData=$("#ideaForm").serialize();
	$.getScript("http://user.zpiyi.com/index/ts/t/"+formData,function(){
		gshow("#idea_result",idea_e);
		});
	}

function loadOrders(){
	$.getScript("http://user.zpiyi.com/index/orders",function(){
		$("#order-list").html(order_html);
		});
	}

function loadResource(){
	$.getScript("http://user.zpiyi.com/index/resource",function(){
		$("#resource-list").html(resource_html);
		});
	}

function gshow(target,text){
	$(target).html(text).show();
	//return false;
	setTimeout(function(){
		$(target).fadeOut('fast','',function(){
			$(target).html('');
			});
		},3000);
	}		
