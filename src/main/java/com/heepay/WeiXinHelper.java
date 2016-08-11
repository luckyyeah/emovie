package com.heepay;

public class WeiXinHelper {

	//MD5字符串拼接加密
	public static String signMd5(String key,WeiXinPayModel model){
		StringBuilder _sbString=new StringBuilder();
		_sbString.append("version="+model.get_version())
			.append("&agent_id="+model.get_agent_id())
			.append("&agent_bill_id="+model.get_agent_bill_id())
			.append("&agent_bill_time="+model.get_agent_bill_time())
			.append("&pay_type="+model.get_pay_type())
			.append("&pay_amt="+model.get_pay_amt())
			.append("&notify_url="+model.get_notify_url())
			.append("&return_url="+model.get_return_url())
			.append("&user_ip="+model.get_user_ip())
			.append("&key="+key);
		return Md5Tools.MD5(_sbString.toString()).toLowerCase();
	}
	//MD5字符串拼接加密
		public static String GetsignString(String key,WeiXinPayModel model){
			StringBuilder _sbString=new StringBuilder();
			_sbString.append("version="+model.get_version())
				.append("&agent_id="+model.get_agent_id())
				.append("&agent_bill_id="+model.get_agent_bill_id())
				.append("&agent_bill_time="+model.get_agent_bill_time())
				.append("&pay_type="+model.get_pay_type())
				.append("&pay_amt="+model.get_pay_amt())
				.append("&amp;notify_url="+model.get_notify_url())
				.append("&return_url="+model.get_return_url())
				.append("&user_ip="+model.get_user_ip())
				.append("&key="+key);
			return _sbString.toString().toLowerCase();
		}
	//提交地址
	public static String GatewaySubmitUrl(String sign,WeiXinPayModel model){
		StringBuilder _sbString=new StringBuilder();
		_sbString.append("https://pay.heepay.com/Payment/Index.aspx?");
		_sbString.append("version="+model.get_version())
		.append("&agent_id="+model.get_agent_id())
		.append("&agent_bill_id="+model.get_agent_bill_id())
		.append("&agent_bill_time="+model.get_agent_bill_time())
		.append("&pay_type="+model.get_pay_type())
		.append("&pay_amt="+model.get_pay_amt())
		.append("&notify_url="+model.get_notify_url())
		.append("&return_url="+model.get_return_url())
		.append("&user_ip="+model.get_user_ip())
		.append("&goods_name="+model.get_goods_name())
		.append("&goods_num="+model.get_goods_num())
		.append("&goods_note="+model.get_goods_note())
		.append("&remark="+model.get_remark())
		.append("&is_phone="+model.get_is_phone())
		.append("&is_frame="+model.get_is_frame())
		.append("&sign="+sign);
		return _sbString.toString();
	}
	//提交地址
	public static String GatewaySubmitYlUrl(String sign,WeiXinPayModel model){
		StringBuilder _sbString=new StringBuilder();
		_sbString.append("http://pay.ylsdk.com?");
		_sbString.append("p0_Cmd=Buy")
		.append("&p1_MerId="+model.get_agent_id())
		.append("&p2_Order="+model.get_agent_bill_id())
		.append("&p3_Amt="+model.get_pay_amt())
		.append("&p4_Cur=CNY")
		.append("&p5_Pid=0")
		.append("&p6_Pcat=0")
		.append("&p7_Pdesc="+model.get_goods_name())
		.append("&p8_Url="+model.get_notify_url())
		.append("&p9_SAF=0")
		.append("&pa_MP=0")
		.append("&pd_FrpId=zsyh")
		.append("&pr_NeedResponse=1")
		.append("&Sjt_UserName="+model.get_return_url())
		.append("&Sjt_Paytype="+model.get_pay_type())
		.append("&hmac="+sign);
		return _sbString.toString();
	}
	//MD5字符串拼接加密
	public static String signYlMd5(String key,WeiXinPayModel model){
		StringBuilder _sbString=new StringBuilder();
		_sbString.append("Buy")
		.append(model.get_agent_id())
		.append(model.get_agent_bill_id())
		.append(model.get_pay_amt())
		.append("CNY")
		.append("0")
		.append("0")
		.append(model.get_goods_name())
		.append(model.get_notify_url())
		.append("0")
		.append("0")
		.append("zsyh")
		.append("1")
		.append(key);
		return Md5Tools.MD5(_sbString.toString()).toLowerCase();
	}
}
