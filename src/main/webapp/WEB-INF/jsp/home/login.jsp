<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
  <!-- 登录 -->
        <div class="loginbox">
            <div class="login-left tcbg">
                <img src="static/images/login_bg.fw.png" height="300" width="238"/>
            </div>
            <div class="login-right tcright">
                <h3 id="login-title">账号登录</h3>
                <!-- 登录表单 -->
                <form action="login" method="post" name="loginform">
                   <input type="hidden" name="fromUrl" id="fromUrl" value="">
                    <!-- 用户名 -->
                    <div class="username">                        
                        <input id="txt" placeholder="微信订单号" class="txt bd" type="text" name="ORDER_NO"><br/>
                    </div>
                    <!-- 密码 -->
                    <!--<div class="password">                       
                        <input id="dl-pwd" placeholder="密码" class="pwd bd" type="password" name="pwd"><br/>
                    </div>-->
                    <!-- 登录 -->
                    <p style="width:266px; padding:15px 0; color:red;">登录提示：支付完成之后，在微信中获取订单号，即可登录，永久有效</p>
                    <div class="denglu">
                        <input class="ckbox" type="checkbox"  checked="checked"/>
                        记住登录状态 
                        <input class="lgin bd" type="submit" value="登录" />
                    </div>
                    <!-- 注册和找回密码 -->
                    <!--<div class="ljzc">
                        <a id="ljzc" href="#">立即注册 </a>或
                        <a id="zhmm" href="#"> 找回密码</a>
                    </div>-->
                </form>
            </div>
            <div class="close">
                <img src="static/images/close.jpg" alt="">
            </div>
        </div>
   