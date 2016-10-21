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
	<div id="downloadbox" class="ui-dialog">
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
	<script>
	var param	= "";
    var ispay	= false;
    var showjs	= "1";
    var cid		= 1;
    var pop		= false;
	var APP		= "";
	var sbnum	= "6";
</script>

	<script src="http://lg08.eeb24.com/wap/static/js/v3/zepto.min.js"></script>
	<script src="http://lg08.eeb24.com/wap/static/js/v3/frozen.js"></script>
	<script src="http://lg08.eeb24.com/wap/static/js/v3/vue.min.js"></script>
	<script src="http://lg08.eeb24.com/wap/static/js/v3/commonv5.js"></script>

<script type="text/javascript">
function goToPay(){
	window.location.href ="<%=basePath%>wapv3/checkPay?CHANNEL_NO=${pd.CHANNEL_NO}";
}
</script>



