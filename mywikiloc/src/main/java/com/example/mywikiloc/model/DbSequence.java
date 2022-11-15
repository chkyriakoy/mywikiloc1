package com.example.mywikiloc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_sequence")
public class DbSequence {
	@Id
	//	users, paths, etc
	private String id;	
	//	counter
	private int seqNo;
	
	
	public DbSequence() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public DbSequence(String id, int seqNo) {
		super();
		this.id = id;
		this.seqNo = seqNo;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}	

}
