<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
 <input type="hidden"  name="CHANNEL_NO"  id="CHANNEL_NO" value="${pd.CHANNEL_NO}" /> 
	<script src="static/js/zepto.min.js"></script>
	<script src="static/js/frozen.min.js"></script>
	<script src="static/js/common.min.js"></script>
	<script src="static/js/tj.js"></script>
	<script type="text/javascript">var gaJsHost = (("https:" == document.location.protocol) ?
        "https://ssl." : "http://www.");
    var head = document.getElementsByTagName("head")[0] || document.documentElement;
    var script = document.createElement("script");
    script.src = gaJsHost + "google-analytics.com/analytics.js";
    var gaDone = false;
    script.onload = script.onreadystatechange = function() {
        if (!gaDone && (!this.readyState ||
                this.readyState === "loaded" ||
                this.readyState === "complete")) {
            gaDone = true;
            try {
                ga('create', 'UA-76442608-4', 'auto');
                ga('send', 'pageview');
            } catch (err) {}
            script.onload = script.onreadystatechange = null;
        }
    };
    head.insertBefore(script, head.firstChild);</script>