package com.micgogi.aircargo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@Column(name = "accountNo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountNo;

	@Column(name = "customerName")

	  @NotBlank(message="username cannot be empty")
	  @Size(min=2, max = 20,message=" customer name should have atleast 2 character and maximum 20 characters")
	private String customerName;
	
	@Column(name = "mobileNo")
	@Pattern(regexp = "(\\9|8|7|6)[0-9]{9}", message="Inavalid MObile no")
	@NotNull(message="mobileNo  cannot be empty")
	private String mobileNo;
	@Column(name = "emailId")
	@Email
	@NotBlank(message="Email  cannot be empty")
	private String emailId;
	@Column(name = "address")
	private String address;
	@Column(name = "password")
	@NotBlank(message="Email  cannot be empty")
	 @Size(min=6,message=" password should have minimum 6 characters")
	private String password;
	
	int role=1;
	
	public Customer() {
		
	}

	public Customer( String customerName, String mobileNo, String emailId, String address, String password,
			int role) {
		
		this.customerName = customerName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.address = address;
		this.password = password;
		this.role = role;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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


}
