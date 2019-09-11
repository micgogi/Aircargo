package com.brajesh.cargoauth.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {

	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "customerName")

	@NotBlank(message = "username cannot be empty")
	@Size(min = 2, max = 20, message = " customer name should have atleast 2 character and maximum 20 characters")
	private String userName;

	@Column(name = "mobileNo")
	@Size(min = 10, max = 10)
	@NotNull(message = "mobileNo  cannot be empty")
	private String mobileNo;
	
	@Column(name = "emailId")
	@Email
	@NotBlank(message = "Email  cannot be empty")
	private String emailId;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "password")
	@NotBlank(message = "Email  cannot be empty")
	@Size(min = 6, message = " password should have minimum 6 characters")
	private String password;

	@Column(name="roleId")
	private int roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	

}