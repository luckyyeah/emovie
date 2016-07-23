package com.fh.entity;

public class ViaPayReturnData {
	
	String result;//	返回内容
	String money;//	支付参数（支付宝app为客户端调起的支付参数，wap支付为url）
	String channelOrderId;//	查询订单辅助参数，查询订单时用到
	String orderNo;//	支付子类型：12001微信wap支付（支付金额不小于50分）；15001支付宝app支付；15002支付宝wap支付；15003支付宝扫码支付；17001银联快捷支付
	String time ;//	VIA订单号
	String signature ;//	是否支持同步返回商户指定url(0支持，1不支持)
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getChannelOrderId() {
		return channelOrderId;
	}
	public void setChannelOrderId(String channelOrderId) {
		this.channelOrderId = channelOrderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
}
