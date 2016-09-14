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
	public static String partner = "2088811486669043";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALM/Gmf2C/bHTXcQSeRq77yeOpYT4X0HQfG+Fn0GLSJX3iAQXnBrp4/q/ek9tNHNyo+mqDqDf8Ujuk/yxNxAVs+ojlmDf5dTXiMefvpjjYmG3HeFyubMMbs92gWPzZabp8vqxpubeTZ4o4lUWMUdQ1r4ze5hbmdX4yM1lvYLPRIdAgMBAAECgYA3k3k8gq1y9piuMf63M/DhKq4/mipv+bAYpDeUzDVyUIwupOSjNr3dgZ74koleA9CqeaHvgpY7Kbi60Sr4n3pKo50yzdT8VvC3CgPsQ3Rur51o2g+m3+BRN7HF54ImUg7p2DpDSWygUmnSaumLie+EMoQledyFyJ9ZeUhZgdzcAQJBAOBx2PaqfJMB3r3hFgs8NlhS2Zvc75am1uNGxw1f0IT2nUw8WBh6aAnwv5x62GdGfwBE+M41M0i8j3HWXeTjwzUCQQDMcn5lOcOFVgsyUWqAOIEXAKQRiFw0PhQGrrBIRhdFMKEGGQkCMKyUd3bPtB4NiJL5o6sEe7o+QSieo7vmxchJAkEArfpyFRqogAfWElfVD5Z4BdULC4+iRKRFNF3b1FkuOLM10S+Blk/kL7hnvzNWZg+2SI4uUyHYBAzuIG7rz45h9QJADuCSBAm1CaHeRLYZ+uShuCvKxJ/YndCaonXZzBiVnupP/WKa/jbOiCs9URX5TeDPWS0ox7W9MsSJESo3BS8FSQJBAJvW0gziEKROOPw1v7SvcIbLrFENYEfQHqOhh2P0Sbl7jHPchrIhvT6/BORrg4tR7F6U0d/8jZoVFPvIRMK/7TI=";
	
	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://xlsdk.xl-game.cn/sdkServer/alinotify.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://0913.enbozm.com:8090/emovie/alipay/callbackPay";
	
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_urlv2 ="http://0913.enbozm.com:8090/emovie/alipay/callbackPayV2";

	
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_urlv3 ="http://0913.enbozm.com:8090/emovie/alipay/callbackPayV3";
	
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

