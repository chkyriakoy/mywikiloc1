package com.example.mywikiloc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
	
	@Transient
	public static final String SEQUENCE_NAME="user_sequence";
	
	@Id
	private Integer id;
	private String name;
	private String lname;
	private String email;
	private String passwd;
	private boolean admin;
	private boolean editor;
	private boolean viewer;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String name, String lname, String email, String passwd, boolean admin, boolean editor, boolean viewer  ) {
		super();
		this.id = id;
		this.name = name;
		this.lname = lname;
		this.email = email;
		this.passwd = passwd;
		this.admin = admin;
		this.editor = editor;
		this.viewer = viewer;
	}

	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lname=" + lname + ", email=" + email + ", passwd=" + passwd
				+ ", admin=" + admin + ", editor=" + editor + ", viewer=" + viewer + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isEditor() {
		return editor;
	}

	public void setEditor(boolean editor) {
		this.editor = editor;
	}

	public boolean isViewer() {
		return viewer;
	}

	public void setViewer(boolean user) {
		this.viewer = user;
	}
	
	
	
	

}
