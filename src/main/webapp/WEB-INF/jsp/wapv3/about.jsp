<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
		<%@ include file="./common.jsp"%> 
</head>
<body>
		<%@ include file="./column.jsp"%> 
		<a class="ui-btn-lg ui-btn-weixin" href="wapv3/login/${pd.CHANNEL_NO}" >自助激活</a>
			<h1 style="text-align: center;"><b>会员类型:&nbsp;<em class="ui-txt-red" id="vipType">游客</em></b></h1>	
	<section class="ui-panel" id="movite_content">
		<div class="about">
	
			<h3>收藏指南</h3>
			<img alt="" src="http://0829img.xyzjtj.com/tuku/shoucang.jpg" style="width:100%;" />
			<h3>免责声明</h3>
			<p>1、一切移动客户端用户在下载并浏览本站时均被视为已经仔细阅读本条款并完全同意。凡以任何方式登陆本站，或直接、间接使用本站资料者，均被视为自愿接受本网站相关声明和用户服务协议的约束。</p>
			<p>2、转载的内容并不代表之意见及观点，也不意味着本站赞同其观点或证实其内容的真实性。</p>
			<p>3、转载的文字、图片、音视频等资料均由本站用户提供，其真实性、准确性和合法性由信息发布人负责。不提供任何保证，并不承担任何法律责任。</p>
			<p>4、所转载的文字、图片、音视频等资料，如果侵犯了第三方的知识产权或其他权利，责任由作者或转载者本人承担，本站对此不承担责任。</p>
			<details>
				<summary style="text-align: right; color: #225599; font-size: 12px">显示更多</summary>
				<p>5、不保证为向用户提供便利而设置的外部链接的准确性和完整性，同时，对于该外部链接指向的不由实际控制的任何网页上的内容，不承担任何责任。</p>
				<p>6、用户明确并同意其使用网络服务所存在的风险将完全由其本人承担；因其使用网络服务而产生的一切后果也由其本人承担，对此不承担任何责任。</p>
				<p>7、除注明之服务条款外，其它因不当使用本站而导致的任何意外、疏忽、合约毁坏、诽谤、版权或其他知识产权侵犯及其所造成的任何损失，概不负责，亦不承担任何法律责任。</p>
				<p>8、对于因不可抗力或因黑客攻击、通讯线路中断等不能控制的原因造成的网络服务中断或其他缺陷，导致用户不能正常使用，不承担任何责任，但将尽力减少因此给用户造成的损失或影响。</p>
				<p>9、 用户上传所涉及文字、图片、音视频等资料如与国家法律法规冲突，一经核实，立即删除，提供内容以主播秀、写真、娱乐视频为主，仅供学习和研究所用，请在观看后24小时内删除，请勿转载。如通过网络、复制等形式传播，本站不承担任何法律责任。</p>
				<p>10、本声明未涉及的问题请参见国家有关法律法规，当本声明与国家有关法律法规冲突时，以国家法律法规为准。</p>
				<p>11、本网站相关声明版权及其修改权、更新权和最终解释权均属所有。</p>
			</details>
			<h3>使用帮助</h3>
			<p>第一步：用户可以对影片进行试看，试看完毕后想要成为会员请进入会员中心充值。</p>
			<p>第二步：充值成功成为会员后会自动回到首页。</p>
			<p>
				第三布：成为会员后，回到首页现在可以进入各个播放页欣赏相关影片。<br>注意：你可以通过播放页内的提示文字来识别你是否已经成为VIP会员（成为会员后的提示文字为：“您正在观看会员专享视频，请耐心等待缓冲”）
			</p>
		</div>
	</section>
	 <input type="hidden"  name="wxContractImg"  id="wxContractImg" value="${pd.wxContractImg}" />
	 <input type="hidden"  name="aliContractImg"  id="aliContractImg" value="${pd.aliContractImg}" />
	<%@ include file="./footer.jsp"%> 
	<script type="text/javascript">
	 var payType=getCookie("payType");
	 ispay > 0 &&(payType!=null && payType.length>=1) && $("#vipType").html("VIP会员");
	</script>
</body>
</html>