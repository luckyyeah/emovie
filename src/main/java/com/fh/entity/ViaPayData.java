package com.fh.entity;

public class ViaPayData {
	int rc;//	返回标志：0-成功；1-失败;2-令牌失效
	String info;//	返回内容
	String payParam;//	支付参数（支付宝app为客户端调起的支付参数，wap支付为url）
	int subParam;//	查询订单辅助参数，查询订单时用到
	int subType;//	支付子类型：12001微信wap支付（支付金额不小于50分）；15001支付宝app支付；15002支付宝wap支付；15003支付宝扫码支付；17001银联快捷支付
	String orderId;//	VIA订单号
	int isSupportReturnUrl;//	是否支持同步返回商户指定url(0支持，1不支持)
	int isSupportQuery;//	是否支持查询接口(0支持，1不支持)
	public int getRc() {
		return rc;
	}
	public void setRc(int rc) {
		this.rc = rc;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getPayParam() {
		return payParam;
	}
	public void setPayParam(String payParam) {
		this.payParam = payParam;
	}
	public int getSubParam() {
		return subParam;
	}
	public void setSubParam(int subParam) {
		this.subParam = subParam;
	}
	public int getSubType() {
		return subType;
	}
	public void setSubType(int subType) {
		this.subType = subType;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getIsSupportReturnUrl() {
		return isSupportReturnUrl;
	}
	public void setIsSupportReturnUrl(int isSupportReturnUrl) {
		this.isSupportReturnUrl = isSupportReturnUrl;
	}
	public int getIsSupportQuery() {
		return isSupportQuery;
	}
	public void setIsSupportQuery(int isSupportQuery) {
		this.isSupportQuery = isSupportQuery;
	}

	
	
	
}
