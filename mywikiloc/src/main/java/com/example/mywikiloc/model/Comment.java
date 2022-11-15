package com.example.mywikiloc.model;

import java.time.LocalDate;

public class Comment {
	
	private Integer commentId;
	private Integer userId;
	private String comment;
	//private LocalDate date;
	private String date;
	
	
	
	public Comment(Integer commentId, Integer userId, String comment, String date) {
		super();
		this.commentId = commentId;
		this.userId = userId;
		this.comment = comment;
		this.date = date;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", userId=" + userId + ", comment=" + comment + ", date=" + date
				+ "]";
	}
	
	

}
