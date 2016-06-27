package com.fh.entity;

public class OrderInfo {
	private int vipType;// VIP类型
	private String orderNo;// 合作商订单ID
	private String userId;// 用户ID
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


	
}
