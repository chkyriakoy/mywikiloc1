package com.example.mywikiloc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class UserWithRoleOption {
	
	@Transient
	public static final String SEQUENCE_NAME="user_sequence";
	
	@Id
	private Integer id;
	private String name;
	private String lname;
	private String email;
	private String passwd;
	private boolean roleSow1;
	private boolean roleSow2;
	private boolean roleSow3;
	private boolean admin;
	private boolean editor;
	private boolean viewer;
	
	public UserWithRoleOption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserWithRoleOption(User user ) {
		 this.id=user.getId();
		 this.name=user.getName();
		 this.lname=user.getLname();
		 this.email=user.getEmail();
		 this.passwd=user.getPasswd();
		 this.roleSow1 = false;
		 this.roleSow2 = false;
		 this.roleSow3 = false;
		 this.admin=user.isAdmin();
		 this.editor=user.isEditor();
		 this.viewer=user.isViewer();
	}
	
	public boolean isRoleSow1() {
		return roleSow1;
	}

	public void setRoleSow1(boolean roleSow) {
		this.roleSow1 = roleSow;
	}
	
	public boolean isRoleSow2() {
		return roleSow2;
	}

	public void setRoleSow2(boolean roleSow) {
		this.roleSow2 = roleSow;
	}
	
	public boolean isRoleSow3() {
		return roleSow3;
	}

	public void setRoleSow3(boolean roleSow) {
		this.roleSow3 = roleSow;
	}

	public UserWithRoleOption(Integer id, String name, String lname, String email, String passwd, boolean admin, boolean editor, boolean viewer  ) {
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
