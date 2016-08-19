<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<iframe style='display:none;' id="tiao_iframe_xxxx"></iframe>
<footer>
		<c:if test="${COLUMN_NO==1}">
		<a href="index/${pd.CHANNEL_NO}" class="icon1  active"></a>
		</c:if>
		<c:if test="${COLUMN_NO!=1}">
		<a href="index/${pd.CHANNEL_NO}" class="icon1"></a>
		</c:if>		
		<c:if test="${COLUMN_NO==2}">
		    <a href="wapv2/channel/${pd.CHANNEL_NO}" class="icon2 active"></a>
		</c:if>
		<c:if test="${COLUMN_NO!=2}">
		    <a href="wapv2/channel/${pd.CHANNEL_NO}" class="icon2 "></a>
		</c:if>				
		<c:if test="${COLUMN_NO==3}">
		    <a href="wapv2/listDiamondVideo/${pd.CHANNEL_NO}/099" class="icon3 active"></a>
		</c:if>
		<c:if test="${COLUMN_NO!=3}">
		    <a href="wapv2/listDiamondVideo/${pd.CHANNEL_NO}/099" class="icon3 "></a>
		</c:if>		   
		<c:if test="${COLUMN_NO==4}">
		   <a href="../tuku.html" class="icon4 active"></a>
		</c:if>
		<c:if test="${COLUMN_NO!=4}">
		  <a href="../tuku.html" class="icon4 "></a>
		</c:if>		
		<c:if test="${COLUMN_NO==5}">
    		<a href="wapv2/member/${pd.CHANNEL_NO}" class="icon5 active"></a>
		</c:if>
		<c:if test="${COLUMN_NO!=5}">
		     <a href="wapv2/member/${pd.CHANNEL_NO}" class="icon5 "></a>
		</c:if>		


</footer>
 <input type="hidden"  name="CHANNEL_NO"  id="CHANNEL_NO" value="${pd.CHANNEL_NO}" /> 
	      <script src="static/js/v2/flexible_0.3.4.js" type="text/javascript" charset="utf-8"></script>
        <script src="static/js/v2/jquery.min.js" type="text/javascript"></script>
        <script src="static/js/v2/swiper-3.3.1.min.js" type="text/javascript"></script>
        <script src="static/js/v2/pay.js" type="text/javascript"></script> 	
        <script type="text/javascript">var sid = 1, aid = 1, checkTimer = 0, vipType = 0, regTime = 0, resourceType = 0;</script>
        <script>
            var $ = jQuery;
            $(function(){
                if(vipType==0){
                    $(".openVIP").html("开通VIP");
                }else if(vipType==1){
                    $(".openVIP").html("白银VIP");
                }else if(vipType==2){
                    $(".openVIP").html("黄金VIP");
                }else if(vipType==3){
                    $(".openVIP").html("钻石VIP");
                }                
            });
        </script>	
        