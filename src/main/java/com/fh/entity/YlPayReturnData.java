package com.fh.entity;

public class YlPayReturnData {
	
	String error;//	返回内容
	String message;//	支付参数（支付宝app为客户端调起的支付参数，wap支付为url）
	String status;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
