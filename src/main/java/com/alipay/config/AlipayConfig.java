package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088221950553378";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALqPrhuZG58dkWWbPh4Obv3pHtf4LbF3Eqqhd1yNaeojGH0QOoejkSdRPSYB6xd4s2UA+PhvgmLwmgT2N4jNTU81heY8INwDUHM9qTt98DbkqI423VnZxVGKc6G0mRNSMmaeHsw/V8YC1FwYyZRa4KmJAcDFfb2+eby/JmM7Z4mNAgMBAAECgYEAoqb2ExWv4U/0HfP1elr3JeSDgaPcoqa/3Ygc+tJID1w903u8q9lNikvn1iVCBBq1vU/VwTPnJ7/BaKYJ0Pm9dd5fLfROk3j818L+ivtsSK6NKvjIWPqmBk+W+6pyD2BPTtNNtmObkvDfeTGabhW+X0LcuMSv5LOUO8le4BbpefkCQQDhkbAwhWE3aO0WWI+b51PKy3yoIE7qGrtSgGTYkXkkE6n873Y/jWBoAt4WtUBCfpisRrKol2lGs1gaEasD9BMDAkEA07rOmHxufs35OnjMPYuY5vVIXur8URVQanuo4k/6qLWM4kqYkBFokZrUzmLvBqpkg63AA3dTp6FVkxpJYcQELwJBAMvbnA0t7f9iz6p7XUZ8GSlVIBLeKBPBFvxn1zw2tPHa560VSZwEDFXUCZ0iL7IosZg4yKw/MsDXws7EmvcWtbsCQGnChi/k98yfVj6+2EZl1JqJKv0+o4pc+y41Vsa07KAZD6Z5XHuaNoGEtYfiI0NRGaQsxhz1HfQ9wmEYYE9VekECQAr8ehh2W5HIRp35IjFsRKO8iHMe0uhIex3Il4u0qEZ+eaPlVkTKK2Dg+x6S1i5kBRwMabCbnlUKpMob27E+/XI=";
	
	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://xlsdk.xl-game.cn/sdkServer/alinotify.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://1024c.dzxfp.com/emovie/alipay/callbackPay";
	
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_urlv2 ="http://1024c.dzxfp.com/emovie/alipay/callbackPayV2";

	
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_urlv3 ="http://1024c.dzxfp.com/emovie/alipay/callbackPayV3";
	
	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
		
	// 字符编码格式 目前支持utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "alipay.wap.create.direct.pay.by.user";

	public static String subject = "VIP会员";
	
//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}

