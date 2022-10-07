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
	private String fname;
	private String lname;
	
	public Login() {}
	
	public Login(String uname, String pass, String emailId, String fname, String lname) {
		super();
		this.uname = uname;
		this.pass = pass;
		this.emailId = emailId;
		this.fname = fname;
		this.lname = lname;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPass() {
		return pass;
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

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@Override
	public String toString() {
		return "Login [uname=" + uname + ", pass=" + pass + ", emailId=" + emailId + ", fname=" + fname + ", lname="
				+ lname + "]";
	}
	
}
