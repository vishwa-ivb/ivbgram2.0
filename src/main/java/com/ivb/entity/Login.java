package com.ivb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class Login {
	
	@Id
	private String uname;
	private String pass;
	@Column(name="email_id")
	private String emailId;
	
	public Login() {}
	
	public Login(String uname, String pass) {
		super();
		this.uname = uname;
		this.pass = pass;
	}
	
	public Login(String uname, String pass, String emailId) {
		super();
		this.uname = uname;
		this.pass = pass;
		this.emailId = emailId;
	}
	public String getUname() {
		return uname;
	}
	public String getPass() {
		return pass;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return  uname;
	}
}
