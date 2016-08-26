<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">

    <head>
			<%@ include file="./head.jsp"%> 
    </head>


<body class="faceback">
    <style>
        .form-result { font-size:12px; color:red; padding-left:10px;}
    </style>
    <header class="black-header">
        <a href="javascript:history.back()" class="back"></a>用户反馈
    </header>
    <section class="pd10">
        <form class="faqForm" id="ideaForm">
            <div class="font14 feedbackBox">
                <p>您所遇到的问题<span class="form-result" id="idea_result"></span></p>
                <p>
                    <label><input type="radio" name="TYPE" class="grayRadio" checked value="1"><span>播放</span></label>
                    <label><input type="radio" name="TYPE" class="grayRadio" value="2"><span>付费</span></label>
                    <label><input type="radio" name="TYPE" class="grayRadio" value="3"><span>其他</span></label>
                </p>
            </div>
            <div class="font14 feedbackBox">
                <p>您的订单号（必填）</p>
                <p><input type="text" placeholder="请您填写真实有效的交易订单号" name="ORDER_NO" class="textInput" datatype="*" errormsg="请输入订单号"><span class="Validform_checktip"></span></p>
            </div>
            <div class="font14 feedbackBox">
                <p>您的邮箱（必填）</p>
                <p><input type="text" placeholder="请您填写真实有效的常用邮箱" name="MAIL" class="textInput" datatype="e" errormsg="请输入正确的邮箱格式"><span class="Validform_checktip"></span></p>
            </div>
            <div class="font14 feedbackBox">
                <p>问题反馈&意见建议</p>
                <p><textarea id="" cols="30" name="FEEDBACK_CONTENT" rows="10" class="textarea"  datatype="*" placeholder="留下您对我们的宝贵意见或建议，有什么问题请及时反馈给我们，我们会努力不断的改进！祝您生活愉快！" errormsg="请输入正确的邮箱格式"></textarea><span class="Validform_checktip"></span></p>
            </div>
            <div>				
                <a class="tj submitButton font16" style="display:block; text-align: center;" href="javascript:submitIdea();" >提交</a>
           </div>
            <input type="hidden" name="CHANNEL_NO" value="${pd.CHANNEL_NO}" />
        </form>
    </section>

    <div style="height:350px;"></div>
<%@ include file="./footer.jsp"%> 
<%@ include file="./paybox.jsp"%> 
   <script src="static/js/v2/Validform_v5.3.2.js" type="text/javascript"></script>
    <script>
        $(function () {
            $(".faqForm").Validform({
                tiptype: 3,
                showAllError: true
            });
        })
    </script>
</body>
</html> 