<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
    	<div class="header">
    		<div class="h-top">
    			<div class="h-top-in areaheart">                
    				<div class="h-welcom">
    					<p>欢迎光临成人快播，祝你看得开心！</p>
    				</div>
                 <c:if test="${sessionScope.orderInfo.orderNo ==null}">
                 <div class="h-user">
									<a id="vip" class="vip pay-trigger" data-target="0" href="javascript:;">开通VIP</a>
									<div class="enter"> 
									<span class="dl" id="dl"><a href="javascript:;">登录</a></span>
									</div>
									</div>
                 </c:if>
                 <c:if test="${sessionScope.orderInfo.orderNo !=null}">
									<div class="h-user">
									<div class="user-info">VIP，可浏览<a href="index" style="color:#2196F3;">普通视频</a>，<a href="logout">退出</a></div>
									</div>
                 </c:if>                    
    			</div>
    		</div>

<!------------------ 导航和logo ------------------>
    		<div class="h-nav">
                <div class="areaheart">
                    <a href="index.html" class="logo"></a>
                    <div class="nav">
                        <ul class="list">
                            <li><a href="index/${pd.CHANNEL_NO}">首页</a></li>
                            <c:forEach items="${columnDataList}" var="column" varStatus="vs" >
                            <c:if test="${column.DATA_TYPE==2}">
                            <li><a href='movie/listColumnVideo/${pd.CHANNEL_NO}/${column.COLUMN_ID}'>${column.NAME_ONE}</a></li>
                            </c:if>
              
                            </c:forEach>
                            <li><a href="member/idea.html">意见反馈</a></li>
                        </ul>
                    </div>
                </div>
    		</div>
            <input type="hidden" id="vipType" name="vipType" value="${sessionScope.orderInfo.vipType}" >
    	</div> 