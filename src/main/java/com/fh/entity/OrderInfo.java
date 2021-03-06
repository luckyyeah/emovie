package com.fh.entity;

public class OrderInfo {
	private int vipType;// VIP类型
	private String orderNo;// 合作商订单ID
	private String channelNo;// 渠道号
	private String payAmt;// 支付金额
	private String userId;// 用户ID
	private String plugin_type;// 支付插件类型
	private int status;// 订单状态
	public int getVipType() {
		return vipType;
	}
	public void setVipType(int vipType) {
		this.vipType = vipType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	public String getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}
	public String getPlugin_type() {
		return plugin_type;
	}
	public void setPlugin_type(String plugin_type) {
		this.plugin_type = plugin_type;
	}


	
}
