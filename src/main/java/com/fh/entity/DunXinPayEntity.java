package com.fh.entity;

public class DunXinPayEntity {
	//商户订单号
	String o_bizcode;
	//应用key
	String o_appkey;
	//终端唯一标示（ip地址MD5加密）
	String o_term_key;
	//通知地址（不传就已后台配置为准）
	String o_address;
	//H5同步通知地址（不传就已后台配置为准）
	String o_showaddress;
	//支付类型(1:支付宝，2：微信，3：银联，4:微信公众号，5:微信APP,6:微信扫码)，H5收银台模式以商户后台支付配置为准。
	String o_paymode_id;
	//商品id，在后台获取
	String o_goods_id;
	//商品名称（不传就已后台配置为准）
	String o_goods_name;
	//商品价格 单位元
	String o_price;
	//商户私有信息,放置需要回传的信息(utf-8)
	String o_privateinfo;
	public String getO_bizcode() {
		return o_bizcode;
	}
	public void setO_bizcode(String o_bizcode) {
		this.o_bizcode = o_bizcode;
	}
	public String getO_appkey() {
		return o_appkey;
	}
	public void setO_appkey(String o_appkey) {
		this.o_appkey = o_appkey;
	}
	public String getO_term_key() {
		return o_term_key;
	}
	public void setO_term_key(String o_term_key) {
		this.o_term_key = o_term_key;
	}
	public String getO_address() {
		return o_address;
	}
	public void setO_address(String o_address) {
		this.o_address = o_address;
	}
	public String getO_showaddress() {
		return o_showaddress;
	}
	public void setO_showaddress(String o_showaddress) {
		this.o_showaddress = o_showaddress;
	}
	public String getO_paymode_id() {
		return o_paymode_id;
	}
	public void setO_paymode_id(String o_paymode_id) {
		this.o_paymode_id = o_paymode_id;
	}
	public String getO_goods_id() {
		return o_goods_id;
	}
	public void setO_goods_id(String o_goods_id) {
		this.o_goods_id = o_goods_id;
	}
	public String getO_goods_name() {
		return o_goods_name;
	}
	public void setO_goods_name(String o_goods_name) {
		this.o_goods_name = o_goods_name;
	}
	public String getO_price() {
		return o_price;
	}
	public void setO_price(String o_price) {
		this.o_price = o_price;
	}
	public String getO_privateinfo() {
		return o_privateinfo;
	}
	public void setO_privateinfo(String o_privateinfo) {
		this.o_privateinfo = o_privateinfo;
	}
	
	
	

}
