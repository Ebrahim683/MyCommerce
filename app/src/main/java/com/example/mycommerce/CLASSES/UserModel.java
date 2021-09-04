package com.example.mycommerce.CLASSES;

public class UserModel {

	String name,email,password,mobile,address;

	public UserModel() {
	}

	public UserModel(String name, String email, String password, String mobile, String address) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.address = address;
	}

	public UserModel(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
