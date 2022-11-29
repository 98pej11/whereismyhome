package com.ssafy.vue.model;

public class MemoDto {
	
	private String memono;
	private String comment;
	private String userId;
	private int articleNo;
	private String memoTime;
	
	
	public String getMemono() {
		return memono;
	}
	public void setMemono(String memono) {
		this.memono = memono;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getMemoTime() {
		return memoTime;
	}
	public void setMemoTime(String memoTime) {
		this.memoTime = memoTime;
	}
	@Override
	public String toString() {
		return "MemoDto [memono=" + memono + ", comment=" + comment + ", userId=" + userId + ", articleNo=" + articleNo
				+ ", memoTime=" + memoTime + "]";
	}

	
	
}
