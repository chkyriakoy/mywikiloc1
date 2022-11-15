package com.example.mywikiloc.model;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "routes")
public class Route {
	
	@Transient
	public static final String SEQUENCE_NAME="route_sequence";
	
	@Id
	private Integer id;
	private String name;
	private Integer[] userId;
	private Image[] images;
	private Comment[] comments;
	
	
	
	public Route() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Route(Integer id, String name, Integer[] userId, Image[] images, Comment[] comments) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.images = images;
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer[] getUserId() {
		return userId;
	}

	public void setUserId(Integer[] userId) {
		this.userId = userId;
	}

	public Image[] getImages() {
		return images;
	}

	public void setImages(Image[] images) {
		this.images = images;
	}

	public Comment[] getComments() {
		return comments;
	}

	public void setComments(Comment[] comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Route [id=" + id + ", userId=" + Arrays.toString(userId) + ", images=" + Arrays.toString(images)
				+ ", comments=" + Arrays.toString(comments) + "]";
	}
	
	
	
	

}
