package com.fh.entity;

public class ClientComment {
	String clientId;//账号
	String clientComment;//留言
	String clientIconUrl;//头像URL
	int  commentTime;//
	int  praisePoint ;//点赞数
	String preCol1;//预备字段1
	String preCol2;//预备字段2
	String preCol3;//预备字段3
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientComment() {
		return clientComment;
	}
	public void setClientComment(String clientComment) {
		this.clientComment = clientComment;
	}
	public String getClientIconUrl() {
		return clientIconUrl;
	}
	public void setClientIconUrl(String clientIconUrl) {
		this.clientIconUrl = clientIconUrl;
	}
	public int getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(int commentTime) {
		this.commentTime = commentTime;
	}
	public int getPraisePoint() {
		return praisePoint;
	}
	public void setPraisePoint(int praisePoint) {
		this.praisePoint = praisePoint;
	}
	public String getPreCol1() {
		return preCol1;
	}
	public void setPreCol1(String preCol1) {
		this.preCol1 = preCol1;
	}
	public String getPreCol2() {
		return preCol2;
	}
	public void setPreCol2(String preCol2) {
		this.preCol2 = preCol2;
	}
	public String getPreCol3() {
		return preCol3;
	}
	public void setPreCol3(String preCol3) {
		this.preCol3 = preCol3;
	}
	
	
}
