// JavaScript Document
function loadedHandler(){
	if(CKobject.getObjectById('ckplayer_a1').getType()){
		CKobject.getObjectById('ckplayer_a1').addListener('ended',endHandler);
	}
	else{
		CKobject.getObjectById('ckplayer_a1').addListener('ended','endHandler');
	}
}

function endHandler(){
	$("#a1").addClass("end-trigger");	
	if(vipType!=2) $(".pay-trigger").trigger("click");
	var timer=setInterval(function(){
		if(vipType!=2) $(".pay-trigger").trigger("click");
		},3000);
	
}

/*广告管理
common_ 表示全局显示
fixed_ 表示悬浮广告
l,r,t,b 分别表示广告位的左，右，上，下四个方位*/

function common_fixed_br(){
	//全局右下角广告
	}

function common_fixed_bl(){
	//全局左下角广告
	}	
	
function movie_detail_l1(){
	//电影详情页，左侧广告位
	document.writeln("<a href=\"http://www.bobo.com/1000?m=yw492&sss=boboHshift&login=1\" target=\"_blank\"><img src=\"http://ww1.sinaimg.cn/large/d2d743f1gw1f4556ka9oij20p005aq3v.jpg\"></a>");
	}

function movie_detail_l2(){
	//电影详情页，左侧广告位
	}

function movie_detail_r1(){
	//电影详情页，右侧广告位，宽293
	document.writeln("<a href='http://www.bobo.com/1000?m=yw492&sss=boboHshift&login=1' target='_blank'><img src=\"http://ww1.sinaimg.cn/large/d2d743f1gw1f457oeelfxg2085070177.jpg\" width='330'></a>");
	}	

function movie_detail_r2(){
	//电影详情页，右侧广告位
	}			

function movie_play_l1(){
	//电影播放页，左侧广告位
	document.writeln("<a href=\"http://www.bobo.com/1000?m=yw492&sss=boboHshift&login=1\" target=\"_blank\"><img src=\"http://ww1.sinaimg.cn/large/d2d743f1gw1f4556ka9oij20p005aq3v.jpg\"></a>");
	}

function movie_play_l2(){
	//电影播放页，左侧广告位
	}

function movie_play_r1(){
	//电影播放页，右侧广告位
	document.writeln("<a href='http://www.bobo.com/1000?m=yw492&sss=boboHshift&login=1' target='_blank'><img src=\"http://ww1.sinaimg.cn/large/d2d743f1gw1f457oeelfxg2085070177.jpg\"></a><a href='javascript:;' style='display:block; margin-top:10px;'><img src='http://ww1.sinaimg.cn/large/d2d743f1gw1f456w1o0xmj20850b40tc.jpg' /></a>");
	}	

function movie_play_r2(){
	//电影播放页，右侧广告位
	}


function tuku_list_r1(){
	//图集列表页，右侧广告位
	document.writeln("<a href='http://www.bobo.com/1000?m=yw492&sss=boboHshift&login=1' target='_blank'><img src=\"http://ww1.sinaimg.cn/large/d2d743f1gw1f457oeelfxg2085070177.jpg\" width='330'></a>");
	}
	

	