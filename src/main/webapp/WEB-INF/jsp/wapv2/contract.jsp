<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" data-dpr="3" style="font-size: 124.5px;">

    <head>
			<%@ include file="./head.jsp"%> 
    </head>


<body class="item">
	<header class="black-header">
        <a href="javascript:history.back()" class="back"></a>用户协议
    </header>
    <div class="pd10">
        <h3>撸撸影音免责声明</h3>
        <p>【重要须知】</p>
        <p>1、一切移动客户端用户在下载并浏览撸撸影音软件时均被视为已经仔细阅读本条款并完全同意。凡以任何方式登陆本APP，或直接、间接使用本APP资料者，均被视为自愿接受本网站相关声明和用户服务协议的约束。</p>
        <p>2、撸撸影音转载的内容并不代表撸撸影音之意见及观点，也不意味着本APP赞同其观点或证实其内容的真实性。</p>
        <p>3、撸撸影音转载的文字、图片、音视频等资料均由本APP用户提供，其真实性、准确性和合法性由信息发布人负责。撸撸影音不提供任何保证，并不承担任何法律责任。
        </p>
        <p>4、撸撸影音所转载的文字、图片、音视频等资料，如果侵犯了第三方的知识产权或其他权利，责任由作者或转载者本人承担，本APP对此不承担责任。</p>
        <p>5、撸撸影音不保证为向用户提供便利而设置的外部链接的准确性和完整性，同时，对于该外部链接指向的不由撸撸影音实际控制的任何网页上的内容，撸撸影音不承担任何责任。</p>
        <p>6、用户明确并同意其使用撸撸影音网络服务所存在的风险将完全由其本人承担；因其使用撸撸影音网络服务而产生的一切后果也由其本人承担，撸撸影音对此不承担任何责任。</p>
        <p>7、除撸撸影音注明之服务条款外，其它因不当使用本APP而导致的任何意外、疏忽、合约毁坏、诽谤、版权或其他知识产权侵犯及其所造成的任何损失，撸撸影音概不负责，亦不承担任何法律责任。</p>
        <p>8、对于因不可抗力或因黑客攻击、通讯线路中断等撸撸影音不能控制的原因造成的网络服务中断或其他缺陷，导致用户不能正常使用撸撸影音，撸撸影音不承担任何责任，但将尽力减少因此给用户造成的损失或影响。</p>
        <p>9、本声明未涉及的问题请参见国家有关法律法规，当本声明与国家有关法律法规冲突时，以国家法律法规为准。</p>
        <p>10、本网站相关声明版权及其修改权、更新权和最终解释权均属撸撸影音所有。</p>
			 <input type="hidden"  name="wxContractImg"  id="wxContractImg" value="${pd.wxContractImg}" />
			 <input type="hidden"  name="aliContractImg"  id="aliContractImg" value="${pd.aliContractImg}" />
    </div>
<%@ include file="./footer.jsp"%> 

</body>
<script type="text/javascript">resourceType=2;</script>
<script type="text/javascript">
    $(function(){
        var userType='',userInfo='';
			if(regTime) $(".payDate").html(regTime);	
			switch(vipType){
				case 1 : userType='白银VIP'; userInfo='可浏览美图';  break;
				case 2 : userType='黄金VIP'; userInfo='可浏览美图和非钻石视频'; break;
				case 3 : userType='钻石VIP'; userInfo='可浏览美图和所有视频'; break;
				default : userType='游客'; userInfo='激情视频等你来看！';
				}
			$(".userType").html(userType);
			$(".userInfo").html(userInfo);	
			if(getCookie('openVIP')!=null&& getCookie('openVIP')>="1"){
		    var contactus = '<details><summary class="contactus"><h3 style="display:inline-block">联系我们</h3></summary><p>如有任何意见或疑问，请联系客服</p><p><img src="' + $("#wxContractImg").val() + '"  width="100%" alt=""></p></details>';
		    var payType=getCookie("payType");
		    if(payType!=null && payType.length>=3){
		    	contactus = '<details><summary class="contactus"><h3 style="display:inline-block">联系我们</h3></summary><p>如有任何意见或疑问，请联系客服</p><p><img src="' + $("#aliContractImg").val() + '"  width="100%" alt=""></p></details>';
		    }
		    $(".pd10").prepend(contactus);		
      }
    });
</script>
</html> 