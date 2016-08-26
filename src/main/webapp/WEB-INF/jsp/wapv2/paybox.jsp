<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<script src="static/js/v2/layer.js"></script>
<script type="text/javascript">        
        function popPayDiv(){
            var popHTML = $('.pop').html(); 
            layer.open({
                content: popHTML,
                success: function() {
                    var device=getDevice();
                    if(device=='android') $('#player video').hide();   
                },
                end: function(index) {
                    $('#player video').show(); 
                }
            });
        }
        
    </script> 
<style>
    .layermbox0 .layermchild {height: auto;border-radius: 30px;overflow: hidden}
    .layermcont{padding:0;}
</style>    
<div class="pop">       
    <div class="popup">
        <div class="popupPic-l"><img src="http://ww2.sinaimg.cn/large/d2d743f1gw1f5x0bu4k7hj20ca0ifab9.jpg"></div>
        <div class="popupPic"><img src="http://ww2.sinaimg.cn/large/d2d743f1gw1f5x0clb8j6j20hs07a466.jpg" alt=""></div>
        <div class="popmainbox">
            <div class="vipText">开通VIP年会员，尽情释放体内的洪荒之力！</div>
            <form action="http://mobile.plc1688.com/movie/alipay" method="get">
            <div class="chooseType">
                <div class="relative typeList silver-right">
                    <label>
                        <div class="type">
                            <div class="typePos">
                                <input type="radio" name="vipType" class="grayRadio" value="1" ><i class="black">白银VIP</i>
                                <br /><span class="typeGray">仅美图权限</span></div>
                        </div>
                        <div class="price">￥${payInfo['1']}</div>
                    </label>
                </div>
                <div class="relative typeList gold-right">
                    <label>
                        <div class="type">
                            <div class="typePos">
                                <input type="radio" name="vipType" class="grayRadio"  value="2"><i class="yellow">黄金VIP</i>
                                <br /><span class="typeGray">美图及普通视频权限</span></div>
                        </div>
                        <div class="price">￥${payInfo['2']}</div>
                    </label>
                </div>
                <div class="relative typeList diamond-right ">
                    <label>
                        <div class="type">
                            <div class="typePos">
                                <input type="radio" name="vipType" class="grayRadio"  value="3"><i class="pink">钻石VIP</i>
                                <br /><span class="typeGray">全部权限</span></div>
                        </div>
                        <div class="price">￥${payInfo['3']}</div>
                    </label>
                </div>
            </div>
            <div class="payType">
                <a class="and-wx weixin" href="javascript:show_wx();" data-pay=""><img src="http://ww3.sinaimg.cn/large/d2d743f1gw1f5x0cdqh1aj20cs020mx3.jpg" alt=""></a>
                <p>
                	<c:forEach items="${payType}" var="map">
                		<c:if    test="${map.key>=100}"> 
                    <a href="javascript:alipay_submit('${map.key}');" class="alipay"><img src="http://ww4.sinaimg.cn/large/d2d743f1gw1f5x0cex20wj20cs020a9z.jpg" alt=""></a>                   
										</c:if>
									</c:forEach>
                </p>
            </div>
            </form>    
        </div>
    </div>
    <div class="and">
        <div class="ewm"><img src="http://ww1.sinaimg.cn/large/d2d743f1gw1f4mszuuec0j205k05kdfp.jpg" alt=""></div>
        <p>打开微信扫描<br/>或截图后在微信中打开扫描</p>        
        <p><a href="javascript:close_wx();" class="backpaytype">返回</a></p>
    </div>
</div>