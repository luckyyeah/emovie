package com.fh.entity;

public class PayData {
	String   pay_id;//支付id
	String   order_no;//商户订单号
	String   point_name;//计费点名称
	String   fee_hint;//付费提示语
	String   enabled;//是否有效
	String   fee; //价格
	String   channel_no;//渠道号
	String   imsi;//imsi号
	String   imei;//imei号
	String   plugin_name;//支付插件名称
	String   plugin_id;//支付插件类型
	String   point_type;//计费点类型
	String   cell_model;  //手机型号  
	String   sdk_version;  //sdk版本号
	String   version_no;  //版本号    
	String   program_id;  //节目ID
	String   program_name;  //节目名称
	String   add_time;//创建时间
	String   update_time;//更新时间
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	public String getPoint_name() {
		return point_name;
	}
	public void setPoint_name(String point_name) {
		this.point_name = point_name;
	}
	public String getFee_hint() {
		return fee_hint;
	}
	public void setFee_hint(String fee_hint) {
		this.fee_hint = fee_hint;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getChannel_no() {
		return channel_no;
	}
	public void setChannel_no(String channel_no) {
		this.channel_no = channel_no;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getPlugin_name() {
		return plugin_name;
	}
	public void setPlugin_name(String plugin_name) {
		this.plugin_name = plugin_name;
	}
	public String getPlugin_id() {
		return plugin_id;
	}
	public void setPlugin_id(String plugin_id) {
		this.plugin_id = plugin_id;
	}
	public String getPoint_type() {
		return point_type;
	}
	public void setPoint_type(String point_type) {
		this.point_type = point_type;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getCell_model() {
		return cell_model;
	}
	public void setCell_model(String cell_model) {
		this.cell_model = cell_model;
	}
	public String getSdk_version() {
		return sdk_version;
	}
	public void setSdk_version(String sdk_version) {
		this.sdk_version = sdk_version;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getVersion_no() {
		return version_no;
	}
	public void setVersion_no(String version_no) {
		this.version_no = version_no;
	}
	public String getProgram_id() {
		return program_id;
	}
	public void setProgram_id(String program_id) {
		this.program_id = program_id;
	}
	public String getProgram_name() {
		return program_name;
	}
	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}
	
	
	
}
