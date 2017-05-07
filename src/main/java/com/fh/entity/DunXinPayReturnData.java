package com.fh.entity;

public class DunXinPayReturnData {
	
	String result;//	返回内容
	String message;//	支付参数（支付宝app为客户端调起的支付参数，wap支付为url）
	String data;//	成功调用后返回支付信息
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
