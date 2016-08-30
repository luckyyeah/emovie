<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<iframe style='display:none;' id="tiao_iframe_xxxx"></iframe>
<footer>
		<c:if test="${COLUMN_NO==1}">
		<a href="index/${pd.CHANNEL_NO}?first=0" class="icon1  active"></a>
		</c:if>
		<c:if test="${COLUMN_NO!=1}">
		<a href="index/${pd.CHANNEL_NO}?first=0" class="icon1"></a>
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
		   <a href="tuku/index/${pd.CHANNEL_NO}" class="icon4 active"></a>
		</c:if>
		<c:if test="${COLUMN_NO!=4}">
		  <a href="tuku/index/${pd.CHANNEL_NO}" class="icon4 "></a>
		</c:if>		
		<c:if test="${COLUMN_NO==5}">
    		<a href="wapv2/member/${pd.CHANNEL_NO}" class="icon5 active"></a>
		</c:if>
		<c:if test="${COLUMN_NO!=5}">
		     <a href="wapv2/member/${pd.CHANNEL_NO}" class="icon5 "></a>
		</c:if>		


</footer>
 <input type="hidden"  name="CHANNEL_NO"  id="CHANNEL_NO" value="${pd.CHANNEL_NO}" /> 

        