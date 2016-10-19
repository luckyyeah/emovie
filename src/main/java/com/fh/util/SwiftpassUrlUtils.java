package com.fh.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fh.controller.base.BaseController;

/**
 * ClassName: UrlUtils 
 * Function:  说   明：微信wap获取支付链接处理方法
 *			      入口方法：reqPayUrl
 *			      依   赖：apache httpclient
 *            场   景：商户是适用android/ios 的sdk方式接入微信wap支付
 *            回调条件：针对新版wap
 *            		android：不能设置User-Agent
 *            		    ios：必须设置User-Agent(替换最后的Safari的值为url schemes)，并且在app中设置url schemes,并且将url schemes提供给商务，商务报备给微信
 *            注意事项：
 *            		1. 该处理方法只是是兼容老版本时使用，只是作为参考，商户后续按照正常接口调用流程来操作。
 *            		2. 如第三方调整，可能导致无法正常唤起微信支付。
 *            
 * @author    
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SwiftpassUrlUtils {
	
	/**
	 * isV2:判断是否为新版
	 */
	private static boolean isV2(String url){
		return url.contains("pay.weixin.wappayv2");
	}
	
	/**
	 * reqPayUrl:获取支付url(兼容老版本wap)
	 */
	public static String reqPayUrl(String url,String userAgent){
		boolean isV2 = isV2(url);
		if(isV2){
			return reqPayUrlV2(url,userAgent);
		}
		
		return reqPayUrlV1(url);
	}
	/**
	 * reqPayUrlV1:获取支付url(旧版wap)
	 */
	private static String reqPayUrlV1(String url){
		try {
			Object [] params = getParams(url);
			HttpUriRequest req = buildReq((String)params[0], (Map)params[1],null);
			HttpEntity entity = HttpClients.custom().build().execute(req).getEntity();
			String pageInfo = EntityUtils.toString(entity);
			if(pageInfo == null || "".equals(pageInfo)){
				return "";
			}
			return proPage(pageInfo, "weixin://");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * req:获取支付url(新版wap)
	 */
	private static String reqPayUrlV2(String url,String userAgent){
		try {
			Object [] params1 = getParams(url);
			HttpUriRequest req1 = buildReq((String)params1[0], (Map)params1[1],null);
			HttpEntity entity1 = HttpClients.custom().build().execute(req1).getEntity();
			String pageInfo1 = EntityUtils.toString(entity1);
			if(pageInfo1 == null || "".equals(pageInfo1)){
				return "";
			}
			String payUrl1 = proPage(pageInfo1, "https://wx.tenpay.com");
			if(payUrl1 == null || "".equals(payUrl1)){
				return "";
			}
			
			Object [] params2 = getParams(payUrl1);
			Map<String,String> m = new HashMap<String,String>();
			m.put("Referer", url);
			//TODO ios中必须设置下面的User-Agent,判断方法：在User-Agent里搜索是否带有以下关键字，如果有表示是iOS设备。iPhone/iPad
		//	m.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601.1");
			m.put("User-Agent", userAgent);
			HttpUriRequest req2 = buildReq((String)params2[0], (Map)params2[1],m);
			HttpEntity entity2 = HttpClients.custom().build().execute(req2).getEntity();
			String pageInfo2 = EntityUtils.toString(entity2);
			if(pageInfo2 == null || "".equals(pageInfo2)){
				return "";
			}
			return proPage(pageInfo2, "weixin://");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * buildReq:组装请求
	 */
	private static HttpUriRequest buildReq(String url,Map<String,String> params,Map<String,String> headers) {
		RequestBuilder build = RequestBuilder.get().setUri(url);

		if(params != null){
			for (Map.Entry<String, String> entry : params.entrySet()) {
				build.addParameter(entry.getKey(), entry.getValue());
			}
		}
		if(headers != null){
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				build.addHeader(entry.getKey(), entry.getValue());
			}
		}
		
		return build.build();
	}
	
	/**
	 * getParams:获取请求的参数信息 
	 */
	private static Object [] getParams(String payUrl){
		Object [] params = new Object[2];
		if(payUrl.contains("?")){
			String sp1 [] = payUrl.split("\\?");
			params[0] = sp1[0];
			params[1] = splitParams(sp1[1]);
		}
		return params;
	}
	
	/**
	 * splitParams:从url中获取请求参数
	 */
	private static Map<String,String> splitParams(String params){
		Map<String,String> m = new HashMap<String,String>();
		if(params.contains("&")){
			String sp1 [] = params.split("&");
			for (int i = 0; i < sp1.length; i++) {
				if(sp1[i].contains("=")){
					String sp2 [] = sp1[i].split("=");
					m.put(sp2[0], sp2[1]);
				}
			}
		}
		return m;
	}
	
	/**
	 * proPage:解析页面
	 */
	private static String proPage(String pageInfo,String flag){
		int start = pageInfo.indexOf(flag);
		if(start == -1){
			System.out.println("解析页面失败");
			return "";
		}
		pageInfo = pageInfo.substring(start);
		int end = pageInfo.indexOf("\"");
		if(end == -1){
			System.out.println("解析页面失败");
			return "";
		}
		pageInfo = pageInfo.substring(0, end);
		return pageInfo;
	}
	
	public static void main(String[] args) {
		System.out.println(reqPayUrl("https://pay.swiftpass.cn/pay/wappay?token_id=**********&service=pay.weixin.wappay",""));
	}
}
